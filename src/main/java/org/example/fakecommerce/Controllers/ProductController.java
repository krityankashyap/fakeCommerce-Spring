package org.example.fakecommerce.Controllers;

import org.example.fakecommerce.dtos.CreateProductDto;
import org.example.fakecommerce.dtos.GetProductResponseDto;
import org.example.fakecommerce.dtos.GetProductWithDetailsResponseDto;
import org.example.fakecommerce.schema.Product;
import org.example.fakecommerce.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService= productService;
    }

    @GetMapping
    public List<GetProductResponseDto> getAllProducts(){
        return this.productService.getAllProduct();
    }

    @PostMapping
    public Product createProduct(@RequestBody CreateProductDto requestdto){
        Product prod= this.productService.createProduct(requestdto);
        return prod;
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id){
        this.productService.deleteProduct(id);
    }

    @GetMapping("/category/{category}")
    public List<Product> getProductByCategory(@PathVariable String category) {
        return this.productService.findProductByCategory(category);
    }

    @GetMapping("/categories")
    public List<String> getAllCategory(){
        return this.productService.findAllCategories();
    }

    @GetMapping("/{id}")
    public GetProductResponseDto getProductById(@PathVariable Long id){
        return this.productService.getProductById(id);
    }

    @GetMapping("/{id}/details")
    public GetProductWithDetailsResponseDto getProductWithDetailsResponseDto(@PathVariable Long id){
        return this.productService.findProductDetailsById(id);
    }
}
