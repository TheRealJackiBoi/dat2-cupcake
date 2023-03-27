package dat.backend.model.entities;

import java.sql.Timestamp;

public class Order {

    private int orderId;
    private Timestamp orderDate;
    private User user;
    private String userEmail;
    private boolean payed;

    public Order(int orderId, Timestamp orderDate, User user, String userEmail, boolean payed) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.user = user;
        this.userEmail = userEmail;
        this.payed = payed;
    }

    public int getOrderId() {
        return orderId;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public User getUser() {
        return user;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public boolean isPayed() {
        return payed;
    }
}
