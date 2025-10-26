public class main { 
    public static void main(String[] args) {
        //loading files 
        CSVReader.loadCustomersFromCSV();
        CSVReader.loadReviewsFromCSV();
        CSVReader.loadProductsFromCSV();
        CSVReader.loadOrdersFromCSV();

        

        //test ----->

        Product p = new Product("1234", "laptop", 30, 20);
        Customer c = new Customer();
        //Review r1 = new Review("1",1,"good","1");
        //Review r2 = new Review("4",2,"not good","2");
        //Review r3 = new Review("3",5,"not good","3");
        //p.addReview(r1);
        //p.addReview(r2);
        //p.addReview(r3);
        //System.out.println(p.getReview().getSize());
        

        //p.editReview(r2);
        
        
        
    }
    
}
