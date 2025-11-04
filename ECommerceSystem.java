
import java.time.LocalDate;

public class ECommerceSystem {

    private LinkedList<Product> productList;
    private LinkedList<Customer> customerList;
    private LinkedList<Order> orderList;
    private LinkedList<Review> reviewList;

    public ECommerceSystem(){
        this.customerList = new LinkedList<Customer>();
        this.productList = new LinkedList<Product>();
        this.orderList = new LinkedList<Order>();
        this.reviewList = new LinkedList<Review>();
    }

    //Product Operations ------------------------------------------------------------------------------

    public void addProduct(Product product){
        //check if product found
            productList.insertAtEnd(product);
       // System.out.println("Product added :"+product+" successfully!");
    }
    public Product searchProductById(String productId){
        Node<Product> p = productList.getCurrent();
        productList.findFirst();
        while (!productList.last()) {
            if(productList.retrieve().getId().equals(productId))
                return productList.retrieve();
            productList.findNext();
        }if(productList.retrieve().getId().equals(productId))
                return productList.retrieve();
        return null;
    }
    public void updateProductPrice(Product product, double newPrice){
        if(searchProductById(product.getId()) == null){
            System.out.println("Product not found!");
        }else{
            Product updatedProduct = new Product(product.getId(), product.getName(), newPrice, product.getStock());
            productList.update(updatedProduct);
            System.out.println("Product updated successfully!");
        }
    }

    public void updateProductStock(Product product, int newStock){
        if(searchProductById(product.getId()) == null){
            System.out.println("Product not found!");
        }else{
            Product updatedProduct = new Product(product.getId(), product.getName(), product.getPrice(), newStock);
            productList.update(updatedProduct);
            System.out.println("Product updated successfully!");
        }
    }

    
    public void removeProduct(Product product){
        if(searchProductById(product.getId()) == null){
            System.out.println("Product not found!");
        }else{
            productList.remove();
            System.out.println("Product removed successfully!");
        }
    }
    public void displayProducts(){
        if(productList.empty()){
            System.out.println("No product were added yet!");
        }else{
        productList.findFirst();
        for(int i = 0; i < productList.getSize(); i++){
            System.out.println(i);
            System.out.println(productList.retrieve());
            productList.findNext();
        }
    }
    }

    //Customer Operations ------------------------------------------------------------------------------


    public Customer searchCustomerById(String customerId){
        if(customerList.empty()){
            System.out.println("Customer not found!");
            return null;
        }
        customerList.findFirst();
        while(!customerList.last()){
            if(customerList.retrieve().getId().equals(customerId))
                return customerList.retrieve();
            customerList.findNext();
        }if(customerList.retrieve().getId().equals(customerId))
                return customerList.retrieve();
        return null;
    }

    public void registerCustomer(Customer customer){
            customerList.insert(customer);
    }

    public void displayCustomers(){
        if(customerList.empty()){
            System.out.println("No customer were register yet!");
        }else{
        customerList.findFirst();
        for(int i = 0; i < customerList.getSize(); i++){
            System.out.println(customerList.retrieve()+"\n");
            customerList.findNext();
        }
    }
    }


    //Order Operations ------------------------------------------------------------------------------


    public void addOrder(Order order){
        orderList.insert(order);
    }


    public void placeOrder(){

    }

    public void displayOrders(){
        if(orderList.empty()){
            System.out.println("No orders were added yet!");
        }else{
            orderList.findFirst();
            for(int i = 0; i < orderList.getSize(); i++){
                System.out.println(orderList.retrieve()+"\n");
                orderList.findNext();
            }
        }
    }
    //didn't use it yet
    public Order searchOrderById(String Order){
        orderList.findFirst();
        while(!orderList.last()){
            if(orderList.retrieve().getId().equals(Order))
                return orderList.retrieve();
            orderList.findNext();
        }if(orderList.retrieve().getId().equals(Order))
            return orderList.retrieve();
        return null;
    }

    //Review Operations ------------------------------->

    public void addReview(Review review){
        reviewList.insertAtEnd(review);
    }
    public void displayReviews(){
        if(reviewList.empty()){
            System.out.println("No review were added yet!");
        }else{
            reviewList.findFirst();
            for(int i = 0;i<reviewList.getSize();i++){
                System.out.println("\n"+reviewList.retrieve()+"\n");
                reviewList.findNext();
            }
        }
    }


    // queries --------------------------------------------------------------->

    public LinkedList<Review> getCustomerReviews(String customerId){
        LinkedList<Review> customerReviewsList = new LinkedList<Review>();
        if(searchCustomerById(customerId) == null){
            System.out.println("Customer not found!");
            return null;
        }else{
            reviewList.findFirst();
            while(!reviewList.last()){
                if(reviewList.retrieve().getCustomerId().equals(customerId))
                    customerReviewsList.insertAtEnd(reviewList.retrieve());
                reviewList.findNext();
            }if(reviewList.retrieve().getCustomerId().equals(customerId))
                customerReviewsList.insertAtEnd(reviewList.retrieve());
        }
        return customerReviewsList;
    }

    public LinkedList<Product> top3Products(){
        if(productList.empty())
            System.out.println("No product were added!");
        
        LinkedList<Product> top3Products = new LinkedList<Product>();
        productList.findFirst();
        Product top1 = productList.retrieve();
        for(int i = 0 ; i < productList.getSize() ; i++){
            if(productList.retrieve().getAverageRating()>= top1.getAverageRating() && productList.retrieve().getStock() < top1.getStock())
                top1 = productList.retrieve();
                productList.findNext();
        }
        top3Products.insertAtEnd(top1);
        productList.findFirst();
        Product top2 = productList.retrieve();
        for(int i = 0; i < productList.getSize() ; i++){
            if(!productList.retrieve().getId().equals(top1.getId())){
                if(productList.retrieve().getAverageRating()>= top2.getAverageRating() && productList.retrieve().getStock() < top2.getStock())
                    top2 = productList.retrieve();
            }
            productList.findNext();
        }
        top3Products.insertAtEnd(top2);
        productList.findFirst();
        Product top3 = productList.retrieve();
        for(int i = 0; i < productList.getSize() ; i++){
            if(!(productList.retrieve().getId().equals(top2.getId()) || productList.retrieve().getId().equals(top1.getId()))){
                if(productList.retrieve().getAverageRating()>= top3.getAverageRating() && productList.retrieve().getStock() < top3.getStock())
                    top3 = productList.retrieve();
            }
            productList.findNext();
        }
        top3Products.insertAtEnd(top3);

        return top3Products;
    }

    public LinkedList<Order> ordersBetweenTwoDates(LocalDate date1, LocalDate date2){
        if(orderList.empty())
            System.out.println("No order were added yet!");
        LinkedList<Order> ordersBetweenDates = new LinkedList<Order>();
        orderList.findFirst();
        for(int i = 0 ; i < orderList.getSize() ; i++){
            if((orderList.retrieve().getOrderDate().isEqual(date1) || orderList.retrieve().getOrderDate().isAfter(date1)) && (orderList.retrieve().getOrderDate().isEqual(date2) || orderList.retrieve().getOrderDate().isBefore(date2)))
                ordersBetweenDates.insertAtEnd(orderList.retrieve());
            orderList.findNext();
        }
        return ordersBetweenDates;    
    }

    /* 
    // not finished yet
    public LinkedList<Product> listOfCommonProducts(String customerId1, String CustomerId2){
        if(searchCustomerById(customerId1) == null || searchCustomerById(CustomerId2) == null)
            System.out.println("Customer not found!");
        LinkedList<Product> commonProduct = new LinkedList<Product>();
        reviewList.findFirst();
        

    }*/


    




}