package model.shippingRate;

public class ROShippingRate implements IShippingRate{

    @Override
    public double getShippingCost(double weight) {
        return weight/0.1;
    }
}
