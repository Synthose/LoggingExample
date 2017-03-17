
public abstract class DLL_Node extends LLL_Node{
	protected DLL_Node Prev;
	protected DLL_Node Next;
	
	public void set_prev(DLL_Node T){
		Prev = T;
	}
	public abstract void add_reservation(Lodging T, Date in, Date out);
	public abstract double Delete_Reservation(Reservation T);
	public abstract Reservation Find_Reservation(Lodging T);

}
