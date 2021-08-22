package lk.hotelManagement.backend.controller;

import lk.hotelManagement.backend.model.Booking;
import lk.hotelManagement.backend.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/booking")
    public class BookingController {


        @Autowired
        BookingService bookingService;



        @GetMapping("/")
        public List<Booking> bookings() {
            return bookingService.bookings();
        }

        @PostMapping("/")
        public boolean createBooking(@RequestBody Booking booking) {
            return bookingService.createBooking(booking);
        }

        @GetMapping("/booking")
        public Booking bookingById(@RequestParam String booking_id) {
            return bookingService.bookingById(booking_id);
        }
        @PutMapping("/{booking_id}")
        public boolean editBooking(@PathVariable String booking_id,@RequestBody Booking booking) {
            return bookingService.editBooking(booking_id, booking);
        }
        @DeleteMapping("/{booking_id}")
        public boolean deleteBooking(@PathVariable String booking_id) {
            return bookingService.deleteBooking(booking_id);
        }
    }







