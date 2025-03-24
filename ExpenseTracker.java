import java.io.*;
import java.util.*;

public class ExpenseTracker {
    private static final String FILE_NAME = "expenses.txt";
    private static List<Expense> expenseList = new ArrayList<>();

    public static void main(String[] args) {
        loadExpenses();

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n---- Expense Tracker ----");
            System.out.println("1. Add Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. Calculate Total Expense");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addExpense(sc);
                    break;
                case 2:
                    viewExpenses();
                    break;
                case 3:
                    calculateTotal();
                    break;
                case 4:
                    saveExpenses();
                    System.out.println("Thank you for using Expense Tracker!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 4);

        sc.close();
    }

    private static void addExpense(Scanner sc) {
        System.out.print("Enter category: ");
        String category = sc.nextLine();

        System.out.print("Enter amount: ");
        double amount = sc.nextDouble();
        sc.nextLine(); // Consume newline

        System.out.print("Enter date (dd-mm-yyyy): ");
        String date = sc.nextLine();

        Expense e = new Expense(category, amount, date);
        expenseList.add(e);
        System.out.println("Expense added successfully.");
    }

    private static void viewExpenses() {
        if (expenseList.isEmpty()) {
            System.out.println("No expenses recorded yet.");
            return;
        }

        System.out.println("\n--- All Expenses ---");
        for (Expense e : expenseList) {
            System.out.println(e);
        }
    }

    private static void calculateTotal() {
        double total = 0;
        for (Expense e : expenseList) {
            total += e.getAmount();
        }
        System.out.println("Total Expense: ₹" + total);
    }

    private static void saveExpenses() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(expenseList);
        } catch (IOException e) {
            System.out.println("Error saving expenses: " + e.getMessage());
        }
    }

    private static void loadExpenses() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            expenseList = (List<Expense>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading expenses: " + e.getMessage());
        }
    }
}
