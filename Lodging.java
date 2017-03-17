//Alan Krajbich, CS202
//Project 5: Room for Rent Pt1
/*This is the Lodging class, which describes a rental. It is created and defined by the users, and they can add
 * and manage the fields held inside. The rentals can have a Name, City, State, and Type associated with them, as well as
 * a average rating and a cost per night. The rental can have a series of Reviews associated with it,
 * and the rating and cost are taken from those reviews. It also has an instance of the class "Amenities", which
 * describe the available amenities for this rental (WiFi, AC, etc), and the class has an array of Strings which
 * describe the Blackout dates. 
 */

public class Lodging {
	protected String Name;
	protected String City;
	protected String State;
	protected double rating;
	protected double cost;
	protected Review head;
	protected int n_ratings;
	protected Amenities available;
	protected String Type;
	protected String [] Blackout;
	public Lodging(String n_name, String n_City, String n_State, String n_type){
		Name = n_name;
		City = n_City;
		State = n_State;
		Type = n_type;
		rating = 0;
		cost = 0;
		head =null;
        n_ratings = 0;
        available = null;
	}
	public Lodging(){
		Name = "No Name Given";
		City = "No City Given";
		State = "No State Given";
		Type = "No Type Given";
		rating = 0;
		cost = 0;
		head = null;
        n_ratings = 0;
        available = null;
	}
	public Lodging(Lodging to_add){
		this.clone(to_add);
	}
	public boolean Equals(Lodging target){
		if(target == null || this == null)
			return false;
		if(Name.equalsIgnoreCase(target.Name) && City.equalsIgnoreCase(target.City) && State.equalsIgnoreCase(target.State))
			return true;
		else
			return false;
	}
	public boolean SameCity(Lodging T){
		if(this.City.equalsIgnoreCase(T.City)){
			if(this.State.equalsIgnoreCase(T.State)){
				return true;
			}
			return false;
		}
		return false;
	}
	public void clone(Lodging target){
		this.Name = target.Name;
		this.City = target.City;
		this.State = target.State;
		this.rating = target.rating;
		this.Type = target.Type;
		this.Blackout = null;
		if(target.Blackout != null){
			this.Blackout = new String[target.Blackout.length];
			for(int i=0; i<target.Blackout.length; ++i){
				this.Blackout[i] = target.Blackout[i];
			}
		}
		this.cost = target.cost;
        this.n_ratings = target.n_ratings;
        if(target.available != null){
        	this.available = new Amenities();
        	this.available.clone(target.available);
        }
        if(target.head != null){
            this.head = new Review();
        	this.head.clone(target.head);
        }
	}
	public void display(){
		if(Name == null || City ==null || State == null){
			System.out.println("No info to print for this entry!");
			return;
		}
		System.out.println("This "+Type+" is called "+Name+", and is located in "+City+", "+State+".");
		return;		
	}
	public void displayDetails(){
		if(Name == null || City ==null || State == null){
			System.out.println("No info to print for this entry!");
			return;
		}
		System.out.println("This "+Type+" is called "+Name+", and is located in "+City+", "+State+".");
		System.out.println("It has an average rating of "+rating+"/5 stars, and an average cost of $"+cost);
		if(head != null){
			head.display();
		}
		if(this.available != null){
			available.display();
		}
		if(this.Blackout != null){
			System.out.print("It is unavailable on ");
			for(int i =0; i<this.Blackout.length;++i){
				System.out.print(this.Blackout[i]+", ");
			}
		}
		System.out.println(" ");
		return;	
		
	}
	public void add_review(Review to_add){
		++n_ratings;
		Review temp = new Review(to_add);
		Review curr = head;
		if(head == null){
			head = temp;
		}else{
			while(curr.go_next() != null){
				curr = curr.go_next();
			}
			curr.setNext(temp);
			curr = curr.go_next();
		}
		rating = head.get_rating()/n_ratings;
		cost = head.get_cost()/n_ratings;
		
	
	}
	public boolean addAmenities(String to_add[]){
    	if(this.available == null){
    		this.available = new Amenities(to_add);
    		return true;
    	}
    	this.available.add_Amenities(to_add);
    	return true;	
    }
    public void addBlackout(String to_add[]){
    	if(this.Blackout == null){
    		this.Blackout = new String[to_add.length];
    		for(int i=0; i<to_add.length; ++i){
				this.Blackout[i] = to_add[i];
			}
    		return;
    	}else{
    		String [] temp = this.Blackout;
    		int n_BO = to_add.length+this.Blackout.length;
    		int i = 0;
    		this.Blackout = new String[n_BO];
    		for(int j = 0; j<temp.length; ++j){
    			this.Blackout[i] = temp[j];
    			++i;
    		}
    		for(int j = 0; j<to_add.length; ++j){
    			this.Blackout[i] = to_add[j];
    			++i;
    		}
    	}	
    }
}
