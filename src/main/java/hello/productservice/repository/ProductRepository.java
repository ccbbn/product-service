package hello.productservice.repository;

import hello.productservice.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);
    Optional<Product> findById(Long id);
    Optional<Product> findByName(String name);
    List<Product> findAll();

    void update(Long productId, Product updateProduct);
    void delete(Long productId);

    void clearStore();





}
