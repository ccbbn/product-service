package hello.productservice.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
public class Product {

    @Id @GeneratedValue(strategy = IDENTITY) // 고유키
    private Long id;
    private String name;
    private Integer price;
    private Integer stock;

    private Boolean open;
    @ElementCollection
    private List<String> regions;

    private ItemType itemType;

    private String deliveryCode;

    public Product(){
    }

    public Product(String name, Integer price, Integer stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}
