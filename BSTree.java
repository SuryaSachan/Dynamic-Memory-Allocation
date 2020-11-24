// Class: Implementation of BST in A2
// Implement the following functions according to the specifications provided in Tree.java

public class BSTree extends Tree {

    private BSTree left, right;     // Children.
    private BSTree parent;          // Parent pointer.
        
    public BSTree(){  
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node!.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
    }    

    public BSTree(int address, int size, int key){
        super(address, size, key); 
    }

    public BSTree Insert(int address, int size, int key) 
    { 
        
        // Inserts a node in the subtree and returns the corresponding dictionary element created and inserted
        // Assumes that subtree is a BST sorted with respect to the key.
        BSTree y=null,x, z=new BSTree(address,size,key);
        x=root(this);
        if(x==null){
           // System.out.println("3.");
            this.right=z;
            z.parent=this;
            return z;
        }
        while(x!=null){
            y=x;
            if(key<=x.key){
                x=x.left;
            }
            else{
                x=x.right;
            }
        }
        x=y;
        if(y==null){
            this.right=z;
            z.parent=this;
            return z;
        }
        while(y!=null&&y.key==key){
            x=y;
            if(address>y.address){
                y=y.getNext();
            }
            else{
                y=y.getPrev();
            }
        }
        if(x.key==key){
            if(x.address<address){
                if(x.right==null){
                    x.right=z;
                    z.parent=x;
                }
                else{
                    x=treemin(x.right);
                    x.left=z;
                    z.parent=x;
                }
            }
            else{
                if(x.left==null){
                    x.left=z;
                    z.parent=x;
                }
                else{
                    x=treemax(x.left);
                    x.right=z;
                    z.parent=x;
                }
            }
        }        
        else if(key<y.key){
            y.left=z;
            z.parent=y;
        }
        else{
            y.right=z;
            z.parent=y;
        }
        return z;
    }
    void remove(BSTree r){
        if(r.left==null&&r.right==null){
            System.out.println("2.1");
            if(r.parent.left==r){
                r.parent.left=null;
            }
            else{
                r.parent.right=null;
            }
        }
        else if(r.left==null){
            System.out.println("2.2");
            if(r.parent.left==r){
                r.parent.left=r.right;
                r.right.parent=r.parent;
            }
            else{
                r.parent.right=r.right;
                r.right.parent=r.parent;
            }
        }
        else if(r.right==null){
            System.out.println("2.3");
            if(r.parent.left==r){
                r.parent.left=r.left;
                r.left.parent=r.parent;
            }
            else{
                r.parent.right=r.left;
                r.left.parent=r.parent;
            }
        }
        else{
            //successor
            System.out.println("2.4");
            BSTree x=treemax(r.left);
            swap(r.key,x.key);
            swap(r.address,x.address);
            swap(r.size,x.size);
            remove(x);
            /*if(x.parent.left==x){
                if(x.left==null){
                    x.parent.left=null;
                }
                else{
                    x.parent.left=x.left;
                    x.left.parent=x.parent;
                }
            }
            else{
                if(x.left==null){
                    x.parent.right=null;
                }
                else{
                    x.parent.right=x.left;
                    x.left.parent=x.parent;
                }
            }*/
        }
        return;
    }
    void swap(int a,int b){
        int temp=a;
        a=b;
        b=temp;
        return;
    }
    public boolean Delete(Dictionary e)
    { 
        // Deletes the entry corresponding to e from the subtree.
        // Assumes that the subtree is BST sorted in non-decreasing order of key
        // Searches for the e.key in the subtree
        // Deletes the element it is found in the subtree and returns true. 
        // Note there may be multiple elements with the same key value. 
        // Delete searches for the node with the same key and same e and returns true only if e as well as e.key match
        // Returns false if e not found in the subtree.
        BSTree r=this,x;
        if(r.key==e.size&&r.address==e.address&&r.size==e.size){
            //only root
            if(r.parent.parent==null&&r.left==null&&r.right==null){
                swap(r.key,r.parent.key);
                swap(r.address,r.parent.address);
                swap(r.size,r.parent.size);
                r.parent=null;
                r.right=null;  
            } 
            //left is not null
            else if(r.left!=null){
                x=treemax(r.left);
                swap(r.key,x.key);
                swap(r.address,x.address);
                swap(r.size,x.size);
                remove(x);
            }
            else if(r.right!=null){
                x=treemin(r.right);
                swap(r.key,x.key);
                swap(r.address,x.address);
                swap(r.size,x.size);
                remove(x);
            }
            else{
                swap(r.key,r.parent.key);
                swap(r.address,r.parent.address);
                swap(r.size,r.parent.size);
                remove(r.parent);
            }
            System.out.println("1.");
            return true;            
        }
        r=this.Find(e.key,true);
        if(r!=null){
            if(r.address==e.address&&r.size==e.size){
                remove(r);
                System.out.println("2.");
                return true;
            }
            else{
                while(e.key==r.key){
                    if(r.address==e.address&&r.size==e.size){
                        remove(r);
                        System.out.println("3.");
                        return true;
                    }
                    r=r.getNext();
                }
            }
        }
        return false;
    }
        
    public BSTree Find(int key, boolean exact)
    { 
        // If exact is true, then performs and exact match and returns an element of the dictionary with key = k
        // and returns null if no such element exists.

        // If exact is false, performs an approximate search and
        // returns the element with SMALLEST key such that key >= k in the subtree.  Returns null in case no such element found.

        // Can be used to implement the Best Split Fit strategy when called on sentinel node or root node with exact = false
        BSTree x,y=null;
        if(exact==true){
            x=root(this);
            while(x!=null && x.key!=key){
                if(key==x.key){
                    y=x;
                    break;
                }
                else if(key<x.key){
                    x=x.left;
                }
                else{
                    x=x.right;
                }
            }
            if(x!=null){
                while(x!=null&&x.key==key){
                    y=x;
                    x=x.getPrev();
                }
                return y;
            }
            return y;
        }
        else{
            x=root(this);
            while(x!=null&&x.key!=key){
                //y=x;
                if(x.key==key){
                    break;
                }
                else if(x.key<key){
                    x=x.right;
                }
                else{
                    y=x;
                    x=x.left;
                }
            }
            
            if(x!=null){
                while(x!=null&&x.key==key){
                    y=x;
                    x=x.getPrev();
                }
                return y;
            }
            return y;
        }
    }

    BSTree treemin(BSTree x){
        //x is the root node of the tree. 
        //function returns the minimum node with minimum value given tree
        while(x.left!=null){
            x=x.left;
        } 
        return x;
    }

    BSTree root(BSTree x){
        //finds the root node of the tree
        if(x.parent==null&&x.right==null)
            return null;
        else if(x.parent==null&&x.right!=null)
            return x.right;    
        while(x.parent!=null){
            x=x.parent;
        }
        return x.right;
    }

    BSTree treemax(BSTree x){
        //x is the root node of the tree. 
        //function returns the maximum node with maximum value given tree
        while(x.right!=null){
            x=x.right;
        }
        return x;
    }

    public BSTree getFirst()
    { 
        BSTree current=root(this);
        if(current!=null){
            return treemin(current); 
        }    
        return null;
    }
    BSTree getPrev(){
        BSTree current=this;
        if(current.left!=null){
            return current.left;
        }
        else if(current.parent!=null&&current.parent.right==current){
            return current.parent;
        }
        else if(current.parent!=null&&current.parent.left==current){
            BSTree x=current.parent;
            while(x!=null&&x.right!=current){
                x=x.parent;
                current=current.parent;
            }
            return x;
        }
        return null;
    }
    public BSTree getNext()
    { 
        BSTree current=this;
        if(current.right!=null){
            return current.right;
        }
        else if(current.parent!=null&&current.parent.left==current){
            return current.parent;
        }
        else if(current.parent!=null&&current.parent.right==current){
            BSTree x=current.parent;
            while(x!=null&&x.left!=current){
                x=x.parent;
                current=current.parent;
            }
            return x;
        }
        return null;
    }
    
    public boolean sanity()
    { 
        // Checks the sanity of the BST subtree and returns true if sane, false otherwise
        BSTree x,y,first=this.getFirst();
        //check getfirst
        if(first.left!=null&&first.right!=null){
            //System.out.println("faulty BST");
            return false;
        }
        //check getnext
        x=first;
        while(x!=null&&x.getNext()!=null){
            y=x.getNext();
            if(x.key>=y.key){
                //System.out.println("faulty BST");
                return false;
            }
            x=y;
        }

        return true;
    }

}


 


