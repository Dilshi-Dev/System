package lk.hotelManagement.backend.controller;
import lk.hotelManagement.backend.model.Delivery;
import lk.hotelManagement.backend.model.User;
import lk.hotelManagement.backend.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/delivery")
public class DeliveryController {

    @Autowired
    DeliveryService deliveryService;

    @GetMapping("/")
    public List<Delivery> delivery() {
        return deliveryService.delivery();
    }
    @PostMapping("/")
    public boolean createDelivery(@RequestBody Delivery delivery) {
        return deliveryService.createDelivery(delivery);
    }
    @GetMapping("/delivery")
    public Delivery deliveryById(@RequestParam String id) {
        return deliveryService.deliveryById(id);
    }
    @PutMapping("/{id}")
    public boolean editDelivery(@PathVariable String id,@RequestBody Delivery delivery) {
        return deliveryService.editDelivery(id, delivery);
    }
    @DeleteMapping("/{deliveryId}")
    public boolean deleteDelivery(@PathVariable String deliveryId) {
        return deliveryService.deleteDelivery(deliveryId);
    }

}
