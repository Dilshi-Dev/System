package lk.hotelManagement.backend.repository.mappers;

import lk.hotelManagement.backend.model.Stock;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StockRowMapper implements RowMapper<Stock> {

    @Override
    public Stock mapRow(ResultSet resultSet, int i) throws SQLException {
        Stock stock = new Stock();
        stock.setStock_id(resultSet.getString("stock_id"));
        stock.setCategoryType(resultSet.getString("categoryType"));
        stock.setCategoryName(resultSet.getString("categoryName"));
        stock.setInStock(resultSet.getString("inStock"));
        stock.setUseStock(resultSet.getString("useStock"));
        stock.setWasteStock(resultSet.getString("wasteStock"));
        stock.setDate(resultSet.getString("Date"));
        return stock;
    }
}
