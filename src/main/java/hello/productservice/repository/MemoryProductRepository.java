package hello.productservice.repository;


import hello.productservice.domain.Product;

import java.util.*;


public class MemoryProductRepository implements ProductRepository {
    public static final Map<Long, Product> store = new HashMap<>();

    private static long sequence = 0L;

    public Product save(Product product) {
        product.setId(++sequence);
        store.put(product.getId(), product);
        return product;
    }

    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Product> findByName(String name) {
        return Optional.of(store.values().stream().filter(product -> product.getName().equals(name))
                .findAny().get());
    }

    public List<Product> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long productId, Product updateProduct) {
        Optional<Product> product = findById(productId);
        product.ifPresent(p -> p.setName(updateProduct.getName()));
        product.ifPresent(p -> p.setPrice(updateProduct.getPrice()));
        product.ifPresent(p -> p.setStock(updateProduct.getStock()));
        product.ifPresent(p -> p.setOpen(updateProduct.getOpen()));
        product.ifPresent(p -> p.setRegions(updateProduct.getRegions()));
        product.ifPresent(p -> p.setItemType(updateProduct.getItemType()));
        product.ifPresent(p -> p.setDeliveryCode(updateProduct.getDeliveryCode()));

    }

    public void delete(Long id){
        store.remove(id);
    }


    public void clearStore(){ store.clear();}
}
