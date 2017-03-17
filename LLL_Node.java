//Project 4, Room for Rent
/*
 * This is the Linear Linked List abstract class. I'm not sure I'm completely comfortable with the hierarchy structures 
 * in Java, so I'm concerned that I did this wrong. This <i>should</i> be a good generic node which any class can be 
 * derived from if they want to include a linear linked list aspect. It has all the function headings, in abstract, which 
 * would be required for managing a generic LLL.
 */
abstract public class LLL_Node {
	protected Object Data;
	protected LLL_Node Next;
	
	public LLL_Node(){
		this.Data = null;
		this.Next = null;
	}
	public LLL_Node(Object T){
		this.Data = T;
		this.Next = null;
	}
	public void clone(LLL_Node T){
		this.Data = T.Data;
	}
	public boolean Equals(LLL_Node T){
		return(this.Data.equals(T.Data));
	}
	public LLL_Node go_next(){
		return Next;
	}
	public void set_next(LLL_Node T){
		this.Next = T;
	}
	public abstract void display();	
}
