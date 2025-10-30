public class ArrayList<T> {

    private int maxSize;
    private int size;
    private int current;
    private T[] nodes;
    
    public ArrayList(int n){
        maxSize = n;
        size = 0;
        current = -1;
        nodes = (T[])new Object[maxSize];
    }

    public boolean full(){
        return size == maxSize;
    }
    public boolean empty(){
        return size == 0;
    }
    public void findFirst(){
        //check if not empty
        current = 0;        
    }
    public void findNext(){
        //check if not last
        current++;
    }
    public boolean last(){
        //check if not empty 
        return current == size-1;
    }
    public T retrieve(){
        //checks if not empty
        return nodes[current];
    }
    public void update(T val){
        // check if not empty
        nodes[current] = val;
    }
    //insert after current
    public void insert(T val){
        if(!full()){
            for(int i = size - 1; current < i; i--){
                nodes[i+1] = nodes[i];
            }
            current++;
            nodes[current] = val;
            size++;
        }
    }

}
