public class Node<T> {
        Node<T> next;
        T data;
        public Node(){
            next = null;
            data =null;
        }
        public Node(T val){
            this.data = val;
            next = null;
        }

    
}
