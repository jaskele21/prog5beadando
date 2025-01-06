package beadando.feladat.entity;

import lombok.Data;

@Data
public class Customer {
    private int id;
    private String name,phone_number,password,key;
}
