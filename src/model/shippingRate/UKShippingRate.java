package model.shippingRate;

public class UKShippingRate implements IShippingRate{

    @Override
    public double getShippingCost(double weight) {
        return (weight*2)/0.1;
    }
}
