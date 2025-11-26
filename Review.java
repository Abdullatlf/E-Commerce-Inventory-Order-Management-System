public class Review {
    private String reviewId;
    private int rating;
    private String comment;
    private String customerId;
    public Review(String reviewId, int rating, String comment, String customerId){
        this.reviewId = reviewId;
        this.rating = rating;
        this.comment = comment;
        this.customerId = customerId;
    }
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
    public void updateReview(int newRating, String newComment){
        comment = newComment;
        rating = newRating;
    }
}
