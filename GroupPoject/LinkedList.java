
public class LinkedList
{
    private ListNode first;
    private ListNode last;
    private ListNode current;

    public LinkedList()
    {
        first = last = current = null;
    }

    public boolean isEmpty()
    {
        return first == null;
    }

    public void insertAtFront(Object insertItem)
    {
        if(isEmpty())
            first = last = new ListNode(insertItem);
        else
            first = new ListNode(insertItem, first);
    }

    public void insertAtBack(Object insertItem)
    {
        if(isEmpty())
            first = last = new ListNode(insertItem);
        else 
            last = last.next = new ListNode(insertItem);
    }
    //remove the data from the List
    public void remove(Object data)throws EmptyListException
    {
        current = first;
        ListNode previous = null;
        if(isEmpty())
            throw new EmptyListException();
        
        while(current != null && !current.data.equals(data))
        {
            previous = current; 
            current = current.next; 
        }
        
        if(current == null)
            return; //not found
        
        if(previous == null)
            first = current.next;
        else
            previous.next = current.next;
    }
    //return first element
    public Object getFirst()
    {
        if(isEmpty())
            return null;
        else
        {
            current = first;
            return current.data;
        }
    }
    //return the next element
    public Object getNext()
    {
        if(current != last)
        {
            current = current.next;
            return current.data;
        }
        else
        return null;
    }
    //display all node
    public void display()
    {
        current = first;
        while (current != null)
        {
            System.out.println(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
}
