package model.shippingRate;

public class USShippingRate implements IShippingRate{
    @Override
    public double getShippingCost(double weight) {
        return (weight*3)/0.1;
    }
}
