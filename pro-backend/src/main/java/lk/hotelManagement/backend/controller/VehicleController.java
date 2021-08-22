package lk.hotelManagement.backend.controller;
import lk.hotelManagement.backend.model.Vehicle;
import lk.hotelManagement.backend.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;


    @GetMapping("/")
    public List<Vehicle> vehicles() {
        return vehicleService.vehicles();
    }

    @PostMapping("/")
    public boolean createVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.createVehicle(vehicle);
    }

    @GetMapping("/vehicle")
    public Vehicle vehicleById(@RequestParam String id) {
        return vehicleService.vehicleById(id);
    }
    @PutMapping("/{id}")
    public boolean editVehicle(@PathVariable String id,@RequestBody Vehicle vehicle) {
        return vehicleService.editVehicle(id, vehicle);
    }
    @DeleteMapping("/{vehicleId}")
    public boolean deleteUser(@PathVariable String vehicleId) {
        return vehicleService.deleteVehicle(vehicleId);
    }
}