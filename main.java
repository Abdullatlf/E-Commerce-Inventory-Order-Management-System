
import java.time.LocalDate;

public class main {
    public static void main(String[] args) {
        System.out.println("========== STARTING E-COMMERCE SYSTEM ==========\n");
        
        // Create system
        ECommerceSystem system = new ECommerceSystem();
        
        // Load data from CSV files
        CSVReader.loadAllData(system);
        
        // OR create test data manually
        System.out.println("\n--- Adding Test Data ---");
        
        // Create some customers
        Customer c1 = new Customer("1", "Ahmed Ali", "ahmed@email.com");
        Customer c2 = new Customer("2", "Sara Mohammed", "sara@email.com");
        
        // Create some products
        Product p1 = new Product("1", "Laptop", 999.99, 10);
        Product p2 = new Product("2", "Mouse", 29.99, 50);
        Product p3 = new Product("3", "Keyboard", 79.99, 1);

        Review r1 = new Review("1", 5, "hello", c1.getId());
        Review r2 = new Review("2", 5, "hello", c1.getId());
        Review r3 = new Review("3", 5, "hello", c1.getId());

        system.registerCustomer(c1);

        system.addReview(r1);
        system.addReview(r2);
        system.addReview(r3);
        p1.createReview(r1);
        p1.createReview(r2);
        p1.createReview(r3);
        //p1.displayReviews();
        

        p1.editReview(r3, 1, "hellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohell");

        //system.top3Products().display();
        LocalDate date1 = LocalDate.parse("2025-02-09");
        LocalDate date2 = LocalDate.parse("2025-02-12");

        system.ordersBetweenTwoDates(date1, date2).display();


        // Register customers
        /*system.registerCustomer(c1);
        system.registerCustomer(c2);
        
        // Add products
        system.addProduct(p1);
        system.addProduct(p2);
        system.addProduct(p3);
        
        // Add some reviews
        Review r1 = new Review("1",5, "Excellent laptop!", "1");
        Review r2 = new Review("2",4, "Good mouse", "1");
        p1.addReview(r1);
        p1.addReview(new Review("fd", 3, "ffff", c1.getId()));
        p2.addReview(r2);

        Order o = new Order("1", c1.getId());
        system.addOrder(o);
        o.addProduct(p3);
        // Display data
        System.out.println("\n========== DISPLAYING CUSTOMERS ==========");

        //system.displayCustomers();

        System.out.println("\n========== DISPLAYING PRODUCTS ==========");

        //system.displayProducts();

        System.out.println("\n========== DISPLAYING ORDERS ==========");

        system.displayOrders();
        


        System.out.println("\n========== DISPLAYING REVIEWS ==========");
        // Run queries
        System.out.println("\n========== RUNNING QUERIES =========="); */

        
    }
}