package lk.hotelManagement.backend.service;
import lk.hotelManagement.backend.model.Vehicle;
import lk.hotelManagement.backend.model.User;
import lk.hotelManagement.backend.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    public List<Vehicle> vehicles() {
        return vehicleRepository.vehicles();
    }
    public boolean createVehicle(  Vehicle vehicle) {
        return vehicleRepository.createVehicle(vehicle);
    }

    public Vehicle vehicleById(String id) {
        return vehicleRepository.vehicleById(id);
    }
    public boolean editVehicle( String id, Vehicle vehicle) {
        return vehicleRepository.editVehicle(id,vehicle);
    }
    public boolean deleteVehicle(@PathVariable String userId) {
        return vehicleRepository.deleteVehicle(userId);
    }
}

