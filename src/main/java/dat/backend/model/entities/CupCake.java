package dat.backend.model.entities;

public class CupCake {

    private int cupCakeId;
    private int quantity;
    private int orderId;
    private int bottomId;
    private int topId;
    private float price;

    private BottomCake bottomCake;
    private TopCake topCake;
    private int amount;

    public CupCake(int cupCakeId, int quantity, int orderId, int bottomId, int topId, float price) {
        this.cupCakeId = cupCakeId;
        this.quantity = quantity;
        this.orderId = orderId;
        this.bottomId = bottomId;
        this.topId = topId;
        this.price = price;
    }

    public CupCake(BottomCake bottomCake, TopCake topCake, int amount,float price){
        this.bottomCake = bottomCake;
        this.topCake = topCake;
        this.amount = amount;
        this.price = price;
    }

    public CupCake(BottomCake bottomCake, TopCake topCake, int amount,float price, int cupCakeId){
        this.bottomCake = bottomCake;
        this.topCake = topCake;
        this.amount = amount;
        this.price = price;
        this.cupCakeId = cupCakeId;
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

    public BottomCake getBottomCake() {
        return bottomCake;
    }

    public TopCake getTopCake() {
        return topCake;
    }

    public int getAmount() {
        return amount;
    }
}
