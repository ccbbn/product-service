package hello.productservice.repository;


import hello.productservice.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemoryProductRepository implements ProductRepository {
    public static final Map<Long, Product> store = new HashMap<>();

    private static long sequence = 0L;

    public Product save(Product product) {
        product.setId(++sequence);
        store.put(product.getId(), product);
        return product;
    }

    public Product findById(Long id) {
        return store.get(id);
    }

    @Override
    public Product findByName(String name) {
        return store.values().stream().filter(product -> product.getName().equals(name))
                .findAny().get();
    }

    public List<Product> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long productId, Product updataProduct) {
        Product product = findById(productId);
        product.setName(updataProduct.getName());
        product.setPrice(updataProduct.getPrice());
        product.setStock(updataProduct.getStock());

    }

    public void delete(Long id){
        store.remove(id);
    }


}
