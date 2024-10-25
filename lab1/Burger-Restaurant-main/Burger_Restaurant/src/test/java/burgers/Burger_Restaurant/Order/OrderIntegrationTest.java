package burgers.Burger_Restaurant.Order;

import burgers.Burger_Restaurant.Food.Burger;
import burgers.Burger_Restaurant.Food.Extras;
import burgers.Burger_Restaurant.Food.Topping;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderIntegrationTest {

    private Burger customBurger;
    private Order order;

    @BeforeEach
    void setUp() {
        customBurger = Burger.createCustomBurger(Burger.Bun.WHEAT, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 13, 20, 29);
        order = new Order(customBurger);
    }

    @Test
    void copyOfCustomBurgerWithPersonalisedAttributesAndOrdersTest() {
        Burger personalisedCustomBurger = new Burger(customBurger);
        personalisedCustomBurger.removeTopping(4, 6, 8);
        personalisedCustomBurger.addTopping(18);
        personalisedCustomBurger.setBun(Burger.Bun.SESAME);

        assertEquals(12, personalisedCustomBurger.getToppings().size());
        assertTrue(Collections.disjoint(personalisedCustomBurger.getToppings(), List.of(
                Topping.ToppingManager.getAvailableToppings().get(4),
                Topping.ToppingManager.getAvailableToppings().get(6),
                Topping.ToppingManager.getAvailableToppings().get(8))));
        assertTrue(personalisedCustomBurger.getToppings().contains(Topping.ToppingManager.getAvailableToppings().get(18)));
        assertEquals(Burger.Bun.SESAME, personalisedCustomBurger.getBun());
        assertEquals(personalisedCustomBurger.getPriceCalculated(), personalisedCustomBurger.getPrice());
        assertNotEquals(personalisedCustomBurger.getPrice(), customBurger.getPrice());

        order.addProducts(personalisedCustomBurger);

        assertEquals(2, order.getOrderedProductsList().size());
        assertEquals(customBurger, order.getOrderedProductsList().get(0));
        assertEquals(personalisedCustomBurger, order.getOrderedProductsList().get(1));
        assertEquals(order.getTotal(), customBurger.getPrice() + personalisedCustomBurger.getPrice());
    }

    @Test
    void addingAndRemovingProductsToOrder() {

        Extras coke = new Extras(2);
        Extras fries = new Extras(27);
        Extras chStrips = new Extras(34);

        coke.setSize(Extras.Size.LARGE);
        chStrips.setSize(Extras.Size.SMALL);

        assertEquals(Extras.Size.LARGE, coke.getSize());
        assertNotEquals(Extras.Size.MEDIUM, chStrips.getSize());

        order.addProducts(coke, fries, chStrips);
        order.removeProducts(customBurger);

        assertEquals(3, order.getOrderedProductsList().size());
        assertEquals(coke, order.getOrderedProductsList().get(0));
        assertEquals(fries, order.getOrderedProductsList().get(1));
        assertEquals(chStrips, order.getOrderedProductsList().get(2));
    }
}