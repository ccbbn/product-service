package hello.productservice.web;

import hello.productservice.domain.Product;
import hello.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.util.List;


// 동적 HTML을 만드는 컨트롤러
@Controller
@RequiredArgsConstructor  // final이 들어간 애를 초기화..생성자..
public class BasicProductController {
    private final ProductRepository productRepository;

    @GetMapping("/basic/products")  // 인덱스에 있는 주소를 타고옴
//    public String product(Model model) { // 바구니는 이미 있음... 바구니에 키값으로 객체를 넣음
//        //1. 저장소에서 전체 상품 정보 가져오기
//        List<Product> products = productRepository.findAll();
//        //2. 가져온 상품 정보를 모델에 집어 넣어야한다.
//        // 키: products, 값: products 객체
//        //3. 모델앤드뷰 or 뷰네임 리턴
//        model.addAttribute("products", products);
//        return "/basic/products";  // 템플렛으로 뱃음
//
//    }
    public ModelAndView product() {
        List<Product> products = productRepository.findAll();
        ModelAndView mav = new ModelAndView("/basic/products")
                .addObject("products",products);
        return mav;
    }



    @PostConstruct // 생성이후에 실행
    public void initProducts() {
        productRepository.save(new Product("삼다수", 800, 20));
        productRepository.save(new Product("아이시스", 500, 30));

    }


}
