package org.example.fakecommerce.services;

import lombok.RequiredArgsConstructor;
import org.example.fakecommerce.Repositories.ProductRepository;
import org.example.fakecommerce.dtos.CreateProductDto;
import org.example.fakecommerce.dtos.GetProductResponseDto;
import org.example.fakecommerce.dtos.GetProductWithDetailsResponseDto;
import org.example.fakecommerce.schema.Category;
import org.example.fakecommerce.schema.Product;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final CategoryService categoryService;


    public List<GetProductResponseDto> getAllProduct(){
        List<Product> products= productRepository.findAll();

//        List<GetProductResponseDto> responseDtos= new ArrayList<>();
//        for(Product product : products){
//            GetProductResponseDto responseDto= GetProductResponseDto.builder()
//                    .id(product.getId())
//                    .title(product.getTitle())
//                    .price(product.getPrice())
//                    .description(product.getDescription())
//                    .image(product.getImage())
//                    .ratings(product.getRatings())
//                    .build();
//
//            responseDtos.add(responseDto);
//        }
//        return responseDtos;

        // Using stream apis
        return products.stream()
                .map(product -> GetProductResponseDto.builder()
                        .id(product.getId())
                        .title(product.getTitle())
                        .price(product.getPrice())
                        .image(product.getImage())
                        .ratings(product.getRatings())
                        .description(product.getDescription())
                        .build())
                .collect(Collectors.toList());
    }

    public GetProductResponseDto getProductById(Long id){
        return this.productRepository.findById(id)
                .map(product -> GetProductResponseDto.builder()
                        .id(product.getId())
                        .title(product.getTitle())
                        .price(product.getPrice())
                        .image(product.getImage())
                        .ratings(product.getRatings())
                        .description(product.getDescription())
                        .build())
                .orElseThrow(()-> new RuntimeException("Product not found"));
    }

    public Product createProduct(CreateProductDto requestdto){

        Category category= categoryService.getCategoryById(
                requestdto.getCategory_id()
        );

        Product newProd= Product.builder()
                .title(requestdto.getTitle())
                .image(requestdto.getImage())
                .price(requestdto.getPrice())
                .category(category)
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

    public GetProductWithDetailsResponseDto findProductDetailsById(Long id){
        Product prod= this.productRepository.findProductWithDetailsById(id).get(0);

        return GetProductWithDetailsResponseDto.builder()
                .id(prod.getId())
                .title(prod.getTitle())
                .price(prod.getPrice())
                .category(prod.getCategory().name)
                .image(prod.getImage())
                .description(prod.getDescription())
                .ratings(prod.getRatings())
                .build();
    }
}
