package product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public String getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }
}


// Product Controller for UI
//@RestController
//@RequestMapping("/products")
//public class ProductController {
//
//    @GetMapping
//    public List<Product> getAllProducts() {
//        return productRepository.findAll(); // Returns JSON
//    }
//}