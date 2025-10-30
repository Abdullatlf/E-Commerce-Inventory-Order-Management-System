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
        //order.updateStatus("pending"); ----> changed when order constructor created
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

    public boolean cancel(String orderId){
        if(/*orderList.empty() (already checked) */ searchOrder(orderId) == null){
            return false;
        }
        searchOrder(orderId).updateOrderStatus("canceled");
        return true;
    }

    public String getId(){
        return customerId;
    }

    public String toString() {
        return "Customer{id=" + customerId + ", name='" + name + 
               "', email='" + email + "', totalOrders=" + orderList.getSize() + "}";
    }
}