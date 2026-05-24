package org.example.fakecommerce.Controllers;

import lombok.RequiredArgsConstructor;
import org.example.fakecommerce.dtos.OrderItemRequestDto;
import org.example.fakecommerce.schema.Order;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {

    public List<Order> getAllOrders(){
        throw new UnsupportedOperationException("Not implemented");
    }

    public Order createOrder(@RequestBody OrderItemRequestDto orderItemRequestDto){
        throw new UnsupportedOperationException("Not implemented");
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id){
        throw new UnsupportedOperationException("Not implemenetd");
    }


}
