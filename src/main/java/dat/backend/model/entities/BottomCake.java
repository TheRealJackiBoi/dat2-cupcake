package dat.backend.model.entities;

public class BottomCake
{
    private int bottomId;
    private String name;
    private float price;

    public BottomCake(int bottomId, String name, float price)
    {
        this.bottomId = bottomId;
        this.name = name;
        this.price = price;
    }

    public int getBottomId() {
        return bottomId;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }
}
