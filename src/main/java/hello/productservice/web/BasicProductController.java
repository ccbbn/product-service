package hello.productservice.web;

import hello.productservice.domain.DeliveryCode;
import hello.productservice.domain.ItemType;
import hello.productservice.domain.Product;
import hello.productservice.repository.MemoryProductRepository;

import hello.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.*;


// 동적 HTML을 만드는 컨트롤러
@Slf4j
@Controller
@RequestMapping("/basic/products")  //공통 유알엘
@RequiredArgsConstructor  // final이 들어간 애를 초기화..생성자..
public class BasicProductController {
    private final ProductService productService;

//    @GetMapping   // 인덱스에 있는 주소를 타고옴 @GetMapping ("/basic/products")
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
//    public ModelAndView product() {
//        List<Product> products = productRepository.findAll();
//        ModelAndView mav = new ModelAndView("/basic/products")
//                .addObject("products",products);
//        return mav;  //템블릿에 모델 뱉음
//    }


    @GetMapping("/{productId}")
    public String product(@PathVariable long productId, Model model) {
        Product product = productService.findById(productId);
        model.addAttribute("product", product);
        return "/basic/product"; //-

    }

    @GetMapping("add") //주소의 입력으로 리턴 파일로 감// 화면틀이 완성됨
    public String addForm(Model model){
        model.addAttribute("product", new Product());
        return "basic/addForm"; //-
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


    @PostMapping ("add") //객체를 만듬, 저장함
    public String addProductV3(@ModelAttribute Product product /*Model model 안만들어도 됨*/) {
        productService.save(product);  // 키는 스프링이 타입명에서 자동으로 바꿈. 키 : product
        /*model.addAttribute("product", product); 굳이 필요 없음 */
        return "/basic/product";  //템플릿으로 감
}
//    redirect : 주소로 바로 꽂음
//    @PostMapping ("add") //객체를 만듬 username=나&age=24234의 정보로 .
//    public String addProductV3(@ModelAttribute Product product, RedirectAttributes redirectAttributes) {
//        Product savedProduct = productService.save(product);
//        redirectAttributes.addAttribute("productId",savedProduct.getId());
//        redirectAttributes.addAttribute("status", true);
//        return "redirect:/basic/products/{productId}";
//    }






    // 수정할 때는 put사용
    @GetMapping("/{productId}/edit") //product 에서 요청
    public String edit(@PathVariable Long productId, Model model){
        Product product = productService.findById(productId);
        model.addAttribute("product", product);
        return "basic/editForm"; //-
    }

    @PostMapping("/{productId}/edit")
    public String edit1(@PathVariable Long productId, @ModelAttribute Product product) {
        productService.update(productId, product);
        return "redirect:/basic/products/{productId}";
    }//-

//    @PostMapping("/{productId}/edit") //@ 모델 어트리뷰트 모든 정보가 담김
//    public String edit2(@ModelAttribute Product product) {
//        productService.update(product.getId(), product);
//        return "redirect:/basic/products";
//    }


    @GetMapping("/{productId}/delete")
    public String d1(@PathVariable Long productId){
       productService.delete(productId);
        return "redirect:/basic/products";
    }

//    @GetMapping("/{productId}/delete")
//    public String d1(@ModelAttribute Product product){
//        productRepository.delete(product.getId());
//        return "redirect:/basic/products";
//    }
////    <- 이거는 왜 안됨? // 왜 객체가 null이지

    @ModelAttribute("regions") // 키 :"regions", 값: regions
    public Map<String, String> regions() {
        Map<String, String> regions = new LinkedHashMap<>();
        regions.put("SEOUL", "서울");
        regions.put("BUSAN", "부산");
        regions.put("JEJU", "제주");
        return regions; //-
    }

    @ModelAttribute("itemTypes")
    public ItemType[] itemTypes() { return ItemType.values();}
    //-


    @ModelAttribute("deliveryCodes")
    public List<DeliveryCode> deliveryCodes() {
        List<DeliveryCode> deliveryCodes = new ArrayList<>();
        deliveryCodes.add(new DeliveryCode("FAST", "빠른 배송"));
        deliveryCodes.add(new DeliveryCode("NORMAL", "일반 배송"));
        deliveryCodes.add(new DeliveryCode("SLOW", "느린 배송"));
        return deliveryCodes;
    }//-

    @GetMapping
    public String products(Model model) {

        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "basic/products"; //-
    }

//    @PostMapping("add")
//    public String addItem(@ModelAttribute Product product, RedirectAttributes redirectAttributes) {
//
//        log.info("item.open={}", product.getOpen());
//        log.info("item.regions={}", product.getRegions());
//        log.info("item.itemType={}", product.getItemType());
//
//        Product savedProduct = productService.save(product);
//        redirectAttributes.addAttribute("productId", savedProduct.getId());
//        redirectAttributes.addAttribute("status", true);
//        return "redirect:/basic/products/{productId}"; //-
//    }
//




    @PostConstruct // 생성이후에 실행
    public void initProducts() {
        productService.save(new Product("삼다수", 800, 20));
        productService.save(new Product("아이시스", 500, 30));
    }




}
