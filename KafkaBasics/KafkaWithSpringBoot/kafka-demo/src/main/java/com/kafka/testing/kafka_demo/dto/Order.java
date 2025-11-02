package com.kafka.testing.kafka_demo.dto;

public class Order {
    private int orderId;
    private int userId;
    private int restaurantId;
    private float amount;

    public Order() {
    }

    public Order(int orderId, int userId, int restaurantId, float amount) {
        this.orderId = orderId;
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.amount = amount;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", restaurantId=" + restaurantId +
                ", amount=" + amount +
                '}';
    }
}
