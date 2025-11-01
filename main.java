public class main { 
    public static void main(String[] args) {
        //loading files 
        //CSVReader.loadCustomersFromCSV();
        //CSVReader.loadReviewsFromCSV();
        //CSVReader.loadProductsFromCSV();
        //CSVReader.loadOrdersFromCSV();

        

        //test ----->

        Customer c1 = new Customer("1125213353", "Abdullatif", "abd@example.com");

        Product p1 = new Product("1", "labtop", 999.99, 10);
        Product p2 = new Product("2", "PC", 1999.99, 10);
        Product p3 = new Product("3", "IPhone", 1199.99, 50);
        Product p4 = new Product("4", "pin", 9.9, 100);

        Order o1 = new Order("11", c1.getId());

        c1.createOrder(o1);

        o1.addProduct(p1);
        o1.addProduct(p1);
        o1.addProduct(p1);
        o1.addProduct(p1);
        o1.addProduct(p1);
        o1.addProduct(p1);
        o1.addProduct(p1);
        o1.addProduct(p1);
        o1.addProduct(p1);
        o1.addProduct(p1);

        System.out.println(o1.toString());
        System.out.println(p1.toString());

        System.out.println("------------------------------------");

        o1.addProduct(p1);

        System.out.println(o1.toString());
        System.out.println(p1.toString());

        System.out.println("------------------------------------");

        o1.updateOrderStatus("shipped");

        System.out.println(o1.toString());
        System.out.println(p1.toString());

        o1.addProduct(p4);

        System.out.println("------------------------------------");

        o1.addProduct(p1);

        System.out.println(o1.toString());
        System.out.println(p1.toString());


        System.out.println("------------------------------------");

        o1.updateOrderStatus("Pending");

        System.out.println(o1);

        o1.removeProduct(p1.getId());
        o1.removeProduct(p1.getId());
        o1.removeProduct(p1.getId());
        o1.removeProduct(p1.getId());
        o1.removeProduct(p1.getId());
        o1.removeProduct(p1.getId());
        o1.removeProduct(p1.getId());
        o1.removeProduct(p1.getId());
        o1.removeProduct(p1.getId());
        o1.removeProduct(p1.getId());
        o1.removeProduct(p1.getId());

        System.out.println(o1);

        System.out.println("------------------------------------");

        Order o3 = new Order("3", c1.getId());
        c1.createOrder(o3);
        System.out.println(o3);
        o3.addProduct(p1);
        o3.removeProduct(p1.getId());
        System.out.println(o3);
        

        System.out.println("------------------------------------");

        o3.addProduct(p4);
        System.out.println(o3.searchProductById(p4.getId()));



        System.out.println("------------------------------------");


        Order o2 = new Order("2", c1.getId());
        c1.createOrder(o2);

        System.out.println(o2.toString());

        c1.cancel(o2);

        System.out.println(o2.toString());

        System.out.println("------------------------------------");

        c1.displayOrders();


        System.out.println("------------------------------------");

        Order o4 = new Order("4", c1.getId());

        System.out.println(c1.searchOrder(o4.getId()));

        Customer c2 = new Customer("2", "ahmed", "ahemd@example.com");

        c2.displayOrders();


        System.out.println("------------------------------------");

        Customer c3 = new Customer("3", "Khalid", "khalid@example.com");

        Review r1 = new Review("1", 2, "gooooood!", c1.getId());
        Review r2 = new Review("2", 2, "great!", c1.getId());
        Review r3 = new Review("3", 2, "not good!", c2.getId());
        Review r4 = new Review("4", 2, "i recommend it!", c3.getId());
        p1.addReview(r1);
        p1.addReview(r2);
        p1.addReview(r3);
        p1.addReview(r4);

        p1.displayReviews();


        System.out.println("------------------------------------");

        System.out.println(p1.searchByReviewId(r3.getId()));

        System.out.println("------------------------------------");

        p1.editReview(r3,5, "very good");
        p1.displayReviews();

        System.out.println("------------------------------------");

        System.out.println(p1.getAverageRating());

        System.out.println(p1);




        








    



        /* 
        System.out.println(o1.toString());

        o1.updateOrderStatus("shipped");

        System.out.println(o1.toString());

        System.out.println("________________________________________\n");
        
        System.out.println(p1.toString());
        System.out.println(p2.toString());
        System.out.println(p3.toString());
        System.out.println(p4.toString());

        System.out.println("________________________________________\n");
        
        o1.removeProduct(p3.getId());

        System.out.println(o1.toString());

        System.out.println("________________________________________\n");


        System.out.println(p1.toString());
        System.out.println(p2.toString());
        System.out.println(p3.toString());
        System.out.println(p4.toString());

        System.out.println("________________________________________\n");

        o1.addProduct(p4);

        o1.displayProducts();

        o1.removeProduct(p1.getId());
        o1.removeProduct(p2.getId());
        o1.removeProduct(p4.getId());

        o1.displayProducts();

        


        System.out.println(o1.toString());
        */





        
        
        
    }
    
}
