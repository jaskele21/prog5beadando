package beadando.feladat.filter;

import beadando.feladat.handler.ApiKeyAuthentication;
import beadando.feladat.repository.CustomerRepository;
import beadando.feladat.service.AuthenticationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.io.PrintWriter;

public class AuthenticationFilter extends GenericFilterBean {
    private CustomerRepository customerRepository;
    private PasswordEncoder passwordEncoder;
    public AuthenticationFilter(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        super();
        this.customerRepository=customerRepository;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        try {
            Authentication authentication = this.getAuthentication((HttpServletRequest) request);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception exp) {
            /*HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
            PrintWriter writer = httpResponse.getWriter();
            writer.print(exp.getMessage());
            writer.flush();
            writer.close();*/
        }

        filterChain.doFilter(request, response);
    }
    public  Authentication getAuthentication(HttpServletRequest request)throws BadCredentialsException {
        String hader = "X-API-KEY";
        String apiKey = request.getHeader(hader);
        if (apiKey == null) {
            throw new BadCredentialsException("Invalid API Key");
        }
        try {
            var customer =customerRepository.findByKey(apiKey);
            return new ApiKeyAuthentication(customer.getId()+"", AuthorityUtils.NO_AUTHORITIES);
        }catch (EmptyResultDataAccessException e){
            throw new BadCredentialsException("Invalid API Key");
        }

    }
}
