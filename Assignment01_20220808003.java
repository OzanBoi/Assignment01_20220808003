/**@author Ozan Ege Çalışır
@since 29.03.2023 **/
import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.lang.*;
public class Assignment01_20220808003 {
    public static void main(String[] args) {
        Store s = new Store("Migros", "www.migros.com.tr");
        Customer c = new Customer("CSE 102");
        System.out.println(c);
        ClubCustomer cc = new ClubCustomer("Club CSE 102", "05551234567");
        cc.addPoints(20);
        cc.addPoints(30);
        System.out.println(cc.getPhone());
        System.out.println(cc);
        Product p = new Product("1234", "Computer", 20, 1000.00);
        FoodProduct fp = new FoodProduct("3456", "Snickers", 100, 2,
                250, true, true, true, false);
        CleaningProduct cp = new CleaningProduct("5678", "Mop", 28, 99,
                false, "Multi-room");
        s.addProduct(p);
        s.addProduct(fp);
        for (int i = 0; i < s.getInventorySize(); i++)
            System.out.println(s.getProduct(i));
        s.addProduct(cp);
        s.addProduct(new Product("4321", "iPhone", 50, 99.00));
        System.out.println(s.getProductIndex(new FoodProduct("8888", "Apples",
                500, 1, 50, false, false, false, false)));
        System.out.println(cp.purchase(2));
        if (fp.containsGluten())
            System.out.println("My wife cannot eat or drink " + fp.getName());
        else
            System.out.println("My wife can eat or drink " + fp.getName());
        if (fp.containsPeanuts())
            System.out.println("My friend cannot eat or drink " + fp.getName());
        else
            System.out.println("My friend can eat or drink " + fp.getName());
        s.getProduct(0).addToInventory(3);
        for (int i = 0; i < s.getInventorySize(); i++) {
            Product cur = s.getProduct(i);
            System.out.println(cur);
            for (int j = i + 1; j < s.getInventorySize(); j++)
                if (cur.equals(s.getProduct(j)))
                    System.out.println(cur.getName() + " is the same price as " + s.getProduct(j).getName());

        }
    }
}
class Product {
    String id;
    String name;
    int quantity;
    double price;

    public Product(String id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public double getPrice() {

        return price;
    }

    public void setPrice(double price) {

        this.price = price;
    }

    public int remaining() {

        return quantity;
    }

    public void setQuantity(int quantity) {

        this.quantity = quantity;
    }

    public int getQuantity() {

        return quantity;
    }

    public int addToInventory(int amount) {
        if (amount >= 0) {
            quantity += amount;
            return quantity;
        } else
            return quantity;
    }

    public double purchase(int amount) {
        if (amount > quantity || amount < 0) {
            return 0;
        }
        quantity -= amount;
        double total = amount * price;
        return total;
    }

    public String toString() {

        return ("Product " + name + " has " + quantity + " remaining");
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }
        Product other = (Product) o;
        return Math.abs(price - other.getPrice()) < 0.001;
    }
}
class FoodProduct extends Product {
    int calories;
    boolean dairy;
    boolean eggs;
    boolean peanuts;
    boolean gluten;

    public FoodProduct(String id, String name, int quantity, double price, int calories,
                       boolean dairy, boolean eggs, boolean peanuts, boolean gluten) {
        super(id, name, quantity, price);
        this.calories = calories;
        this.dairy = dairy;
        this.eggs = eggs;
        this.peanuts = peanuts;
        this.gluten = gluten;
    }

    public int getCalories() {

        return calories;
    }

    public void setCalories(int calories) {

        this.calories = calories;
    }

    public boolean containsDairy() {

        return dairy;
    }

    public boolean containsEggs() {

        return eggs;
    }

    public boolean containsPeanuts() {

        return peanuts;
    }

    public boolean containsGluten() {

        return gluten;
    }

}

class CleaningProduct extends Product {
    private boolean liquid;
    private String whereToUse;

    public CleaningProduct(String id, String name, int quantity,
                           double price, boolean liquid, String whereToUse) {
        super(id, name, quantity, price);
        this.whereToUse = whereToUse;
        this.liquid = liquid;
    }

    public String getWhereToUse() {

        return whereToUse;
    }

    public void setWhereToUse(String whereToUse) {

        this.whereToUse = whereToUse;
    }

    public boolean isLiquid() {

        return liquid;
    }
}
class Customer {
    private String name;

    public Customer(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {

        return name;
    }
}


class ClubCustomer extends Customer {
    private String phone;
    private int points;

    public ClubCustomer(String name, String phone) {
        super(name);
        this.phone = phone;
        this.points = 0;
    }

    public String getPhone() {

        return phone;
    }

    public void setPhone(String phone) {

        this.phone = phone;
    }

    public int getPoints() {

        return points;
    }

    public void addPoints(int points) {
        if (points > 0) {
            this.points += points;
        }
    }


    public String toString() {

        return (super.getName() + " has " + points + " points");
    }
}


class Store {
    private String name;
    private String website;
    private List<Product> products;

    public Store(String name, String website) {
        this.name = name;
        this.website = website;
        this.products = new ArrayList<Product>();
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getWebsite() {

        return website;
    }

    public void setWebsite(String website) {

        this.website = website;
    }

    public int getInventorySize() {

        return products.size();
    }

    public void addProduct(Product product, int index) {
        if (index < 0 || index > products.size()) {
            products.add(product);
        } else {
            products.add(index, product);
        }
    }

    public void addProduct(Product product) {

        products.add(product);
    }

    public Product getProduct(int index) {
        if (index >= 0 && index < products.size()) {
            return products.get(index);
        } else {
            return null;
        }
    }

    public int getProductIndex(Product p) {

        return products.indexOf(p);
    }
}
