package lk.hotelManagement.backend.repository;

import lk.hotelManagement.backend.model.Addingfood;
import lk.hotelManagement.backend.repository.mappers.AddingfoodRowMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AddingfoodRepository {

    @Autowired
    Logger logger;

    @Autowired
    @Qualifier("reservation-named-param-jdbc")
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Addingfood> addingfoods() {
        try {
            String query = "SELECT * FROM ADDINGFOOD";
            try {
                return namedParameterJdbcTemplate.query(query, new AddingfoodRowMapper());
            } catch (Exception e) {
                logger.error("Error getting addingfood : {}", e.toString());
                throw new RuntimeException("Error getting addingfood : {}" + e.toString());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error getting addingfood : " + e.getMessage());
        }
    }

    //insert
    public boolean createAddingfood(Addingfood addingfood) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("food_name", addingfood.getFood_name());
            params.put("food_price", addingfood.getFood_price());
            params.put("food_quantity", addingfood.getFood_quantity());

            String query = "INSERT INTO `ADDINGFOOD` (`food_id`,`food_name`, `food_price`, `food_quantity`)" +
                    " VALUES (NULL,:food_name, :food_price, :food_quantity)";

            int i = namedParameterJdbcTemplate.update(query, params);
            if (i == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error("Error getting Addingfood: {}", e.toString());
            throw new RuntimeException("Error getting Addingfood : " + e.getMessage());
        }
    }

    //edit
    public Addingfood addingfoodById(String food_id) {
        try {
            String query = "SELECT * FROM ADDINGFOOD WHERE food_id=:food_id";
            try {
                return namedParameterJdbcTemplate.queryForObject(query, Collections.singletonMap("food_id",food_id), new AddingfoodRowMapper());
            } catch (Exception e) {
                logger.error("Error getting Addingfoods : {}", e.toString());
                throw new RuntimeException("Error getting Addingfoods : {}" + e.toString());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error getting Addingfoods : " + e.getMessage());
        }
    }

    public boolean editFood(String foodId ,Addingfood addingfood) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("food_name", addingfood.getFood_name());
            params.put("food_price", addingfood.getFood_price());
            params.put("food_quantity", addingfood.getFood_quantity());
            params.put("food_id", foodId);


            String query =" UPDATE `ADDINGFOOD` "+
                    " SET `food_name`= :food_name, `food_price`=:food_price, `food_quantity`=:food_quantity"+
                    " WHERE `food_id`=:food_id";

            int i = namedParameterJdbcTemplate.update(query, params);
            if (i == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error("Error getting Addingfoods : {}", e.toString());
            throw new RuntimeException("Error getting Addingfoods : " + e.getMessage());
        }
    }

    //delete
    public boolean deleteFood(String foodId) {
        try {
            String query = "DELETE FROM ADDINGFOOD WHERE food_id=:food_id";
            try {
                int i = namedParameterJdbcTemplate.update(query, Collections.singletonMap("food_id",foodId));
                if (i == 1) {
                    return true;
                } else {
                    return false;
                }

            } catch (Exception e) {
                logger.error("Error getting users : {}", e.toString());
                throw new RuntimeException("Error getting Addingfoods : {}" + e.toString());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error getting foods : " + e.getMessage());
        }
    }
}
