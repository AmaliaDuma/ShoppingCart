package controller;

import lombok.Getter;
import lombok.Setter;
import model.catalog.Catalog;
import model.product.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Controller {
    private List<Product> shoppingCart;
    private Catalog catalog;
    private HashMap<String, Integer> productsCount;
    private HashMap<String, Double> discounts;

    public Controller(Catalog catalog) {
        shoppingCart = new ArrayList<>();
        this.catalog = catalog;
        productsCount = new HashMap<>();
        discounts = new HashMap<>();
        initializeCount();
    }

    /**
     * Adds a product to the shopping cart
     * @param productName name of the product to be added in the cart
     */
    public void addToCart(String productName){
        Product product = catalog.getProduct(productName);
        shoppingCart.add(product);

        int prev = productsCount.get(product.getName());
        productsCount.put(product.getName(), prev+1);
    }

    /**
     * Calculates the subtotal for all products in the cart
     * @return sum of each product price
     */
    public double calculateSubtotal(){
        double sum = 0;
        for (Product p: shoppingCart){
            sum += p.getPrice();
        }
        return sum;
    }

    /**
     * Calculates the shipping fee for all products in the cart
     * @return total shipping fee
     */
    public double calculateShippingFee(){
        double sum = 0;
        for (Product p: shoppingCart){
            sum += p.getShippingRate().getShippingCost(p.getWeight());
        }
        return sum;
    }

    /**
     * Calculates the total as: subtotal + shipping fee
     * @return total price
     */
    public double calculateTotal(){
        return calculateSubtotal() + calculateShippingFee();
    }

    /**
     * Calculates the VAT for all products in the cart
     * @param total total price from which we compute VAT
     * @return total VAT
     */
    public double calculateVAT(double total){
        return 0.19 * total;
    }

    /**
     * Applies discounts on total price and returns the new total.
     * For every discount that is applicable, we store it in a HashMap as < description, value subtracted>
     * @param total total price from which we substract the discounts
     * @return new total price
     */
    public double applyDiscounts(double total){
        if (productsCount.get("Keyboard")!=0){
            double discount = 4.09 * productsCount.get("Keyboard");
            total -= discount;
            discounts.put("10% off keyboards", discount);
        }

        if (shoppingCart.size() >= 2){
            total -= 10;
            discounts.put("$10 off shipping", 10.0);
        }

        if (productsCount.get("Monitor")>=2 && productsCount.get("Desk Lamp")!=0){
            int lamps = (productsCount.get("Desk Lamp") - (productsCount.get("Monitor")%productsCount.get("Desk Lamp")));
            double discount = lamps * 44.99;
            total -= discount;
            discounts.put(Integer.toString(lamps)+" lamps at half price", discount);
        }
        return total;
    }

    private void initializeCount(){
        productsCount.put("Mouse", 0);
        productsCount.put("Keyboard", 0);
        productsCount.put("Monitor", 0);
        productsCount.put("Webcam", 0);
        productsCount.put("Headphones", 0);
        productsCount.put("Desk Lamp", 0);
    }
}
