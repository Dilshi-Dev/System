package lk.hotelManagement.backend.repository;

import lk.hotelManagement.backend.model.Booking;
import lk.hotelManagement.backend.repository.mappers.BookingRawMapper;
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
public class BookingRepository {

    @Autowired
    Logger logger;

    @Autowired
    @Qualifier("reservation-named-param-jdbc")
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Booking> bookings() {
        try {
            String query = "SELECT * FROM BOOKING";
            try {
                return namedParameterJdbcTemplate.query(query, new BookingRawMapper());
            } catch (Exception e) {
                logger.error("Error getting booking : {}", e.toString());
                throw new RuntimeException("Error getting booking : {}" + e.toString());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error getting booking : " + e.getMessage());
        }
    }

    //insert
    public boolean createBooking(Booking booking) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("firstName", booking.getFirstName());
            params.put("lastName", booking.getLastName());
            params.put("contact", booking.getContact());
            params.put("date", booking.getDate());
            params.put("person", booking.getPerson());
            params.put("email", booking.getEmail());

            String query = "INSERT INTO `BOOKING` (`booking_id`,`firstName`,`lastName`, `contact`, `date`,`person`,`email`)" +
                    " VALUES (NULL,:firstName, :lastName,:contact,:date,:person,:email)";

            int i = namedParameterJdbcTemplate.update(query, params);
            if (i == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error("Error getting Booking : {}", e.toString());
            throw new RuntimeException("Error getting Booking : " + e.getMessage());
        }
    }


    //delete
    public boolean deleteBooking(String bookingId) {
        try {
            String query = "DELETE FROM BOOKING WHERE booking_id=:bookingId";
            try {
                int i = namedParameterJdbcTemplate.update(query, Collections.singletonMap("bookingId",bookingId));
                if (i == 1) {
                    return true;
                } else {
                    return false;
                }

            } catch (Exception e) {
                logger.error("Error getting booking : {}", e.toString());
                throw new RuntimeException("Error getting booking : {}" + e.toString());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error getting booking : " + e.getMessage());
        }
    }

    public Booking bookingById(String bookingId) {
        try {
            String query = "SELECT * FROM BOOKING WHERE booking_id=:bookingId";
            try {
                return namedParameterJdbcTemplate.queryForObject(query, Collections.singletonMap("bookingId",bookingId), new BookingRawMapper());
            } catch (Exception e) {
                logger.error("Error getting Booking : {}", e.toString());
                throw new RuntimeException("Error getting Booking : {}" + e.toString());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error getting Booking : " + e.getMessage());
        }
    }

    public boolean editBooking(String booking_id ,Booking booking) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("firstName", booking.getFirstName());
            params.put("lastName", booking.getLastName());
            params.put("contact", booking.getContact());
            params.put("date", booking.getDate());
            params.put("person", booking.getPerson());
            params.put("email", booking.getEmail());
            params.put("booking_id", booking_id);


            String query =" UPDATE `BOOKING` "+
                    " SET `firstname`= :firstName, `lastname`=:lastName, `contact`=:contact  ,`date`=:date ,`person`=:person, `email`=:email"+
                    " WHERE `booking_id`=:booking_id";

            int i = namedParameterJdbcTemplate.update(query, params);
            if (i == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error("Error getting Booking : {}", e.toString());
            throw new RuntimeException("Error getting Booking : " + e.getMessage());
        }
    }
}
