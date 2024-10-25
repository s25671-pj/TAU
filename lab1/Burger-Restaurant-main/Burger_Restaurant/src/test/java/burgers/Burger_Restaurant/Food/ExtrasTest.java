package burgers.Burger_Restaurant.Food;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExtrasTest {

    @Test
    public void testValidExtra() {
        Extras extras = new Extras(0);
        assertNotNull(extras.getName());
        assertNotNull(extras.getType());
        assertNotNull(extras.getSize());
        assertNotNull(extras.getPrice());
    }

    @Test
    public void testInvalidExtra() {
        assertThrows(IllegalArgumentException.class, () -> new Extras(51));
    }

    @Test
    public void shouldGetExtra() {
        assertDoesNotThrow(() -> Extras.ExtrasManager.getAvailableExtras().get(0));
    }

    @Test
    public void shouldNotGetExtra() {
        assertThrows(IndexOutOfBoundsException.class, () -> Extras.ExtrasManager.getAvailableExtras().get(50));
    }

    @Test
    public void shouldSetSize() {
        Extras extras = new Extras(0);
        var extrasOldSize = extras.getSize();
        var extrasOldPrice = extras.getPrice();
        extras.setSize(Extras.Size.LARGE);

        assertNotEquals(extrasOldPrice, extras.getPrice());
        assertNotEquals(extrasOldSize, extras.getSize());
    }
}