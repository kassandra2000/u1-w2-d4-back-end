package kassandrafalsitta.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class Order {
    private final Long id;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private List<Product> product;
    private Customer customer;
    private String status;

    //costruttore
    public Order(String status, LocalDate orderDate, LocalDate deliveryDate, List<Product> product, Customer customer) {
        Random r = new Random();
        this.id = r.nextLong(111111, 999999999);
        this.status = status;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.product = product;
        this.customer = customer;
    }


    //metodi

    //getter e setter
    public Long getId() {
        return id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    //to string


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", deliveryDate=" + deliveryDate +
                ", product=" + product +
                ", customer=" + customer +
                ", status='" + status + '\'' +
                '}';
    }
}
