//Alan Krajbich, CS202
//Project 4: Room for Rent Pt.1
/* This is the Binary Search Tree node for the Cities stored in the program. Each of these nodes
 * holds the root for the binary search tree of listings for the city the node represents. It has a
 * name string which holds the city and state/country the node represents. It also has an H_count short
 * which tells us how many listings are stored in the BST stored in this node.
 * It has a Left and a Right pointer, which point to other Cities.
 */
import java.io.*;
import java.util.Scanner;

public class BST_Cities{
	protected String Name;
	protected BST_Cities Left;
	protected BST_Cities Right;
	protected BST_Lodgin root;
	protected short H_count;
	
	public BST_Cities(){
		this.Name = null;
		this.Left = null;
		this.Right = null;
		this.root = null;
		this.H_count = 0;
	}
	public BST_Cities(BST_Lodgin to_add){
		root = new BST_Lodgin();
		root.clone(to_add);	
		this.H_count = 1;
	}
	public BST_Cities(Lodging T){
		this.Name = T.City + ", "+ T.State; 
		this.root = new BST_Lodgin(T);
		this.Left = null;
		this.Right = null;
		this.H_count = 1;
	}
	public BST_Cities(String h_name, String city, String state, String type){
		Name = (city+", "+state);
		root = new BST_Lodgin(h_name,city,state,type);
		this.H_count = 1;
	}
	public boolean equals(BST_Cities T){
		return((this.Name.equals(T.Name))&&(this.Left.equals(T.Left))&&(this.Right.equals(T.Right))&&(this.root.equals(T.root)));
	}
	public boolean equals(String city){
		return(this.Name.equals(city));
	}
	public boolean SameCity(BST_Lodgin T){
		return(root.SameCity(T));
	}
	public void clone(BST_Cities T){
		if(T.Name != null)
			this.Name = T.Name;
		if(T.Left != null){
			this.Left = new BST_Cities();
			this.Left.clone(T.Left);
		}
		if(T.Right != null){
			this.Right = new BST_Cities();
			this.Right.clone(T.Right);
		}
		if(T.root != null){
			this.root = new BST_Lodgin();
			this.root.clone(T.root);
		}
	}
	public BST_Cities go_left(){
		return this.Left;
	}
	public BST_Cities go_right(){
		return this.Right;
	}
	public void display(){
		if(root == null){
			System.out.println("No hotels to show here");
			return;
		}
		System.out.println("The city of "+Name+" has "+H_count+" places available: ");
		root.display();
		System.out.println(" ");
		if(this.Left != null){
			this.Left.display();
		}
		if(this.Right != null){
			this.Right.display();
		}
	}
	public void displayCity(String T_city){
		if(this.Name.equalsIgnoreCase(T_city)){
			root.display();
			return;
		}else{
			int result = this.Name.compareToIgnoreCase(T_city);
			if((result > 0) && (this.Left != null)){
				Left.displayCity(T_city);
				return;
			}else if((result < 0) && (this.Right != null)){
				Right.displayCity(T_city);
				return;
			}
		}System.out.println("No such city");
	}
	public void showHotel(BST_Lodgin T){
    	if(T != (T = FindHotel(T))){
    		T.displayDetails();
    	}else{
    		System.out.println("Hotel not found");
    	}
    }
    public void read_lodging(){
      try {
    	File file = new File("C:\\Users\\Synthose\\Project_4\\src\\Lodging_List.txt");
		Scanner read = new Scanner(file);
    	while (read.hasNextLine()) {
    		String n_name;
    		String n_city;
    		String n_state;
    		String n_type;
    		BST_Lodgin temp;
            n_name = read.nextLine();
            n_city = read.nextLine();
            n_state = read.nextLine();
            n_type = read.nextLine();
            temp = new BST_Lodgin(n_name,n_city,n_state,n_type);
            add_Inn(temp);
            String Reviews = read.nextLine();
            if(Reviews.length() > 3){
            	Scanner in = new Scanner(Reviews);
            	in.useDelimiter("\\s*:;\\s*");
            	while(in.hasNext()){
            		String n_review="";
                	int n_rates=0;
                	double n_price=0;
               		Review t_rev;
            		n_review = in.next();
            		n_rates = in.nextInt();
            		n_price = in.nextDouble();
            		t_rev = new Review(n_review, n_rates, n_price);
            		this.add_review(temp, t_rev);	
            	}
            	in.close();
            }
            String T_Amenities = read.nextLine();
            if(T_Amenities.length() > 3){
               Scanner in = new Scanner(T_Amenities);
               String[] t_amenities;
      		   t_amenities = new String[50];
     		   short i = 0;
     	       in.useDelimiter("\\s*,\\s*");
     	       while (in.hasNext()) {
     	    		t_amenities[i]= in.next();
     	    		++i; 
     	        }
     	    	String [] to_add;
     	    	to_add = new String[i];
     	    	for(int j = 0; j<i; ++j){
     	    		to_add[j]=t_amenities[j];
     	    	}
     	    	in.close();
     	    	addAmenities(temp, to_add);     
            }
            String T_BO = read.nextLine();
            if(T_BO.length()>3){
            	Scanner in = new Scanner(T_BO);
            	in.useDelimiter("\\s*,\\s*");
     		   String [] t_BO = new String[50];
     		   short i = 0;
     	       while (in.hasNextLine()) {
     	    		t_BO[i]= in.next();
     	    		++i; 
     	        }
     	    	String [] to_add;
     	    	to_add = new String[i];
     	    	for(int j = 0; j<i; ++j){
     	    		to_add[j]=t_BO[j];
     	    	}
     	    	in.close();
     	        addBlackouts(temp, to_add);  
            }

        }
        read.close();
      } catch (Exception ex) {
          ex.printStackTrace();
      }
    }
    public boolean isBiggerCityThan(Lodging T){
		int result = this.Name.compareTo(T.City+", "+T.State);
		return(result >0);
    }
    public boolean isSmallerCityThan(Lodging T){
		int result = this.Name.compareTo(T.City+", "+T.State);
		return(result <0);
    }
    public boolean isBiggerCityThan(BST_Lodgin T){
		int result = this.Name.compareTo(T.City+", "+T.State);
		return(result >0);
    }
    public boolean isSmallerCityThan(BST_Lodgin T){
		int result = this.Name.compareTo(T.City+", "+T.State);
		return(result <0);
    }
    public boolean add_Inn(Lodging to_add){
			if(this.root == null){
				root = new BST_Lodgin(to_add);
				this.Name = to_add.City+", "+to_add.State;
				return true;
			}else if(root.SameCity(to_add)){
				++this.H_count;
				BST_Lodgin current = root;
				while(current != null){
					if(current.isBiggerThan(to_add)){
						if(current.go_left() == null){
							current.set_left(to_add);
							return true;
						}else{
						current = current.go_left();
						}
					}else if(current.isSmallerThan(to_add)){
						if(current.go_right() == null){
							current.set_right(to_add);
							return true;
						}else{						
						current = current.go_right();
						}
					}
				}
			}else if(this.isBiggerCityThan(to_add)){
				if(this.Left == null){
					this.Left = new BST_Cities(to_add);
					return true;
				}else{
					return this.Left.add_Inn(to_add);
				}
				
			}else if(this.isSmallerCityThan(to_add)){
				if(this.Right == null){
					this.Right = new BST_Cities(to_add);
					return true;
				}else
					return this.Right.add_Inn(to_add);
				
			}
			System.out.println("Failed to add this hotel");
			to_add.display();
			return false;
	    }
	public void add_review(BST_Lodgin T,Review to_add){
		if((T != (T = FindHotel(T)))){
			T.add_review(to_add);
		}else{
			System.out.println("Hotel Not Found");
		}
		return;
	}
	public boolean addAmenities(BST_Lodgin T, String to_add[]){
    	if((T != (T = FindHotel(T)))){
    		return T.addAmenities(to_add);
    	}
    	return false;
    }
    public void addBlackouts(BST_Lodgin T, String to_add[]){
    	if((T != (T = FindHotel(T)))){
    		T.addBlackout(to_add);
    	}
    }
	protected BST_Lodgin FindHotel(BST_Lodgin T){
		if(SameCity(T)){
			return root.FindHotel(T);
		}else if(this.isBiggerCityThan(T) && this.Left != null){
			return Left.FindHotel(T);
		}else if(this.isSmallerCityThan(T) && this.Right != null){
			return Right.FindHotel(T);
		}
		BST_Lodgin empty = null;
		return empty;
	}
    public void writeAll(){
    	try{
    		File file = new File("C:\\Users\\Synthose\\Project_4\\src\\Lodging_List.txt");
    		FileWriter fstream = new FileWriter(file);
    		BufferedWriter out = new BufferedWriter(fstream);

    		if(root != null){
    			root.writeAll(file, out);
    		}
    		if(Left != null){
    			Left.writeAll(file, out);
    		}   	
    		if(Right != null){
    			Right.writeAll(file, out);
    		}
    		out.close();
    	}catch (Exception e){
    		System.err.println("Error: "+e.getMessage());
    	}
    }
    public void writeAll(File file, BufferedWriter out){
    	try{
    		if(root != null){
    			root.writeAll(file, out);
    		}
    		if(Left != null){
    			Left.writeAll(file,out);
    		}
    		if(Right != null){
    			Right.writeAll(file, out);
    		}
    	}catch (Exception e){
    		System.err.println("Error: "+e.getMessage());
    	}
    }
}
