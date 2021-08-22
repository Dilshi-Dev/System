package lk.hotelManagement.backend.repository;

import lk.hotelManagement.backend.model.Stock;
import lk.hotelManagement.backend.repository.mappers.StockRowMapper;
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
public class StockRepository {


    @Autowired
    Logger logger;

    @Autowired
    @Qualifier("reservation-named-param-jdbc")
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Stock> stocks() {
        try {
            String query = "SELECT * FROM STOCK";
            try {
                return namedParameterJdbcTemplate.query(query, new StockRowMapper());
            } catch (Exception e) {
                logger.error("Error getting stock : {}", e.toString());
                throw new RuntimeException("Error getting stock : {}" + e.toString());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error getting stock : " + e.getMessage());
        }
    }
    //insert
    public boolean createStock(Stock stock) {
        try {
            Map<String, Object> params = new HashMap<>();
            //params.put("stock_id", stock.getStock_id());
            params.put("categoryType", stock.getCategoryType());
            params.put("categoryName", stock.getCategoryName());
            params.put("inStock", stock.getInStock());
            params.put("useStock", stock.getUseStock());
            params.put("wasteStock", stock.getWasteStock());
            params.put("date", stock.getDate());



            String query = "INSERT INTO `Stock` (`stock_id`,`categoryType`,`categoryName`, `inStock`, `useStock`, `wasteStock`,`date`)" +
                    " VALUES (NULL,:categoryType, :categoryName, :inStock , NULL , NULL , :date)";





            int i = namedParameterJdbcTemplate.update(query, params);
            if (i == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error("Error getting Stocks : {}", e.toString());
            throw new RuntimeException("Error getting Stocks : " + e.getMessage());
        }
    }

    //edit
    public Stock stockById(String stockId) {
        try {
            String query = "SELECT * FROM STOCK WHERE stock_id=:stockId";
            try {
                return namedParameterJdbcTemplate.queryForObject(query, Collections.singletonMap("stockId",stockId), new StockRowMapper());
            } catch (Exception e) {
                logger.error("Error getting users : {}", e.toString());
                throw new RuntimeException("Error getting Stock : {}" + e.toString());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error getting Stock : " + e.getMessage());
        }
    }


    public boolean editStock(String stock_id ,Stock stock) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("categoryType", stock.getCategoryType());
            params.put("categoryName", stock.getCategoryName());
            params.put("inStock", stock.getInStock());
            params.put("useStock", stock.getUseStock());
            params.put("wasteStock", stock.getWasteStock());
            params.put("date", stock.getDate());
            params.put("stock_id",stock_id);

            String query =" UPDATE `STOCK` "+

                    " SET  `categoryType`= :categoryType,`categoryName`=:categoryName, `inStock` =:inStock, `useStock`= :useStock,`wasteStock`= :wasteStock, `date`= :date   "+
                    " WHERE `stock_id`=:stock_id";


            int i = namedParameterJdbcTemplate.update(query, params);
            if (i == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error("Error edit stocks : {}", e.toString());
            throw new RuntimeException("Error edit stocks : " + e.getMessage());
        }
    }

    //delete
    public boolean deleteStock(String stockId) {
        try {
            String query = "DELETE FROM STOCK WHERE stock_id=:stockId";
            try {
                int i = namedParameterJdbcTemplate.update(query, Collections.singletonMap("stockId",stockId));
                if (i == 1) {
                    return true;
                } else {
                    return false;
                }

            } catch (Exception e) {
                logger.error("Error getting stock : {}", e.toString());
                throw new RuntimeException("Error getting stock: {}" + e.toString());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error getting stock: " + e.getMessage());
        }
    }
}
