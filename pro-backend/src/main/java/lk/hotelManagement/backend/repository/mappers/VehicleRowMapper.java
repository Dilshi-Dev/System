package lk.hotelManagement.backend.repository.mappers;
import lk.hotelManagement.backend.model.Vehicle;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleRowMapper implements RowMapper<Vehicle> {

    @Override
    public Vehicle mapRow(ResultSet resultSet, int i) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(resultSet.getString("id"));
        vehicle.setVehicle_brand(resultSet.getString("vehicle_brand"));
        vehicle.setVehicle_name(resultSet.getString("vehicle_name"));
        vehicle.setColour(resultSet.getString("colour"));
        vehicle.setNumber(resultSet.getString("number"));
        return vehicle;
    }
}
