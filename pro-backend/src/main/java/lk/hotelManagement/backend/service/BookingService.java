package lk.hotelManagement.backend.service;

import lk.hotelManagement.backend.model.Booking;
import lk.hotelManagement.backend.model.Employee;
import lk.hotelManagement.backend.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    public List<Booking> bookings() {
        return bookingRepository.bookings();
    }
    public Booking bookingById(String bookingId) {
        return bookingRepository.bookingById(bookingId);
    }

    public boolean editBooking( String booking_id, Booking booking) {
        return bookingRepository.editBooking(booking_id,booking);
    }

    public boolean createBooking(Booking booking) {
        return bookingRepository.createBooking(booking);
    }
    public boolean deleteBooking(String booking_id) {
        return bookingRepository.deleteBooking(booking_id);
    }
}