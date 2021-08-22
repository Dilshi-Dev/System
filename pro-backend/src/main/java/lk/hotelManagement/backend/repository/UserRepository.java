package lk.hotelManagement.backend.repository;
import lk.hotelManagement.backend.model.User;
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
public class UserRepository {

    @Autowired
    Logger logger;

    @Autowired
    @Qualifier("reservation-named-param-jdbc")
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<User> users() {
        try {
            String query = "SELECT * FROM USERS";
            try {
                return namedParameterJdbcTemplate.query(query, new UserRowMapper());
            } catch (Exception e) {
                logger.error("Error getting users : {}", e.toString());
                throw new RuntimeException("Error getting users : {}" + e.toString());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error getting users : " + e.getMessage());
        }
    }

    //insert
    public boolean createUser(User user) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("firstName", user.getFirstName());
            params.put("lastName", user.getLastName());
            params.put("email", user.getEmail());
            params.put("password", user.getPassword());
            params.put("address1", user.getAddress1());
            params.put("address2", user.getAddress2());
            params.put("country", user.getCountry());
            params.put("countryCode", user.getCountryCode());
            params.put("contact", user.getContact());
            params.put("Role", user.getRole());

            String query = "INSERT INTO `users` (`user_id`,`country_code`,`country`,`first_name`, `last_name`, `email`,`password`,`address_1`,`address_2`,`contact`,`role`)" +
                    " VALUES (NULL,:countryCode,:country,:firstName, :lastName,:email,:password,:address1,:address2,:contact ,:Role)";

            int i = namedParameterJdbcTemplate.update(query, params);
            if (i == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error("Error getting users : {}", e.toString());
            throw new RuntimeException("Error getting users : " + e.getMessage());
        }
    }

    //edit
    public User userById(String userId) {
        try {
            String query = "SELECT * FROM USERS WHERE user_id=:userId";
            try {
                return namedParameterJdbcTemplate.queryForObject(query, Collections.singletonMap("userId", userId), new UserRowMapper());
            } catch (Exception e) {
                logger.error("Error getting users : {}", e.toString());
                throw new RuntimeException("Error getting users : {}" + e.toString());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error getting users : " + e.getMessage());
        }
    }

    public boolean editUser(String userId, User user) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("firstName", user.getFirstName());
            params.put("lastName", user.getLastName());
            params.put("email", user.getEmail());
            params.put("password", user.getPassword());
            params.put("address1", user.getAddress1());
            params.put("address2", user.getAddress2());
            params.put("country", user.getCountry());
            params.put("countryCode", user.getCountryCode());
            params.put("contact", user.getContact());
            params.put("Role", user.getRole());
            params.put("userid", userId);


            String query = " UPDATE `USERS` " +
                    " SET `first_name`= :firstName, `last_name`=:lastName,`country`=:country,`country_code`=:countryCode, `email`=:email, `password`=:password, `address_1`=:address1 , `address_2`=:address2, `contact`=:contact,`role`=:Role" +
                    " WHERE `user_id`=:userid";

            int i = namedParameterJdbcTemplate.update(query, params);
            if (i == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error("Error getting users : {}", e.toString());
            throw new RuntimeException("Error getting users : " + e.getMessage());
        }
    }

    //delete
    public boolean deleteUser(String userId) {
        try {
            String query = "DELETE FROM USERS WHERE user_id=:userId";
            try {
                int i = namedParameterJdbcTemplate.update(query, Collections.singletonMap("userId", userId));
                if (i == 1) {
                    return true;
                } else {
                    return false;
                }

            } catch (Exception e) {
                logger.error("Error getting users : {}", e.toString());
                throw new RuntimeException("Error getting users : {}" + e.toString());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error getting users : " + e.getMessage());
        }
    }

    public User login(String username, String password) {
        try {

            Map<String, Object> params = new HashMap<>();
            params.put("userName", username);
            params.put("password", password);
            String query = "SELECT * FROM USERS WHERE email=:userName AND password = :password LIMIT 1 ";
            return namedParameterJdbcTemplate.queryForObject(query, params, new UserRowMapper());
        } catch (Exception e) {
            throw new RuntimeException("User Not Found : " + e.getMessage());
        }
    }

}
