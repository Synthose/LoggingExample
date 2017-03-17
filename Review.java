import java.io.*;
//Project 4: Room for Rent pt1
/*
 * This is the class for Reviews. This will store a review in a LLL. The review has a string
 * element, which is the actual review; a double for the rating, and a double for the cost. of
 * the stay. It also a next pointer which will point to the next Review in the list. If the Next
 * reference is null, then it is the last review for the the rental.
 */
public class Review extends LLL_Node{
	protected String review;
	protected double rating;
	protected double cost;
	protected Review Next;
	
	public Review(){
		super();
		rating = 0;
		cost = 0.0;
	}
	public Review(String t_review, int t_rating, double t_cost){
		super();
		review = t_review;
		rating = t_rating;
		cost = t_cost;
	}
	public Review(Review to_add){
		clone(to_add);
	}	
	public Review(Review to_add, Review up_next){
		clone(to_add);
		Next = up_next;
	}
	public boolean equals(Review target){
		return(target.review == review && target.rating == rating && target.cost == cost);
	}
	public void clone(Review target){
		review = target.review;
		rating = target.rating;
		cost = target.cost;
		Next = target.Next;
	}
	public void setNext(Review nextNode){
		this.Next = nextNode;
	}
	@Override
	public void display(){
		System.out.println(this.review);
		System.out.println("Rating:"+this.rating+"/5");
		System.out.println("Cost: $"+this.cost);
		if(this.Next != null)
			display(this.Next);
	}
	protected void display(Review here){
		if(here == null)
			return;
		System.out.println(here.review);
		System.out.println("Rating:"+here.rating+"/5");
		System.out.println("Cost: $"+here.cost);
	    display(here.Next);		
	}
	@Override
	public Review go_next(){
		return Next;
	}
	public double get_rating()
	{
		return rating + get_rating(Next);
	}
	public double get_cost(){
		return cost + get_cost(Next);
	}
	protected double get_rating(Review here){
		if(here == null){
			return 0;
		}
		return here.rating+ get_rating(here.Next);
	}
	protected double get_cost(Review here){
		if(here == null){
			return 0.0;
		}
		return here.cost + get_cost(here.Next);		
	}
	protected void writeAll(BufferedWriter out){
       	try{
       		out.write(this.review); out.write(":;");
       		String temp = String.valueOf(rating);
       		String temp2 = String.valueOf(cost);
       		out.write(temp.charAt(0)); out.write(":;");
       		out.write(temp2);
       		if(this.Next != null){
       			out.write(":;");
       			this.Next.writeAll(out);
       		}
       	}catch (Exception e){
    		System.err.println("Error: "+e.getMessage());
    	}   	
	}
}
