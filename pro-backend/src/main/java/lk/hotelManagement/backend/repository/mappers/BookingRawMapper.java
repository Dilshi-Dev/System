package lk.hotelManagement.backend.repository.mappers;

import lk.hotelManagement.backend.model.Booking;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingRawMapper implements RowMapper<Booking> {
    @Override
    public Booking mapRow(ResultSet resultSet, int i) throws SQLException {
        Booking booking = new Booking();
        booking.setBooking_id(resultSet.getString("booking_id"));
        booking.setFirstName(resultSet.getString("firstname"));
        booking.setLastName(resultSet.getString("lastname"));
        booking.setContact(resultSet.getString("contact"));
        booking.setDate(resultSet.getString("date"));
        booking.setPerson(resultSet.getString("person"));
        booking.setEmail(resultSet.getString("email"));
        return booking;
    }
}
