public class Review {
    private String reviewId;
    private int rating;
    private String comment;
    private String customerId;
    //private String productId;
    public Review(String reviewId, int rating, String comment, String customerId){
        /*if(rating > 5 || rating < 1){
            throw new IllegalArgumentException("Rating must be between 1 and 5!");
        }*/
        this.reviewId = reviewId;
        this.rating = rating;
        this.comment = comment;
        this.customerId = customerId;
    }
    //testing in main
    public int getRating(){
        return rating;
    }
    public String toString(){
        return "Review{id="+ reviewId +", rating=" + rating + ", comment='" + comment + 
               "', customerId=" + customerId + /* ", date=" + reviewDate + */"}";
    }
    public String getId(){
        return reviewId;
    }
    public String getCustomerId(){
        return customerId;
    }
}
