package model.shippingRate;


public interface IShippingRate {
    /**
     * @param weight the weight of the Product
     * @return the shipping cost for the respective Product
     */
    double getShippingCost(double weight);
}
