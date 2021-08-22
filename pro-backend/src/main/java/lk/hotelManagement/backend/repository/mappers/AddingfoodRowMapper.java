package lk.hotelManagement.backend.repository.mappers;

import lk.hotelManagement.backend.model.Addingfood;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddingfoodRowMapper implements RowMapper<Addingfood> {

    @Override
    public Addingfood mapRow(ResultSet resultSet, int i) throws SQLException {
        Addingfood addingfood = new Addingfood();
        addingfood.setFood_id(resultSet.getString("food_id"));
        addingfood.setFood_name(resultSet.getString("food_name"));
        addingfood.setFood_price(resultSet.getString("food_price"));
        addingfood.setFood_quantity(resultSet.getString("food_quantity"));
        return addingfood;
    }
}
