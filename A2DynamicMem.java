// Class: A2DynamicMem
// Implements Degragment in A2. No other changes should be needed for other functions.

public class A2DynamicMem extends A1DynamicMem {
      
    public A2DynamicMem() {  super(); }

    public A2DynamicMem(int size) { super(size); }

    public A2DynamicMem(int size, int dict_type) { super(size, dict_type); }

    // In A2, you need to test your implementation using BSTrees and AVLTrees. 
    // No changes should be required in the A1DynamicMem functions. 
    // They should work seamlessly with the newly supplied implementation of BSTrees and AVLTrees
    // For A2, implement the Defragment function for the class A2DynamicMem and test using BSTrees and AVLTrees. 

    // This function defragments the free block list (dictionary)
       // All the contiguous free blocks are merged into a single large free block
       // Algorithm:
       //     1. Create a new BST/AVT Tree indexed by address. Use AVL/BST depending on the type.
       //     2. Traverse all the free blocks and add them to the tree indexed by address 
       //        Note that the free blocks tree will be indexed by size, therefore a new tree 
       //        indexed by address needs to be created
       //     3. Find the first block in the new tree (indexed by address) and then find the next block
       //     4. If the two blocks are contiguous, then 
       //     4.1 Merge them into a single block
       //     4.2 Remove the free blocks from the free list and the new dictionary
       //     4.3 Add the merged block into the free list and the new dictionary
       //     5. Continue traversing the new dictionary
       //     6. Once the traversal is complete, delete the new dictionary
    public void Defragment() {
        switch(type){
            
            case 2:{    
                Dictionary x=new BSTree();
                Dictionary y=null,d=freeBlk.getFirst();
                while(d!=null){
                    x.Insert(d.address,d.size,d.address);
                    d=d.getNext();
                }
                x=x.getFirst();
                if(x!=null)
                y=x.getNext();
                while(y!=null){
                    if(y.key==x.key+x.size){
                        System.out.println("dyn..delete....."+x.key+" "+x.address);
                        int size=x.size+y.size;
                        int address=x.address;
                        Dictionary e=new BSTree(x.address,x.size,x.size);
                        freeBlk.Delete(e);
                        e=new BSTree(y.address,y.size,y.size);
                        freeBlk.Delete(e);
                        x.Delete(x);
                        x.Delete(y);
                        x=x.Insert(address, size, address);
                        freeBlk.Insert(address, size,size);
                        y=x.getNext();
                    }
                    else{
                        System.out.println("dyn..delete..loop..."+x.key+" "+x.size+" "+y.key);
                        x=y;
                        y=x.getNext();
                    }

                }
            }
                break;
            case 3:{
                    Dictionary x=new AVLTree();
                    Dictionary y=null,d=freeBlk.getFirst();
                    while(d!=null){
                        x.Insert(d.address,d.size,d.address);
                        d=d.getNext();
                    }
                    x=x.getFirst();
                    for (Dictionary k =x.getFirst(); k != null; k = k.getNext()) {
                        System.out.print(k.key+" "+k.address+" ");//+k.getheight()+" || ");
                        //count = count + 1;
                    }
                    if(x!=null)
                    y=x.getNext();
                    while(y!=null){
                        if(y.key==x.key+x.size){
                            System.out.println("dyn..delete....."+x.key+" "+x.address);
                            //System.out.print(y.key+":"+x.key+" ");
                            int size=x.size+y.size;
                            int address=x.address;
                            Dictionary e=new AVLTree(x.address,x.size,x.size);
                            freeBlk.Delete(e);
                            e=new AVLTree(y.address,y.size,y.size);
                            freeBlk.Delete(e);
                            x.Delete(x);
                            x.Delete(y);
                            x=x.Insert(address, size, address);
                            freeBlk.Insert(address, size,size);
                            y=x.getNext();
                        }
                        else{
                            System.out.println("dyn..delete..loop..."+x.key+" "+x.size+" "+y.key);
                            x=y;
                            y=x.getNext();
                        }

                    }
            }
                break;
            default:
                    break;
        }
        return ;
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
            int address=node.address;
            int size=node.size;
            if(allocBlk.Delete(node)){
                freeBlk.Insert(address,size,size);
                return 0;
            }
        }    
        return -1;
    }
}