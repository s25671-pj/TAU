package burgers.Burger_Restaurant.BusinessLogic.Controller;

import burgers.Burger_Restaurant.BusinessLogic.Service.ProductService;
import burgers.Burger_Restaurant.Food.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("api/v1/products")
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("api/v1/products/{productId}")
    public Product getProductById(@PathVariable("productId") Integer productId) {
        return productService.getProduct(productId);
    }
}
