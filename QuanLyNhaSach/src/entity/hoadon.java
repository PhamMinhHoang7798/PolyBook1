package entity;

import java.util.Date;

public class hoadon {
    private Long id;
    private String username;
    private Integer cardId;
    private Date checkin = new Date();
    private Date checkout;
    private int status;
    
    public enum Status {
        Servicing, Completed, Canceled;
    }
    public static final String DATE_PATTERN = "HH:mm:ss dd-MM-yyyy";
}