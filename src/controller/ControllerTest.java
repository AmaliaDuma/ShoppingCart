package controller;

import model.catalog.Catalog;

import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    Controller controller;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        this.controller = new Controller(new Catalog());
        controller.addToCart("Keyboard");
        controller.addToCart("Monitor");
    }

//    @org.junit.jupiter.api.Test
//    void addToCart() {
//    }

    @org.junit.jupiter.api.Test
    void calculateSubtotal() {
        double subtotal = controller.calculateSubtotal();
        assertEquals(Double.parseDouble(df.format(subtotal)), 205.98);
    }

    @org.junit.jupiter.api.Test
    void calculateShippingFee() {
        double shippingFee = controller.calculateShippingFee();
        assertEquals(Double.parseDouble(df.format(shippingFee)), 71);
    }

    @org.junit.jupiter.api.Test
    void calculateTotal() {
        double total = controller.calculateTotal();
        assertEquals(Double.parseDouble(df.format(total)), 276.98);
    }

    @org.junit.jupiter.api.Test
    void applyDiscounts() {
        double total = controller.applyDiscounts(controller.calculateTotal());
        assertEquals(Double.parseDouble(df.format(total)), 262.89);
    }

    @org.junit.jupiter.api.Test
    void calculateVAT() {
        double vat = controller.calculateVAT(controller.calculateSubtotal());
        assertEquals(Double.parseDouble(df.format(vat)), 39.14);
    }
}