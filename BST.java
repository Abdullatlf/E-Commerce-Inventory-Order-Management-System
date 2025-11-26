public class BST<K extends Comparable<K>, T> {
    private BSTNode<K,T> root;
    private BSTNode<K,T> current;
    private int size;

    public BST(){
        root = current = null;
        size = 0;
    }

    public boolean empty(){
        return root == null;
    }

    public T retrieve(){
        return current.data;
    }

    public boolean findKey(K key){
        BSTNode<K,T> p = root, q = root;
        /*if(empty()){
            return false;
        }*/

        while(p != null){
            q = p;
            if(key.compareTo(p.key) == 0){
                current = p;
                return true;
            }
            else if(key.compareTo(p.key) < 0)
                p = p.left;
            else
                p = p.right;
        }
        current = q;
        return false;

    }

    public boolean insert(K key, T val){
        BSTNode<K,T> newNode, q = current;
        if(findKey(key)){
            current = q;
            return false;
        }
        newNode = new BSTNode<K,T>(key, val);
        if(empty()){
                root = current = newNode;
            }
        else{
            if(key.compareTo(current.key)<0)
                current.left = newNode;
            else
                current.right = newNode;
        }
        current = newNode;
        size++;
        return true;
    }

    public boolean remove_key(K key){
        BooleanWrapper flag = new BooleanWrapper(false);
        BSTNode<K,T> parent;
        parent = remove_aux(key, root, flag);
        current = root = parent;
        if(flag.get())
            size--;
        return flag.get();
    }

    public BSTNode<K,T> remove_aux(K key, BSTNode<K,T> parent, BooleanWrapper flag){
        BSTNode<K,T> q, child = null;

        if(parent == null){
            return null;
        }
        if(key.compareTo(parent.key) < 0) 
            parent.left = remove_aux(key, parent.left, flag);
        else if(key.compareTo(parent.key) > 0)
            parent.right = remove_aux(key, parent.right, flag);
        else{
            flag.set(true);
            if(parent.left !=null && parent.right != null){
                q = find_min(parent.right);
                parent.data = q.data;
                parent.key = q.key;
                parent.right = remove_aux(q.key, parent.right, flag);
            }else{
                if(parent.right == null)
                    child = parent.left;
                else if(parent.left == null)
                    child = parent.right;
                return child;
            }
        }
        return parent;
    }

    public BSTNode<K,T> find_min(BSTNode<K,T> parent){
        if(parent == null)
            return null;
        while(parent.left != null)
            parent = parent.left;
        return parent;
    }

    public boolean update(K key, T val){
        if(key.compareTo(current.key) == 0){
            current.data = val;
            return true;
        }
        if(findKey(key))
            return false;

        remove_key(current.key);
        return insert(key, val);
    }
    public void displayTree(){
        System.out.println("Tree: ");
        displayInOrder(root);
        System.out.println();
    }
    public void displayInOrder(BSTNode<K,T> node){
        if(node == null)
            return;
        displayInOrder(node.left);
        System.out.println(node.data+" ");
        displayInOrder(node.right);
    }

    public int getSize(){
        return size;
    }

    public BSTNode<K,T> getRoot(){
        return root;
    }

    //test ---------------------------------------------------------------

    public void printTree() {
        System.out.println("Tree structure:");
        printVisual(root, "", true);
    }
    
    private void printVisual(BSTNode<K,T> node, String prefix, boolean isTail) {
        if (node == null) {
            return;
        }
        
        // Print right subtree first (top of visual)
        if (node.right != null) {
            printVisual(node.right, prefix + (isTail ? "│   " : "    "), false);
        }
        
        // Print current node
        System.out.println(prefix + (isTail ? "└── " : "┌── ") + node.key);
        
        // Print left subtree
        if (node.left != null) {
            printVisual(node.left, prefix + (isTail ? "    " : "│   "), true);
        }
    }
}
