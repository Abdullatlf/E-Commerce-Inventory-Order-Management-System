import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;


public class CSVReader{
    
    public static void loadAllData(ECommerceSystem system){

        loadProductsFromCSV(system);
        loadCustomersFromCSV(system);
        loadReviewsFromCSV(system);
        loadOrdersFromCSV(system);        
    }
    
    private static ArrayList<String> parseCSVLine(String line) {
        int commaCount = 1;
        boolean inQuotes = false;
        
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                commaCount++;
            }
        }
        
        ArrayList<String> values = new ArrayList<>(commaCount);
        StringBuilder currentValue = new StringBuilder();
        inQuotes = false;
        
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                values.insert(currentValue.toString());
                currentValue = new StringBuilder();
            } else {
                currentValue.append(c);
            }
        }
        values.insert(currentValue.toString());
        
        return values;
    }
    
    public static void loadCustomersFromCSV(ECommerceSystem system){
        String path = "dataset/customers.csv";
        String line = "";
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            br.readLine();
            while ((line = br.readLine()) != null) { 
                ArrayList<String> values = parseCSVLine(line);
                
                values.findFirst();
                String customerId = values.retrieve();
                values.findNext();
                String name = values.retrieve();
                values.findNext();
                String email = values.retrieve();                
                Customer customer = new Customer(customerId, name, email);
                system.registerCustomer(customer);
            }
            br.close();
            System.out.println("Customers loaded successfully from customers.csv");
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void loadReviewsFromCSV(ECommerceSystem system){
        String path ="dataset/reviews.csv";
        String line ="";
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            br.readLine();
            while((line = br.readLine()) != null){
                ArrayList<String> values = parseCSVLine(line);
                
                values.findFirst();
                String reviewId = values.retrieve();
                values.findNext();
                int productId = Integer.parseInt(values.retrieve());
                values.findNext();
                String customerId = values.retrieve();
                values.findNext();
                int rating = Integer.parseInt(values.retrieve());
                values.findNext();
                String comment = values.retrieve();

                //Customer customer = system.searchCustomerById(customerId);
                
                Review review = new Review(reviewId, rating, comment, customerId);
                system.addReview(review);
                Product product = system.searchProductById(productId);
                if (product != null) {
                    product.createReview(review);
                }
            }
            br.close();
            System.out.println("Reviews loaded successfully from reviews.csv");
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

public static void loadProductsFromCSV(ECommerceSystem system){
        String path = "dataset/products.csv";
        String line ="";

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            br.readLine();
            while ((line = br.readLine()) != null) { 
                ArrayList<String> values = parseCSVLine(line);
                
                values.findFirst();
                int productId = Integer.parseInt(values.retrieve());
                values.findNext();
                String name = values.retrieve();
                values.findNext();
                double price = Double.parseDouble(values.retrieve());
                values.findNext();
                int stock = Integer.parseInt(values.retrieve());
                
                Product product = new Product(productId, name, price, stock);
                system.addProduct(product);
            }
            
            br.close();
            System.out.println("Products loaded successfully from products.csv");
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void loadOrdersFromCSV(ECommerceSystem system){
        String path="dataset/orders.csv";
        String line = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            br.readLine();
            while ((line = br.readLine()) != null){ 
                ArrayList<String> values = parseCSVLine(line);
                
                values.findFirst();
                int orderId = Integer.parseInt(values.retrieve());
                values.findNext();
                String customerId = values.retrieve();
                values.findNext();
                String productIds = values.retrieve().replace("\"", "");
                values.findNext();
                values.findNext();
                LocalDate orderDate = LocalDate.parse(values.retrieve());
                values.findNext();
                String status = values.retrieve();

                Order order = new Order(orderId, customerId);
                system.placeOrder(customerId, order);
                order.setOrderDate(orderDate);

                // Count semicolons to determine size
                int count = 1;
                for (int i = 0; i < productIds.length(); i++) {
                    if (productIds.charAt(i) == ';') {
                        count++;
                    }
                }
                
                ArrayList<String> productIdList = new ArrayList<>(count);
                
                int start = 0;
                for (int i = 0; i <= productIds.length(); i++) {
                    if (i == productIds.length() || productIds.charAt(i) == ';') {
                        String productId = productIds.substring(start, i);
                        productIdList.insert(productId);
                        start = i + 1;
                    }
                }

                productIdList.findFirst();
                while (true) {
                    int productId = Integer.parseInt(productIdList.retrieve());
                    Product product = system.searchProductById(productId);
                    order.addProduct(product);
                    
                    if (productIdList.last()) {
                        break;
                    }
                    productIdList.findNext();
                }
                
                order.updateOrderStatus(status);
            }
            br.close();
            System.out.println("Orders loaded successfully from orders.csv");
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}