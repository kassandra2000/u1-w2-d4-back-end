package kassandrafalsitta.entities;

import java.util.Random;

public class Product {
    private final Long id;
    private String name;
    private String category;
    private double price;

    //costruttore
    public Product(String name, String category, double price) {
        Random r = new Random();
        this.id = r.nextLong(111111, 999999999);
        this.name = name;
        this.category = category;
        this.price = price;
    }

    //metodi
    public void sconto10(double num) {
        this.setPrice(num - (num * 10 / 100));
    }
    //getter e setter

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    //to string

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name='" + name + '\'' + ", category='" + category + '\'' + ", price=" + price + '}';
    }
}
