public class Node<T> {

    public T data;
    public Node<T> next;

    public Node(){
        data = null;
        next = null;
    }
    public Node(T val){
        this.data = val;
        next = null;
    }
    
}
