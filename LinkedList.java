public class LinkedList<T> {
    private Node<T> head;
    private Node<T> current;
    private Node<T> tail;
    private int size = 0;


    public LinkedList(){
        head = current = null;
    }
    public void insertAtEnd(T val){
        Node<T> newNode = new Node<T>(val);
        if(empty()){
            head = current = tail = newNode;
        }else{
            tail.next = newNode;
            tail = newNode;
            current = newNode;
        }
        size++;
    }

    public boolean empty(){
        return head == null;
    }
    public boolean last(){
        //check if not empty
        return current.next == null;
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

    public boolean display(){
        if(empty()){
            System.out.println("List is empty");
            return false;
        }else{
            findFirst();
            for(int i = 0 ; i < size; i++){
                System.out.println(current.data.toString());
                findNext();
            }
            return true;
        }
    }




 
}