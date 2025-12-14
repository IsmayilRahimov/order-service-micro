package az.ingress.orderservice.service;

import az.ingress.orderservice.client.InventoryClient;
import az.ingress.orderservice.dto.InventoryResponse;
import az.ingress.orderservice.dto.OrderRequest;
import az.ingress.orderservice.dto.OrderResponse;
import az.ingress.orderservice.entity.Order;
import az.ingress.orderservice.mapper.OrderMapper;
import az.ingress.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j

public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final InventoryClient inventoryClient;


    public List<OrderResponse> getAll() {
        return orderMapper.toResponseList(orderRepository.findAll());
    }


    public OrderResponse create(OrderRequest orderRequest) {
        InventoryResponse product = inventoryClient.getInventory()
                .stream()
                .filter(p -> p.getProductCode().equals(orderRequest.getProductCode()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("product not found"));


        if (product.getQuantity() < orderRequest.getQuantity()) {
            throw new RuntimeException("product quantity not enough");
        }

        Order order = orderMapper.toEntity(orderRequest);
        order.setStatus("CREATED");
        order.setTotalPrice(product.getPrice().multiply(BigDecimal.valueOf(orderRequest.getQuantity())));
        return orderMapper.toResponse(orderRepository.save(order));
    }
}
