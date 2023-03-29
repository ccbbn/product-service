package hello.productservice.domain;

import lombok.Data;

import java.util.List;

@Data
public class Product {
    private Long id;
    private String name;
    private Integer price;
    private Integer stock;

    private Boolean open;
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
