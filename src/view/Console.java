package view;

import controller.Controller;
import model.catalog.Catalog;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Console {
    private Controller controller;
    private final Map<Integer, Runnable> menu = new HashMap<>();

    public Console(Controller controller){
        this.controller = controller;
        assignOptions();
    }

    public void run(){
        boolean done = false;
        Scanner scanner = new Scanner(System.in);
        while (!done){
            printMenu();
            try{
                int choice = scanner.nextInt();
                if (choice == 0)
                    done = true;
                else {
                    Runnable toRun = menu.get(choice);
                    if (toRun == null){
                        System.out.println("Bad choice!");
                    } else{
                        toRun.run();
                    }
                }
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    private void printMenu(){
        System.out.println("1. Display catalog.");
        System.out.println("2. Add product to cart.");
        System.out.println("3. Checkout.");
        System.out.println("0. Exit.");
    }

    private void assignOptions(){
        menu.put(1, this::displayCatalog);
        menu.put(2, this::addToCart);
        menu.put(3, this::checkout);
    }

    private void displayCatalog(){
        System.out.println(controller.getCatalog());
    }

    private void addToCart(){
        System.out.println("Enter product name: ");
        Scanner scanner = new Scanner(System.in);
        String productName = scanner.nextLine();
        controller.addToCart(productName);
        displayCartContent();
    }

    private void displayCartContent(){
        for (Map.Entry<String, Integer> entry : controller.getProductsCount().entrySet()){
            if (entry.getValue() != 0){
                System.out.println(entry.getKey() + " x " + entry.getValue());
            }
        }
    }

    private void checkout(){
        System.out.println("---------- Cart ----------");
        displayCartContent();

        System.out.println("---------- Checkout ----------");
        System.out.printf("Subtotal: $%.2f%n", controller.calculateSubtotal());
        System.out.printf("Shipping: $%.2f%n", controller.calculateShippingFee());
        double vat = controller.calculateVAT(controller.calculateSubtotal());
        System.out.printf("VAT: $%.2f%n", vat);

        double total = controller.applyDiscounts(controller.calculateTotal()) + vat;

        System.out.println("---------- Discounts ----------");
        for (Map.Entry<String, Double> entry : controller.getDiscounts().entrySet()){
            System.out.println(entry.getKey() +": -$"+entry.getValue());
        }
        System.out.println("");
        System.out.printf("Total: $%.2f%n", total);
    }
}
