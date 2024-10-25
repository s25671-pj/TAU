package burgers.Burger_Restaurant.Food;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class BurgerTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6})
    public void testValidBurger(int value) {
        Burger burger = new Burger(value);
        assertNotNull(burger.getName());
        assertNotNull(burger.getBun());
        assertNotNull(burger.getToppings());
        assertNotNull(burger.getPrice());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 123, 7})
    public void testInvalidBurger(int value) {
        assertThrows(IllegalArgumentException.class, () -> new Burger(value));
    }

    @Test
    public void shouldCreateCustomBurger() {
        assertDoesNotThrow(() -> Burger.createCustomBurger(Burger.Bun.PLAIN, 1, 3, 6, 9, 15, 20, 25, 30));
    }

    @Test
    public void shouldNotCreateCustomBurger() {
        assertThrows(IllegalArgumentException.class, () -> Burger.createCustomBurger(Burger.Bun.WHEAT));
        assertThrows(IllegalArgumentException.class, () -> Burger.createCustomBurger(Burger.Bun.WHEAT, 0));
        assertThrows(IllegalArgumentException.class, () -> Burger.createCustomBurger(Burger.Bun.WHEAT, 0, 1));
        assertThrows(IllegalArgumentException.class, () -> Burger.createCustomBurger(Burger.Bun.WHEAT, 0, 1, 2));
        assertThrows(IllegalArgumentException.class, () -> Burger.createCustomBurger(Burger.Bun.WHEAT, 0, 1, 2, 6, -1, 2, 5));
    }

    @Test
    public void shouldNotAddToppingsToCustomBurger() {
        assertThrows(IllegalArgumentException.class, () -> Burger.createCustomBurger(Burger.Bun.WHEAT, -1, -50, 513, 200));
    }

    @Test
    public void shouldNotCreateAvailableBurger() {
        assertThrows(IllegalArgumentException.class, () -> new Burger(7));
    }

    @Test
    public void shouldRemoveTopping() {
        Burger burger = new Burger(0);
        int before = burger.getToppings().size();
        assertDoesNotThrow(() -> burger.removeTopping(0, 1, 2));
        int after = burger.getToppings().size();
        assertEquals(after, before - 3);
    }

    @Test
    public void shouldNotRemoveNotExistingToppingInBurger() {
        Burger burger = new Burger(0);
        int before = burger.getToppings().size();
        burger.removeTopping(26);
        int after = burger.getToppings().size();
        assertEquals(before,after);
    }

    @Test
    public void shouldChangeTotalPriceAfterBunChange(){
        Burger burger = new Burger(0);
        double beforePrice = burger.getPrice();
        burger.setBun(Burger.Bun.SESAME);
        double afterPrice = burger.getPrice();
        assertNotEquals(beforePrice,afterPrice);
    }
}