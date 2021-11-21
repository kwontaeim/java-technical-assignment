package kata.supermarket;

import java.math.BigDecimal;
import java.util.List;

public class DiscountBogo implements Discount {

    public BigDecimal calculateDiscount(List<Item> items){ return BigDecimal.ZERO;}
}
