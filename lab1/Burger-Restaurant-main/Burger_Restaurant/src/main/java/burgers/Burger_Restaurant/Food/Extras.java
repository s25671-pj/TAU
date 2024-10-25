package burgers.Burger_Restaurant.Food;

import java.util.List;

public class Extras extends Product {

    //main goal of ExtrasManager class is to generate list of extras which are allowed to process through the app
    public static class ExtrasManager {
        private static final List<Extras> availableExtras = createAvailableExtras();

        private static List<Extras> createAvailableExtras() {
            return List.of(
                    /*0*/   new Extras("Coke", Type.DRINK, Size.SMALL, 2.1),
                    /*1*/   new Extras("Coke", Type.DRINK, Size.MEDIUM, 2.6),
                    /*2*/   new Extras("Coke", Type.DRINK, Size.LARGE, 3.73),
                    /*3*/   new Extras("Orangeade", Type.DRINK, Size.SMALL, 1.7),
                    /*4*/   new Extras("Orangeade", Type.DRINK, Size.MEDIUM, 2.06),
                    /*5*/   new Extras("Orangeade", Type.DRINK, Size.LARGE, 2.8),
                    /*6*/   new Extras("Apple Juice", Type.DRINK, Size.SMALL, 1.85),
                    /*7*/   new Extras("Apple Juice", Type.DRINK, Size.MEDIUM, 2.35),
                    /*8*/   new Extras("Apple Juice", Type.DRINK, Size.LARGE, 3.21),
                    /*9*/   new Extras("Orange Juice", Type.DRINK, Size.SMALL, 1.85),
                    /*10*/  new Extras("Orange Juice", Type.DRINK, Size.MEDIUM, 2.35),
                    /*11*/  new Extras("Orange Juice", Type.DRINK, Size.LARGE, 3.09),
                    /*12*/  new Extras("Black Tea", Type.DRINK, Size.SMALL, 1.76),
                    /*13*/  new Extras("Black Tea", Type.DRINK, Size.MEDIUM, 2.2),
                    /*14*/  new Extras("Black Tea", Type.DRINK, Size.LARGE, 2.7),
                    /*15*/  new Extras("Green Tea", Type.DRINK, Size.SMALL, 2.02),
                    /*16*/  new Extras("Green Tea", Type.DRINK, Size.MEDIUM, 2.32),
                    /*17*/  new Extras("Green Tea", Type.DRINK, Size.LARGE, 3.16),
                    /*18*/  new Extras("Black Coffee", Type.DRINK, Size.SMALL, 1.90),
                    /*19*/  new Extras("Black Coffee", Type.DRINK, Size.MEDIUM, 2.15),
                    /*20*/  new Extras("Black Coffee", Type.DRINK, Size.LARGE, 2.87),
                    /*21*/  new Extras("Cappuccino", Type.DRINK, Size.SMALL, 1.99),
                    /*22*/  new Extras("Cappuccino", Type.DRINK, Size.MEDIUM, 2.17),
                    /*23*/  new Extras("Cappuccino", Type.DRINK, Size.LARGE, 2.98),
                    /*24*/  new Extras("Latte", Type.DRINK, Size.SMALL, 1.95),
                    /*25*/  new Extras("Latte", Type.DRINK, Size.MEDIUM, 2.3),
                    /*26*/  new Extras("Latte", Type.DRINK, Size.LARGE, 2.79),
                    /*27*/  new Extras("Fries", Type.SNACK, Size.SMALL, 2.7),
                    /*28*/  new Extras("Fries", Type.SNACK, Size.MEDIUM, 3.2),
                    /*29*/  new Extras("Fries", Type.SNACK, Size.LARGE, 3.6),
                    /*30*/  new Extras("Onion Rings", Type.SNACK, Size.SMALL, 2.94),
                    /*31*/  new Extras("Onion Rings", Type.SNACK, Size.MEDIUM, 3.48),
                    /*32*/  new Extras("Onion Rings", Type.SNACK, Size.LARGE, 4.18),
                    /*33*/  new Extras("Chicken Strips", Type.SNACK, Size.SMALL, 3.2),
                    /*34*/  new Extras("Chicken Strips", Type.SNACK, Size.MEDIUM, 3.7),
                    /*35*/  new Extras("Chicken Strips", Type.SNACK, Size.LARGE, 4.2)
            );
        }

        public static List<Extras> getAvailableExtras() {
            return availableExtras;
        }
    }

    enum Type {DRINK, SNACK}

    public enum Size {
        SMALL, MEDIUM, LARGE;

        // depending on the type of extras item, method generates different volume value
        private String getValue(Type type) {
            switch (type) {
                case DRINK -> {
                    return switch (this) {
                        case SMALL -> "250ml";
                        case MEDIUM -> "333ml";
                        case LARGE -> "500ml";
                    };
                }
                case SNACK -> {
                    return switch (this) {
                        case SMALL -> "200g";
                        case MEDIUM -> "300g";
                        case LARGE -> "400g";
                    };
                }
            }
            return null;
        }
    }

    private final Type type;
    private Size size;
    private final List<Extras> extrasList = ExtrasManager.getAvailableExtras();

    private Extras(String name, Type type, Size size, Double price) {
        super(name + " " + size.getValue(type));
        this.type = type;
        this.size = size;
        this.setPrice(price);
    }

    // constructor below allows to create new object of type Extras by providing index from available extras list (extrasList)
    public Extras(int index) {
        super("Extra");
        Extras extras = null;
        try {
            extras = extrasList.get(index);
        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("Exception: Unauthorised extra");
        }
        if (extras != null) {
            this.setName(extras.getName());
            type = extras.getType();
            size = extras.getSize();
            this.setPrice(extras.getPrice());
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Type getType() {
        return type;
    }

    public Size getSize() {
        return size;
    }

    // method below allows to change the size of invoked object to demanded one, also setting price to equivalent of new size
    public void setSize(Size size) {
        Size actual = this.getSize();
        if (actual != size) {
            for (var e : extrasList) {
                if(getName().substring(0,getName().length() - 5).equals(e.getName().substring(0,e.getName().length() - 5)) && e.getSize() == size){
                    this.setPrice(e.getPrice());
                    this.size = e.getSize();
                    break;
                }
            }
        }
    }

    @Override
    public String toString() {
        if (this.getName() != null) {
            return super.toString();
        }
        return "Unauthorized extra";
    }
}
