package model.catalog;

import lombok.Getter;
import lombok.Setter;
import model.product.Product;
import model.shippingRate.ROShippingRate;
import model.shippingRate.UKShippingRate;
import model.shippingRate.USShippingRate;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Getter
@Setter
public class Catalog {
    private List<Product> catalog;

    public Catalog(){
        catalog = new ArrayList<>();
        loadCatalog();
    }

    /**
     * Loads the pre-defined data into the catalog
     */
    private void loadCatalog(){
        catalog.add(new Product("Mouse", 10.99, 0.2, new ROShippingRate()));
        catalog.add(new Product("Keyboard", 40.99, 0.7, new UKShippingRate()));
        catalog.add(new Product("Monitor", 164.99, 1.9, new USShippingRate()));
        catalog.add(new Product("Webcam", 84.99, 0.2, new ROShippingRate()));
        catalog.add(new Product("Headphones", 59.99, 0.6, new USShippingRate()));
        catalog.add(new Product("Desk Lamp", 89.99, 1.3, new UKShippingRate()));
    }

    /**
     * @param name name of the new Product instance we want to create
     * @return a new Product instance with the corresponding price, weight and shipping rate based on the name
     */
    public Product getProduct(String name){
        name = name.toLowerCase(Locale.ROOT);
        return switch (name) {
            case "mouse" -> new Product("Mouse", 10.99, 0.2, new ROShippingRate());
            case "keyboard" -> new Product("Keyboard", 40.99, 0.7, new UKShippingRate());
            case "monitor" -> new Product("Monitor", 164.99, 1.9, new USShippingRate());
            case "webcam" -> new Product("Webcam", 84.99, 0.2, new ROShippingRate());
            case "headphones" -> new Product("Headphones", 59.99, 0.6, new USShippingRate());
            case "desk lamp" -> new Product("Desk Lamp", 89.99, 1.3, new UKShippingRate());
            default -> throw new RuntimeException("Invalid product.");
        };
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("---------- Catalog ----------\n");

        for (Product p : catalog){
            builder.append(p.toString()).append("\n");
        }
       return builder.toString();
    }
}
