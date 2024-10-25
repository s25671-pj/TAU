package burgers.Burger_Restaurant.BusinessLogic.Service;

import burgers.Burger_Restaurant.BusinessLogic.ProductDAO;
import burgers.Burger_Restaurant.Food.Burger;
import burgers.Burger_Restaurant.Food.Extras;
import burgers.Burger_Restaurant.Food.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("list")
public class ProductListDataAccessService implements ProductDAO {

    private static final List<Product> products;

    static {
        products = new ArrayList<>();
        products.addAll(Burger.BurgerManager.getAvailableBurgers());
        products.addAll(Extras.ExtrasManager.getAvailableExtras());
    }

    @Override
    public List<Product> selectAllProducts() {
        return products;
    }

    @Override
    public Optional<Product> selectProductById(Integer id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    @Override
    public void insertProduct(Product product) {
        products.add(product);
    }
}