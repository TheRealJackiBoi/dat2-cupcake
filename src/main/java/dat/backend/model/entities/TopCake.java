package dat.backend.model.entities;

public class TopCake
{
    private int topId;
    private String name;
    private float price;

    public TopCake(int topId, String name, float price)
    {
        this.topId = topId;
        this.name = name;
        this.price = price;
    }

    public int getTopId() {
        return topId;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }
}
