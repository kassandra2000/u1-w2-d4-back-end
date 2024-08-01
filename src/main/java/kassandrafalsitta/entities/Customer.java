package kassandrafalsitta.entities;

import java.util.Random;

public class Customer {
    private final Long id;
    private String name;
    private Integer tier;

    //costruttore
    public Customer(String name) {
        Random r = new Random();
        this.id = r.nextLong(111111, 999999999);
        this.name = name;
        this.tier = r.nextInt(1, 4);
    }
    //metodi

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

    public Integer getTier() {
        return tier;
    }

    public void setTier(Integer tier) {
        this.tier = tier;
    }

    //to string

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tier=" + tier +
                '}';
    }
}
