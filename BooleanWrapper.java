public class BooleanWrapper {
    private boolean flag;
    public BooleanWrapper(boolean flag){
        this.flag = flag;
    }
    public boolean get(){
        return flag;
    }
    public void set(boolean newflag){
        flag = newflag;
    }
    
}
