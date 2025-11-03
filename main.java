import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class main {

    static LinkedList<Product> products = new LinkedList<Product>();
    static LinkedList<Customer> customers = new LinkedList<Customer>();
    static LinkedList<Order> orders = new LinkedList<Order>();

    static final String PRODUCTS_CSV = "dataset/products.csv";
    static final String CUSTOMERS_CSV = "dataset/customers.csv";
    static final String ORDERS_CSV = "dataset/orders.csv";
    static final String REVIEWS_CSV = "dataset/reviews.csv";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int mainChoice = -1;
        while (mainChoice != 0) {
            println("");
            println("===== E-Commerce System =====");
            println("1) Admin");
            println("2) Customer");
            println("0) Exit");
            print("Choice: ");
            if (!in.hasNextInt()) { in.nextLine(); continue; }
            mainChoice = in.nextInt(); in.nextLine();
            if (mainChoice == 1) adminMenu(in);
            else if (mainChoice == 2) customerMenu(in);
            else if (mainChoice == 0) println("Goodbye!");
        }
        in.close();
    }

    static void adminMenu(Scanner in){
        int c = -1;
        while (c != 0) {
            println("");
            println("=== Admin Menu ===");
            println("1) Add product");
            println("2) Add customer");
            println("3) Load dataset (CSV)");
            println("4) View all products");
            println("5) View all customers");
            println("6) View all orders");
            println("0) Back");
            print("Choice: ");
            if (!in.hasNextInt()) { in.nextLine(); continue; }
            c = in.nextInt(); in.nextLine();

            if (c == 1) {
                print("Product ID: "); String pid = in.nextLine();
                print("Name: "); String name = in.nextLine();
                print("Price: "); double price = readDouble(in);
                print("Stock: "); int stock = readInt(in);
                tailInsert(products, new Product(pid, name, price, stock));
                println("added");
            } else if (c == 2) {
                print("Customer ID: "); String cid = in.nextLine();
                print("Name: "); String name = in.nextLine();
                print("Email: "); String email = in.nextLine();
                tailInsert(customers, new Customer(cid, name, email));
                println("added");
            } else if (c == 3) {
                loadProductsFromCSV(PRODUCTS_CSV);
                loadCustomersFromCSV(CUSTOMERS_CSV);
                loadOrdersFromCSV(ORDERS_CSV);
                loadReviewsFromCSV(REVIEWS_CSV);
                println("loaded");
            } else if (c == 4) {
                listProductsPretty();
            } else if (c == 5) {
                listCustomersPretty();
            } else if (c == 6) {
                listOrdersPretty();
            }
        }
    }

    static void customerMenu(Scanner in){
        print("\nEnter your Customer ID: ");
        String cid = in.nextLine();
        Customer current = findCustomerById(cid);
        if (current == null) { println("not found"); return; }

        int c = -1;
        while (c != 0) {
            println("");
            println("=== Customer Menu ===");
            println("1) Create order");
            println("2) Add product to order");
            println("3) Remove product from order");
            println("4) View my orders");
            println("5) Add review");
            println("0) Back");
            print("Choice: ");
            if (!in.hasNextInt()) { in.nextLine(); continue; }
            c = in.nextInt(); in.nextLine();

            if (c == 1) {
                print("Order ID: "); String oid = in.nextLine();
                Order o = new Order(oid, current.getId());
                current.createOrder(o);
                tailInsert(orders, o);
                println("created");
            } else if (c == 2) {
                listOrdersMini();
                listProductsMini();
                print("Order ID: "); String oid = in.nextLine();
                print("Product ID: "); String pid = in.nextLine();
                Order o = findOrderById(oid);
                Product p = findProductById(pid);
                if (o == null) println("order ?");
                else if (p == null) println("product ?");
                else if (o.addProduct(p)) println("done");
                else println("cant");
            } else if (c == 3) {
                listOrdersMini();
                print("Order ID: "); String oid = in.nextLine();
                Order o = findOrderById(oid);
                if (o == null) println("order ?");
                else {
                    println("-- items --");
                    o.displayProducts();
                    print("Product ID: "); String pid = in.nextLine();
                    if (o.removeProduct(pid)) println("done");
                    else println("cant");
                }
            } else if (c == 4) {
                current.displayOrders();
            } else if (c == 5) {
                listProductsMini();
                print("Product ID: "); String pid = in.nextLine();
                print("Review ID: "); String rid = in.nextLine();
                print("Rating (1-5): "); int rating = readInt(in);
                print("Comment: "); String comment = in.nextLine();
                if (rating < 1 || rating > 5) println("rating ?");
                else {
                    Product p = findProductById(pid);
                    if (p == null) println("product ?");
                    else {
                        Review r = new Review(rid, rating, comment, current.getId());
                        if (p.addReview(r)) println("done");
                        else println("cant");
                    }
                }
            }
        }
    }

    static void loadProductsFromCSV(String path){
        BufferedReader br = null; String line; int count = 0;
        try {
            br = new BufferedReader(new FileReader(path));
            br.readLine();
            while ((line = br.readLine()) != null){
                String[] v = line.split(",");
                if (v.length < 4) continue;
                String id = v[0];
                String name = v[1];
                double price = parseDoubleSafe(v[2], 0.0);
                int stock = parseIntSafe(v[3], 0);
                tailInsert(products, new Product(id, name, price, stock));
                count++;
            }
            println("Loaded products: " + count);
            br.close();
        } catch (FileNotFoundException e){ println("Missing: " + path);
        } catch (IOException e){ println("IO error: " + path); }
    }

    static void loadCustomersFromCSV(String path){
        BufferedReader br = null; String line; int count = 0;
        try {
            br = new BufferedReader(new FileReader(path));
            br.readLine();
            while ((line = br.readLine()) != null){
                String[] v = line.split(",");
                if (v.length < 3) continue;
                String id = v[0];
                String name = v[1];
                String email = v[2];
                tailInsert(customers, new Customer(id, name, email));
                count++;
            }
            println("Loaded customers: " + count);
            br.close();
        } catch (FileNotFoundException e){ println("Missing: " + path);
        } catch (IOException e){ println("IO error: " + path); }
    }

    static void loadOrdersFromCSV(String path) {
        BufferedReader br = null; String line; int count = 0;
        try {
            br = new BufferedReader(new FileReader(path));
            br.readLine();
            while ((line = br.readLine()) != null){
                String[] v = line.split(",");
                if (v.length < 6) continue;
                String orderId = v[0];
                String customerId = v[1];
                String productIds = v[2];
                String status = v[5];

                Customer c = findCustomerById(customerId);
                if (c == null) continue;

                Order o = new Order(orderId, customerId);
                o.updateOrderStatus(status);
                tailInsert(orders, o);
                c.createOrder(o);

                String[] ids = productIds.split("\\|");
                for (int i = 0; i < ids.length; i++){
                    Product p = findProductById(ids[i]);
                    if (p != null) o.addProduct(p);
                }
                count++;
            }
            println("Loaded orders: " + count);
            br.close();
        } catch (FileNotFoundException e){ println("Missing: " + path);
        } catch (IOException e){ println("IO error: " + path); }
    }

    static void loadReviewsFromCSV(String path){
        BufferedReader br = null; String line; int count = 0;
        try {
            br = new BufferedReader(new FileReader(path));
            br.readLine();
            while ((line = br.readLine()) != null){
                String[] v = line.split(",");
                if (v.length < 5) continue;
                String reviewId  = v[0];
                String productId = v[1];
                String customerId= v[2];
                int rating       = parseIntSafe(v[3], 0);
                String comment   = v[4];
                if (rating < 1 || rating > 5) continue;
                Product p = findProductById(productId);
                if (p == null) continue;
                p.addReview(new Review(reviewId, rating, comment, customerId));
                count++;
            }
            println("Loaded reviews: " + count);
            br.close();
        } catch (FileNotFoundException e){ println("Missing: " + path);
        } catch (IOException e){ println("IO error: " + path); }
    }

    static void listProductsMini() {
        println("\nProducts:");
        if (products.empty()) { println("  (none)"); return; }
        products.findFirst();
        while (true) {
            Product p = products.retrieve();
            println("  - " + p.getId() + "  |  " + p.getName());
            if (products.last()) break;
            products.findNext();
        }
    }

    static void listCustomersMini() {
        println("\nCustomers:");
        if (customers.empty()) { println("  (none)"); return; }
        customers.findFirst();
        while (true) {
            Customer c = customers.retrieve();
            println("  - " + c.getId());
            if (customers.last()) break;
            customers.findNext();
        }
    }

    static void listOrdersMini() {
        println("\nOrders:");
        if (orders.empty()) { println("  (none)"); return; }
        orders.findFirst();
        while (true) {
            Order o = orders.retrieve();
            println("  - " + o.getId());
            if (orders.last()) break;
            orders.findNext();
        }
    }

    static void listProductsPretty() {
        println("\n=== Products ===");
        if (products.empty()) { println("(none)"); return; }
        products.findFirst();
        while (true) {
            Product p = products.retrieve();
            println(p.getId() + "  -  " + p.getName() + "  -  " + p.getPrice());
            if (products.last()) break;
            products.findNext();
        }
    }

    static void listCustomersPretty() {
        println("\n=== Customers ===");
        if (customers.empty()) { println("(none)"); return; }
        customers.findFirst();
        while (true) {
            Customer c = customers.retrieve();
            println(c.getId() + "  -  " + c.toString());
            if (customers.last()) break;
            customers.findNext();
        }
    }

    static void listOrdersPretty() {
        println("\n=== Orders ===");
        if (orders.empty()) { println("(none)"); return; }
        orders.findFirst();
        while (true) {
            Order o = orders.retrieve();
            println(o.getId() + "  -  " + o.toString());
            if (orders.last()) break;
            orders.findNext();
        }
    }

    static <T> void tailInsert(LinkedList<T> list, T val) {
        if (list.empty()) { list.insert(val); return; }
        list.findFirst();
        while (!list.last()) list.findNext();
        list.insert(val);
    }

    static Product findProductById(String id) {
        if (products.empty()) return null;
        products.findFirst();
        while (true) {
            Product p = products.retrieve();
            if (p.getId().equals(id)) return p;
            if (products.last()) break;
            products.findNext();
        }
        return null;
    }

    static Customer findCustomerById(String id) {
        if (customers.empty()) return null;
        customers.findFirst();
        while (true) {
            Customer c = customers.retrieve();
            if (c.getId().equals(id)) return c;
            if (customers.last()) break;
            customers.findNext();
        }
        return null;
    }

    static Order findOrderById(String id) {
        if (orders.empty()) return null;
        orders.findFirst();
        while (true) {
            Order o = orders.retrieve();
            if (o.getId().equals(id)) return o;
            if (orders.last()) break;
            orders.findNext();
        }
        return null;
    }

    static int readInt(Scanner in) {
        while (!in.hasNextInt()) { in.nextLine(); print("Enter a number: "); }
        int v = in.nextInt(); in.nextLine(); return v;
    }

    static double readDouble(Scanner in) {
        while (!in.hasNextDouble()) { in.nextLine(); print("Enter a number: "); }
        double v = in.nextDouble(); in.nextLine(); return v;
    }

    static int parseIntSafe(String s, int fb){
        try { return Integer.parseInt(s); } catch(Exception e){ return fb; }
    }
    static double parseDoubleSafe(String s, double fb){
        try { return Double.parseDouble(s); } catch(Exception e){ return fb; }
    }

    static void println(String s){ System.out.println(s); }
    static void print(String s){ System.out.print(s); }
}