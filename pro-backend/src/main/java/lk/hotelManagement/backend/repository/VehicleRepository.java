package lk.hotelManagement.backend.repository;
import lk.hotelManagement.backend.model.Vehicle;
import lk.hotelManagement.backend.repository.mappers.VehicleRowMapper;
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
public class VehicleRepository {

    @Autowired
    Logger logger;

    @Autowired
    @Qualifier("reservation-named-param-jdbc")
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Vehicle> vehicles() {
        try {
            String query = "SELECT * FROM VEHICLE";
            try {
                return namedParameterJdbcTemplate.query(query, new VehicleRowMapper());
            } catch (Exception e) {
                logger.error("Error getting vehicles : {}", e.toString());
                throw new RuntimeException("Error getting vehicles : {}" + e.toString());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error getting vehicles : " + e.getMessage());
        }
    }

    //insert
    public boolean createVehicle(Vehicle vehicle) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("vehicle_brand", vehicle.getVehicle_brand());
            params.put("vehicle_name", vehicle.getVehicle_name());
            params.put("colour", vehicle.getColour());
            params.put("number", vehicle.getNumber());

            String query = "INSERT INTO `VEHICLE` (`id`,`vehicle_brand`, `vehicle_name`, `colour`,`number`)" +
                    " VALUES (NULL,:vehicle_brand,:vehicle_name,:colour,:number)";

            int i = namedParameterJdbcTemplate.update(query, params);
            if (i == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error("Error getting Vehicle: {}", e.toString());
            throw new RuntimeException("Error getting Vehicles : " + e.getMessage());
        }
    }

    //edit
    public Vehicle vehicleById(String id) {
        try {
            String query = "SELECT * FROM VEHICLE WHERE id=:id";
            try {
                return namedParameterJdbcTemplate.queryForObject(query, Collections.singletonMap("id",id), new VehicleRowMapper());
            } catch (Exception e) {
                logger.error("Error getting Vehicles : {}", e.toString());
                throw new RuntimeException("Error getting Vehicles : {}" + e.toString());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error getting Vehicles : " + e.getMessage());
        }
    }


    public boolean editVehicle(String id ,Vehicle vehicle) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("vehicle_brand", vehicle.getVehicle_brand());
            params.put("vehicle_name", vehicle.getVehicle_name());
            //params.put("password", vehicle.getPassword());
            params.put("colour", vehicle.getColour());
            params.put("number", vehicle.getNumber());
            params.put("id", id);


            String query =" UPDATE `VEHICLE` "+
                    " SET `vehicle_brand`= :vehicle_brand, `vehicle_name`=:vehicle_name, `colour`=:colour, `number`=:number"+
                    " WHERE `id`=:id";

            int i = namedParameterJdbcTemplate.update(query, params);
            if (i == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error("Error getting Vehicle : {}", e.toString());
            throw new RuntimeException("Error getting Vehicles : " + e.getMessage());
        }
    }

    //delete
    public boolean deleteVehicle(String vehicleId) {
        try {
            String query = "DELETE FROM VEHICLE WHERE id=:id";
            try {
                int i = namedParameterJdbcTemplate.update(query, Collections.singletonMap("id",vehicleId));
                if (i == 1) {
                    return true;
                } else {
                    return false;
                }

            } catch (Exception e) {
                logger.error("Error getting users : {}", e.toString());
                throw new RuntimeException("Error getting Vehicles : {}" + e.toString());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error getting Vehicles : " + e.getMessage());
        }
    }

}