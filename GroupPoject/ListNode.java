
public class ListNode
{
    Object data;
    ListNode next;
    //normal constructor
    ListNode(Object o)
    {
        this(o,null);
    }
    //normal constructor
    ListNode(Object o,ListNode nextNode)
    {
        data = o;
        next = nextNode;
    }
    
    Object getData()
    {
        return data;
    }
    
    ListNode getNext()
    {
        return next;
    }
}
