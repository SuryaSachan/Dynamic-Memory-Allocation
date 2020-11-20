// Class: A1DynamicMem
// Implements DynamicMem
// Does not implement defragment (which is for A2).

public class A1DynamicMem extends DynamicMem {
      
    public A1DynamicMem() {
        super();
    }

    public A1DynamicMem(int size) {
        super(size);
    }

    public A1DynamicMem(int size, int dict_type) {
        super(size, dict_type);
    }

    public void Defragment() {
        return ;
    }

    // In A1, you need to implement the Allocate and Free functions for the class A1DynamicMem
    // Test your memory allocator thoroughly using Doubly Linked lists only (A1List.java).
    // In A1, you need to implement the Allocate and Free functions for the class A1DynamicMem
    // Test your memory allocator thoroughly using Doubly Linked lists only (A1List.java).
    // While inserting into the list, only call insert at the head of the list
    // Please note that ALL insertions in the DLL (used either in A1DynamicMem or used independently
    // as the “dictionary” class implementation) are to be made at the HEAD (from the front).
    // Also, the find-first should start searching from the head (irrespective of the use for A1DynamicMem). 
    //Similar arguments will follow with regards to the ROOT in the case of trees (specifying this in case it was not so trivial to anyone of you earlier)

    public int Allocate(int blockSize) {
       // This function should allocate a contiguous array of size blockSize and return the first address. 
       // It should return -1 if memory is not avaiable. 
       // The free blocks list and the allocated blocks list should be suitably modified
       // Algorithm: 
       //     1. Search in the free block dictionary to find a block of size >= blockSize using the find function
       //     2. If found, check if splitting of the block is needed. 
       //     3. If yes, split the block and insert the two blocks into the free and allocated blocks dictionary
       //     3.1. Delete the block from the free block dictionary
       //     4 If no, insert the block into allocated blocks dictionary and remove it from free blocks dictionary
       
       if(blockSize>0){ 
            Dictionary headfree=freeBlk.Find(blockSize,false);
            //System.out.println(freeBlk.sanity());
            if(headfree!=null){
                if(headfree.size>=blockSize){
                    Dictionary node;
                    if(headfree.size>blockSize){
                        node= allocBlk.Insert(headfree.address,blockSize,blockSize);
                        int size=headfree.size-blockSize;
                        int address=headfree.address+blockSize;
                        if(freeBlk.Delete(headfree)){
                            freeBlk.Insert(address, size, size);
                            return node.address;
                        }

                    }
                    else if(headfree.size==blockSize){
                        node=allocBlk.Insert(headfree.address,blockSize,blockSize);

                        if(freeBlk.Delete(headfree))
                            return node.address;
                    }
                }
                headfree=headfree.getNext();
            }
        }    
        return -1;
    } 
    
    public int Free(int startAddr) {
        // This function should free the memory block starting at the startAddr
       // It should return -1 in case block not found
       // Algorithm: 
       //    1. Add the block to free blocks list (dictionary)
       //    2. Delete the bock from the allocated blocks list (dictionary)
       Dictionary node=allocBlk.getFirst();
       while(node!=null){
           if(node.address==startAddr){
               break;
           }
           node=node.getNext();
       }
        if(node!=null){
            if(allocBlk.Delete(node)){
                freeBlk.Insert(node.address,node.size,node.key);
                return 0;
            }
        }    
        return -1;
    }
}