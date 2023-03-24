package dat.backend.model.entities;

public class CupCake {

    private int cupCakeId;
    private int quantity;
    private int orderId;
    private int bottomId;
    private int topId;
    private float price;

    public CupCake(int cupCakeId, int quantity, int orderId, int bottomId, int topId, float price) {
        this.cupCakeId = cupCakeId;
        this.quantity = quantity;
        this.orderId = orderId;
        this.bottomId = bottomId;
        this.topId = topId;
        this.price = price;
    }

    public int getCupCakeId() {
        return cupCakeId;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getBottomId() {
        return bottomId;
    }

    public int getTopId() {
        return topId;
    }

    public float getPrice() {
        return price;
    }
}
