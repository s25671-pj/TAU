package burgers.Burger_Restaurant;

import burgers.Burger_Restaurant.BusinessLogic.Repository.ProductRepository;
import burgers.Burger_Restaurant.Food.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class BurgerRestaurantApplication {

    public static void main(String[] args) {
        SpringApplication.run(BurgerRestaurantApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(ProductRepository productRepository) {
        return args -> {

            Product burger = new Product(
                    "Burger",
                    20.0);

            Product extra = new Product(
                    "Fries",
                    10.0);

            List<Product> products = List.of(burger, extra);
            productRepository.saveAll(products);

        };
    }
}