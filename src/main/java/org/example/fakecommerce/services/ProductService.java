package org.example.fakecommerce.services;

import lombok.RequiredArgsConstructor;
import org.example.fakecommerce.Repositories.ProductRepository;
import org.example.fakecommerce.dtos.CreateProductDto;
import org.example.fakecommerce.schema.Product;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProduct(){
        return this.productRepository.findAll();
    }

    public Product getProductById(Long id){
        return this.productRepository.findById(id).orElseThrow(()-> new RuntimeException("Product not found"));
    }

    public Product createProduct(CreateProductDto requestdto){

        Product newProd= Product.builder()
                .title(requestdto.getTitle())
                .image(requestdto.getImage())
                .price(requestdto.getPrice())
                // .category(requestdto.getCategory())  todo
                .description(requestdto.getDescription())
                .ratings(requestdto.getRatings())
                .build();

        return this.productRepository.save(newProd);  // this will save the prod in db
    }


    public void deleteProduct(Long id){
         this.productRepository.deleteById(id);
    }

    public List<Product> findProductByCategory(String category){
        return this.productRepository.findByCategory(category);
    }

    public List<String> findAllCategories(){
        return this.productRepository.findAllCategories();
    }
}
