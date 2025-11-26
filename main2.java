public class main2 {

    public static void main(String[] args) {
        ECommerceSystem system = new ECommerceSystem();
        CSVReader.loadAllData(system);

        Product p1 = new Product(1, "mac", 6000, 10);
        Product p2 = new Product(2, "sony", 1800, 20);
        Product p3 = new Product(3, "iphone", 4000, 30);
        Customer c1 = new Customer("1", "ABdullatid", "gmail");
        Order o1 = new Order(301, c1.getCustomerId());
        Order o2 = new Order(302, c1.getCustomerId());
        Order o3 = new Order(303, c1.getCustomerId());

        system.registerCustomer(c1);
        system.placeOrder(c1.getCustomerId(), o3);
        system.displayCustomersTree();
        
        system.rangeQueryByPrice(150, 200).displayTree();

        System.out.println();
        system.getCustomerOrderHistory(c1.getCustomerId()).displayTree();;







    }
    
}
