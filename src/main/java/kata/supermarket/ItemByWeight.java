package kata.supermarket;

import java.math.BigDecimal;

public class ItemByWeight implements Item {

    private final WeighedProduct product;
    private final BigDecimal weightInKilos;

    ItemByWeight(final WeighedProduct product, final BigDecimal weightInKilos) {
        this.product = product;
        this.weightInKilos = weightInKilos;
    }



    public BigDecimal price() {
        return getProduct().pricePerKilo().multiply(getWeightInKilos()).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getWeightInKilos() {
        return weightInKilos;
    }


    public WeighedProduct getProduct() {
        return product;
    }
}
