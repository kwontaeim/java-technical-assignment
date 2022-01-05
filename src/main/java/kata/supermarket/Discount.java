package kata.supermarket;

import java.math.BigDecimal;
import java.util.Map;

public interface Discount {
    BigDecimal total();
    BigDecimal calculate(Map<BigDecimal, BigDecimal> map);
    Map <BigDecimal, BigDecimal> distribute();
}
