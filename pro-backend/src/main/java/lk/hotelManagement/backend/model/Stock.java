package lk.hotelManagement.backend.model;

import lombok.Data;

@Data
public class Stock {

    private String stock_id;
    private String categoryType;
    private String categoryName;
    private String inStock;
    private String useStock;
    private String wasteStock;
    private String date;
}
