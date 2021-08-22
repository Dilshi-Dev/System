package lk.hotelManagement.backend.controller;


import lk.hotelManagement.backend.model.Addingfood;
import lk.hotelManagement.backend.service.AddingfoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/addingfood")
public class AddingfoodController {

    @Autowired
    AddingfoodService addingfoodService;


    @GetMapping("/")
    public List<Addingfood> addingfoods() {
        return addingfoodService.addingfoods();
    }

    @PostMapping("/")
    public boolean createAddingfood(@RequestBody Addingfood addingfood) {
        return addingfoodService.createAddingfood(addingfood);
    }

    @GetMapping("/addingfood")
    public Addingfood addingfoodById(@RequestParam String food_id) {
        return addingfoodService.addingfoodById(food_id);
    }
    @PutMapping("/{food_id}")
    public boolean editFood(@PathVariable String food_id,@RequestBody Addingfood addingfood) {
        return addingfoodService.editFood(food_id, addingfood);
    }
    @DeleteMapping("/{food_id}")
    public boolean deleteFood(@PathVariable String food_id) {
        return addingfoodService.deleteFood(food_id);
    }
}
