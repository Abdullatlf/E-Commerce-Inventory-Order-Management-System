import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;


public class CSVReader{
    //create load method for every CSV file and gather them in one loadFile method
    public static void loadAllData(ECommerceSystem system){

        loadProductsFromCSV(system);
        loadCustomersFromCSV(system);
        loadReviewsFromCSV(system);
        loadOrdersFromCSV(system);        
    }
    public static void loadCustomersFromCSV(ECommerceSystem system){
    String path = "dataset/customers.csv";
    String line = "";
    try{
    BufferedReader br = new BufferedReader(new FileReader(path));
        br.readLine();
        while ((line = br.readLine()) != null) { 
            String[] values = line.split(",");
            String customerId = values[0];
            String name = values[1];
            String email = values[2];
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
            String[] values = line.split(",");
            String reviewId = values[0];
            String productId = values[1];
            String customerId = values[2];
            //maybe it needs try and catch for numberformatexception
            int rating = Integer.parseInt(values[3]);
            String comment = values[4];
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
            String[] values = line.split(",");
            String productId = values[0];
            String name = values[1];
            //maybe need try and catch for numberformatexception
            double price = Double.parseDouble(values[2]);
            int stock = Integer.parseInt(values[3]);
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
                String[] values = line.split(",");
                String orderId = values[0];
                String customerId = values[1];
                String productIds = values[2].replace("\"", "");
                //maybe needs try and catch for numberformatexception
                //double totalPrice = Double.parseDouble(values[3]);
                LocalDate orderDate = LocalDate.parse(values[4]);
                String status = values[5];

                Customer customer = system.searchCustomerById(customerId);
                Order order = new Order(orderId, customerId);
                system.addOrder(order);
                customer.createOrder(order);
                order.setOrderDate(orderDate);

                String []productId = productIds.split(";");

                for (int i = 0; i < productId.length ;i++) {
                    Product product = system.searchProductById(productId[i]);
                    order.addProduct(product);
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