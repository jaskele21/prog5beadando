package beadando.feladat.entity;

import lombok.Data;

@Data
public class Tyre {
    private int id, customer_id;
    private String brand,war;
    private short quantity;
}
