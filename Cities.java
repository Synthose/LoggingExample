//Project 5: Booking
/* This is the Cities Class. This class extends the DLL Node class. It stores all the information
 * related to the reservations in a specific city. It stores the City and State of the city as a
 * string, and the Number of reservations as an Int. It holds an array of Reservations. The array is
 * dynamically set to the correct size every time a new reservation is added.
 */
public class Cities extends DLL_Node {
	protected Reservation [] AvailableRooms;
	protected String Name;
	protected String State;
	protected int NumListings;
	
	public Cities(){
		//Default constructor. Sets everything to null.
		this.Name = null;
		this.State = null;
		this.AvailableRooms = null;
		this.NumListings = 0;
		this.Next = null;
		this.Prev = null;
	}	
	public Cities(String name , String state){
		//Constructor which sets the city and state to their correct values.
		this.Name = name;
		this.State = state;
		this.AvailableRooms = null;
		this.NumListings = 0;
	}
	public boolean Equals(Cities T){
		//Checks to see if two Cities have the same city and state name.
		return((this.Name.equalsIgnoreCase(T.Name)) &&(this.State.equalsIgnoreCase(T.State)));
	}
	public boolean Equals(Lodging T){
		//checks to see if this City matches the city and state of a supplied lodging.
		return((this.Name.equalsIgnoreCase(T.City))&& (this.State.equalsIgnoreCase(T.State)));
	}
    public void Clone(Cities T){
    	//Turns this city into a copy of the supplied City. Creates a deep copy of the
    	//DLL as well.
    	if(T.Name != null){
    		this.Name = T.Name;
    	}
    	if(T.State != null){
    		this.State = T.State;
    	}
    	if(T.AvailableRooms != null){
    		this.AvailableRooms = new Reservation[T.AvailableRooms.length];
    		for(int i = 0; i<T.AvailableRooms.length; ++i){
    			this.AvailableRooms[i] = T.AvailableRooms[i];
    		}	
    	}
    	this.NumListings = T.NumListings;    	
    }
	public void display(){
    	//Displays all the information about this City, including number of reservations
    	//Details about each reservation, and then calls the display on the next city in
    	//the linked list.
    	if(this.Name == null || this.State == null){
    		return;
    	}
    	if(this.AvailableRooms == null){
    		System.out.println("You don't have any reservations in "+this.Name+", "+this.State+" yet.");
    		return;
    	}
    	System.out.println("For the city of "+this.Name+", "+this.State+", you have the following reservations: ");
    	for(int i = 0; i<this.AvailableRooms.length; ++i){
    		AvailableRooms[i].display();
    	}
    	if(this.Next != null){
    		Next.display();
    	}
    }
    public String display_Simple(){
    	//Returns the City and State, concatenated into one string.
    	if(this.Name == null || this.State == null){
    		return("No city");
    	}
    	return(this.Name+", "+this.State+" ");
    }
	public void add_reservation(Lodging T, Date in, Date out){
    	//Checks to see if the supplied reservation is in this city. If it is, it
    	//stores the reservation here. If it isn't,
    	//it will see if there are other City nodes available, and attempt to store it
    	//there. If the reservation doesn't match any current cities, a new city is created,
    	//and the reservation is stored there.
    	if(this.Equals(T)){
	    	if(this.AvailableRooms == null){
	    		this.AvailableRooms = new Reservation[1];
	    		this.AvailableRooms[0] = new Reservation(T, in, out);
	    		this.NumListings =1;
	    	}else{
	    		Lodging [] temp= new Lodging[this.AvailableRooms.length];
	    		for(int i = 0; i<this.AvailableRooms.length; ++i){
	    			temp[i] = new Lodging(AvailableRooms[i]);
	    		}
	    		this.AvailableRooms = new Reservation[(temp.length)+1];
	    		int index = 0;
	    		for(int i = 0; i<temp.length; ++i){
	    			this.AvailableRooms[i]= new Reservation(temp[i]);
	    			index = i;
	    		}
	    		this.AvailableRooms[index+1] = new Reservation(T, in, out);
	    		++this.NumListings;
	    	}
    	}else if(this.Next == null){    		
    		Next = new Cities(T.City, T.State);
    		this.Next.set_prev(this);
    		this.Next.add_reservation(T, in, out);
    	}else{
    		Next.add_reservation(T, in, out);
    	}
    }
    @Override
	public double Delete_Reservation(Reservation T){
    	//This will compare the supplied Reservation against the reservations for each city,
    	//and delete any that match. It then reallocates the array of reservations to suit
    	//the new size.
    	double refund = 0.0;
    	if(this.Equals(T)){
    		int deleted = 0;
    		for(int i =0; i<NumListings; ++i){
    			if(this.AvailableRooms[i]==T){
    				refund += (this.AvailableRooms[i].cost * this.AvailableRooms[i].check_in.time_between(this.AvailableRooms[i].check_out));
    				this.AvailableRooms[i] = null;
    				++deleted;
    			}
    		}
    		this.NumListings -= deleted;
    		if(this.NumListings == 0){
    			this.AvailableRooms = null;
    			return refund;
    		}else{
	    		Lodging [] temp= new Lodging[this.AvailableRooms.length - deleted];
	    		for(int i = 0; i<(this.AvailableRooms.length - deleted); ++i){
	    			if(this.AvailableRooms[i]!= null){
	    				temp[i] = new Lodging(AvailableRooms[i]);
	    			}
	    		}
	    		this.AvailableRooms = new Reservation[(temp.length)];
	    		for(int i = 0; i<temp.length; ++i){
	    			this.AvailableRooms[i]= new Reservation(temp[i]);
	    		}
	    		return refund;
    		}
    	}else if(this.Next != null){
    		return this.Next.Delete_Reservation(T);
    	}else{
    		return 0.0;
    	}
    }
    @Override
	public Reservation Find_Reservation(Lodging T){
    	//Takes in a dummy Lodging, and returns a pointer to a reservation that
    	//matches its details. If no match is found, returns a null reference.
    	Reservation Empty = null;
    	if(this.Equals(T)){
    		if(this.AvailableRooms == null){
    			return Empty;
    		}
    		for(int i = 0; i<this.NumListings; ++i){
    			if(this.AvailableRooms[i].Equals(T)){
    				return this.AvailableRooms[i];
    			}
    		}
    		return Empty;
    	}if(this.Next == null){
    		return Empty;
    	}else{
    		return this.Next.Find_Reservation(T);
    	}
    }
}
