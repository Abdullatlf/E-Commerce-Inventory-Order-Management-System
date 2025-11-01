
public class Product {

    private String productId;
    private String name;
    private double price;
    private int stock;
    private LinkedList<Review> reviewList;

    Product(String productId, String name, double price, int stock){
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.reviewList = new LinkedList<Review>();
    }

    public String getId(){
        return productId;
    }
    public boolean isOutOfStock(){
        return stock <= 0;
    }
    public double getPrice(){
        return price;
    }
    public void decrementStock(){
        stock--;
    }
    public void incrementStock(){
        stock++;
    }

    public String toString() {
        return "Product{id=" + productId + ", name='" + name + 
               "', price=" + price + ", stock=" + stock + 
               ", avgRating=" + getAverageRating() + "}";
    }
    public String getName(){
        return name;
    }
    //rating from 0 to 5 condition in main
    public boolean addReview(Review review){
        //not necessary
        /*if(reviews.search(review) != null){
            review.display();
            System.out.println("Review already inserted!");
        }
        else{*/
        if(review.getRating() > 5 && review.getRating() < 0){
            System.out.println("Erorr: Rating should be from 1 to 5!");
            return false;
        }
        reviewList.insert(review);
        return true;
        //}
    }

    public void displayReviews(){
        if(reviewList.empty()){
            System.out.println("No reviews were made!");
        }else{
            reviewList.findFirst();
        for (int i = 0 ; i< reviewList.getSize() ; i++) {
            System.out.println(reviewList.retrieve().toString());
            reviewList.findNext();
        }
        }
    }

    public Review searchByReviewId(String reviewId){
        if(!reviewList.empty()){
            reviewList.findFirst();
            while(!reviewList.empty()){
                if(reviewList.retrieve().getId().equals(reviewId)){
                    return reviewList.retrieve();
                }
                reviewList.findNext();
            }if(reviewList.retrieve().getId().equals(reviewId)){
                return reviewList.retrieve();
            }
        }
        return null;
    }

    public boolean editReview(Review review, int newRating, String newComment){
        if(searchByReviewId(review.getId()) == null){
            System.out.println("Review not found!");
            return false;
        }
        Review newReview = new Review(review.getId(), newRating, newComment, review.getCustomerId());
        reviewList.update(newReview);
        return true;
    }

    public double getAverageRating(){
        reviewList.findFirst();
        double sum = 0;
        for(int i = 0 ; i <reviewList.getSize() ; i++){
            sum += reviewList.retrieve().getRating();
            reviewList.findNext();
        }
        return sum/reviewList.getSize();
    }

}
