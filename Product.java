public class Product {
    //changed productId to integer
    private int productId;
    private String name;
    private double price;
    private int stock;
    private LinkedList<Review> reviewList;

    //changed productId to integer
    public Product(int productId, String name, double price, int stock){
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.reviewList = new LinkedList<Review>();
    }

    //copy constructor due to the relationship between order and product
    public Product(Product product){
        this.productId = product.productId;
        this.name = product.name;
        this.price = product.price;
        this.stock = product.stock;
        this.reviewList = new LinkedList<Review>();
    }
    public int getStock(){
        return stock;
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
    public boolean createReview(Review review){
        if(review.getRating() > 5 || review.getRating() < 0){
            System.out.println("Erorr: Rating should be from 1 to 5!");
            return false;
        }
        reviewList.insertAtEnd(review);
        return true;
    }

    public void displayReviews(){
    if(reviewList.empty()){
        System.out.println("No reviews were made!");
    } else {
        reviewList.findFirst();  // Move this AFTER the empty check
        for (int i = 0; i < reviewList.getSize(); i++) {
            System.out.println(reviewList.retrieve());
            reviewList.findNext();
        }
    }
}

    public Review searchReviewById(String reviewId){
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
        if(searchReviewById(review.getId()) == null){
            System.out.println("Review not found!");
            return false;
        }
        review.updateReview(newRating,newComment);
        reviewList.update(review);
        return true;
    }

    public boolean removeReview(Review review){
        Review deletedReview = searchReviewById(review.getId());
        if(deletedReview == null){
            System.out.println("Review not found!");
            return false;
        }
        reviewList.remove();
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

    public void setPrice(double newPrice){
        price = newPrice;
    }

    public LinkedList<Review> getReviewList(){
        return reviewList;
    }
    public void setStock(int newStock){
        stock = newStock;
    }

    //Phase two 

    public int getProductId(){
        return productId;
    }

}