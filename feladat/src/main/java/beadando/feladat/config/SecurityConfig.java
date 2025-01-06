package beadando.feladat.config;

import beadando.feladat.filter.AuthenticationFilter;
import beadando.feladat.mapper.CustomerRowMapper;
import beadando.feladat.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig{

    private final JdbcTemplate jdbcTemplate;
    private final CustomerRowMapper customerRowMapper;

    @Bean
    public SecurityFilterChain clientFilterChain(HttpSecurity http) throws Exception {
        return http.cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        (customizer) -> customizer
                                .requestMatchers("/auth/login").permitAll()
                                .requestMatchers("/auth/sign").permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new AuthenticationFilter(customerRepository(),passwordEncoder()),UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public CustomerRepository customerRepository(){return new CustomerRepository(jdbcTemplate,customerRowMapper);}
}
