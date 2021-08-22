package lk.hotelManagement.backend.model;
import lombok.Data;

@Data

public class Booking {
    private String booking_id;
    private String firstName;
    private String lastName;
    private String contact;
    private String date;
    private String person;
    private String email;
}
