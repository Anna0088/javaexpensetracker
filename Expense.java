import java.io.Serializable;

public class Expense implements Serializable {
    private String category;
    private double amount;
    private String date;

    public Expense(String category, double amount, String date) {
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    public String getCategory() { return category; }
    public double getAmount() { return amount; }
    public String getDate() { return date; }

    @Override
    public String toString() {
        return "Category: " + category + ", Amount: ₹" + amount + ", Date: " + date;
    }
}
