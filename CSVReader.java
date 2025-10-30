
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class CSVReader{
    //create load method for every CSV file and gather them in one loadFile method
    public static void loadCustomersFromCSV(/*need to pass system and file name*/){
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

            //System.out.println(line);

            Customer customer = new Customer(customerId, name, email);
            //needs to add customer to list
            //system.registerCustomer(customer);

        }
        br.close();
        System.out.println("Customers loaded successfully from " /*+ filename */);
    }catch(FileNotFoundException e){
        e.printStackTrace();
    }catch(IOException e){
        e.printStackTrace();

    }
}

    public static void loadReviewsFromCSV(/*needs system and file name */){

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

            //Product product = searchProductById(productId);
            //if(product != null){
            // product.addReview(review);
            //}else{
            // product not found + productId
            //}
            
        }

        br.close();
        System.out.println("Reviews loaded successfully from " /*+ filename*/);
    }catch(FileNotFoundException e){
        e.printStackTrace();
    }catch(IOException e){
        e.printStackTrace();
    }

}

    public static void loadProductsFromCSV(/*system and filename */){


    String path = "dataset/prodcuts.csv";
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
            //system.addProduct(product);
        }

        br.close();
        System.out.println("Products loaded successfully from "/*+ filename */);
        
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }catch(IOException e){
        e.printStackTrace();

    }

}

    public static void loadOrdersFromCSV(/*system and filename */){

        String path="dataset/orders.csv";
        String line = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            br.readLine();
            while ((line = br.readLine()) != null){ 
                String[] values = line.split(",");
                String orderId = values[0];
                String customerId = values[1];
                String productIds = values[2];
                //maybe needs try and catch for numberformatexception
                double totalPrice = Double.parseDouble(values[3]);
                String orderDate = values[4];
                String status = values[5];

                Order order = new Order(orderId, customerId);
                //update status
                //order.updateOrderStatus(status);

                //add products in order
                //order.addProduct(product);

                //place order



            }
            br.close();
            System.out.println("Orders loaded successfully from"/* + filename */);
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }

    }
}