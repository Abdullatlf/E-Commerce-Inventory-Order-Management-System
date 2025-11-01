public class Customer{
    String customerId;
    String name;
    String email;
    LinkedList<Order> orderList;

    public Customer(String customerId, String name, String email){
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        orderList = new LinkedList<Order>();
    }
    public void createOrder(Order order){
        orderList.insert(order);
    } 

    public Order searchOrder(String orderId){
        if(!orderList.empty()){        
            orderList.findFirst();
            while(!orderList.last()){
                if(orderList.retrieve().getId().equals(orderId))
                    return orderList.retrieve();
                
                orderList.findNext();
            }
            if(orderList.retrieve().getId().equals(orderId))
                return orderList.retrieve();
        }
        return null;
    }

    public boolean cancel(Order order){
        if(/*orderList.empty() (already checked) */ searchOrder(order.getId()) == null){
            System.out.println("No orders were made! or order not found!");
            return false;
        }
        searchOrder(order.getId()).updateOrderStatus("canceled");
        return true;
    }

    public String getId(){
        return customerId;
    }

    public void displayOrders(){
        if(orderList.empty()){
            System.out.println("No orders were made!");
        }
        orderList.findFirst();
        for (int i = 1; i <= orderList.getSize(); i++) {
            System.out.println(orderList.retrieve().toString());
            orderList.findNext();    
        }
    }

    
    @Override
    public String toString() {
        return "Customer{id=" + customerId + ", name='" + name + 
               "', email='" + email + "', totalOrders=" + orderList.getSize() + "}";
    }
}