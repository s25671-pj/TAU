package burgers.Burger_Restaurant.Order;

import burgers.Burger_Restaurant.Food.Burger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110,
            111, 112, 113, 114, 115, 116, 117, 118})
    public void shouldCreateOrder(int value) {
        assertFalse(new Order(value).getOrderedProductsList().isEmpty());
    }

    @Test
    public void ordersShouldHaveDifferentId() {
        Order order1 = new Order(0, 1);
        Order order2 = new Order(0, 105);
        assertNotSame(order1.getOrderNumber(), order2.getOrderNumber());
    }

    @Test
    public void shouldNotCreateOrder() {
        assertThrows(IllegalArgumentException.class, () -> new Order(-1, 7, 250));
    }

    @Test
    public void shouldMakeCustomBurger() {
        assertDoesNotThrow(() -> new Order((Burger.createCustomBurger(Burger.Bun.SESAME, 1, 2, 3, 4))));
    }

    @Test
    public void shouldNotCreateOrderWithInvalidProduct() {
        assertThrows(IllegalArgumentException.class, () -> new Order(new Burger(8)));
    }

    @Test
    void shouldAddProductsToOrder() {
        Order order = new Order();
        order.addProductsByIndex(0, 1, 2, 3);
        assertEquals(4, order.getOrderedProductsList().size());
    }

    @Test
    void shouldRemoveProductsInOrder() {
        Order order = new Order();
        order.addProductsByIndex(0, 1, 2, 3);
        order.removeProductsByIndex(0, 2);
        assertEquals(2, order.getOrderedProductsList().size());
    }

    @Test
    void shouldNotAddProductsToOrder() {
        Order order = new Order();
        assertThrows(IllegalArgumentException.class, () -> order.addProductsByIndex(0, 1, 2, 3, -1));
    }

    @Test
    void shouldNotRemoveProductsInOrder() {
        Order order = new Order();
        order.addProductsByIndex(0, 1, 2, 3);
        assertThrows(IllegalArgumentException.class, () -> order.removeProductsByIndex(0, 2, 5));
    }
}