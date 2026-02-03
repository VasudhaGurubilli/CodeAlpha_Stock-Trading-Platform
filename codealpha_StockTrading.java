import java.util.ArrayList;
import java.util.Scanner;

/* Stock class */
class Stock {
    String symbol;
    double price;

    Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }
}

/* Transaction class */
class Transaction {
    String type;
    String symbol;
    int quantity;
    double price;

    Transaction(String type, String symbol, int quantity, double price) {
        this.type = type;
        this.symbol = symbol;
        this.quantity = quantity;
        this.price = price;
    }
}

/* Portfolio class */
class Portfolio {
    ArrayList<Transaction> transactions = new ArrayList<>();
    double balance = 100000;   // initial balance

    void buyStock(String symbol, int qty, double price) {
        double cost = qty * price;

        if (cost <= balance) {
            balance -= cost;
            transactions.add(new Transaction("BUY", symbol, qty, price));
            System.out.println("Stock bought successfully.");
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    void sellStock(String symbol, int qty, double price) {
        balance += qty * price;
        transactions.add(new Transaction("SELL", symbol, qty, price));
        System.out.println("Stock sold successfully.");
    }

    void showPortfolio() {
        System.out.println("\n--- Portfolio Summary ---");

        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (Transaction t : transactions) {
                System.out.println(
                        t.type + " | " + t.symbol +
                        " | Qty: " + t.quantity +
                        " | Price: " + t.price
                );
            }
        }
        System.out.println("Current Balance: " + balance);
    }
}

/* MAIN PUBLIC CLASS */
public class codealpha_StockTrading {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Portfolio portfolio = new Portfolio();

        Stock stock1 = new Stock("TCS", 3500);
        Stock stock2 = new Stock("INFY", 1500);

        int choice;

        do {
            System.out.println("\n--- Stock Trading Platform ---");
            System.out.println("1. View Market Data");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("\nMarket Data:");
                    System.out.println(stock1.symbol + " : " + stock1.price);
                    System.out.println(stock2.symbol + " : " + stock2.price);
                    break;

                case 2:
                    System.out.print("Enter stock symbol (TCS/INFY): ");
                    String buySymbol = sc.next();
                    System.out.print("Enter quantity: ");
                    int buyQty = sc.nextInt();

                    double buyPrice = buySymbol.equalsIgnoreCase("TCS")
                            ? stock1.price : stock2.price;

                    portfolio.buyStock(buySymbol, buyQty, buyPrice);
                    break;

                case 3:
                    System.out.print("Enter stock symbol (TCS/INFY): ");
                    String sellSymbol = sc.next();
                    System.out.print("Enter quantity: ");
                    int sellQty = sc.nextInt();

                    double sellPrice = sellSymbol.equalsIgnoreCase("TCS")
                            ? stock1.price : stock2.price;

                    portfolio.sellStock(sellSymbol, sellQty, sellPrice);
                    break;

                case 4:
                    portfolio.showPortfolio();
                    break;

                case 5:
                    System.out.println("Exiting Stock Trading Platform...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 5);

        sc.close();
    }
}