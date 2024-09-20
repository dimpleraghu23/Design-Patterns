// Uses Decorator Pattern Method - allows to dynamically add features to a base coffee order.

/* Coffee Shop Program - asks user the coffe type of their choice and if they need any
additional options such as milk, sugar, etc and calculates the cost of the coffee */
  
import java.util.Scanner;

// Component Interface
interface Coffee {
    String getDescription();
    double cost();
}

// Concrete Components
class BasicCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Basic Coffee";
    }

    @Override
    public double cost() {
        return 5.0;
    }
}

class Espresso implements Coffee {
    @Override
    public String getDescription() {
        return "Espresso";
    }

    @Override
    public double cost() {
        return 6.0;
    }
}

class Americano implements Coffee {
    @Override
    public String getDescription() {
        return "Americano";
    }

    @Override
    public double cost() {
        return 6.5;
    }
}

class Latte implements Coffee {
    @Override
    public String getDescription() {
        return "Latte";
    }

    @Override
    public double cost() {
        return 7.0;
    }
}

// Abstract Decorator
abstract class CoffeeDecorator implements Coffee {
    protected Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }

    public String getDescription() {
        return decoratedCoffee.getDescription();
    }

    public double cost() {
        return decoratedCoffee.cost();
    }
}

// Concrete Decorators
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", Milk";
    }

    @Override
    public double cost() {
        return decoratedCoffee.cost() + 1.0;
    }
}

class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", Sugar";
    }

    @Override
    public double cost() {
        return decoratedCoffee.cost() + 0.5;
    }
}

class WhippedCreamDecorator extends CoffeeDecorator {
    public WhippedCreamDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", Whipped Cream";
    }

    @Override
    public double cost() {
        return decoratedCoffee.cost() + 1.5;
    }
}

// Client code
public class DecoratorPattern {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Available coffee types:");
        System.out.println("1. Basic Coffee");
        System.out.println("2. Espresso");
        System.out.println("3. Americano");
        System.out.println("4. Latte");
        System.out.print("Enter coffee type (1-4): ");
        int coffeeChoice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        Coffee coffee = switch (coffeeChoice) {
            case 1 -> new BasicCoffee();
            case 2 -> new Espresso();
            case 3 -> new Americano();
            case 4 -> new Latte();
            default -> {
                System.out.println("Invalid choice. Defaulting to Basic Coffee.");
                yield new BasicCoffee();
            }
        };

        System.out.print("Enter additional options (milk, sugar, whipped cream) separated by commas: ");
        String[] additions = scanner.nextLine().split("\\s*,\\s*");

        for (String addition : additions) {
            switch (addition.toLowerCase()) {
                case "milk":
                    coffee = new MilkDecorator(coffee);
                    break;
                case "sugar":
                    coffee = new SugarDecorator(coffee);
                    break;
                case "whipped cream":
                    coffee = new WhippedCreamDecorator(coffee);
                    break;
                default:
                    System.out.println("Unknown option: " + addition);
            }
        }

        System.out.println("Order: " + coffee.getDescription());
        System.out.println("Total cost: $" + coffee.cost());

        scanner.close();
    }
}
