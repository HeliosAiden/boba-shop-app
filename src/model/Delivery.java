package model;

import java.time.LocalDate;

public class Delivery {
    private Order order;
    private User courier;
    private int tracking_id, status;
    private LocalDate createdAt, updatedAt;
}
