package lk.hotelManagement.backend.repository;
import lk.hotelManagement.backend.model.Employee;
import lk.hotelManagement.backend.repository.mappers.EmployeeRowMapper;
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
public class EmployeeRepository {

    @Autowired
    Logger logger;

    @Autowired
    @Qualifier("reservation-named-param-jdbc")
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Employee> employees() {
        try {
            String query = "SELECT * FROM EMPLOYEE";
            try {
                return namedParameterJdbcTemplate.query(query, new EmployeeRowMapper());
            } catch (Exception e) {
                logger.error("Error getting employees : {}", e.toString());
                throw new RuntimeException("Error getting employees : {}" + e.toString());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error getting employees : " + e.getMessage());
        }
    }

    //insert
    public boolean createEmployee(Employee employee) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("first_name", employee.getFirst_name());
            params.put("last_name", employee.getLast_name());
            params.put("email", employee.getEmail());
            params.put("address1", employee.getAddress1());
            params.put("address2", employee.getAddress2());
            params.put("city", employee.getCity());
            params.put("contact", employee.getContact());

            String query = "INSERT INTO `EMPLOYEE` (`id`,`first_name`, `last_name`, `email`,`address1`,`address2`,`city`,`contact`)" +
                    " VALUES (NULL,:first_name, :last_name,:email,:address1,:address2,:city,:contact )";

            int i = namedParameterJdbcTemplate.update(query, params);
            if (i == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error("Error getting Employee: {}", e.toString());
            throw new RuntimeException("Error getting Employees : " + e.getMessage());
        }
    }

    //edit
    public Employee employeeById(String id) {
        try {
            String query = "SELECT * FROM EMPLOYEE WHERE id=:id";
            try {
                return namedParameterJdbcTemplate.queryForObject(query, Collections.singletonMap("id",id), new EmployeeRowMapper());
            } catch (Exception e) {
                logger.error("Error getting Employees : {}", e.toString());
                throw new RuntimeException("Error getting Employees : {}" + e.toString());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error getting Employees : " + e.getMessage());
        }
    }

    public boolean editEmployee(String employeeId ,Employee employee) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("first_name", employee.getFirst_name());
            params.put("last_name", employee.getLast_name());
            params.put("email", employee.getEmail());
            //params.put("password", employee.getPassword());
            params.put("address1", employee.getAddress1());
            params.put("address2", employee.getAddress2());
            params.put("city", employee.getCity());
            params.put("contact", employee.getContact());
            params.put("id", employeeId);


            String query =" UPDATE `EMPLOYEE` "+
                    " SET `first_name`= :first_name, `last_name`=:last_name, `email`=:email, `address1`=:address1 , `address2`=:address2, `city` = :city, `contact`=:contact"+
                    " WHERE `id`=:id";

            int i = namedParameterJdbcTemplate.update(query, params);
            if (i == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error("Error getting Employees : {}", e.toString());
            throw new RuntimeException("Error getting Employees : " + e.getMessage());
        }
    }

    //delete
    public boolean deleteEmployee(String employeeId) {
        try {
            String query = "DELETE FROM EMPLOYEE WHERE id=:id";
            try {
                int i = namedParameterJdbcTemplate.update(query, Collections.singletonMap("id",employeeId));
                if (i == 1) {
                    return true;
                } else {
                    return false;
                }

            } catch (Exception e) {
                logger.error("Error getting users : {}", e.toString());
                throw new RuntimeException("Error getting Employees : {}" + e.toString());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error getting Employees : " + e.getMessage());
        }
    }

}