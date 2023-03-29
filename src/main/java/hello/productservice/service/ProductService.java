package hello.productservice.service;

import hello.productservice.domain.Product;
import hello.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product save(Product product) {
        return productRepository.save(product);
    };
    public Product findById(Long id) {
        return productRepository.findById(id);
    };
    Product findByName(String name){
        return productRepository.findByName(name);
    };
    public List<Product> findAll() {
        return productRepository.findAll();
    };

    public void update(Long productId, Product updateProduct) {
        productRepository.update(productId, updateProduct);
    };
    public void delete(Long productId) {
        productRepository.delete(productId);
    };


}
