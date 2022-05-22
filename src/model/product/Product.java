package model.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.shippingRate.IShippingRate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String name;
    private Double price, weight;
    private IShippingRate shippingRate;

    @Override
    public String toString() {
        return name + " | Price: " + price;
    }
}
