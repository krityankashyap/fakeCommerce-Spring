package org.example.fakecommerce.services;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.fakecommerce.Repositories.ProductRepository;
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
}
