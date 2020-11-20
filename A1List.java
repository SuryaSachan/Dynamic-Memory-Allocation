// Implements Dictionary using Doubly Linked List (DLL)
// Implement the following functions using the specifications provided in the class List

public class A1List extends List {

    private A1List  next; // Next Node
    private A1List prev;  // Previous Node 

    public A1List(int address, int size, int key) { 
        super(address, size, key);
    }
    
    public A1List(){
        super(-1,-1,-1);
        // This acts as a head Sentinel

        A1List tailSentinel = new A1List(-1,-1,-1); // Intiate the tail sentinel
        
        this.next = tailSentinel;
        tailSentinel.prev = this;
    }

    public A1List Insert(int address, int size, int key)
    {
        // Should insert the element in the DLL after the current node.
        // Should return the corresponding element created and inserted.
        A1List ll=new A1List(address,size,key);
        //when this points to tail sentinal
        if(this.next==null){
            ll.next=this.prev;
            ll.prev=this.prev.prev;
            ll.prev.next=ll;
            this.prev=ll;
        }
        else if(this.getNext()==null){
            ll.next=this.next;
            ll.next.prev=ll;
            ll.prev=this;
            this.next=ll;
        }
        else{
            ll.next=this.next;
            ll.next.prev=ll;
            this.next=ll;
            ll.prev=this; 
        }
        return ll;
    }
    //swap values
    public void swap(int a,int b){
        int temp=a;
        a=b;
        b=temp;
        return;
    }

    public boolean Delete(Dictionary d) 
    {
        // Deletes the entry corresponding to d from the DLL.
        // Searches for the d.key in the DLL
        // Can be called with any node in the DLL. So this function should search forward as well as backwards.
        // Deletes the element it is found in the DLL and returns true. 
        // Note there may be multiple elements with the same key value. 
        // Delete searches for the node with the same key and same d and returns true only if d as well as d.key match
        // Returns false if d not found in the DLL.
        A1List current=this;
        if(current.key==d.key&&current.address==d.address && current.size==d.size){
            swap(current.key,current.next.key);
            swap(current.address,current.next.address);
            swap(current.size,current.next.size);
            if(current.getNext()==null)
              current.next=null;
            else{
                current.next=current.next.next;
                current.next.prev=current;
            }
            return true;            
        }
        //empty node case:if this==null&&this.getNext()==null then return -1
        if(this!=null||this.getNext()!=null){
            //towards end
            while(current!=null){
                if(current.key==d.key && current.address==d.address && current.size==d.size){
                    current.prev.next=current.next;
                    current.next.prev=current.prev;
                    return true;
                }
                current=current.getNext();
            }
            //towards front
            while(current.prev!=null){
                if(current.key==d.key && current.address==d.address && current.size==d.size){
                    current.prev.next=current.next;
                    current.next.prev=current.prev;
                    return true;
                }
                current=current.prev;
            }
        }
        return false;
    }

    public A1List Find(int k, boolean exact)
    { 
        // Searches for the key k in the DLL. 
        // If exact is true, then performs and exact match and returns an element of the dictionary with key = k
        // and returns null if no such element exists.

        // If exact is false, performs an approximate search and
        // returns an element with key >= k in the dictionary.  Returns null in case no such element found.

        // Can be used to implement the First Split Fit strategy with exact = false
        A1List head=this.getFirst();
        A1List d=head;
        if(exact==true){
         if(head!=null){
            while(d!=null){
                if(d.key==k)
                    return d;
                d=d.getNext();
            }
         }       
        }
        else{
            if(head!=null){
                while(d!=null){
                    if(d.key>=k)
                        return d;
                    d=d.getNext();
                }
            }
        }
        return null;
    }

    public A1List getFirst()
    {
        // The getFirst and getNext functions are for traversal of the dictionary. 
        // The getFirst() returns the first element of the dictionary and null if the dictionary is empty
        // The getNext(d) returns the next element after d
        // The dictionary class does not define any order in which the elements of the dictionary are to be traversed. 
        // The only requirement is that using the following loop, getFirst() and getNext() should be able to 
        // traverse all the elements in the dictionary. 
        // count = 0; for (d = dict.getFirst(); d != null; d = d.getNext()) count = count + 1;
        // After the above loop, count should contain the total number of elements in the dictionary.
        A1List current=this;
        //when this is headsentinal
        if(current.prev==null&&current.getNext()==null)
            return this;

        while(current.prev!=null){
            current=current.prev;
        }
        return current.getNext();
    }
    
    public A1List getNext() 
    {
        if(this.next==null||this.next.next==null)
            return null;
        return this.next;
    }

    public boolean sanity()
    {
        A1List temp,head;
        temp=this;
        head=this.getFirst();    
        //null sentinel for tail
        if(head!=null && (head.prev.prev!=null))
            return false;
        //node.next.prev != node
        if(temp!=null){
            while(temp.next!=null){
                if(temp.next.prev!=temp)
                    return false;
                temp=temp.next;
            }
        }
        //circular
        temp=head;
        while(temp.next!=null){
            if(temp.next==head)
                return false;
            temp=temp.next;
        }
        return true;
    }

}


