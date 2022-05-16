public class ListNode <T>{

    private ListNode<T> before;
    private ListNode<T> after;
    private T val;
    
    public ListNode(T valin){
        val = valin;
    }

    public ListNode(T valin, ListNode<T> beforein, ListNode<T> afterin){
        val = valin;
        before = beforein;
        after = afterin;
    }

    public T get(){
        return val;
    }

    public void setbefore(ListNode<T> node){
        before = node;
    }

    public void setafter(ListNode<T> node){
        after = node;
    }

    public ListNode<T> getbefore(){
        return before;
    }

    public ListNode<T> getafter(){
        return after;
    }

}