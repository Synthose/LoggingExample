//Project 5: Booking
import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String name,city,state,type,resp;
		type = "";
		BST_Cities MyCities = new BST_Cities();
		MyCities.read_lodging();
		Trip Agenda = null;
		System.out.println("Lets begin planning your trip. First when are you leaving?(MM DD YY)");
		int day,month,year;
		month = input.nextInt();
		day = input.nextInt();
		year = input.nextInt();
		Date start = new Date(day,month,year);
		System.out.println("When will you be returning from your trip?(MM DD YY)");
		month = input.nextInt();
		day = input.nextInt();
		year = input.nextInt();
		Date back = new Date(day,month,year);
		System.out.println("What is your budget for this trip?(XXX.XX)");
		double funds = input.nextDouble();
		input.nextLine();
		Agenda = new Trip(start,back,funds);
		

		do{
			System.out.println("What would you like to do?");
			System.out.println("(A) Manage my Agenda   (B)Browse Available Rentals   (C)Add a new Rental   (Q)quit.");
			resp = input.nextLine();
			if(resp.equalsIgnoreCase("A")){
				do{
					System.out.println("(A)Add a Reservation  (B) Review your Agenda   (X) Return to Previous Menu");
					System.out.println("(C) Cancel a Reservation from your Agenda    (D)Add funds to your Budget");
					resp = input.nextLine();
					
					if(resp.equalsIgnoreCase("A")){
						System.out.println("Lets add a reservation to our agenda!");
					    System.out.println("Please enter the name of the Rental you want to find");
					    name = input.nextLine();
					    System.out.println("What city is "+name+" located?");
					    city = input.nextLine(); 
					    System.out.println("What State is "+city+" located in?");
				        state = input.nextLine();
				        BST_Lodgin temp = new BST_Lodgin(name,city,state,type);
				        Lodging found = MyCities.FindHotel(temp);
				        if(found != null){
				        	if(Agenda == null){
				        		Agenda = new Trip();
				        	}
				        	System.out.println("Rental found! The price is "+ found.cost+" per night.");
				        	System.out.println("You have "+Agenda.budget+" left in your coffers.");
				        	System.out.println("When do you plan to check in to this hotel?(MM/DD/YYYY)");
				        	String check_in = input.nextLine();
				        	Scanner read_date = new Scanner(check_in);
				        	read_date.useDelimiter("/");
				        	month = read_date.nextInt();
				        	day = read_date.nextInt();
				        	year = read_date.nextInt();
				        	Date temp_in = new Date(day,month,year);
				        	read_date.close();
				        	System.out.println(" When do you plan to check out of this rental?(MM/DD/YYYY)");
				        	String check_out = input.nextLine();
				        	Scanner read_date_t = new Scanner(check_out);
				        	read_date_t.useDelimiter("/");
				        	month = read_date_t.nextInt();
				        	day = read_date_t.nextInt();
				        	year = read_date_t.nextInt();
				        	Date temp_out = new Date(day,month,year);
				        	read_date_t.close();				        	
				        	Agenda.add_reservation(found,temp_in, temp_out );
				        }else{
				        System.out.println("Hotel Not found");
				        }
					}
					if(resp.equalsIgnoreCase("B")){
						Agenda.display();
					}
					if(resp.equalsIgnoreCase("C")){
					    System.out.println("Please enter the name of the Reservation you want to cancel:");
					    name = input.nextLine();
					    System.out.println("What city is "+name+" located?");
					    city = input.nextLine(); 
					    System.out.println("What State is "+city+" located in?");
				        state = input.nextLine();
				        Lodging temp = new Lodging(name,city,state,"Null");
				        temp = Agenda.Find_Reservation(temp);
				        if(temp != null){
				        	if(Agenda.Delete_Reservation((Reservation) temp)){
				        		System.out.println("Reservation Canceled");
				        	}else{
				        		System.out.println("Failed to Cancel the Reservation");
				        	}
				        }else{
				        	System.out.println("No such Reservation found");
				        }
			    	}
					if(resp.equalsIgnoreCase("D")){
						System.out.println("How much money would you like to add or remove from your budget?");
						double new_funds = 0.0;
						new_funds += input.nextDouble();
						Agenda.add_funds(new_funds);
						input.nextLine();
					}
				}while(!(resp.equalsIgnoreCase("X")));
			}
			if(resp.equalsIgnoreCase("B")){
				do{
					System.out.println("(A) See all listings    (B)See all listings for a City");
					System.out.println("(C) See details for a listing   (X)Return to previous menu");
					resp = input.nextLine();
			    	if(resp.equalsIgnoreCase("A")){
			    		MyCities.display();
			    	}
			    	if(resp.equalsIgnoreCase("B")){
			    		String n_city;
			    		String n_state;
			    		System.out.println("What city would you like to see?:");
			    		n_city = input.nextLine();
			    		System.out.println("What State?: ");
			    		n_state = input.nextLine();
			    		n_city = n_city+", "+n_state;
			    		MyCities.displayCity(n_city);		
			    	}
			    	if(resp.equalsIgnoreCase("C")){
			    		System.out.println("Lets find a hotel!");
			    	    System.out.println("Please enter the name of the Lodging you want to find");
			            name = input.nextLine();
			            System.out.println("What city is "+name+" located?");
			   	        city = input.nextLine(); 
			   	        System.out.println("What State is "+city+" located in?");
			    	    state = input.nextLine();
			    	    BST_Lodgin temp = new BST_Lodgin(name,city,state,type);
			    	    MyCities.showHotel(temp);
			    	}
				}while(!resp.equalsIgnoreCase("X"));
			}
			if(resp.equalsIgnoreCase("C")){
				   String new_review;
				   int rating;
				   double cost;
				   Review t_review;
	    		   System.out.println("Please enter the name of your Rental");
	    		   name = input.nextLine();
	    	       System.out.println("What city is "+name+" located?");
	    	       city = input.nextLine(); 
	    	       System.out.println("What State is "+city+" located in?");
	    	       state = input.nextLine();
	    	       System.out.println("What Type of Rental is this?");
	    	       type = input.nextLine();
	    	       BST_Lodgin temp = new BST_Lodgin(name,city,state,type);
	    	       
	    	       System.out.println("Please leave a description of the Rental");
	      		   new_review = input.nextLine();
	      		   System.out.println("Out of 5, how would you rate this Rental? ");
	      	       rating = input.nextInt();
	      	       System.out.println("How much are you charging per night?");
	   		       cost = input.nextDouble();
	   		       input.nextLine();
	   		   	   t_review = new Review(new_review, rating, cost);
	   		   	   temp.add_review(t_review);
	   		   	   
	   		   	   
	    		   String[] t_amenities;
	    		   String storage;
	    		   t_amenities = new String[50];
	    		   short i = 0;
	    		   System.out.println("What Amenities are offered at your rental?(Seperate by commas)");
	    	       storage = input.nextLine();
				   Scanner s = new Scanner(storage);
	    	       s.useDelimiter(",");
	    	       while (s.hasNextLine()) {
	    	    		t_amenities[i]= s.next();
	    	    		++i; 
	    	        }
	    	    	String [] to_add;
	    	    	to_add = new String[i];
	    	    	for(int j = 0; j<i; ++j){
	    	    		to_add[j]=t_amenities[j];
	    	    	}
	    	    	s.close();
	    	        temp.addAmenities(to_add);  
	    	        
	     		   String[] t_BO;
	    		   t_BO = new String[50];
	    		   i = 0;
	    	       System.out.print("What are the blackout dates?(split with a comma): ");
	    	       storage = null;
	    	       storage = input.nextLine();
	    	       s = new Scanner(storage);
	    	       s.useDelimiter(",");
	    	       while (s.hasNextLine()) {
	    	    		t_BO[i]= s.next();
	    	    		++i; 
	    	        }
	    	    	to_add = new String[i];
	    	    	for(int j = 0; j<i; ++j){
	    	    		to_add[j]=t_BO[j];
	    	    	}
	    	    	s.close();
	    	        temp.addBlackout(to_add);
	    	       MyCities.add_Inn(temp);
			}
	}while(!resp.equalsIgnoreCase("q"));
	input.close();
	MyCities.writeAll();
	}
}
