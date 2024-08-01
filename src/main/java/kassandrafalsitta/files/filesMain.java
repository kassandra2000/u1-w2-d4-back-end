package kassandrafalsitta.files;

import com.github.javafaker.Faker;
import kassandrafalsitta.entities.Product;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class filesMain {
    static Faker f = new Faker(Locale.ITALY);
    static Random r = new Random();
    static Supplier<Product> bookProducts = () -> new Product(f.book().title(), "book", Double.parseDouble(f.commerce().price(r.nextDouble(5, 40), r.nextDouble(100, 300)).replace(",", ".")));
    static Supplier<Product> babyProducts = () -> new Product(f.name().firstName(), "baby", Double.parseDouble(f.commerce().price(r.nextDouble(50, 200), r.nextDouble(250, 400)).replace(",", ".")));
    static Supplier<Product> boyProducts = () -> new Product(f.name().firstName(), "boy", Double.parseDouble(f.commerce().price(r.nextDouble(200, 300), r.nextDouble(500, 900)).replace(",", ".")));
    //creo la lista di prodotti random tra book,boy,baby
    static Supplier<List<Product>> productListMix = () -> {
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

    public static void main(String[] args) {

        File file = new File("src/main/java/kassandrafalsitta/files/info.txt");

        try {
            //aggiunge una lista di file
            FileUtils.writeStringToFile(file, addFileList(productListMix.get()), StandardCharsets.UTF_8, true);
            //aggiunge un solo elemento
            //FileUtils.writeStringToFile(file, addFile(new Product(f.book().title(), "book", Double.parseDouble(f.commerce().price(r.nextDouble(5, 40), r.nextDouble(100, 300)).replace(",", ".")))), StandardCharsets.UTF_8, true);

            //---------------------------lettura------------------------------------
            String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
            readFile(content);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String addFile(Product product) {
        return product.getName() + "@" + product.getCategory() + "@" + product.getPrice() + "@" + product.getId() + System.lineSeparator();
    }

    public static String addFileList(List<Product> productList) {
        return productList.stream().map(product -> product.getName() + "@" + product.getCategory() + "@" + product.getPrice() + "@" + product.getId()).collect(Collectors.joining(System.lineSeparator()));
    }

    public static void readFile(String content) {
        String[] contentArr = content.split(System.lineSeparator());
        List<Product> listOfProduct = new ArrayList<>();
        Arrays.stream(contentArr).map(str -> str.split("@")).forEach(strArr -> listOfProduct.add(new Product(strArr[0], strArr[1], Double.parseDouble(strArr[2]))));
        System.out.println("leggere " + listOfProduct);
    }
}
