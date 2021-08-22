package lk.hotelManagement.backend.repository;
import lk.hotelManagement.backend.model.Delivery;
import lk.hotelManagement.backend.model.User;
import lk.hotelManagement.backend.repository.mappers.DeliveryRowMapper;
import lk.hotelManagement.backend.repository.mappers.UserRowMapper;
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
public class DeliveryRepository {

    @Autowired
    Logger logger;

    @Autowired
    @Qualifier("reservation-named-param-jdbc")
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Delivery> delivery() {
        try {
            String query = "SELECT * FROM DELIVERY";
            try {
                return namedParameterJdbcTemplate.query(query, new DeliveryRowMapper());
            } catch (Exception e) {
                logger.error("Error getting delivery : {}", e.toString());
                throw new RuntimeException("Error getting delivery : {}" + e.toString());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error getting delivery : " + e.getMessage());
        }
    }


    public boolean createDelivery(Delivery delivery) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("id", delivery.getId());
            params.put("rider_id", delivery.getRider_id());
            params.put("name", delivery.getName());
            params.put("email", delivery.getEmail());
            params.put("address1", delivery.getAddress1());
            params.put("address2", delivery.getAddress2());
            params.put("delivery_date", delivery.getDelivery_date());
            params.put("contact", delivery.getContact());

            String query = "INSERT INTO `DELIVERY` (`id` ,`rider_id`,`name`, `email`,`address1`,`address2`,`delivery_date`,`contact`)" +
                    " VALUES (NULL,:rider_id, :name,:email,:address1,:address2,:delivery_date,:contact )";

            int i = namedParameterJdbcTemplate.update(query, params);
            if (i == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error("Error getting delivery: {}", e.toString());
            throw new RuntimeException("Error getting delivery : " + e.getMessage());
        }
    }

    //edit
    public Delivery deliveryById(String id) {
        try {
            String query = "SELECT * FROM DELIVERY WHERE id=:id";
            try {
                return namedParameterJdbcTemplate.queryForObject(query, Collections.singletonMap("id",id), new DeliveryRowMapper());
            } catch (Exception e) {
                logger.error("Error getting delivery : {}", e.toString());
                throw new RuntimeException("Error getting delivery : {}" + e.toString());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error getting delivery : " + e.getMessage());
        }
    }

    public boolean editDelivery(String deliveryId ,Delivery delivery) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("id", delivery.getId());
            params.put("rider_id", delivery.getRider_id());
            params.put("name", delivery.getName());
            params.put("email", delivery.getEmail());
            params.put("address1", delivery.getAddress1());
            params.put("address2", delivery.getAddress2());
            params.put("delivery_date", delivery.getDelivery_date());
            params.put("contact", delivery.getContact());
            params.put("id", deliveryId);


            String query =" UPDATE `DELIVERY` "+
                    " SET `rider_id`=:rider_id, `name`= :name, `email`=:email, `address1`=:address1,`address2`=:address2,`delivery_date`=:delivery_date, `contact`=:contact"+
                    " WHERE `id`=:id";

            int i = namedParameterJdbcTemplate.update(query, params);
            if (i == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error("Error getting delivery : {}", e.toString());
            throw new RuntimeException("Error getting delivery : " + e.getMessage());
        }
    }

    //delete
    public boolean deleteDelivery(String deliveryId) {
        try {
            String query = "DELETE FROM DELIVERY WHERE id=:deliveryId";
            try {
                int i = namedParameterJdbcTemplate.update(query, Collections.singletonMap("deliveryId",deliveryId));
                if (i == 1) {
                    return true;
                } else {
                    return false;
                }

            } catch (Exception e) {
                logger.error("Error getting delivery : {}", e.toString());
                throw new RuntimeException("Error delete delivery : {}" + e.toString());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error delete delivery : " + e.getMessage());
        }
    }

}
