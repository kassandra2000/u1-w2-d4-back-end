package kassandrafalsitta;

import com.github.javafaker.Faker;
import kassandrafalsitta.entities.Customer;
import kassandrafalsitta.entities.Order;
import kassandrafalsitta.entities.Product;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        Faker f = new Faker(Locale.ITALY);
        Random r = new Random();
        //prodotti
        Supplier<Product> bookProducts = () -> new Product(f.book().title(), "book", Double.parseDouble(f.commerce().price(r.nextDouble(5, 40), r.nextDouble(100, 300)).replace(",", ".")));
        Supplier<Product> babyProducts = () -> new Product(f.name().firstName(), "baby", Double.parseDouble(f.commerce().price(r.nextDouble(50, 200), r.nextDouble(250, 400)).replace(",", ".")));
        Supplier<Product> boyProducts = () -> new Product(f.name().firstName(), "boy", Double.parseDouble(f.commerce().price(r.nextDouble(200, 300), r.nextDouble(500, 900)).replace(",", ".")));
        //creo la lista di prodotti random tra book,boy,baby
        Supplier<List<Product>> productListMix = () -> {
            List<Product> ProductList = new ArrayList<>();

            for (int i = 0; i < r.nextInt(1, 4); i++) {
                ProductList.add(babyProducts.get());
            }
            for (int i = 0; i < r.nextInt(1, 4); i++) {
                ProductList.add(boyProducts.get());
            }
            for (int i = 0; i < r.nextInt(1, 4); i++) {
                ProductList.add(bookProducts.get());
            }
            return ProductList;
        };


        //creo ordine
        Supplier<Order> order = () -> {
            Calendar startCal1 = Calendar.getInstance();
            startCal1.set(2024, Calendar.JANUARY, 1, 0, 0, 0);
            Calendar endCal1 = Calendar.getInstance();
            endCal1.set(2024, Calendar.JULY, 30, 23, 59, 59);

            Calendar startCal2 = Calendar.getInstance();
            startCal2.set(2024, Calendar.MARCH, 1, 0, 0, 0);
            Calendar endCal2 = Calendar.getInstance();
            endCal2.set(2024, Calendar.JULY, 30, 23, 59, 59);

            // Genera date casuali per startDate e endDate
            Date randomStartDate = f.date().between(startCal1.getTime(), endCal1.getTime());
            Date randomEndDate = f.date().between(startCal2.getTime(), endCal2.getTime());

            // Conversione da Date a LocalDate
            LocalDate startDate = randomStartDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate endDate = randomEndDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            return new Order("in corso", startDate, endDate, productListMix.get(), new Customer(f.name().firstName()));
        };

        //creo lista ordini
        Supplier<List<Order>> orders = () -> {
            List<Order> ordersList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                ordersList.add(order.get());
            }
            return ordersList;
        };
        List<Order> ordersList = orders.get();
        System.out.println(ordersList);

        System.out.println("-------------------------esercizio 1---------------------------");
        Map<Customer, List<Order>> clientGroup = ordersList.stream().collect(Collectors.groupingBy(Order::getCustomer));
        clientGroup.forEach((customer, singleOrder) -> System.out.println("Cliente: " + customer + " -------> " + singleOrder));
        System.out.println("-------------------------esercizio 2---------------------------");
        Map<Customer, Double> clientAndSum = ordersList.stream().collect(Collectors.groupingBy(Order::getCustomer, Collectors.summingDouble(ord -> ord.getProduct().stream().mapToDouble(Product::getPrice).sum())));
        clientAndSum.forEach((customer, total) -> System.out.println("Cliente: " + customer + "  totale ---> " + Math.round(total * 100.0) / 100.0));
        System.out.println("-------------------------esercizio 3---------------------------");
        List<Product> productsList = productListMix.get();
        OptionalDouble maxPrice = productsList.stream().mapToDouble(Product::getPrice).max();
        if (maxPrice.isPresent()) {
            System.out.println("Il prezzo più alto è: " + maxPrice.getAsDouble() + "\n");
        } else {
            System.out.println("Non è stato possibile trovare il prezzo più alto \n");
        }

        //secondo modo
        System.out.println("--------------------5 prodotti più costosi---------------------");
        List<Product> productListSorted = productsList.stream().sorted(Comparator.comparingDouble(Product::getPrice).reversed()).limit(5).toList();
        productListSorted.forEach(System.out::println);
        System.out.println("-------------------------esercizio 4---------------------------");
        OptionalDouble totalAverage = ordersList.stream().flatMap(ord -> ord.getProduct().stream()).mapToDouble(Product::getPrice).average();
        if (totalAverage.isPresent())
            System.out.println("la media dei prezzi è: " + Math.round(totalAverage.getAsDouble() * 100.0) / 100.0);
        else System.out.println("Non è stato possibile trovare la media dei prezzi \n");
        System.out.println("-------------------------esercizio 5---------------------------");
        Map<String, Double> productsGroupForCategory = productsList.stream().collect(Collectors.groupingBy(Product::getCategory, Collectors.summingDouble(Product::getPrice)));
        productsGroupForCategory.forEach((category, total) -> System.out.println("categoria: " + category + " -->  " + total));
    }
}
