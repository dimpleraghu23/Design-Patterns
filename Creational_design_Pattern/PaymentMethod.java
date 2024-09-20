// Factory Method Pattern used

/*  Depending on the payment type chosen by the user, we are instantiating the 
 appropriate payment processing object without exposing the instantiation logic to the client. */


// Payment Processing Program - depending upon the payment type chosen by the user

import java.util.Scanner;

// Abstract Product (Payment Processor)
abstract class PaymentProcessor {
    abstract void processPayment(double amount);
}

// Concrete Product: CreditCardPaymentProcessor
class CreditCardPaymentProcessor extends PaymentProcessor {
    @Override
    void processPayment(double amount) {
        System.out.println("Processing credit card payment of $" + amount + ".");
    }
}

// Concrete Product: PayPalPaymentProcessor
class PayPalPaymentProcessor extends PaymentProcessor {
    @Override
    void processPayment(double amount) {
        System.out.println("Processing PayPal payment of $" + amount + ".");
    }
}

// Concrete Product: CryptoPaymentProcessor
class CryptoPaymentProcessor extends PaymentProcessor {
    @Override
    void processPayment(double amount) {
        System.out.println("Processing crypto payment of $" + amount + ".");
    }
}

// Factory (Payment Factory)
class PaymentFactory {
    public static PaymentProcessor getPaymentProcessor(String paymentType) {
        switch (paymentType.toLowerCase()) {
            case "credit":
                return new CreditCardPaymentProcessor();
            case "paypal":
                return new PayPalPaymentProcessor();
            case "crypto":
                return new CryptoPaymentProcessor();
            default:
                return null;
        }
    }
}

// Main Class
public class PaymentMethod {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter payment type (credit/paypal/crypto): ");
        String paymentType = scanner.nextLine();

        System.out.print("Enter payment amount: ");
        double amount = scanner.nextDouble();

        PaymentProcessor paymentProcessor = PaymentFactory.getPaymentProcessor(paymentType);

        if (paymentProcessor != null) {
            paymentProcessor.processPayment(amount);
        } else {
            System.out.println("Invalid payment type!");
        }

        scanner.close();
    }
}
