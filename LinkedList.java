public class LinkedList<T> {
    private Node<T> head;
    private Node<T> current;
    private int size = 0;


    public LinkedList(){
        head = current = null;
    }

    public boolean empty(){
        return head == null;
    }
    public boolean last(){
        //check if not empty
        return current.next == null;
    }

    public void insert(T val){
        Node<T> tmp;
        if(empty()){
            head = current = new Node<T>(val);
        }
        else{
            tmp = current.next;
            current.next = new Node<T>(val);
            current = current.next;
            current.next = tmp;
        }
        size++;
    }

    public void findFirst(){
        current = head;
    }
    public void findNext(){
        current = current.next;
    }
    public T retrieve(){
        return current.data;
    }

    public void remove(){
        if(empty()){
            return;
        }
        if(current == head){
            head = head.next;
        }else{
        Node<T> tmp;
        tmp = head;
        while(tmp.next != current)
            tmp = tmp.next;
            
        tmp.next = current.next;
    }
        if(current.next == null)
            current = head;
        else
        current = current.next;
        size--;
    }

    public int getSize(){
        return size;
    }
    public void update(T val){
        current.data = val;
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
*/
    public Node<T> getHead(){
        return head;
    }

    public Node<T> getCurrent(){
        return current;
    }




    
}



    
    
