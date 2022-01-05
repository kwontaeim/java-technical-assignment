package kata.supermarket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscountByKilo extends DiscountByUnit {

    public DiscountByKilo(List<Item> items, BigDecimal buy, BigDecimal get) {
        super(items, buy, get);
    }


    @Override
    public  Map<BigDecimal, BigDecimal> distribute() {

        Map map = new HashMap<>();

        for(Item item : this.items) {
            if (item instanceof ItemByWeight) {
                ItemByWeight itemByWeight = (ItemByWeight) item;
                map.put(itemByWeight.getProduct().getPricePerKilo(), itemByWeight.getWeightInKilos());
            }

        }
        return map;
    }

    @Override
    protected BigDecimal getUnitDiscount(BigDecimal unitPrice, BigDecimal unitCount){
        return unitPrice.multiply(get).multiply(unitCount.divide(buy, 0, RoundingMode.DOWN));
    }

}
