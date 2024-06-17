package model;

import java.time.LocalDate;


public class Order {
    private User user;
    private float total_amount;
    private int status;
    private LocalDate createdAt, updatedAt;
    private Delivery delivery;
    private Discount discount;
}
