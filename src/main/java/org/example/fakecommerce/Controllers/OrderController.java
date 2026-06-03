package org.example.fakecommerce.Controllers;

import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.example.fakecommerce.Repositories.OrderProductRepository;
import org.example.fakecommerce.Repositories.OrderRepository;
import org.example.fakecommerce.Repositories.ProductRepository;
import org.example.fakecommerce.dtos.*;
import org.example.fakecommerce.exceptions.ResourceNotFoundException;
import org.example.fakecommerce.schema.Order;
import org.example.fakecommerce.schema.OrderStatus;
import org.example.fakecommerce.schema.Order_Products;
import org.example.fakecommerce.schema.Product;
import org.example.fakecommerce.services.OrderService;
import org.example.fakecommerce.utils.ApiResponse;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;

    @GetMapping
    public ResponseEntity<ApiResponse<List<GetOrderResponseDto>>> getAllOrders(){
        List<GetOrderResponseDto> orders= this.orderService.getAllOrders();

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(orders, "Fetched All orders successfully"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<GetOrderResponseDto>> createOrder(@RequestBody CreateOrderRequestDto createOrderRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(orderService.createOrder(createOrderRequestDto), "Order Created Successfully"));
    }


    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id){
        throw new UnsupportedOperationException("Not implemenetd");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<GetOrderResponseDto>> getOrderById(@PathVariable Long id){
        GetOrderResponseDto order= this.orderService.getOrderById(id);

        return ResponseEntity.status(HttpStatus.FOUND).body(ApiResponse.success(order, "Order fectched with corresponding id"));
    }


    @PutMapping
    public ResponseEntity<ApiResponse<GetOrderResponseDto>> updateOrder(long id, @RequestBody UpdateOrderDto updateOrderDto) {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(this.orderService.updateOrder(id, updateOrderDto), "Order updated successfully"));
    }


    @GetMapping("/{id}/summary")
    public void getOrderSummary(@PathVariable long id){

    }


}

