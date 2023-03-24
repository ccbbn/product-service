package hello.productservice.web;

import hello.productservice.domain.Product;
import hello.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.text.ParsePosition;
import java.util.List;
import java.util.Scanner;


// 동적 HTML을 만드는 컨트롤러
@Controller
@RequestMapping("/basic/products")  //공통 유알엘
@RequiredArgsConstructor  // final이 들어간 애를 초기화..생성자..
public class BasicProductController {
    private final ProductRepository productRepository;

    @GetMapping   // 인덱스에 있는 주소를 타고옴 @GetMapping ("/basic/products")
//    public String product(Model model) { // 바구니는 이미 있음... 바구니에 키값으로 객체를 넣음
//        //1. 저장소에서 전체 상품 정보 가져오기
//        List<Product> products = productRepository.findAll();
//        //2. 가져온 상품 정보를 모델에 집어 넣어야한다.
//        // 키: products, 값: products 객체
//        //3. 모델앤드뷰 or 뷰네임 리턴
//        model.addAttribute("products", products);
//        return "/basic/products";  // 템플렛으로 뱉음

//
//    }
    public ModelAndView product() {
        List<Product> products = productRepository.findAll();
        ModelAndView mav = new ModelAndView("/basic/products")
                .addObject("products",products);
        return mav;
    }


    @GetMapping("/{productId}")
    public String product(@PathVariable Long productId, Model model) {
        Product product =productRepository.findById(productId);
        model.addAttribute("product", product);
        return "/basic/product";

    }

    @GetMapping("/add")
    public String addForm(){
        return "/basic/addForm";
    }

    //상품 저장 후 상품목록으로 되돌아가기
//    @PostMapping("add")
//    public String addProductV1(@RequestParam String name, @RequestParam int price, @RequestParam int stock) {
//        Product product = new Product(name, price, stock); save도 가능
//        return "redirect:/basic/products";
//    }

    // 상품 저장후 상세 페이지로
//    @PostMapping("add")
//    public String addProductV2(@RequestParam String name, @RequestParam int price, @RequestParam int stock, Model model) {
//        Product product = new Product(name, price, stock);
//        model.addAttribute("product", product);
//        return "/basic/product";
//    }


//    @PostMapping ("add") //객체를 만듬 username=나&age=24234의 정보로 .
//    public String addProductV3(@ModelAttribute Product product /*Model model 안만들어도 됨*/) {
//        productRepository.save(product);  // 키는 스프링이 타입명에서 자동으로 바꿈. 키 : product
//        /*model.addAttribute("product", product); 굳이 필요 없음 */
//        return "/basic/product";
//}

    @PostMapping ("add") //객체를 만듬 username=나&age=24234의 정보로 .
    public String addProductV3(@ModelAttribute Product product, RedirectAttributes redirectAttributes) {
        product = productRepository.save(product);
        redirectAttributes.addAttribute("productId",product.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/product/{productId}";
    }





//
// 수정할 때는 put사용
    @GetMapping("/{productId}/edit")
    public String edit(@PathVariable Long productId, Model model){
        Product product = productRepository.findById(productId);
        model.addAttribute("product", product);
        return "/basic/editForm";
    }

    @PostMapping("/{productId}/edit")
    public String edit1(@PathVariable Long productId, Product product) {
        productRepository.update(productId, product);
        return "redirect:/basic/products";
    }

    @GetMapping("/{productId}/delete")
    public String d1(@PathVariable Long productId){
       productRepository.delete(productId);
        return "redirect:/basic/products";
    }

    @PostConstruct // 생성이후에 실행
    public void initProducts() {
        productRepository.save(new Product("삼다수", 800, 20));
        productRepository.save(new Product("아이시스", 500, 30));

    }


}
