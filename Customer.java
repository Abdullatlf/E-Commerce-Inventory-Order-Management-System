public class Customer{
    public String customerId;
    public String name;
    public String email;
    public BST<Integer,Order> orderList;

    public Customer(String customerId, String name, String email){
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        orderList = new BST<Integer,Order>();
    }
    public void createOrder(Order order){
        Order copyOrder = new Order(order.getOrderId(), customerId);
        orderList.insert(copyOrder.getOrderId(),copyOrder);
    } 

    public Order searchOrderById(int orderId){
        if(orderList.findKey(orderId))
            return orderList.retrieve();
        return null;
    }

    public boolean cancel(Order order){
        //changed but maybe logic error
        Order updatedOrder = searchOrderById(order.getOrderId());
        if(/*orderList.empty() (already checked) */ updatedOrder == null){
            System.out.println("No orders were made! or order not found!");
            return false;
        }
        updatedOrder.updateOrderStatus("canceled");
        return true;
    }

    public String getCustomerId(){
        return customerId;
    }

    public String getName(){
        return name;
    }

    public void displayOrders(){
        if(orderList.empty()){
            System.out.println("No orders were add yet");
        }else{
            System.out.println(name+"'s' orders:");
            orderList.displayTree();
        }
    }

    public void displayTree(){

        if(orderList.empty()){
            System.out.println("No orders were add yet");
        }else{
            System.out.println(name+"'s' orders:");
            orderList.printTree();
        }

    }

    public BST<Integer,Order> getOrderList(){
        return orderList;
    }

    
    @Override
    public String toString() {
        return "Customer{id=" + customerId + ", name='" + name + 
               "', email='" + email + "', totalOrders=" + orderList.getSize() + "}";
    }
}