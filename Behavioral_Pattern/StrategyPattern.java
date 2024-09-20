/*  Strategy Pattern is mostly used when you need to switch between different algorithms 
 or behaviors dynamically, such as payment methods in e-commerce or sorting algorithms. */

// Payment Method Selection Program 

import java.util.Scanner;

// Strategy Interface - amount to be paid
interface PaymentStrategy {
    void pay(int amount);
}

// Concrete Strategy for Credit Card Payment - Notifies the amount paid using credit card and displays the ending with 4 digits
class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid $" + amount + " using Credit Card ending in " + cardNumber.substring(cardNumber.length() - 4));
    }
}

// Concrete Strategy for PayPal Payment - notifies the amount paid using PayPal account
class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid $" + amount + " using PayPal account: " + email);
    }
}

// Checks if amount is paid or not
class ShoppingCart {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void checkout(int amount) {
        if (paymentStrategy != null) {
            paymentStrategy.pay(amount);
        } else {
            System.out.println("No payment method selected.");
        }
    }
}

// Create class to ask user to enter necessary details
public class StrategyPattern {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ShoppingCart cart = new ShoppingCart();

        System.out.println("Enter total amount to pay: ");
        int amount = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        System.out.println("Select payment method (credit/paypal): ");
        String paymentMethod = scanner.nextLine();

        if (paymentMethod.equalsIgnoreCase("credit")) {
            System.out.println("Enter Credit Card number: ");
            String cardNumber = scanner.nextLine();
            cart.setPaymentStrategy(new CreditCardPayment(cardNumber));
        } else if (paymentMethod.equalsIgnoreCase("paypal")) {
            System.out.println("Enter PayPal email: ");
            String email = scanner.nextLine();
            cart.setPaymentStrategy(new PayPalPayment(email));
        } else {
            System.out.println("Invalid payment method selected.");
        }

        cart.checkout(amount);

        scanner.close();
    }
}
