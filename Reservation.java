//Project 5: Booking
//This is the Reservation class. It extends the Lodging class, and adds a check in
// and check out date to the information. 
public class Reservation extends Lodging{
    protected Date check_in;
    protected Date check_out;
    
    public Reservation(){
    	//default constructor. Calls the constructor for Lodging
    	super();
    	check_in = null;
    	check_out = null;
    }
    public Reservation(Lodging T, Date in, Date out){
    	//Constructor which sets the info for the rental, plus a check in and out date.
    	super(T);
    	check_in = new Date(in);
    	check_out = new Date(out);
    }
    public Reservation(Lodging T){
    	//constructor does creates the rental, but does not set a check in or out date.
    	super(T);
    }
    @Override
	public void display(){
    	//displays all the relevant information about this reservation.
    	//doesn't display all the information about the rental, just enough
    	//for the itinerary.
    	if(Name == null || City ==null || State == null){
			System.out.println("No info to print for this entry!");
			return;
    	}
    	System.out.println("Your reservation for the "+this.Type+" "+this.Name+", in "+this.City+", "+this.State+".");
    	System.out.print("You are due to check in on "); this.check_in.display();System.out.println();
    	System.out.print("You are checking out on "); this.check_out.display();System.out.println();
    }
    public void set_dates(Date in, Date out){
    	//Sets or resets the check in and out date.
    	this.check_in = new Date(in);
    	this.check_out = new Date(out);
    }
}
