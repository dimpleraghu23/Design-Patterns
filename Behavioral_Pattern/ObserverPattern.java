/* Observer Pattern is useful when you need to notify multiple objects about changes in another object,
like in the stock market or event-driven systems */

// Stock Market Price Update Notification Program

// Import necessary packages
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Subject

// Create class Stock which stores private variables such as investors, stockName and stockPrice
class Stock {
    private List<Investor> investors = new ArrayList<>(); // To store list of investors names
    private String stockName; // To store stock name
    private double stockPrice; // To store stock price

    // Create objects of stockName and stockPrice
    public Stock(String stockName, double stockPrice) {
        this.stockName = stockName;
        this.stockPrice = stockPrice;
    }

    // To add investor
    public void attach(Investor investor) {
        investors.add(investor);
    }

    // To remove investor
    public void detach(Investor investor) {
        investors.remove(investor);
    }

    // To set stock price. Uses double type
    public void setStockPrice(double stockPrice) {
        this.stockPrice = stockPrice;
        notifyAllInvestors();
    }

    // To notify stock name and its price for investors
    public void notifyAllInvestors() {
        for (Investor investor : investors) {
            investor.update(stockName, stockPrice);
        }
    }
}

// Observer
interface Investor {
    void update(String stockName, double stockPrice);
}

/*  Concrete Observer - which is implemented by Investor interface to notify investor
    about stock name and its price */
    
class ConcreteInvestor implements Investor {
    private String investorName;

    public ConcreteInvestor(String investorName) {
        this.investorName = investorName;
    }

    @Override
    public void update(String stockName, double stockPrice) {
        System.out.println("Investor " + investorName + " notified: " + stockName + " is now $" + stockPrice);
    }
}

// class ObserverPattern which stores 2 stocks and its price
public class ObserverPattern {
    public static void main(String[] args) {
        Stock appleStock = new Stock("Apple", 150.00);
        Stock teslaStock = new Stock("Tesla", 700.00);

        Investor investor1 = new ConcreteInvestor("John");
        Investor investor2 = new ConcreteInvestor("Emma");

        Scanner scanner = new Scanner(System.in);
        String choice;

        // Subscription options
        System.out.println("Do you want to subscribe John to Apple Stock? (yes/no): ");
        choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("yes")) {
            appleStock.attach(investor1);
        }

        System.out.println("Do you want to subscribe Emma to Tesla Stock? (yes/no): ");
        choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("yes")) {
            teslaStock.attach(investor2);
        }

        // Updating stock prices
        System.out.println("Enter the new price for Apple stock: ");
        double applePrice = scanner.nextDouble();
        appleStock.setStockPrice(applePrice);

        System.out.println("Enter the new price for Tesla stock: ");
        double teslaPrice = scanner.nextDouble();
        teslaStock.setStockPrice(teslaPrice);

        scanner.close();
    }
}
