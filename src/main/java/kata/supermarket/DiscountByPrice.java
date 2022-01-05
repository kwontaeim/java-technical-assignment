package kata.supermarket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class DiscountByPrice extends DiscountByUnit{

    public DiscountByPrice(List<Item> items, BigDecimal buy, BigDecimal get) {
        super(items, buy, get);
    }

    @Override
    public BigDecimal getUnitDiscount(BigDecimal unitPrice, BigDecimal unitCount){
        BigDecimal deductionPerItem = unitPrice.subtract(get.divide(buy));
        BigDecimal ratio = unitCount.divide(buy, 0, RoundingMode.DOWN).multiply(buy);
        if (deductionPerItem.intValue() <= 0)
            deductionPerItem = unitPrice.multiply(ratio);
        else
            deductionPerItem =  deductionPerItem.multiply(ratio);
        return deductionPerItem;
    }
}
