package kata.supermarket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscountByUnit implements Discount {

    protected final List<Item> items;
    protected final BigDecimal buy;
    protected final BigDecimal get;

    public DiscountByUnit(List<Item> items, BigDecimal buy, BigDecimal get) {
        this.items = items;
        this.buy = buy;
        this.get = get;
    }



    @Override
    public BigDecimal total() {
        return calculate(distribute());
    }

    @Override
    public Map<BigDecimal, BigDecimal> distribute() {

        Map<BigDecimal, BigDecimal> map = new HashMap<>();

        for(Item item : this.items) {
            if (item instanceof ItemByUnit) {
                if (map.get(item.price()) == null)
                    map.put(item.price(), BigDecimal.valueOf(1));
                else
                    map.put(item.price(), map.get(item.price()).add(BigDecimal.valueOf(1)));
            }

        }
        return map;
    }

    @Override
    public BigDecimal calculate(Map<BigDecimal, BigDecimal> map) {
        BigDecimal discount = BigDecimal.ZERO.setScale(2);

        for(BigDecimal unitPrice : map.keySet()) {
            BigDecimal unitCount = map.get(unitPrice);
            discount = discount.add(getUnitDiscount(unitPrice, unitCount));
        }
        return discount.setScale(2, RoundingMode.HALF_UP);
    }

    protected BigDecimal getUnitDiscount(BigDecimal unitPrice, BigDecimal unitCount){
        return unitPrice.multiply(unitCount.divide(buy, 0 , RoundingMode.DOWN).multiply(get));
    }

}
