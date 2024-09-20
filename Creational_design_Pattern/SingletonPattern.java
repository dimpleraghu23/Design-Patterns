// Singleton Pattern Method is used

// Logging System Program

// import necessary packages
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Singleton Logger class - used to store logging logs in a list
class Logger {
    private static Logger instance;
    private List<String> logs = new ArrayList<>();

    private Logger() { }

    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        logs.add(message);
        System.out.println("Log: " + message); // prints log message
    }

    public List<String> getLogs() {
        return logs;
    }
}

// Main class 
public class SingletonPattern {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter an action (login/logout): ");
        String action = scanner.nextLine();

        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        if (action.equalsIgnoreCase("login")) {
            logger.log("User " + username + " logged in.");
        } else if (action.equalsIgnoreCase("logout")) {
            logger.log("User " + username + " logged out.");
        } else {
            logger.log("Unknown action by " + username + ".");
        }

        System.out.println("Current logs: " + logger.getLogs());

        scanner.close();
    }
}
