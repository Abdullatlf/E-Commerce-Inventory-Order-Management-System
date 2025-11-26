import java.time.LocalDate;

import javax.management.Query;

public class ECommerceSystem {

    private BST<Integer, Product> productList;
    private BST<String, Customer> customerList;
    private BST<Integer, Order> orderList;
    private LinkedList<Review> reviewList;

    public ECommerceSystem(){
        this.customerList = new BST<String,Customer>();
        this.productList = new BST<Integer,Product>();
        this.orderList = new BST<Integer,Order>();
        this.reviewList = new LinkedList<Review>();
    }

    //Product Operations ------------------------------------------------------------------------------

    public void addProduct(Product product){
        //check if product found
            productList.insert(product.getProductId(), product);
       // System.out.println("Product added :"+product+" successfully!");
    }
    public void addProductToOrder(int productId, int orderId){
        Product product = searchProductById(productId);
        Order order = searchOrderById(orderId);
        order.addProduct(product);
    }
    public void removeProductFromOrder(int productId, int orderId){
        Order order = searchOrderById(orderId);
        Product product = order.searchProductById(productId);
        if(product != null){
        order.removeProduct(product);
    }
    }
    public Product searchProductById(int productId){
        if(productList.findKey(productId))
            return productList.retrieve();
        System.out.println("Product not found");
        return null;
    }
    public void updateProductPrice(int productId, double newPrice){
        Product updatedProduct = searchProductById(productId);
        if(updatedProduct == null){
            System.out.println("Product not found!");
        }else{
            updatedProduct.setPrice(newPrice);
            productList.update(productId,updatedProduct);
            System.out.println("Product updated successfully!");
        }
    }

    public void updateProductStock(int productId, int newStock){
        Product updatedProduct = searchProductById(productId);
        if(updatedProduct == null){
            System.out.println("Product not found!");
        }else{
            updatedProduct.setStock(newStock);
            productList.update(productId,updatedProduct);
            System.out.println("Product updated successfully!");
        }
    }

    
    public void removeProduct(int productId){
        Product product = searchProductById(productId);
        if(product == null){
            System.out.println("Product not found!");
        }else{
            //it i want to remove product from all active orders i cannot make it O(logn) because 
            //i need to iterate all orders and remove from them
            /*orderList.findFirst();
            for(int i = 0; i < orderList.getSize(); i++){
                removeProductFromOrder(productId, orderList.retrieve().getId());
                orderList.findNext();
        }*/
            productList.remove_key(productId);
            System.out.println("Product removed successfully!");
        }
    }
    public void displayProducts(){
        if(productList.empty()){
            System.out.println("No product were added yet!");
        }else{
        productList.displayTree();
        }
    }

    //Customer Operations ------------------------------------------------------------------------------

    //Warning ===============
    //logic error in CSVReader Class because of String name and String customerId
    //probebly the problem is in loadOrder
    //also check every method in ECommerce from possible key error

    public Customer searchCustomerById(String customerId){
        if(customerList.findKey(customerId))
            return customerList.retrieve();
        System.out.println("Customer not found!");
        return null;
    }

    public void registerCustomer(Customer customer){
            customerList.insert(customer.getCustomerId(),customer);
    }

    public void displayCustomers(){
        if(customerList.empty()){
            System.out.println("No customer were register yet!");
        }else{
            customerList.displayTree();
        }
    }
    // testt============================
    public void displayCustomersTree(){
        if(customerList.empty()){
            System.out.println("No customer were register yet!");
        }else{
            customerList.printTree();
        }
    }
    


    //Order Operations ------------------------------------------------------------------------------


    public void addOrder(Order order){
        orderList.insert(order.getOrderId(),order);
    }

    public void displayOrders(){
        if(orderList.empty()){
            System.out.println("No orders were added yet!");
        }else{
            orderList.displayTree();
        }
    }
    //didn't use it yet
    public Order searchOrderById(int orderId){
        if(orderList.findKey(orderId))
            return orderList.retrieve();
        return null;
    }

    public void placeOrder(String name, Order order){
        Customer customer = searchCustomerById(name);
        if(customer == null){
            System.out.println("Customer not found!");
        }else{
            addOrder(order);
            customer.createOrder(order );
        }
    }

    public void cancelOrder(int orderId, String name){
        Customer customer = searchCustomerById(name);
        Order order = customer.searchOrderById(orderId);
        customer.cancel(order);
    }

    //Review Operations ------------------------------->

    public void addReview(Review review){
        reviewList.insertAtEnd(review);
    }

    public void addReviewToProduct(Review reviewId, int productId){
        Product product = searchProductById(productId);
        if(product == null)
            System.out.println("Product not found!");
        else{
        addReview(reviewId);
        product.createReview(reviewId);
        }
    }
    public void removeReviewFromProduct(String reviewId, int productId){
        if(productList.empty()){
            System.out.println("Product not found!");
        }else{
            Product product = searchProductById(productId);
            if(product != null){
                Review review = product.searchReviewById(reviewId);
            
                 if(review != null){
                product.removeReview(review);
                removeReview(reviewId);
                 }
            }
        }
    }
    public void removeReview(String reviewId){
        Review review = searchReviewById(reviewId);
        
        if(review == null){
            System.out.println("Review not found!");
        }else{
            reviewList.remove();
        }
    }
    
    public Review searchReviewById(String reviewId){
        reviewList.findFirst();
        while (!reviewList.last()) {
            if(reviewList.retrieve().getId().equals(reviewId))
                return reviewList.retrieve();
            reviewList.findNext();
        }if(reviewList.retrieve().getId().equals(reviewId))
                return reviewList.retrieve();
        return null;
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

    public BST<Integer, Product> rangeQueryByPrice(double minPrice, double maxPrice){
        BST<Integer, Product> result = new BST<Integer, Product>();
        
        if(productList.empty()){
            System.out.println("No products available!");
            return result;
        }
        
        rangeQueryHelper(productList.getRoot(), minPrice, maxPrice, result);
        return result;
    }
    
    private void rangeQueryHelper(BSTNode<Integer, Product> node, double minPrice, double maxPrice, BST<Integer, Product> result){
        if(node == null)
            return;
        
        rangeQueryHelper(node.left, minPrice, maxPrice, result);
        
        Product product = node.data;
        if(product.getPrice() >= minPrice && product.getPrice() <= maxPrice){
            result.insert(product.getProductId(), product);
        }
        
        rangeQueryHelper(node.right, minPrice, maxPrice, result);
    }

    public BST<Integer,Order> getCustomerOrderHistory(String customerId){
        Customer customer = searchCustomerById(customerId);
        
        if(customer == null){
            System.out.println("Customer not found!");
            return new BST<Integer,Order>();
        }
        
        return customer.getOrderList();
    }

    /*public LinkedList<Review> getCustomerReviews(String customerId){
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

    
    public LinkedList<Product> listOfCommonProducts(String customerId1, String customerId2){
        if(productList.empty()){
            System.out.println("no Product were added yet!");
            return null;
        }
        if(reviewList.empty()){
            System.out.println("no Reviews were added yet!");
            return null;
        }
        LinkedList<Product> commonProducts = new LinkedList<Product>();
                productList.findFirst();

                for(int i = 0; i < productList.getSize(); i++){
                    Product currentProduct = productList.retrieve();
    
                    if(currentProduct.getAverageRating() > 4){
                        boolean reviewedByCustomer1 = false;
                        boolean reviewedByCustomer2 = false;
        
                        LinkedList<Review> productReviews = currentProduct.getReviewList();
        
                            if(productReviews != null && !productReviews.empty()){
                                productReviews.findFirst();
            
                                for(int j = 0; j < productReviews.getSize(); j++){
                                    Review currentReview = productReviews.retrieve();
                
                                    if(currentReview.getCustomerId().equals(customerId1))
                                         reviewedByCustomer1 = true;
                                    if(currentReview.getCustomerId().equals(customerId2))
                                         reviewedByCustomer2 = true;
                
                productReviews.findNext(); 
            }
        }
        
        if(reviewedByCustomer1 && reviewedByCustomer2)
            commonProducts.insertAtEnd(currentProduct);
    }
    productList.findNext(); 
}
return commonProducts;
    }*/


    




}