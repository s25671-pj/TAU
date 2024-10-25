package burgers.Burger_Restaurant.Food;

import java.util.ArrayList;
import java.util.List;

public class Burger extends Product {

    //main goal of BurgerManager class is to generate list of burgers which are allowed to process through the app
    public static class BurgerManager {
        private static final List<Burger> availableBurgers = createAvailableBurgers();

        public static List<Burger> getAvailableBurgers() {
            return availableBurgers;
        }

        private static List<Burger> createAvailableBurgers() {
            return List.of(
                    /*0*/ new Burger("Hamburger", 0, 12, 15, 1, 6, 2, 25),
                    /*1*/ new Burger("Cheeseburger", 0, 12, 15, 1, 6, 2, 18, 19, 25),
                    /*2*/ new Burger("Veggie", 0, 8, 11, 7, 1, 2, 5, 30),
                    /*3*/ new Burger("Meat-boy", 0, 14, 15, 1, 6, 16, 17, 26),
                    /*4*/ new Burger("Chicken burger", 0, 13, 15, 1, 6, 2, 25),
                    /*5*/ new Burger("Hot burger", 0, 12, 15, 9, 6, 3, 10, 29),
                    /*6*/ new Burger("Cheesaur", 22, 23, 4, 6, 1, 18, 19));
        }
    }

    public enum Bun {
        PLAIN(1.12), SESAME(1.25), WHEAT(1.12), GRAHAM(1.18);
        private double price;

        Bun(double price) {
            this.price = price;
        }

        public double getBunPrice() {
            return price;
        }

        public void setBunPrice(double price) {
            this.price = price;
        }
    }

    private Bun bun;
    private List<Topping> toppings = new ArrayList<>();
    private final List<Burger> burgersList = BurgerManager.getAvailableBurgers();

    // constructor below allows to create new object of type Burger by providing index from available burgers list (burgerList)
    public Burger(int index) {
        super("Burger");
        Burger preparedBurger;
        try {
            preparedBurger = burgersList.get(index);
        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("Exception: Unauthorised burger");
            throw new IllegalArgumentException("Product with ID " + index + " not available.");
        }
        if (preparedBurger != null) {
            this.setName(preparedBurger.getName());
            bun = preparedBurger.getBun();
            toppings = preparedBurger.getToppings();
            this.setPrice(preparedBurger.getPriceCalculated());
        }
    }

    public Burger(Burger burger){
        super("Another " + burger.getName());
        bun = burger.getBun();
        toppings = burger.getToppings();
        this.setPrice(burger.getPriceCalculated());
    }

    //constructor which allows to create CustomBurger
    private Burger(Bun bun, int... toppingIndex) {
        super("Custom");
        this.bun = bun;

        this.addTopping(toppingIndex);
        this.setPrice(getPriceCalculated());
    }

    // constructor used to create burgers in availableBurgers
    private Burger(String name, int... toppingIndex) {
        this(Bun.PLAIN, toppingIndex);
        this.setName(name);
    }

    // logic of creating custom burger
    public static Burger createCustomBurger(Bun bun, int... index) {
        if (index.length < 4) {
            throw new IllegalArgumentException("Can't create burger without at least 4 toppings");
        }
        for (int id : index) {
            if (id > Topping.ToppingManager.getAvailableToppings().size() || id < 0) {
                throw new IllegalArgumentException();
            }
        }
        return new Burger(bun, index);
    }

    public Bun getBun() {
        return bun;
    }

    // after changing burgers bun, methods keeps track on changing bun and burgers price correctly
    public void setBun(Bun bun) {
        this.bun.setBunPrice(bun.getBunPrice());
        this.bun = bun;
        this.setPrice(getPriceCalculated());
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    // calculates burgers total cost according to toppings and buns prices.
    public Double getPriceCalculated() {
        Double total = bun.getBunPrice();
        for (var topping : getToppings()) {
            total += topping.getToppingPrice();
        }
        return total;
    }

    // allows to remove topping(s) from existing burger
    public void removeTopping(int... toppingIndex) {
        List<Topping> toppingsToRemove = new ArrayList<>();
        for (var id : toppingIndex) {
            try {
                toppingsToRemove.add(new Topping(id));
            } catch (IllegalArgumentException iae) {
                System.out.println("Exception: " + iae.getMessage());
            }
        }
        this.getToppings().removeAll(toppingsToRemove);
    }

    // allows to add topping(s) to existing burger
    public void addTopping(int... toppingIndex) {
        for (int id : toppingIndex) {
            try {
                Topping topping = new Topping(id);
                getToppings().add(topping);
            } catch (IllegalArgumentException iae) {
                System.out.println("Exception: " + iae.getMessage());
            }
        }
    }

    @Override
    public String toString() {
        if (this.getName() == null || this.getPrice() == null || toppings == null || bun == null) {
            return "";
        }
        return super.toString();
    }
}