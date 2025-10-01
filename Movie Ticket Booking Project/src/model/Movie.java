package model;

public class Movie {
    private int id;
    private String name;
    private String timing;
    private double price;

    public Movie(int id, String name, String timing, double price) {
        this.id = id;
        this.name = name;
        this.timing = timing;
        this.price = price;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getTiming() { return timing; }
    public double getPrice() { return price; }

    public void setName(String name) { this.name = name; }
    public void setTiming(String timing) { this.timing = timing; }
    public void setPrice(double price) { this.price = price; }
}
