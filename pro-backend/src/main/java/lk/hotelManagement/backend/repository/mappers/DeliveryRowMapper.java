package lk.hotelManagement.backend.repository.mappers;
import lk.hotelManagement.backend.model.Delivery;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeliveryRowMapper implements RowMapper<Delivery> {

    @Override
    public Delivery mapRow(ResultSet resultSet, int i) throws SQLException {
        Delivery delivery = new Delivery();
        delivery.setId(resultSet.getString("id"));
        delivery.setRider_id(resultSet.getString("rider_id"));
        delivery.setName(resultSet.getString("name"));
        delivery.setEmail(resultSet.getString("email"));
        delivery.setAddress1(resultSet.getString("address1"));
        delivery.setAddress2(resultSet.getString("address2"));
        delivery.setDelivery_date(resultSet.getString("delivery_date"));
        delivery.setContact(resultSet.getString("contact"));
        return delivery;
    }
}