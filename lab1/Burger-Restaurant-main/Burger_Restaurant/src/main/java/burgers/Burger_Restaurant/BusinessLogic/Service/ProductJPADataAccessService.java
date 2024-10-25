package burgers.Burger_Restaurant.BusinessLogic.Service;

import burgers.Burger_Restaurant.BusinessLogic.ProductDAO;
import burgers.Burger_Restaurant.BusinessLogic.Repository.ProductRepository;
import burgers.Burger_Restaurant.Food.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("jpa")
public class ProductJPADataAccessService implements ProductDAO {

    private final ProductRepository productRepository;

    public ProductJPADataAccessService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> selectAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> selectProductById(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public void insertProduct(Product product) {
        productRepository.save(product);
    }
}
