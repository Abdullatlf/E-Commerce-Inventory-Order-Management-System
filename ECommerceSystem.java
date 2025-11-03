public class ECommerceSystem {

    private LinkedList<Product> productList;
    private LinkedList<Customer> customerList;
    private LinkedList<Order> orderList;

    public ECommerceSystem(){
        this.customerList = new LinkedList<Customer>();
        this.productList = new LinkedList<Product>();
        this.orderList = new LinkedList<Order>();
    }

    //Product Operations ------------------------------------------------------------------------------

    public void addProduct(Product product){
        productList.insert(product);
       // System.out.println("Product added :"+product+" successfully!");
    }
    public Product searchProductById(String productId){
        productList.findFirst();
        while (!productList.last()) {
            if(productList.retrieve().getId().equals(productId))
                return productList.retrieve();
            productList.findFirst();
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
    public void removeProduct(Product product){
        if(searchProductById(product.getId()) == null){
            System.out.println("Product not found!");
        }else{
            productList.remove();
            System.out.println("Product removed successfully!");
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
            System.out.println(customerList.retrieve());
            customerList.findNext();
        }
    }
    }
    public void displayProducts(){
        if(productList.empty()){
            System.out.println("No product were added yet!");
        }else{
        productList.findFirst();
        for(int i = 0; i < productList.getSize(); i++){
            System.out.println(productList.retrieve());
            productList.findNext();
        }
    }
    }


}