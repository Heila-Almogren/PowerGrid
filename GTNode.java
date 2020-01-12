/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author heila
 */
public class GTNode <T> {
    	public T data;
        public ElemType Type;
	public GTNode<T> next;
        public LinkedList <GTNode<T>> Children;
        public GTNode<T> parent;

	public GTNode () {
		data = null;
		next = null;
	}
       
	public GTNode (T val) {
		data = val;
		next = null;
                Children = new LinkedList <GTNode<T>>();
	}
        
        
        
        public GTNode (T val, LinkedList<GTNode<T>> L) {
		data = val;
		next = null;
                Children = L;
	}
        
        public GTNode (GTNode<T> N){
        data = N.data;
        Type = N.Type;
	next = N.next;
        Children = N.Children;
        }
        
        public T getData(){
            return data;
        }
        
        public void setData(T e){
            data = e;
        }
        
        public GTNode<T> next(){
            return next;
        }
        
        public void setNext(GTNode<T> N){
            next = N;
        }
        
        public List<GTNode<T>> getChildren(){
            return Children;
        }
        
        
        
        public void setParent(GTNode<T> p){
            parent = p;
        }
        public boolean hasChildren(){
            return (Children != null);
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
}
