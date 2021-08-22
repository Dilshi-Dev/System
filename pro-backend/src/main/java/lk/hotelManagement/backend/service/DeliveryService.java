package lk.hotelManagement.backend.service;
import lk.hotelManagement.backend.model.Delivery;
import lk.hotelManagement.backend.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class DeliveryService {

    @Autowired
    DeliveryRepository deliveryRepository;

    public List<Delivery> delivery() {
        return deliveryRepository.delivery();
    }
    public boolean createDelivery(Delivery delivery) {
        return deliveryRepository.createDelivery(delivery);
    }
    public Delivery deliveryById(String deliveryId) {
        return deliveryRepository.deliveryById(deliveryId);
    }
    public boolean editDelivery( String id,Delivery delivery) {
        return deliveryRepository.editDelivery(id,delivery);
    }
    public boolean deleteDelivery(@PathVariable String deliveryId) {
        return deliveryRepository.deleteDelivery(deliveryId);
    }
}
