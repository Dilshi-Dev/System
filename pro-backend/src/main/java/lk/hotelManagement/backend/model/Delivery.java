package lk.hotelManagement.backend.model;

import lombok.Data;

@Data
public class Delivery {
    private String id;
    private String rider_id;
    private String name;
    private String email;
    private String address1;
    private String address2;
    private String delivery_date;
    private String contact;
}
