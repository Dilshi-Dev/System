package lk.hotelManagement.backend.service;


import lk.hotelManagement.backend.model.Addingfood;
import lk.hotelManagement.backend.repository.AddingfoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddingfoodService {

    @Autowired
    AddingfoodRepository addingfoodRepository;

    public List<Addingfood> addingfoods() {
        return addingfoodRepository.addingfoods();
    }
    public Addingfood addingfoodById(String foodId) {
        return addingfoodRepository.addingfoodById(foodId);
    }

    public boolean editFood( String food_id, Addingfood addingfood) {
        return addingfoodRepository.editFood(food_id,addingfood);
    }

    public boolean createAddingfood(Addingfood addingfood) {
        return addingfoodRepository.createAddingfood(addingfood);
    }
    public boolean deleteFood(String food_id) {
        return addingfoodRepository.deleteFood(food_id);
    }
}
