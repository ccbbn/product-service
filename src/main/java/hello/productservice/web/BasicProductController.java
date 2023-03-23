package hello.productservice.web;

import hello.productservice.domain.Product;
import hello.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;


// 동적 HTML을 만드는 컨트롤러
@Controller
@RequiredArgsConstructor  // final이 들어간 애를 초기화..생성자..
public class BasicProductController {
    private final ProductRepository productRepository;

    @GetMapping



    @PostConstruct // 생성이후에 실행
    public void initProducts() {
        productRepository.save(new Product("삼다수", 800, 20));
        productRepository.save(new Product("아이시스", 500, 30));

    }


}
