package burgers.Burger_Restaurant.BusinessLogic.Repository;

import burgers.Burger_Restaurant.Food.Product;
import org.springframework.data.jpa.repository.JpaRepository;

// @Repository
public interface ProductRepository
        extends JpaRepository<Product, Integer> {
}
