//Alan Krajbich, CS202
//Project 5: Booking
/*This is the Date class. This represents a specific date. The class allows for the date to 
 * be compared against other dates, and the number of days between two dates can be determined here.
 */
public class Date {
	protected int Month;
	protected int Day;
    protected int Year;
    
    public Date(){
    	//default constructor for a Date. Since 0 is an
    	//invalid date, this will not work in most contexts.
    	this.Month = 0;
    	this.Day = 0;
    	this.Year = 0;   	
    }
    public Date(int day, int month, int year){
    	//Constructor which sets the day,month, and year to a specific date.
    	this.Day = day;
    	this.Month = month;
    	this.Year = year;
    }
    public Date(Date T){
    	//Initializes a a date as a copy of another date.
    	this.Clone(T);
    }
    public boolean Equals(int day, int month, int year){
    	//Checks to see if two dates are the same.
    	return(this.Day == day && this.Month == month && this.Year == year);
    }
    public boolean Equals(Date t){
    	//checks to see if two dates are the same.
    	return(this.Day == t.Day && this.Month == t.Month && this.Year == t.Year); 
    }
    public void Clone(Date T){
    	//Sets this date to be a copy of the T Date.
    	this.Day = T.Day;
    	this.Month = T.Month;
    	this.Year = T.Year; 	
    }
    public void display(){
    	//displays the date in USA standard format. MM/DD/YYYY
    	System.out.print(this.Month +"/"+this.Day+"/"+this.Year);
    }
    public boolean is_before(Date t){
    	//returns true if the T date is before this date.
    	if(this.Equals(t)){
    		return false;
    	}
    	if(this.Year < t.Year){
    		return true;
    	}else if(this.Year > t.Year){
    		return false;
    	}else if(this.Month < t.Month){
    		return true;
    	}else if(this.Month > t.Month){
    		return false;
    	}else if(this.Day < t.Day){
    	    return true;
    	}else{
    	    return false;	  		
    	}
    }
    public int time_between(Date t){
    	//Returns the number of days between this date and the T date as an INT.
    	//!!Does not account for Leap Years.!!
    	if(this.Year == t.Year && this.Month == t.Month && this.Day == t.Day){
    		return 0;
    	}
    	if(!(this.is_before(t))){
    		System.out.println("Your return date is before your leave date!");
    		return 0;
    	}
    	Date temp = new Date();
    	temp.Clone(t);
    	int t_day = this.Day;
    	int t_month = this.Month;
    	int t_year = this.Year;
    	int length = 0;
    	while(!(temp.Equals(t_day,t_month, t_year))){
    		t_day++;
    		if(t_day >= 29){
    			if(t_month == 2){
    				t_day = 1;
    				++t_month;
    			}else if(t_day >=31 && (t_month ==4 || t_month == 6 || t_month == 9 || t_month == 11)){
    				t_day =1;
    				++t_month;
    			}else if(t_day >=32){
    				t_day =1;
    				++t_month;
    			}
    			if(t_month >=13){
    				t_month = 1;
    				++t_year;
    			}
    		}
    		++length;
    	}
    	return length;
    }
}
