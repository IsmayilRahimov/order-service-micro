package az.ingress.orderservice.mapper;

import az.ingress.orderservice.dto.OrderRequest;
import az.ingress.orderservice.dto.OrderResponse;
import az.ingress.orderservice.entity.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toEntity(OrderRequest orderRequest);

    OrderResponse toResponse(Order order);

    List<OrderResponse>  toResponseList(List<Order> orders);


}
