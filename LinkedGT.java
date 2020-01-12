public class LinkedGT<T> implements GT<T>{

	GTNode <T> root;
	GTNode <T> current;
        

	
	public <T> LinkedGT(){
            root = null;
            current = null;
        }
        
        public boolean empty(){
		return root == null;
	}

	// Return true if the tree is full
	public boolean full(){
		return false;		
	}

	// Return the data of the current node
	public T retrieve(){
		return current.data;
	}

	// Update the data of the current node
	public void update(T e){
                current.data = e;
	}
        
        

	// If the tree is empty e is inserted as root. If the tree is not empty, e is added as a child of the current node. The new node is made current and true is returned.
	public boolean insert(T e){
                if(empty()){
                    root = new GTNode<T>(e);
                    current = root;
		return true;
                }else{
                    //current.addChild(e);
                    GTNode<T> add = new GTNode<T>(e);
                    add.parent = current;
                    current.Children.insert(add);
                    current = add;
                    return true;
                }
	}
        
        
        
        
	// Return the number of children of the current node.
	public int nbChildren(){
		return current.Children.getLength();		//change later
	}

	// Put current on the i-th child of the current node (starting from 0), if it exists, and return true. If the child does not exist, current is not changed and the method returns false.
	public boolean findChild(int i){
            
            if(current.Children == null){
                return false;
            }
            
            if (i >= current.Children.getLength()|| current.Children.empty())
            return false;
            
            current.Children.findFirst();
            GTNode<T> m = current.Children.retrieve();
            for (int k = 0; k < i; k++){
                current.Children.findNext();
                m = current.Children.retrieve();
            }
            current = m;
            return true;
            
				//change later
	}
        

	// Put current on the parent of the current node. If the parent does not exist, current does not change and false is returned.
	public boolean findParent(){
            
            if (current!= root){
                current = current.parent;
                return true;
            
            }
            
		return false;
			//change later
	}
        
        
        
        public boolean hasChildren(){
            return current.hasChildren();
        }

	// Put current on the root. If the tree is empty nothing happens.
	public void findRoot(){
            if(!empty())
                current = root;
	}

	// Remove the current subtree. The parent of current, if it exists, becomes the new current.
	public void remove(){
            
            if (current == null){
                return;
            }
            
            if(current==root){
                current = root = null;
                return;
            }
            
            GTNode<T> p = current;
            GTNode<T> s = new GTNode<T>();
            findParent();
            current.Children.findFirst();
            
            while(!current.Children.last()){
                    s = current.Children.retrieve();
                if (s.equals(p)){
                    current.Children.remove();
                    return;
                }
                current.Children.findNext();
            }
            
            s=current.Children.retrieve();
            
            if (s.equals(p)){
                    current.Children.remove();
                    return;
                }
            
                
                
	}
        
        
        

}
