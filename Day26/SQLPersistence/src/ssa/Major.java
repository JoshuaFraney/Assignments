package ssa;

public class Major {

	int id;
	String description;
	int req_sat;
	
	@Override
	public String toString() {
		String outpt = String.format("%d %-16s %6d",
				this.id,
				this.description,
				this.req_sat);
		return outpt;
	}
	
	Major() {
		
	}
}
