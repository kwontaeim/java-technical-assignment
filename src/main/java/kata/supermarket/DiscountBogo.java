package kata.supermarket;

import java.math.BigDecimal;
import java.util.List;

public class DiscountBogo implements Discount {
    BigDecimal discountPrice;

    public BigDecimal calculateDiscount(List<Item> items){
        return discountPrice = BigDecimal.valueOf(Math.ceil(items.size() / 2));
    }
}
