// Class: Height balanced AVL Tree
// Binary Search Tree

public class AVLTree extends BSTree {
    
    private AVLTree left, right;     // Children. 
    private AVLTree parent;          // Parent pointer. 
    private int height;  // The height of the subtree
        
    public AVLTree() { 
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node !.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
        
    }

    public AVLTree(int address, int size, int key) { 
        super(address, size, key);
        this.height = 0;
    }

    // Implement the following functions for AVL Trees.
    // You need not implement all the functions. 
    // Some of the functions may be directly inherited from the BSTree class and nothing needs to be done for those.
    // Remove the functions, to not override the inherited functions.
    
    public AVLTree Insert(int address, int size, int key) 
    { 
        AVLTree t=new AVLTree(address,size,size);
        AVLTree r=root(this);
        if(r==null){
            //System.out.println("1.1.....");
            this.right=t;
            t.parent=this;
            //t.height++;
            //System.out.println("1.1....."+this.right.key+" "+this.address);
            return t;
        }
        //System.out.println("1.....");
        return add(t,r);
    }
    AVLTree add(AVLTree node,AVLTree t){
        AVLTree x;
        //System.out.println("2.....");
        if(t.key==node.key){
            //System.out.println("2....=.");
            if(t.address>node.address){
                if(t.left==null){
                    t.left=node;
                    node.parent=t;
                    t.height++;
                    x= node; 
                }
                else{
                    x=add(node,t.left);
                }
            }
            else{
                if(t.right==null){
                    t.right=node;
                    node.parent=t;
                    t.height++;
                    x=node;
                }
                else{
                    x=add(node,t.right);
                }
            }
        }
        else if(t.key>node.key){
            //System.out.println("2.....<");
            if(t.left==null){
                t.left=node;
                node.parent=t;
                t.height++;
                /*while(t.parent!=null){
                    t.height++;
                    t=t.parent;
                }*/
                x= node;
            }
            else{
                x=add(node, t.left);
            }
        }
        else{
            //System.out.println("2.....>");
            if(t.right==null){
                t.right=node;
                node.parent=t;
                t.height++;
                /*while(t.parent!=null){
                    t.height++;
                    t=t.parent;
                }*/
                x= node;
            }
            else{
                x=add(node, t.right);
            }
        }
        //System.out.println("2.0....."+t.key+" "+t.left.key);
        if(t.right==null&&t.left==null){
            t.height=0;
        }
        else if(t.left==null&&t.right!=null){
        t.height=t.right.height+1;
        //System.out.println("2.1.....");
        checkbalance(t);
        }
        else if(t.left!=null&&t.right==null){
        t.height=t.left.height+1;
        //System.out.println("2.2....."+t.key+" "+t.left.key);
        checkbalance(t);
        //System.out.println("2.2....."+t.key+" "+t.left.key);
        }
        else if(t.left!=null&&t.right!=null)
        {t.height=Math.max(t.left.height,t.right.height)+1;
        //System.out.println("2.3....."+t.left.height+" "+t.right.height);
        checkbalance(t);
        }
        return x;
    }
    void checkbalance(AVLTree t){
        
        if(t.left==null&&t.right!=null&&t.right.height>0){
            //System.out.println("unbalance ..1.....");
            //inorder(root(t));
            balance(t);
            return;
        }
        else if(t.right==null&&t.left!=null&&t.left.height>0){
            //System.out.println("unbalance ..2.....");
            balance(t);
            return;
        }
        if(t.left!=null&&t.right!=null&&(Math.abs(t.left.height-t.right.height)>1)){
            //System.out.println(t.left.height+" l r "+t.right.height);
            balance(t);
        }
        return;
    }
    void balance(AVLTree t){
        //null case
        if(t.left==null&&t.right==null){
            t.height=0;
            return;
        }
        if(t.right==null){
            //System.out.println("...balance 2..");
            //inorder(root(t));
            //System.out.println("\n.....");
            //preorder(root(t));
            //if(root(t).right!=null)
            //System.out.println("\n"+root(t).right.parent.key+" || ");
            AVLTree k;
            if(t.left.right==null){
                 k=t.left;
                if(t.parent.left==t){
                    t.parent.left=k;
                    k.parent=t.parent;
                }
                else{
                    t.parent.right=k;
                    k.parent=t.parent;
                }
                k.right=t;
                t.parent=k;
                t.left=null;
                k.left.height=0;
                k.right.height=0;
                k.height=Math.max(k.left.height,k.right.height)+1;
            }
            else if(t.left.left==null){
                k=t.left.right;
                if(t.parent.left==t){
                    t.parent.left=k;
                    k.parent=t.parent;
                }
                else{
                    t.parent.right=k;
                    k.parent=t.parent;
                }
                //System.out.println(k.parent.key+" "+t.parent.key+" "+t.parent.getheight()+" || ");
                k.left=t.left;
                k.left.parent=k;
                k.right=t;
                t.parent=k;
                t.left=null;
                k.left.right=null;
                k.left.height=0;
                k.right.height=0;
                k.height=Math.max(k.left.height,k.right.height)+1;
            }
            else if(t.left.left!=null&&t.left.right!=null){
                rightrotate(t);
            } 
            return;            
        }
        if(t.left==null){
            //System.out.println("...balance 1..");
            if(t.right.left==null){
                AVLTree k=t.right;
                /*preorder(t);
                inorder(t);
                System.out.println("...\nbalance 1.."+t.key);
                preorder(root(t));
                inorder(root(t));*/
                if(t.parent.left==t){
                    t.parent.left=k;
                    k.parent=t.parent;
                }
                else{
                    t.parent.right=k;
                    k.parent=t.parent;
                }
                k.left=t;
                t.parent=k;
                t.right=null;
                k.left.height=0;
                k.right.height=0;
                k.height=Math.max(k.left.height,k.right.height)+1;
            }
            else if(t.right.right==null){
                AVLTree k=t.right.left;
                if(t.parent.left==t){
                    t.parent.left=k;
                    k.parent=t.parent;
                }
                else{
                    t.parent.right=k;
                    k.parent=t.parent;
                }
                k.left=t;
                k.right=t.right;
                k.right.parent=k;
                k.right.left=null;
                t.parent=k;
                t.right=null;
                k.left.height=0;
                k.right.height=0;
                k.height=Math.max(k.left.height,k.right.height)+1;
            }
            //newcase
            else if(t.right.right!=null&&t.right.left!=null){

                leftrotate(t);
            }
            return;
        }
        //normal case
        else if((t.right.height-t.left.height)>1){
            if(t.right.right.height>t.right.left.height){
                //System.out.println("\n .1......"+t.key);
                leftrotate(t);
            }
            else{
                //System.out.println("\n ...1.1...."+t.key);
                //rightrotate(t.right);
                leftrotate(rightrotate(t.right).parent);
            }
        }
        else if((t.right.height-t.left.height)<-1){
            if(t.left.right.height<t.left.left.height){
                rightrotate(t);
            }
            else{
                //System.out.println("\n ...2...."+t.key);
                //leftrotate(t.left);
                rightrotate(leftrotate(t.left).parent);
            }
        }
    }
    AVLTree leftrotate(AVLTree t){
        //inorder(root(t));
        //print(t);
        //System.out.println("\n ......."+t.parent.key);
        //preorder(root(t));
        AVLTree x=t.right;
        x.parent=t.parent;
        if(t.parent.left==t){
            t.parent.left=x;
        }
        else{
            t.parent.right=x;
        }
        //see again
        if(x.left!=null){
        t.right=x.left;
        x.left.parent=t;
        }
        else
        t.right=null;
        x.left=t;
        t.parent=x;
        if(t.right!=null&&t.left!=null)
        t.height=Math.max(t.left.height,t.right.height)+1;
        else if(t.right==null&&t.left==null)
        t.height=0;
        else
        t.height=1;
        if(x.right!=null&&x.left!=null)
        x.height=Math.max(x.left.height, x.right.height)+1;
        else if(x.right!=null&&x.left==null)
        x.height=x.right.height+1;
        else if(x.left!=null&&x.right==null)
        x.height=x.left.height+1;
        //print(t);
        return x;
    }
    AVLTree rightrotate(AVLTree t){
        AVLTree x=t.left;
        x.parent=t.parent;
        if(t.parent.left==t){
            t.parent.left=x;
            x.parent=t.parent;
        }
        else{
            t.parent.right=x;
            x.parent=t.parent;
        }
        if(x.right!=null){
        t.left=x.right;
        x.right.parent=t;
        //x.left.parent=t;
        }
        else
        t.left=null;
        x.right=t;
        t.parent=x;
        if(t.right!=null&&t.left!=null)
        t.height=Math.max(t.left.height,t.right.height)+1;
        else if(t.left==null&&t.right==null)
        t.height=0;
        else
        t.height=1;
        if(x.right!=null&&x.left!=null)
        x.height=Math.max(x.left.height, x.right.height)+1;
        else if(x.right!=null&&x.left==null)
        x.height=x.right.height+1;
        else if(x.left!=null&&x.right==null)
        x.height=x.left.height+1;
        //print(t);
        return x;
    }
    AVLTree root(AVLTree t){
        if(t.parent==null&&t.right==null)
            return null;
        while(t.parent!=null){
            t=t.parent;
        }
        return t.right;
        
    } 
    public boolean Delete(Dictionary e)
    {   //System.out.println("delete....."+e.key+" "+e.address);
        AVLTree r=this,x;
        if(r==null)
            return false;
        if(r.key==e.key&&r.address==e.address){
            if(r.parent!=null&&r.parent.parent==null&&r.left==null&&r.right==null){
                swap(r,r.parent);
                r.parent.right=null;
                r.parent=null;
            }
            else{
                x=r.getNext();
                if(x!=null){
                    swap(r,x);
                    x= del(x,r.right);
                }
                else{
                    x=r.getPrev();
                    swap(r,x);
                    x=del(x, r);
                }
                
            }
        }        
        x=root(r);
        x=del(e,x);
        //System.out.println( this.sanity());
        if(x!=null)
        return true;
        return false;
    }
    AVLTree del(Dictionary e,AVLTree r){
        //System.out.println("2.2....."+r.key+" "+r.address);
        AVLTree x=null;
        if(r==null){
            return null;
        }
        if(e.key<r.key){
            x=del(e,r.left);
            x=r;
            //2
        }
        else if(e.key>r.key){
            //print(root(r));
            //System.out.println("2.2....."+r.key+" "+r.right.key);
            x=del(e,r.right);
            x=r;
            //1
        }
        else if(e.key==r.key){
            //System.out.println("2.3.key found "+r.address+" "+e.address);
            if(e.address<r.address){
                del(e,r.left);
                x=r;
            }
            else if(e.address>r.address){
                del(e,r.right);
                x=r;
            }
            //remove
            if(e.address==r.address){
                //System.out.println("2.3....."+r.key+" "+r.address);
                if(r.left==null&&r.right==null){
                    if(r.parent.left==r){
                        r.parent.left=null;
                    }
                    else{
                        r.parent.right=null;
                    }
                    x=r.parent;
                    //r.parent=null;
                    //r.parent.height--;
                }
                else if(r.left==null){
                    if(r.parent.left==r){
                        r.parent.left=r.right;
                        r.right.parent=r.parent;
                    }
                    else{
                        r.parent.right=r.right;
                        r.right.parent=r.parent;
                    }
                    x=r.parent;
                    //r.parent.height--;
                }
                else if(r.right==null){
                    if(r.parent.left==r){
                        r.parent.left=r.left;
                        r.left.parent=r.parent;
                    }
                    else{
                        r.parent.right=r.left;
                        r.left.parent=r.parent;
                    }
                    x=r.parent;
                    //r.parent=null;
                    r.left=null;
                    //r.parent.height--;
                }
                else{
                    x=treemax(r.left);
                    swap(r,x);
                    //System.out.println("2.4....."+x.address+" "+r.address);
                    AVLTree y=r;
                    del(x,r.left);
                    x=y;
                }
            }
        }
        //inorder(root(x));
        //System.out.println("......\n....."+x.key);
        //preorder(root(x));
        //if(x.key==22)

        if(x!=null&&x.parent!=null)
        {
        if(x.right==null&&x.left==null){
            x.height=0;
        }
        else if(x.left==null&&x.right!=null){
            //System.out.println("1.3.1....."+x.right.height+" "+x.height);
            x.height=x.right.height+1;
            //System.out.println("1.3.2....."+x.right.height+" "+x.height);
            checkbalance(x);
            //System.out.println("1.3.3....."+x.right.height+" "+x.height);
        }
        else if(x.left!=null&&x.right==null){
            //System.out.println("1.3.1....."+x.left.height+" "+x.height);
            x.height=x.left.height+1;
            //System.out.println("1.3.2....."+x.left.height+" "+x.height);
            checkbalance(x);
        }
        else if(x.left!=null&&x.right!=null){
            //System.out.println("2.3.1....."+x.left.height+" "+x.right.height+" "+x.height);
            x.height=Math.max(x.left.height,x.right.height)+1;
            //System.out.println("2.3....."+x.left.height+" "+x.right.height+" "+x.height);
            checkbalance(x);
        }
    }
        return x;
    }
    void swap(AVLTree a,AVLTree b){
        int temp=a.size;
        a.size=b.size;
        b.size=temp;
        temp=a.address;
        a.address=b.address;
        b.address=temp;
        temp=a.key;
        a.key=b.key;
        b.key=temp;
        return;
    }
    AVLTree treemax(AVLTree x){
        while(x.right!=null){
            x=x.right;
        }
        return x;
    }
    AVLTree treemin(AVLTree x){
        while(x.left!=null){
            x=x.left;
        }
        return x;
    }
    AVLTree getPrev(){
        AVLTree t=this;
        if(t.left!=null){
            return treemax(t.left);
        }
        while(t.parent!=null&&t.parent.right!=t){
            t=t.parent;
        }
        return t.parent;
    }    
    public AVLTree Find(int k, boolean exact)
    { 
        AVLTree x,y=null;
        x=root(this);
        if(x==null)
            return null;
        if(exact==true){
            while(x!=null && x.key!=key){
              if(key<=x.key){
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
            }
            return y;
        }
        else{
            while(x!=null&&x.key!=key){
                //y=x;
                if(x.key<key){
                    x=x.right;
                }
                else{
                    y=x;
                    x=x.left;
                }
            }
            if(x!=null&&x.key==key){
                y=x;
            }
            if(y!=null){
                x=y;
                while(x!=null&&x.key==key){
                    y=x;
                    x=x.getPrev();
                }
                return y;
            }
            return y;
        }
    }

    public AVLTree getNext()
    { 
        AVLTree t=this;
        if(t.right!=null){
            return treemin(t.right);
        }
        while(t.parent!=null&&t.parent.left!=t){
            t=t.parent;
        }
        return t.parent;
    }

    public AVLTree getFirst()
    {
        AVLTree t=root(this);
        if(t!=null){
            return treemin(t);
        }
        return null;
    }
    /*public int getheight(){
        return this.height;
    }*/

 boolean heightsanity(AVLTree r){
        boolean x=true,y=true;
        if(r!=null){
            
            if((r.left==null&&r.right==null&&r.height!=0)||(r.left==null&&r.right!=null&&r.right.height>0)||(r.right==null&&r.left!=null&&r.left.height>0)||(r.left!=null&&r.right!=null&&Math.abs(r.left.height-r.right.height)>1))
            return false;
            x=heightsanity(r.left);
            y=heightsanity(r.right);
        }
        return x&&y;
    }
Boolean loop(AVLTree x,Boolean flag){
    Boolean a=true,b=true;
    if(x!=null){
        if(x.left!=null&&x.left.parent!=x){
            flag=false;              
        }
        if(x.right!=null&&x.right.parent!=x){
            flag=false;
        }
        a=loop(x.left,flag);
        b=loop(x.right,flag);
    }
    return a&&b&&flag;
}
    public boolean sanity()
    { 
        // Checks the sanity of the BST subtree and returns true if sane, false otherwise
        AVLTree x,y,first=this.getFirst();
        //check getfirst
        if(first!=null&&first.left!=null&&first.right!=null){
            //System.out.println("faulty BST getfirst");
            return false;
        }
        //loop check
        x=root(this);
        if(x!=null&&x.loop(x, true)==false){
            //System.out.println("faulty BST node");
            return false;
        }
        //check getnext
        x=first;
        while(x!=null&&x!=null){
            y=x.getNext();
            if(y!=null&&x.key>y.key){
                //System.out.println("faulty BST getnext");
                return false;
            }
            x=y;
        }
        x=root(this);
        return heightsanity(x);
    }
}


