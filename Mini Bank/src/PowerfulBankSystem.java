import java.util.*;

class BankAccount {
    private String name;
    private String accountNumber;
    private String pin;
    private double balance;
    private List<String> transactionHistory = new ArrayList<>();

    public BankAccount(String name, String accountNumber, String pin) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = 0.0;
        transactionHistory.add("Account created with ₹0.0 balance");
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public boolean authenticate(String inputPin) {
        return pin.equals(inputPin);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited ₹" + amount);
            System.out.println("✅ ₹" + amount + " deposited successfully.");
        } else {
            System.out.println("❌ Invalid amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount <= balance && amount > 0) {
            balance -= amount;
            transactionHistory.add("Withdrew ₹" + amount);
            System.out.println("✅ ₹" + amount + " withdrawn successfully.");
        } else {
            System.out.println("❌ Insufficient balance or invalid amount.");
        }
    }

    public void checkBalance() {
        System.out.println("💰 Balance: ₹" + balance);
    }

    public void showInfo() {
        System.out.println("\n👤 Name: " + name);
        System.out.println("🔢 Account No: " + accountNumber);
        checkBalance();
    }

    public void showTransactionHistory() {
        System.out.println("📄 Transaction History:");
        for (String txn : transactionHistory) {
            System.out.println(" - " + txn);
        }
    }
}

public class PowerfulBankSystem {
    private static Scanner sc = new Scanner(System.in);
    private static List<BankAccount> accounts = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("🏦 Welcome to Powerful Bank System!");

        while (true) {
            System.out.println("\n======== MAIN MENU ========");
            System.out.println("1️⃣ Create Account");
            System.out.println("2️⃣ Login");
            System.out.println("3️⃣ Exit");
            System.out.print("Choose option: ");
            int option = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (option) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    System.out.println("👋 Thank you for using Powerful Bank System!");
                    return;
                default:
                    System.out.println("❌ Invalid option.");
            }
        }
    }

    private static void createAccount() {
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.print("Create Account Number: ");
        String accNumber = sc.nextLine();
        System.out.print("Set PIN (4-digits): ");
        String pin = sc.nextLine();

        accounts.add(new BankAccount(name, accNumber, pin));
        System.out.println("🎉 Account created successfully!");
    }

    private static void login() {
        System.out.print("Enter Account Number: ");
        String accNumber = sc.nextLine();
        BankAccount user = findAccount(accNumber);

        if (user == null) {
            System.out.println("❌ Account not found.");
            return;
        }

        System.out.print("Enter PIN: ");
        String pin = sc.nextLine();

        if (user.authenticate(pin)) {
            System.out.println("✅ Login successful!");
            userDashboard(user);
        } else {
            System.out.println("❌ Incorrect PIN.");
        }
    }

    private static BankAccount findAccount(String accNumber) {
        for (BankAccount acc : accounts) {
            if (acc.getAccountNumber().equals(accNumber)) {
                return acc;
            }
        }
        return null;
    }

    private static void userDashboard(BankAccount user) {
        int choice;
        do {
            System.out.println("\n===== DASHBOARD =====");
            System.out.println("1️⃣ Deposit");
            System.out.println("2️⃣ Withdraw");
            System.out.println("3️⃣ Check Balance");
            System.out.println("4️⃣ Show Account Info");
            System.out.println("5️⃣ Transaction History");
            System.out.println("6️⃣ Logout");
            System.out.print("Choose option: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to deposit: ₹");
                    double dep = sc.nextDouble();
                    user.deposit(dep);
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ₹");
                    double wd = sc.nextDouble();
                    user.withdraw(wd);
                    break;
                case 3:
                    user.checkBalance();
                    break;
                case 4:
                    user.showInfo();
                    break;
                case 5:
                    user.showTransactionHistory();
                    break;
                case 6:
                    System.out.println("🚪 Logged out.");
                    break;
                default:
                    System.out.println("❌ Invalid choice.");
            }
        } while (choice != 6);
    }
}
