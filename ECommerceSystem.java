import java.time.LocalDate;

public class ECommerceSystem {

    private BST<Integer, Product> productList;
    private BST<String, Customer> customersById;
    private BST<String, Customer> customersByName; 
    private BST<Integer, Order> orderList;
    private LinkedList<Review> reviewList;

    public ECommerceSystem() {
        this.customersById   = new BST<String,Customer>();
        this.customersByName = new BST<String,Customer>();
        this.productList     = new BST<Integer,Product>();
        this.orderList       = new BST<Integer,Order>();
        this.reviewList      = new LinkedList<Review>();
    }

    // ===================== Product Operations =====================

    public void addProduct(Product product){
        productList.insert(product.getProductId(), product);
        //System.out.println("Product added: " + product);
    }

    //customer is the one who add order

    /*
    public void addProductToOrder(int productId, String customerId){
        Product product = searchProductById(productId);
        Customer customer = searchCustomerById(customerId);
        Order order = searchOrderById(orderId);
        if (product != null && order != null)
            order.addProduct(product);
    }
    
    */

    public void addProductToOrder(int productId, int orderId){
        Product product = searchProductById(productId);
        Order order = searchOrderById(orderId);
        if (product != null && order != null)
            order.addProduct(product);
    }

    public void removeProductFromOrder(int productId, int orderId){
        Order order = searchOrderById(orderId);
        if (order == null) return;

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
            System.out.println("Product not found");
        }else{
            updatedProduct.setPrice(newPrice);
            productList.update(productId,updatedProduct);
            System.out.println("Product updated successfully");
        }
    }

    public void updateProductStock(int productId, int newStock){
        Product updatedProduct = searchProductById(productId);
        if(updatedProduct == null){
            System.out.println("Product not found");
        }else{
            updatedProduct.setStock(newStock);
            productList.update(productId,updatedProduct);
            System.out.println("Product updated successfully");
        }
    }

    public void removeProduct(int productId){
        Product product = searchProductById(productId);
        if(product == null){
            System.out.println("Product not found");
        }else{
            productList.remove_key(productId);
            System.out.println("Product removed successfully");
        }
    }

    public void displayProducts(){
        if(productList.empty()){
            System.out.println("No products were added yet");
        }else{
            productList.displayTree();
        }
    }

    // ===================== Customer Operations =====================

    public Customer searchCustomerById(String customerId){
        if(customersById.findKey(customerId))
            return customersById.retrieve();
        System.out.println("Customer not found");
        return null;
    }

    public Customer searchCustomerByName(String name){
        if(customersByName.findKey(name))
            return customersByName.retrieve();
        System.out.println("Customer not found");
        return null;
    }

    public void registerCustomer(Customer customer){
        customersById.insert(customer.getCustomerId(), customer);
        customersByName.insert(customer.getName(), customer);
    }

    public void displayCustomers(){
        if(customersByName.empty()){
            System.out.println("No customers were registered yet");
        }else{
            customersByName.displayTree();
        }
    }

    public void displayCustomersTree(){
        if(customersByName.empty()){
            System.out.println("No customers were registered yet");
        }else{
            customersByName.displayTree();
        }
    }

    // ===================== Order Operations =====================

    public void addOrder(Order order){
        orderList.insert(order.getOrderId(),order);
    }

    public void displayOrders(){
        if(orderList.empty()){
            System.out.println("No orders were added yet");
        }else{
            orderList.displayTree();
        }
    }

    public Order searchOrderById(int orderId){
        if(orderList.findKey(orderId))
            return orderList.retrieve();
        return null;
    }

    public void placeOrder(String customerId, Order order){
        Customer customer = searchCustomerById(customerId);
        if(customer == null){
            System.out.println("Customer not found");
        }else{
            addOrder(order);
            customer.createOrder(order);
        }
    }

    public void cancelOrder(int orderId, String customerId){
        Customer customer = searchCustomerById(customerId);
        if (customer == null) return;

        Order order = customer.searchOrderById(orderId);
        if (order != null)
            customer.cancel(order);
    }

    // ===================== Review Operations =====================

    public void addReview(Review review){
        reviewList.insertAtEnd(review);
    }

    public void addReviewToProduct(Review review, int productId){
        Product product = searchProductById(productId);
        if(product == null){
            System.out.println("Product not found");
        }else{
            addReview(review);
            product.createReview(review);
        }
    }

    public void removeReviewFromProduct(String reviewId, int productId){
        if(productList.empty()){
            System.out.println("Product not found");
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
            System.out.println("Review not found");
        }else{
            reviewList.remove();
        }
    }

    public Review searchReviewById(String reviewId){
        if (reviewList.empty())
            return null;

        reviewList.findFirst();
        while (!reviewList.last()) {
            if (reviewList.retrieve().getId().equals(reviewId))
                return reviewList.retrieve();
            reviewList.findNext();
        }
        if (reviewList.retrieve().getId().equals(reviewId))
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

    public BST<Integer, Product> rangeQueryByPrice(double minPrice, double maxPrice){
        BST<Integer, Product> result = new BST<Integer, Product>();

        if(productList.empty()){
            System.out.println("No products available");
            return result;
        }

        rangeRec(productList.getRoot(), minPrice, maxPrice, result);

        return result;
    }

    private void rangeRec(BSTNode<Integer,Product> root,
                          double minPrice,
                          double maxPrice,
                          BST<Integer,Product> result){

        if (root == null) return; 

        rangeRec(root.left, minPrice, maxPrice, result);

        Product p = root.data;
        double price = p.getPrice();
        if(price >= minPrice && price <= maxPrice){
            result.insert(p.getProductId(), p);
        }

        rangeRec(root.right, minPrice, maxPrice, result);
    }
    public BST<Integer, Order> CustomerOrderHistory(String cid) {
        Customer customer = searchCustomerById(cid); 

        if (customer == null) {
            System.out.println("There is no customer with that id");
            BST<Integer, Order> emptyTree = new BST<Integer, Order>();
            return emptyTree;
        }

        return customer.getOrderList();
    }

   
    public BST<Integer, Order> orderBetweenTwoDates(LocalDate Start , LocalDate End) {

        BST<Integer , Order > ResultOrder = new BST<Integer , Order>(); // tree we will fill

        if (orderList.empty()) {
            return ResultOrder; 
        }

        OrderBetweenTwoDatesRec(orderList.getRoot(), Start, End, ResultOrder);

        return ResultOrder;
    }

    private void OrderBetweenTwoDatesRec(BSTNode<Integer,Order> root,
                                         LocalDate start,
                                         LocalDate end,
                                         BST<Integer, Order> ResultOrder) {

        if (root == null) {
            return; 
        }

        OrderBetweenTwoDatesRec(root.left, start, end, ResultOrder);

        Order order = root.data;
        LocalDate date = order.getOrderDate();

        if ( (date.isAfter(start) || date.isEqual(start)) &&
             (date.isBefore(end)  || date.isEqual(end)) ) {

            ResultOrder.insert(order.getOrderId(), order);
        }

        OrderBetweenTwoDatesRec(root.right, start, end, ResultOrder);
    }

    public ArrayList<Product> top3ByRating() {
        ArrayList<Product> top = new ArrayList<>(3);
        top.insert(null);
        top.insert(null);
        top.insert(null);

        top3ByRatingRec(productList.getRoot(), top);

        return top;
    }

    private void top3ByRatingRec(BSTNode<Integer, Product> node, ArrayList<Product> top) {
        if (node == null) return;

        top3ByRatingRec(node.left, top);

        Product p = node.data;
        double rating = p.getAverageRating();

        insertTop3(top, p, rating);

        top3ByRatingRec(node.right, top);
    }

    private void insertTop3(ArrayList<Product> top, Product p, double score) {

        top.findFirst();  
        Product first = top.retrieve();
        double f = (first == null) ? -1 : first.getAverageRating();

        if (score > f) {
            top.findFirst();
            top.findNext();
            Product second = top.retrieve();

            top.update(first);

            top.findNext();
            top.update(second);

            top.findFirst();
            top.update(p);
            return;
        }

        top.findFirst();
        top.findNext();
        Product second = top.retrieve();
        double s = (second == null) ? -1 : second.getAverageRating();

        if (score > s) {
            top.findNext();
            top.update(second);

            top.findFirst();
            top.findNext();
            top.update(p);
            return;
        }

        top.findFirst();
        top.findNext();
        top.findNext();
        Product third = top.retrieve();
        double t = (third == null) ? -1 : third.getAverageRating();

        if (score > t) {
            top.update(p);
        }
    }

    public void listCustomersAlphabetically() {
        if (customersByName.empty()) {
            System.out.println("No customers registered yet");
            return;
        }

        System.out.println("Customers sorted alphabetically:");
        listCustomersRec(customersByName.getRoot());
    }

    private void listCustomersRec(BSTNode<String, Customer> root) {
        if (root == null) return;

        listCustomersRec(root.left);
        System.out.println(root.data);
        listCustomersRec(root.right);
    }

    public BST<String, Customer> getCustomersWhoReviewedProduct(int productId) {

        BST<String, Customer> result = new BST<String, Customer>();

        Product p = searchProductById(productId);
        if (p == null)
            return result;

        LinkedList<Review> reviews = p.getReviewList();
        if (reviews.empty())
            return result;

        reviews.findFirst();
        while (true) {

            Review r = reviews.retrieve();
            String cid = r.getCustomerId();
            Customer c = searchCustomerById(cid);

            if (c != null) {
                if (!result.findKey(c.getCustomerId()))
                    result.insert(c.getCustomerId(), c);
            }

            if (reviews.last())
                break;

            reviews.findNext();
        }

        return result;
    }
}
