package entity;

import java.util.Date;

public class doanhthu {
    public static class ByCategory {

        private String category;
        private double revenue;
        private int quantity;
        private double minPrice;
        private double maxPrice;
        private double avgPrice;
    }
    public static class ByUser {

        private String user;
        private double revenue;
        private int quantity;
        private Date firstTime;
        private Date lastTime;
    }
}
