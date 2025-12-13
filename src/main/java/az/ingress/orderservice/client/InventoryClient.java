package az.ingress.orderservice.client;

import az.ingress.orderservice.dto.InventoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "inventory-service", url = "${inventory-service.url}")
public interface InventoryClient {

    @GetMapping("/getAll")
    List<InventoryResponse> getInventory();

}
