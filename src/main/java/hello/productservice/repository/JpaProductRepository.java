package hello.productservice.repository;

import hello.productservice.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Repository
@Transactional
public class JpaProductRepository implements ProductRepository{

    private final EntityManager em;

    @Override
    public Product save(Product product) {
        em.persist(product);
        return product;

    }

    @Override
    public Optional<Product> findById(Long id) {
        //jpa가 알아서 데이터베이스에 조회sql을 날려 우리가 원하는 데이터를 가져와준다
        Product product = em.find(Product.class, id);
        return Optional.ofNullable(product);
    }

    @Override
    public Optional<Product> findByName(String name) {

        return null;
    }

    @Override
    public List<Product> findAll() {  //jpql
       return em.createQuery("select p from Product p",Product.class).
                getResultList();
    }

    @Override
    public void update(Long productId, Product updateProduct) {
        Product product = findById(productId).get();
        product.setName(updateProduct.getName());
        product.setPrice(updateProduct.getPrice());
        product.setStock(updateProduct.getStock());
        // 사실 쿼리도 같이 만들어지고 있음

    }

    @Override
    public void delete(Long productId) {


        em.remove(findById(productId).get());

    }

    @Override
    public void clearStore() {

    }
}
