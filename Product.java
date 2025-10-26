
public class Product {

    private String productId;
    private String name;
    private double price;
    private int stock;
    private LinkedList<Review> reviews;

    Product(String productId, String name, double price, int stock){
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.reviews = new LinkedList<Review>();
    }
    //rating from 0 to 5 condition in main
    public void addReview(Review review){
        //not necessary
        /*if(reviews.search(review) != null){
            review.display();
            System.out.println("Review already inserted!");
        }
        else{*/
        reviews.insert(review);
        //}
    }
    //testing
    public LinkedList<Review> getReview(){
        return this.reviews;
    }
    

    /*public void editReview(Review review){
        reviews.update(review);
    }

    //test ----->

    

    public void display() {
        System.out.println("Reviews for " + name + ":");
        if (reviews.empty()) {
            System.out.println("No reviews yet.");
            return;
        }
        Node<Review> current = reviews.getHead();
        while (current != null) {
            current.data.display();
            current = current.next;
        } 
    }

    public double getAverageReview(){
        if(reviews.empty()){
            System.out.println("No reviews yet!");
        return 0.0;
        }
        int sum = 0;
        Node<Review> current = reviews.getHead();
        while(current != null){
            sum += current.data.getRating();
            current = current.next;
        }
        double average = (double)sum/reviews.size();
        return average;
    }

    public boolean isOutOfStock(){
        return stock <= 0;
    }

*/
    



    
}
