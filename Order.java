
import java.time.LocalDate;

public class Order {
    private String orderId;
    private String customerId;
    private double totalPrice;
    private LocalDate orderDate;
    private String status;
    private LinkedList<Product> productList;

    public Order(String orderId,String customerId){
        // I think orderId should be automaticly generated
        this.orderId = orderId;
        this.customerId = customerId;
        this.productList = new LinkedList<Product>();
        totalPrice = 0.0;
        status ="pending";
        this.orderDate = LocalDate.now();
    }

    public boolean addProduct(Product product){
        //i think we can put it in main
        if(status.equalsIgnoreCase("shipped")){
            System.out.println("\nOrder already shipped!\n");
            return false;
        }

        if(product.isOutOfStock()){
            System.out.println("\nProduct \""+ product.getName()+ "\" is out of stock!\n");
            return false;
        }

        productList.insert(product);
        totalPrice += product.getPrice();
        product.decrementStock();
        return true;
    }

    public Product searchProductById(String productId){
        if(!productList.empty()){ 
            productList.findFirst();
            while(!productList.last()){
                if(productList.retrieve().getId().equals(productId))
                    return productList.retrieve();
                productList.findNext();
            }
            if(productList.retrieve().getId().equals(productId))
                return productList.retrieve();
        }
        return null;
    }

    public boolean removeProduct(String productId){
        //i think we can put it in main
        if(status.equalsIgnoreCase("shipped")){
            System.out.println("Order already shipped!");
            return false;
        }

        if(/* already checked if empty*/searchProductById(productId) == null){
            System.out.println("Product not found! or ProductList is empty");
            return false;
        }

        searchProductById(productId).incrementStock();
        totalPrice -= searchProductById(productId).getPrice();
        productList.remove();
        return true;
    }

    public String toString() {
        return "Order{id=" + orderId + ", customerId=" + customerId + 
               ", total=" + totalPrice + ", date=" + orderDate + 
               ", status='" + status + "', products=" + productList.getSize() + "}";
    }


    public void displayProducts(){
        if(productList.empty()){
            System.out.println("the list is empty!");
        }
        productList.findFirst();
        for(int i = 1; i <= productList.getSize(); i++){
            System.out.println(productList.retrieve().toString());
            productList.findNext();
        }
    }
    
    public void updateOrderStatus(String status){
        this.status = status;
    }
    public String getId(){
        return orderId;
    }

    
}
