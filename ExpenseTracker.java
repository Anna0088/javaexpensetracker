import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ExpenseTracker {
    static ArrayList<Expense> expenses = new ArrayList<>();
    static final String FILE_NAME = "expenses.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        loadExpensesFromFile();

        while (true) {
            System.out.println("\n1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. View Total Expenses");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addExpense(sc);
                    break;
                case 2:
                    viewExpenses();
                    break;
                case 3:
                    viewTotalExpenses();
                    break;
                case 4:
                    saveExpensesToFile();
                    System.out.println("Expenses saved. Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    static void addExpense(Scanner sc) {
        System.out.print("Enter category: ");
        String category = sc.nextLine();
        System.out.print("Enter amount: ");
        double amount = sc.nextDouble();
        sc.nextLine(); // consume newline
        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = sc.nextLine();
        expenses.add(new Expense(category, amount, date));
        System.out.println("Expense added.");
    }

    static void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
            return;
        }
        for (Expense e : expenses) {
            System.out.println(e);
        }
    }

    static void viewTotalExpenses() {
        double total = 0;
        for (Expense e : expenses) {
            total += e.getAmount();
        }
        System.out.println("Total Expenses: ₹" + total);
    }

    static void saveExpensesToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Expense e : expenses) {
                writer.write(e.getCategory() + "," + e.getAmount() + "," + e.getDate());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving expenses: " + e.getMessage());
        }
    }

    static void loadExpensesFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 3);
                if (parts.length == 3) {
                    String category = parts[0];
                    double amount = Double.parseDouble(parts[1]);
                    String date = parts[2];
                    expenses.add(new Expense(category, amount, date));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading expenses: " + e.getMessage());
        }
    }
}
