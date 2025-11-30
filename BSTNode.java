public class BSTNode<K extends Comparable<K>,T> {
        public T data;
        public K key;
        public BSTNode<K,T> left,right;
        public BSTNode(K k,T val){
            this.key = k;
            this.data = val;
            left = right = null;
        }    

}
