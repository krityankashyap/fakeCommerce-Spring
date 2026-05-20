package org.example.fakecommerce.Controllers;

import org.example.fakecommerce.dtos.CreateProductDto;
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
    public List<Product> getAllProducts(){
        return this.productService.getAllProduct();
    }

    @PostMapping
    public Product createProduct(@RequestBody CreateProductDto requestdto){
        Product prod= this.productService.createProduct(requestdto);
        return prod;
    }
}
