package lk.hotelManagement.backend.model;

import lombok.Data;

@Data
public class Employee {
    private String id;
    private String first_name;
    private String last_name;
    private String address1;
    private String address2;
    private String email;
    private String city;
    private String contact;

}
