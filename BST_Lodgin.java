//Project 4: Room for Rent
/*This is the BST_Lodgin(g) class. This class is derived from the Lodging class, and adds
 *controls for manipulating the binary search tree. It represents a specific listing for a 
 *rental. It has many methods, but they are almost exclusively for navigating and manipulating 
 *the Cities BST of rentals. It also contains a method for writing all the information stored
 *in the node to an external data file.
 */

import java.io.*;

public class BST_Lodgin extends Lodging{
	protected BST_Lodgin Left;
	protected BST_Lodgin Right;
	public BST_Lodgin(){
		super();
		this.Left = null;
		this.Right = null;
	}
	public BST_Lodgin(Lodging to_add){
		super(to_add);	
		this.Left = null;
		this.Right = null;	
	}
	public BST_Lodgin(String name, String City, String State, String Type){
		super(name,City,State,Type);
		this.Left = null;
		this.Right = null;
	}
	public BST_Lodgin go_left(){
		return this.Left;
	}
	public void set_left(Lodging to_add){
		this.Left = new BST_Lodgin(to_add);
	}
	public BST_Lodgin go_right(){
		return this.Right;
	}
	public void set_right(Lodging to_add){
		this.Right = new BST_Lodgin(to_add);
	}
	public boolean equals(BST_Lodgin T){
		return((this.Left == T.Left)&&(this.Right == T.Right)&&(super.equals(T)));
	}
	public boolean Equals(String name){
		return(super.equals(name));
	}
	@Override
	public boolean Equals(Lodging T){
		return(super.Name.equals(T.Name));
	}
	@Override
	public boolean SameCity(Lodging T){
		return(super.SameCity(T));
	}
	public void clone(BST_Lodgin T){
		if(T.Left != null){
			this.Left = new BST_Lodgin();
			this.Left.clone(T.Left);
		}
		if(T.Right != null){
			this.Right = new BST_Lodgin();
			this.Right.clone(T.Right);
		}
		super.clone(T);
	}
	public boolean isBiggerThan(Lodging T){
		int result = Name.compareTo(T.Name);
		return(result >0);
	}
	public boolean isBiggerThan(String name){
		int result = this.Name.compareTo(name);
		return(result >0);
	}
	public boolean isBiggerThan(BST_Lodgin T){
		int result = this.Name.compareTo(T.Name);
		return(result >0);
	}
	public boolean isSmallerThan(String name){
		int result = this.Name.compareTo(name);
		return(result < 0);
	}
	public boolean isSmallerThan(Lodging T){
		int result = this.Name.compareTo(T.Name);
		return(result < 0);
	}
	public boolean isSmallerThan(BST_Lodgin T){
		int result = this.Name.compareTo(T.Name);
		return(result < 0);
	}
	@Override
	public void display(){
		if(this.Left != null){
			this.Left.display();
		}
		super.display();
		if(this.Right != null){
			this.Right.display();
		}
	}
	protected BST_Lodgin FindHotel(BST_Lodgin T){
		if(this.Equals(T)){
			return this;
		}
		if(this.isBiggerThan(T) && this.Left != null){
			
			return this.Left.FindHotel(T);
		}
		if(this.isSmallerThan(T) && this.Right != null){
			return this.Right.FindHotel(T);
		}
		System.out.println("Hotel Not Found");
		BST_Lodgin empty = null;
		return empty;
	}
    protected void writeAll(File tf, BufferedWriter out){
       	try{
       		out.write(this.Name); out.newLine();
       		out.write(this.City); out.newLine();
       		out.write(this.State); out.newLine();
       		out.write(this.Type); out.newLine();
       		if(head != null){
       			out.write(":;");
       			this.head.writeAll(out);     
       			out.newLine();
       		}else{
       			out.newLine();
       		}
       		if(available != null){
       		    this.available.writeAll(out);
       		    out.newLine();
       		}else{
       			out.newLine();
       		}
       		if(Blackout != null){
       			out.write(this.Blackout[0]);
       			for(int i = 1; i<this.Blackout.length; ++i){
       				out.write(", ");
       				out.write(Blackout[i]);
       			}
       			out.newLine();
       		}else{
       			out.newLine();
       		}
       		if(Left != null){
       			Left.writeAll(tf,out);	
       		}
       		if(Right != null){
       			Right.writeAll(tf, out);
       		}
    	}catch (Exception e){
    		System.err.println("Error: "+e.getMessage());
    	}   	
    }
}
