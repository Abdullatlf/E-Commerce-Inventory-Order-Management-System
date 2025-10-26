public class LinkedList<T> {
    private Node<T> head;
    private int size = 0;


    public LinkedList(){
        head = null;
    }

    public boolean empty(){
        return head == null;
    }


    public void insert(T val){
        Node<T> newNode = new Node<T>(val);
        if(empty()){
            head = newNode;
        }
        else{
             Node<T> current = head;
             while(current.next != null){
                current = current.next;
             }
             current.next = newNode;    
        }
        size++;
    }
    public int getSize(){
        return size;
    }
    public T search(T val){
            Node<T> current = head;
            while(current != null){
                if(current.data.equals(val))
                    return current.data;
                current = current.next;
            }
            return null;
    }

   /*  public void update(T val){
        if(empty()){
         current.data = val;
        }
    }

    public int size(){
        current = head;
        int count = 0;
        while(current != null){
            count++;
            current = current.next;
        }
        return count++;

    }


    //test ----->

    public boolean last(){
        return !empty() && current.next == null;
    }

    public T retrieve(){
        if (!empty()) {
            return current.data;       
        }
        return null;
    }
    public void findfirst(){
        current = head;
    }

    public Node<T> getHead(){
        return head;
    }

    
    */
}
