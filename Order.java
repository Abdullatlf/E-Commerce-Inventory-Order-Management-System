import java.time.LocalDate;

public class Order {
    //changed into integer
    private int orderId;
    private String customerId;
    private double totalPrice;
    private LocalDate orderDate;
    private String status;
    private BST<Integer, Product> productList;

    public Order(int orderId,String customerId){
        // I think orderId should be automaticly generated 
        // I think it will be in the main since we can count the orders
        this.orderId = orderId;
        this.customerId = customerId;
        this.productList = new BST<Integer, Product>();
        totalPrice = 0.0;
        status ="pending";
        this.orderDate = LocalDate.now();
    }
    public Order(Order order){
        this.orderId = order.orderId;
        this.customerId = order.customerId;
        this.productList = order.productList; 
        this.totalPrice = order.totalPrice;   
        this.status = order.status;
        this.orderDate = order.orderDate;
        this.orderDate = order.orderDate;
    }
    public boolean addProduct(Product product){
        //i think we can put it in main or in place order method
        String flag = status;
        switch (flag) {
            case"Shipped":
                System.out.println("Order already shipped!");
                return false;
            case"Delivered":
                System.out.println("Order already delivered!");
                return false;
            case"Cancelled":
                System.out.println("Order cancelled!");
                return false;
        }

        if(product.isOutOfStock()){
            System.out.println("\nProduct \""+ product.getName()+ "\" is out of stock!\n");
            return false;
        }
        Product copyProduct = new Product(product);
        productList.insert(copyProduct.getProductId(), copyProduct);
        totalPrice += product.getPrice();
        product.decrementStock();
        return true;
    }

    

    //in the start of the programm
    public Product searchProductById(int productId){
        if(productList.findKey(productId))
            return productList.retrieve();
        return null;
    }
    

    public boolean removeProduct(Product product){
        Product copyProduct = new Product(product);
        //i think we can put it in main
        String flag = status;
        switch (flag) {
            case"Shipped":
                System.out.println("Order already shipped!");
                return false;
            case"Delivered":
                System.out.println("Order already delivered!");
                return false;
            case"Cancelled":
                System.out.println("Order cancelled!");
                return false;
        }

        Product foundProduct = searchProductById(copyProduct.getProductId());

        if( foundProduct == null){
            System.out.println("Product not found! or ProductList is empty");
            return false;
        }
        if(productList.remove_key(foundProduct.getProductId())){
            product.incrementStock();
            totalPrice -= foundProduct.getPrice();
            return true;
        }
        return false;
    }

    public String toString() {
        return "Order{id=" + orderId + ", customerId=" + customerId + 
               ", total=" + totalPrice + ", date=" + orderDate + 
               ", status='" + status + "', products=" + productList.getSize() + "}";
    }


    public void displayProducts(){
        if(productList.empty()){
            System.out.println("No product were add yet");
        }else{
            System.out.println("Products in order number("+orderId+"):");
            productList.displayTree();
        }
    }

    public void displayTree(){

        if(productList.empty()){
            System.out.println("No product were add yet");
        }else{
            System.out.println("Products in order number("+orderId+"):");
            productList.printTree();
        }

    }
    
    
    public void updateOrderStatus(String status){
        this.status = status;
    }
    public int getOrderId(){
        return orderId;
    }
    public void setTotalPrice(double newTotalPrice){
        totalPrice = newTotalPrice;
    }
    public void setOrderDate(LocalDate newOrderDate){
        orderDate = newOrderDate;

    }
    public LocalDate getOrderDate(){
        return orderDate;
    }

    public BST<Integer, Product> getProductList(){
        return productList;
    }
}