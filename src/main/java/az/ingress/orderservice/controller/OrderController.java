package az.ingress.orderservice.controller;

import az.ingress.orderservice.dto.OrderRequest;
import az.ingress.orderservice.dto.OrderResponse;
import az.ingress.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @GetMapping("/getAll")
    public List<OrderResponse> getAllOrders() {
        return orderService.getAll();
    }

    @PostMapping
    public OrderResponse createOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.create(orderRequest);
    }
}
