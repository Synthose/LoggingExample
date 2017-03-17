//Project 5: Booking
/*This is the Trip class. This class manages one trip from start to completion. It takes in
 * a start and end date, as well as a budget for the trip. It holds a reference to a "Cities"
 * node, which is the start of a DLL of Cities in which you will be staying. You can add new 
 * reservations, view your trip itinerary, add more funds to your trip, and adjust the start and 
 * end day of the trip.
 */
public class Trip {
	protected Date start;
	protected Date leave;
	protected Cities Agenda;
	protected double budget;
	
	public Trip(){
		//Default constructor.
		start = null;
		leave = null;
		Agenda = null;
		budget = 0.0;	
	}
	public Trip(Date t_Start){
		//Constructor which sets the start day, but nothing else.
		this.start.Clone(t_Start);
		this.leave = null;
		Agenda = null;
		budget = 0.0;
	}
	public Trip(Date t_Start, Date t_End, double t_funds){
		//Constructor which sets the start and end date, as well as a budget.
		//this is the constructor which will be called most frequently.
		this.start = new Date(t_Start);
		this.leave = new Date(t_End);
		Agenda = null;
		budget = t_funds;
	}
    public void add_funds(double funds){
    	//adds funds to the budget for this trip.
    	this.budget += funds;
    }
    public void add_reservation(Lodging T, Date in, Date out){
    	//Checks to see if the desired reservation is within the start and end date, and if it
    	//is, adds it to the correct City node, then adjusts the Budget.
    	if(this.Agenda == null){
    		this.Agenda = new Cities(T.City, T.State);
    	}
    	if(out.is_before(this.start) || in.is_before(this.start)){
    		System.out.println("Check in or Check out day before trip start.");
    		return;
    	}
    	if(this.leave.is_before(in) || this.leave.is_before(out)){
    		System.out.println("Check in or Check out day after trip completion");
    		return;
    	}
    	this.Agenda.add_reservation(T, in, out);
    	this.budget -= (T.cost * (in.time_between(out)));
    	this.check_funds();
    }
    public void check_funds(){
    	//This will check the users current funds, and see if they are running out of money!
    	if(this.budget == 0){
    		System.out.println("You have exausted all your funds.");
    	}
    	if(this.budget < 0){
    		System.out.println("You have spent more money than you budgeted.");
    		System.out.println("Please adjust your spending, or add more funds!");
    	}
    }
    public void Clone(Trip T){
    	//turns this Trip into a copy of the T trip. Makes deep copies
    	//of the DLL associated with the trip.
    	this.start = new Date(T.start);
    	this.leave = new Date(T.leave);
    	this.Agenda = new Cities();
    	this.Agenda.Clone(T.Agenda);
    	this.budget = T.budget;
    }
    public boolean Delete_Reservation(Reservation T){
    	//This will take in a reference to a Reservation, then remove that
    	//reservation from the appropriate city.
    	double refund = 0.0;
    	if(Agenda == null){
    		System.out.println("No Reservations to Delete");
    		return false;
    	}
    	refund = this.Agenda.Delete_Reservation(T);
    	budget += refund;
    	return(refund != 0);
    }
    public void display(){
    	//Displays all the relevant details about the trip.
    	if(start == null){
    		System.out.println("Not enought information to display this trip");
    	}
    	System.out.print("You are leaving for on this Journey on ");start.display();System.out.println();
    	System.out.print("And are returning on "); leave.display();System.out.println();
    	System.out.print("You will be gone for "+this.start.time_between(this.leave)+" days.");System.out.println();

    	if(this.Agenda != null){
        	System.out.println("You will be staying at the following rentals: ");
    		this.Agenda.display();
    	}else{
    		System.out.println("No rentals reserved yet.");
    	}
    	System.out.println("You have "+this.budget+" left in your budget.");
    }
    public boolean Equals(Trip T){
    	//checks to see if two trips have the same start and end date, and budget.
    	//returns true if they do, false otherwise.
    	return((this.start.Equals(T.start)) && (this.leave.Equals(T.leave)) && (this.budget == T.budget) && (this.Agenda.Equals(T.Agenda)));
    }
    public Reservation Find_Reservation(Lodging T){
    	Reservation Empty = null;
    	if(this.Agenda == null){
    		return Empty;
    	}
    	return this.Agenda.Find_Reservation(T);
    }    
    public void set_Budget(double funds){
    	//sets or resets the budget for the trip.
    	this.budget = funds;
    }
    public void set_end(Date T){
    	//sets or resets the end date for the trip.
    	this.leave = new Date(T);
    }
    public void set_start(Date T){
    	//sets or resets the start date for the trip.
    	this.start = new Date(T);
    }
}
