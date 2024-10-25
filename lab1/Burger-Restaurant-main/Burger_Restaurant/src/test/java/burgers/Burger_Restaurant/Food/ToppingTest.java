package burgers.Burger_Restaurant.Food;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ToppingTest {

    @Test
    public void testValidTopping() {
        Topping topping = new Topping(0);
        assertNotNull(topping.getName());
        assertNotNull(topping.getType());
        assertNotNull(topping.getToppingPrice());
    }

    @Test
    public void testInvalidTopping() {
        assertThrows(IllegalArgumentException.class, () -> new Topping(50));
    }

    @Test
    public void shouldGetTopping() {
        assertDoesNotThrow(() -> Topping.ToppingManager.getAvailableToppings().get(0));
    }
}