import java.io.*;

//Alan Krajbich, CS202
//Project 4: Room for rent
/*This is the Amenities class. It is where the list of amenities for a rental is created and stored.
 * Not too much to say about this class.
 */
public class Amenities {
	protected String offered[];
	public Amenities(){
		offered = null;
	}
	public Amenities(String to_add[]){
		offered = to_add;
	}
	public boolean Equals(Amenities T){
		if(this.offered == null && T.offered == null){
			return true;
		}
		if(this.offered.length != T.offered.length)
			return false;
		boolean same = true;
		for(int i = 0; i<this.offered.length; ++i){
			if(!(this.offered[i].equalsIgnoreCase(T.offered[i]))){
				same = false;
			}
		}return same;
	}
	public void clone(Amenities T){
		if(this == T)
			return;
		offered  = new String[T.offered.length];
		for(int i = 0; i<T.offered.length; ++i){
			this.offered[i] = T.offered[i];
		}
	}
	public void display(){
		if(this.offered == null){
			System.out.println("No Amenities known");
			return;
		}
		for(int i = 0; i< this.offered.length; ++i){
			System.out.println(this.offered[i]);
		}
	}
	public void add_Amenities(String to_add[]){
		if(this.offered == null){
			offered = to_add;
		}else{
			int i = 0;
			String[] temp = offered;
			offered = new String[offered.length + to_add.length];
			for(int j = 0; j<temp.length; ++j){
				this.offered[i] = temp[j];
				++i;
			}
			for(int k = 0; k<to_add.length; ++k){
				this.offered[i] = to_add[k];
				++i;
			}
			
		}
	}
    protected void writeAll(BufferedWriter out){
    	try{
    		out.write(this.offered[0]);
    		for(int i=1; i<this.offered.length; ++i){
    			out.write(", ");
    			out.write(this.offered[i]);
    		}
    	}catch (Exception e){
    		System.err.println("Error: "+e.getMessage());
    	}   	
    }
}
