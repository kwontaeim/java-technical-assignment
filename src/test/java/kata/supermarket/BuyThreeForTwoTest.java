package kata.supermarket;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BuyThreeForTwoTest {
    @DisplayName("basket provides its discount total value when containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void hasExpectedTotalDiscount(String description, String expectedDiscountTotal, List<Item> items) {

        final Discount discount = new DiscountByUnit(items, new BigDecimal(3), new BigDecimal(2));

        assertEquals(new BigDecimal(expectedDiscountTotal),  discount.total());
        BigDecimal price = new BigDecimal(expectedDiscountTotal);

//        assertEquals(price, discount.calculate());
//        System.out.println(totalDiscount);
//        Map map = discount.distribute();
//        map.forEach((k,v) -> System.out.println(new BigDecimal(k.toString()).multiply(BigDecimal.valueOf(((Integer) v / 2)))));
//        System.out.println(map);
    }

    static Stream<Arguments> hasExpectedTotalDiscount() {
        return Stream.of(
                noItems(),
                aSingleItemPricedPerUnit(),
                multipleItemsPricedPerUnit(),
                aSingleItemPricedByWeight(),
                multipleItemsPricedByWeight()
        );
    }

    private static Arguments multipleItemsPricedPerUnit() {
        return Arguments.of("multiple items priced per unit", "0.98",
                Arrays.asList(aPackOfDigestives(),aPackOfDigestives()
                        , aPintOfMilk(), aPintOfMilk(), aPintOfMilk(), aPintOfMilk(), aPackOfCheese()));
    }

    private static Arguments aSingleItemPricedPerUnit() {
        return Arguments.of("a single item priced per unit", "0.00", Collections.singletonList(aPintOfMilk()));
    }

    private static Arguments aSingleItemPricedByWeight() {
        return Arguments.of("a single weighed item", "0.00", Collections.singletonList(twoFiftyGramsOfAmericanSweets()));
    }

    private static Arguments multipleItemsPricedByWeight() {
        return Arguments.of("multiple weighed items", "0.00",
                Arrays.asList(twoFiftyGramsOfAmericanSweets(), twoHundredGramsOfPickAndMix())
        );
    }

    private static Arguments noItems() {
        return Arguments.of("no items", "0.00", Collections.emptyList());
    }

    private static Item aPintOfMilk() {
        return new Product(new BigDecimal("0.49")).oneOf();
    }

    private static Item aPackOfDigestives() {
        return new Product(new BigDecimal("1.55")).oneOf();
    }

    private static Item aPackOfCheese() {
        return new Product(new BigDecimal("2.50")).oneOf();
    }

    private static WeighedProduct aKiloOfAmericanSweets() {
        return new WeighedProduct(new BigDecimal("4.99"));
    }

    private static Item twoFiftyGramsOfAmericanSweets() {
        return aKiloOfAmericanSweets().weighing(new BigDecimal(".25"));
    }

    private static WeighedProduct aKiloOfPickAndMix() {
        return new WeighedProduct(new BigDecimal("2.99"));
    }

    private static Item twoHundredGramsOfPickAndMix() {
        return aKiloOfPickAndMix().weighing(new BigDecimal(".2"));
    }
}
