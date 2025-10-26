public class Review {
    private String reviewId;
    private int rating;
    private String comment;
    private String customerId;
    public Review(String reviewId, int rating, String comment, String customerId){
        /*if(rating > 5 || rating < 1){
            throw new IllegalArgumentException("Rating must be between 1 and 5!");
        }*/
        this.rating = rating;
        this.comment = comment;
        this.customerId = customerId;
    }

    public void display(){
        System.out.printf("Rating: %d \nComment: %s\n",rating,comment);
    }

    //testing in main
    public int getRating(){
        return rating;
    }
    //for search in product
    public String getReviewId() {
        return reviewId;
    }
    


}
