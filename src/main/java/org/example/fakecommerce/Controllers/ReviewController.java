package org.example.fakecommerce.Controllers;


import lombok.RequiredArgsConstructor;
import org.example.fakecommerce.schema.Review;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/review")
public class ReviewController {

    @GetMapping
    public List<Review> getAllReviews() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @PostMapping
    public Review createReview() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id){
        throw new UnsupportedOperationException("Not implemented");
    }

    @GetMapping("/{id}")
    public Review getReviewById(@PathVariable Long id){
        throw new UnsupportedOperationException("Not implemented");
    }

    @GetMapping("/product/{productId}")
    public List<Review> getReviewByProductId(@PathVariable Long productId){
        throw new UnsupportedOperationException("Not implemented");
    }

    @GetMapping("/order/{orderId}")
    public List<Review> getReviewByOrderId(@PathVariable Long orderId){
        throw new UnsupportedOperationException("Not implemented");
    }

}
