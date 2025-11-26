/*import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class main {
    private static Scanner scanner = new Scanner(System.in);
    private static ECommerceSystem system = new ECommerceSystem();
    
    private static final String ADMIN_NAME = "abdullatif";
    private static final String ADMIN_ID = "1";

    public static void main(String[] args) {
        System.out.println("=== Loading Data from CSV Files ===");
        CSVReader.loadAllData(system);
        System.out.println("\n=== Data Loaded Successfully ===\n");
        
        boolean running = true;
        while (running) {
            System.out.println("\n╔════════════════════════════════════════╗");
            System.out.println("║   E-COMMERCE MANAGEMENT SYSTEM         ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.println("1. Admin Login");
            System.out.println("2. Customer Login");
            System.out.println("3. Exit");
            System.out.print("\nSelect option: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    adminLogin();
                    break;
                case 2:
                    customerLogin();
                    break;
                case 3:
                    running = false;
                    System.out.println("\nThank you for using the E-Commerce System!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
        scanner.close();
    }

    // ==================== ADMIN SECTION ====================
    
    private static void adminLogin() {
        System.out.println("\n=== ADMIN LOGIN ===");
        System.out.print("Enter Admin ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Admin Name: ");
        String name = scanner.nextLine();
        
        if (id.equals(ADMIN_ID) && name.equalsIgnoreCase(ADMIN_NAME)) {
            System.out.println("\n✓ Login Successful! Welcome, " + ADMIN_NAME + "!");
            adminMenu();
        } else {
            System.out.println("\n✗ Invalid credentials!");
        }
    }
    
    private static void adminMenu() {
        boolean adminRunning = true;
        while (adminRunning) {
            System.out.println("\n╔════════════════════════════════════════╗");
            System.out.println("║         ADMIN DASHBOARD                ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.println("=== Product Management ===");
            System.out.println("1.  View All Products");
            System.out.println("2.  Add New Product");
            System.out.println("3.  Update Product Price");
            System.out.println("4.  Update Product Stock");
            System.out.println("5.  Remove Product");
            System.out.println("\n=== Customer Management ===");
            System.out.println("6.  View All Customers");
            System.out.println("7.  Register New Customer");
            System.out.println("8.  View Customer Orders");
            System.out.println("\n=== Order Management ===");
            System.out.println("9.  View All Orders");
            System.out.println("10. View Orders Between Dates");
            System.out.println("\n=== Review Management ===");
            System.out.println("11. View All Reviews");
            System.out.println("12. View Reviews for Specific Product");
            System.out.println("13. Remove Review from Product");
            System.out.println("14. View Customer Reviews");
            System.out.println("\n=== Analytics & Queries ===");
            System.out.println("15. View Top 3 Products");
            System.out.println("16. View Common Products Between Customers");
            System.out.println("\n17. Logout");
            System.out.print("\nSelect option: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1: adminViewProducts(); break;
                case 2: adminAddProduct(); break;
                case 3: adminUpdateProductPrice(); break;
                case 4: adminUpdateProductStock(); break;
                case 5: adminRemoveProduct(); break;
                case 6: adminViewCustomers(); break;
                case 7: adminRegisterCustomer(); break;
                case 8: adminViewCustomerOrders(); break;
                case 9: adminViewOrders(); break;
                case 10: adminViewOrdersBetweenDates(); break;
                case 11: adminViewAllReviews(); break;
                case 12: adminViewProductReviews(); break;
                case 13: adminRemoveReview(); break;
                case 14: adminViewCustomerReviews(); break;
                case 15: adminViewTop3Products(); break;
                case 16: adminViewCommonProducts(); break;
                case 17: 
                    adminRunning = false;
                    System.out.println("\nLogging out...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
    
    private static void adminViewProducts() {
        System.out.println("\n=== ALL PRODUCTS ===");
        system.displayProducts();
    }
    
    private static void adminAddProduct() {
        System.out.println("\n=== ADD NEW PRODUCT ===");
        System.out.print("Product ID: ");
        String id = scanner.nextLine();
        
        if (system.searchProductById(id) != null) {
            System.out.println("Product with this ID already exists!");
            return;
        }
        
        System.out.print("Product Name: ");
        String name = scanner.nextLine();
        System.out.print("Price: ");
        double price = getDoubleInput();
        System.out.print("Stock: ");
        int stock = getIntInput();
        
        Product product = new Product(id, name, price, stock);
        system.addProduct(product);
        System.out.println("✓ Product added successfully!");
    }
    
    private static void adminUpdateProductPrice() {
        System.out.println("\n=== UPDATE PRODUCT PRICE ===");
        System.out.print("Enter Product ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter New Price: ");
        double price = getDoubleInput();
        system.updateProductPrice(id, price);
    }
    
    private static void adminUpdateProductStock() {
        System.out.println("\n=== UPDATE PRODUCT STOCK ===");
        System.out.print("Enter Product ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter New Stock: ");
        int stock = getIntInput();
        system.updateProductStock(id, stock);
    }
    
    private static void adminRemoveProduct() {
        System.out.println("\n=== REMOVE PRODUCT ===");
        System.out.print("Enter Product ID: ");
        String id = scanner.nextLine();
        system.removeProduct(id);
    }
    
    private static void adminViewCustomers() {
        System.out.println("\n=== ALL CUSTOMERS ===");
        system.displayCustomers();
    }
    
    private static void adminRegisterCustomer() {
        System.out.println("\n=== REGISTER NEW CUSTOMER ===");
        System.out.print("Customer ID: ");
        String id = scanner.nextLine();
        
        if (system.searchCustomerById(id) != null) {
            System.out.println("Customer with this ID already exists!");
            return;
        }
        
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        Customer customer = new Customer(id, name, email);
        system.registerCustomer(customer);
        System.out.println("✓ Customer registered successfully!");
    }
    
    private static void adminViewCustomerOrders() {
        System.out.println("\n=== VIEW CUSTOMER ORDERS ===");
        System.out.print("Enter Customer ID: ");
        String id = scanner.nextLine();
        Customer customer = system.searchCustomerById(id);
        
        if (customer == null) {
            System.out.println("Customer not found!");
        } else {
            System.out.println("\nOrders for " + customer.toString());
            customer.displayOrders();
        }
    }
    
    private static void adminViewOrders() {
        System.out.println("\n=== ALL ORDERS ===");
        system.displayOrders();
    }
    
    private static void adminViewOrdersBetweenDates() {
        System.out.println("\n=== ORDERS BETWEEN DATES ===");
        System.out.print("Enter Start Date (YYYY-MM-DD): ");
        String date1Str = scanner.nextLine();
        System.out.print("Enter End Date (YYYY-MM-DD): ");
        String date2Str = scanner.nextLine();
        
        try {
            LocalDate date1 = LocalDate.parse(date1Str);
            LocalDate date2 = LocalDate.parse(date2Str);
            LinkedList<Order> orders = system.ordersBetweenTwoDates(date1, date2);
            
            if (orders.empty()) {
                System.out.println("No orders found in this date range.");
            } else {
                orders.findFirst();
                for (int i = 0; i < orders.getSize(); i++) {
                    System.out.println(orders.retrieve());
                    orders.findNext();
                }
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format! Use YYYY-MM-DD");
        }
    }
    
    private static void adminViewAllReviews() {
        System.out.println("\n=== ALL REVIEWS ===");
        system.displayReviews();
    }
    
    private static void adminViewProductReviews() {
        System.out.println("\n=== PRODUCT REVIEWS ===");
        System.out.print("Enter Product ID: ");
        String id = scanner.nextLine();
        Product product = system.searchProductById(id);
        
        if (product == null) {
            System.out.println("Product not found!");
        } else {
            System.out.println("\nReviews for: " + product.getName());
            product.displayReviews();
        }
    }
    
    private static void adminRemoveReview() {
        System.out.println("\n=== REMOVE REVIEW ===");
        System.out.print("Enter Product ID: ");
        String productId = scanner.nextLine();
        System.out.print("Enter Review ID: ");
        String reviewId = scanner.nextLine();
        system.removeReviewFromProduct(reviewId, productId);
    }
    
    private static void adminViewCustomerReviews() {
        System.out.println("\n=== CUSTOMER REVIEWS ===");
        System.out.print("Enter Customer ID: ");
        String id = scanner.nextLine();
        LinkedList<Review> reviews = system.getCustomerReviews(id);
        
        if (reviews == null || reviews.empty()) {
            System.out.println("No reviews found for this customer.");
        } else {
            reviews.findFirst();
            for (int i = 0; i < reviews.getSize(); i++) {
                System.out.println(reviews.retrieve());
                reviews.findNext();
            }
        }
    }
    
    private static void adminViewTop3Products() {
        System.out.println("\n=== TOP 3 PRODUCTS ===");
        LinkedList<Product> top3 = system.top3Products();
        
        if (top3 != null && !top3.empty()) {
            top3.findFirst();
            for (int i = 0; i < top3.getSize(); i++) {
                System.out.println((i + 1) + ". " + top3.retrieve());
                top3.findNext();
            }
        }
    }
    
    private static void adminViewCommonProducts() {
        System.out.println("\n=== COMMON PRODUCTS BETWEEN CUSTOMERS ===");
        System.out.print("Enter First Customer ID: ");
        String id1 = scanner.nextLine();
        System.out.print("Enter Second Customer ID: ");
        String id2 = scanner.nextLine();
        
        LinkedList<Product> common = system.listOfCommonProducts(id1, id2);
        
        if (common == null || common.empty()) {
            System.out.println("No common products found.");
        } else {
            System.out.println("\nCommon products (rated >4 by both customers):");
            common.findFirst();
            for (int i = 0; i < common.getSize(); i++) {
                System.out.println(common.retrieve());
                common.findNext();
            }
        }
    }

    // ==================== CUSTOMER SECTION ====================
    
    private static void customerLogin() {
        System.out.println("\n=== CUSTOMER LOGIN ===");
        System.out.print("Enter Customer ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        
        Customer customer = system.searchCustomerById(id);
        
        if (customer != null && customer.toString().contains(email)) {
            System.out.println("\n✓ Login Successful! Welcome!");
            customerMenu(customer);
        } else {
            System.out.println("\n✗ Invalid credentials!");
        }
    }
    
    private static void customerMenu(Customer customer) {
        boolean customerRunning = true;
        while (customerRunning) {
            System.out.println("\n╔════════════════════════════════════════╗");
            System.out.println("║         CUSTOMER DASHBOARD             ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.println("=== My Account ===");
            System.out.println("1.  View My Information");
            System.out.println("\n=== Shopping ===");
            System.out.println("2.  Browse All Products");
            System.out.println("3.  View Product Details");
            System.out.println("4.  Search Product by ID");
            System.out.println("\n=== Order Management ===");
            System.out.println("5.  Create New Order");
            System.out.println("6.  View My Orders");
            System.out.println("7.  Add Product to Order");
            System.out.println("8.  Remove Product from Order");
            System.out.println("9.  Cancel Order");
            System.out.println("10. View Order Details");
            System.out.println("\n=== Review Management ===");
            System.out.println("11. View My Reviews");
            System.out.println("12. Write a Review");
            System.out.println("13. Edit My Review");
            System.out.println("14. Remove My Review");
            System.out.println("15. View Product Reviews");
            System.out.println("\n16. Logout");
            System.out.print("\nSelect option: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1: customerViewInfo(customer); break;
                case 2: customerBrowseProducts(); break;
                case 3: customerViewProductDetails(); break;
                case 4: customerSearchProduct(); break;
                case 5: customerCreateOrder(customer); break;
                case 6: customerViewOrders(customer); break;
                case 7: customerAddProductToOrder(customer); break;
                case 8: customerRemoveProductFromOrder(customer); break;
                case 9: customerCancelOrder(customer); break;
                case 10: customerViewOrderDetails(customer); break;
                case 11: customerViewMyReviews(customer); break;
                case 12: customerWriteReview(customer); break;
                case 13: customerEditReview(customer); break;
                case 14: customerRemoveReview(customer); break;
                case 15: customerViewProductReviews(); break;
                case 16:
                    customerRunning = false;
                    System.out.println("\nLogging out...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
    
    private static void customerViewInfo(Customer customer) {
        System.out.println("\n=== MY INFORMATION ===");
        System.out.println(customer.toString());
    }
    
    private static void customerBrowseProducts() {
        System.out.println("\n=== AVAILABLE PRODUCTS ===");
        system.displayProducts();
    }
    
    private static void customerViewProductDetails() {
        System.out.print("\nEnter Product ID: ");
        String id = scanner.nextLine();
        Product product = system.searchProductById(id);
        
        if (product == null) {
            System.out.println("Product not found!");
        } else {
            System.out.println("\n=== PRODUCT DETAILS ===");
            System.out.println(product);
            System.out.println("\nReviews:");
            product.displayReviews();
        }
    }
    
    private static void customerSearchProduct() {
        System.out.print("\nEnter Product ID to search: ");
        String id = scanner.nextLine();
        Product product = system.searchProductById(id);
        
        if (product == null) {
            System.out.println("Product not found!");
        } else {
            System.out.println("\n✓ Product found:");
            System.out.println(product);
        }
    }
    
    private static void customerCreateOrder(Customer customer) {
        System.out.println("\n=== CREATE NEW ORDER ===");
        System.out.print("Enter Order ID: ");
        String orderId = scanner.nextLine();
        
        Order order = new Order(orderId, customer.getId());
        
        boolean addingProducts = true;
        while (addingProducts) {
            System.out.print("\nEnter Product ID to add (or 'done' to finish): ");
            String productId = scanner.nextLine();
            
            if (productId.equalsIgnoreCase("done")) {
                addingProducts = false;
            } else {
                Product product = system.searchProductById(productId);
                if (product == null) {
                    System.out.println("Product not found!");
                } else {
                    if (order.addProduct(product)) {
                        System.out.println("✓ Product added to order!");
                    }
                }
            }
        }
        
        system.placeOrder(customer.getId(), order);
        System.out.println("\n✓ Order created successfully!");
    }
    
    private static void customerViewOrders(Customer customer) {
        System.out.println("\n=== MY ORDERS ===");
        customer.displayOrders();
    }
    
    private static void customerAddProductToOrder(Customer customer) {
        System.out.println("\n=== ADD PRODUCT TO ORDER ===");
        System.out.print("Enter Order ID: ");
        String orderId = scanner.nextLine();
        System.out.print("Enter Product ID: ");
        String productId = scanner.nextLine();
        
        system.addProductToOrder(productId, orderId);
        System.out.println("✓ Product added to order!");
    }
    
    private static void customerRemoveProductFromOrder(Customer customer) {
        System.out.println("\n=== REMOVE PRODUCT FROM ORDER ===");
        System.out.print("Enter Order ID: ");
        String orderId = scanner.nextLine();
        System.out.print("Enter Product ID: ");
        String productId = scanner.nextLine();
        
        system.removeProductFromOrder(productId, orderId);
    }
    
    private static void customerCancelOrder(Customer customer) {
        System.out.println("\n=== CANCEL ORDER ===");
        System.out.print("Enter Order ID: ");
        String orderId = scanner.nextLine();
        
        system.cancelOrder(orderId, customer.getId());
        System.out.println("✓ Order cancelled!");
    }
    
    private static void customerViewOrderDetails(Customer customer) {
        System.out.println("\n=== ORDER DETAILS ===");
        System.out.print("Enter Order ID: ");
        String orderId = scanner.nextLine();
        
        Order order = customer.searchOrderById(orderId);
        if (order == null) {
            System.out.println("Order not found!");
        } else {
            System.out.println(order);
            System.out.println("\nProducts in this order:");
            order.displayProducts();
        }
    }
    
    private static void customerViewMyReviews(Customer customer) {
        System.out.println("\n=== MY REVIEWS ===");
        LinkedList<Review> reviews = system.getCustomerReviews(customer.getId());
        
        if (reviews == null || reviews.empty()) {
            System.out.println("You haven't written any reviews yet.");
        } else {
            reviews.findFirst();
            for (int i = 0; i < reviews.getSize(); i++) {
                System.out.println(reviews.retrieve());
                reviews.findNext();
            }
        }
    }
    
    private static void customerWriteReview(Customer customer) {
        System.out.println("\n=== WRITE A REVIEW ===");
        System.out.print("Enter Product ID: ");
        String productId = scanner.nextLine();
        
        Product product = system.searchProductById(productId);
        if (product == null) {
            System.out.println("Product not found!");
            return;
        }
        
        System.out.print("Enter Review ID: ");
        String reviewId = scanner.nextLine();
        System.out.print("Rating (1-5): ");
        int rating = getIntInput();
        System.out.print("Comment: ");
        String comment = scanner.nextLine();
        
        Review review = new Review(reviewId, rating, comment, customer.getId());
        system.addReviewToProduct(review, productId);
        System.out.println("✓ Review added successfully!");
    }
    
    private static void customerEditReview(Customer customer) {
        System.out.println("\n=== EDIT MY REVIEW ===");
        System.out.print("Enter Product ID: ");
        String productId = scanner.nextLine();
        System.out.print("Enter Review ID: ");
        String reviewId = scanner.nextLine();
        
        Product product = system.searchProductById(productId);
        if (product == null) {
            System.out.println("Product not found!");
            return;
        }
        
        Review review = product.searchReviewById(reviewId);
        if (review == null || !review.getCustomerId().equals(customer.getId())) {
            System.out.println("Review not found or you don't have permission to edit it!");
            return;
        }
        
        System.out.print("New Rating (1-5): ");
        int rating = getIntInput();
        System.out.print("New Comment: ");
        String comment = scanner.nextLine();
        
        product.editReview(review, rating, comment);
        System.out.println("✓ Review updated successfully!");
    }
    
    private static void customerRemoveReview(Customer customer) {
        System.out.println("\n=== REMOVE MY REVIEW ===");
        System.out.print("Enter Product ID: ");
        String productId = scanner.nextLine();
        System.out.print("Enter Review ID: ");
        String reviewId = scanner.nextLine();
        
        Review review = system.searchReviewById(reviewId);
        if (review == null || !review.getCustomerId().equals(customer.getId())) {
            System.out.println("Review not found or you don't have permission to remove it!");
            return;
        }
        
        system.removeReviewFromProduct(reviewId, productId);
        System.out.println("✓ Review removed successfully!");
    }
    
    private static void customerViewProductReviews() {
        System.out.print("\nEnter Product ID: ");
        String id = scanner.nextLine();
        Product product = system.searchProductById(id);
        
        if (product == null) {
            System.out.println("Product not found!");
        } else {
            System.out.println("\n=== REVIEWS FOR: " + product.getName() + " ===");
            System.out.println("Average Rating: " + product.getAverageRating());
            product.displayReviews();
        }
    }

    // ==================== UTILITY METHODS ====================
    
    private static int getIntInput() {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Please enter a number: ");
            }
        }
    }
    
    private static double getDoubleInput() {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Please enter a valid number: ");
            }
        }
    }
}*/