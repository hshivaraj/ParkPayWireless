package Vehical;

public enum OwnerShip {
	POV("Private Owned Vehical"), 
	COV("Company Owned Vehical"), 
	PLV("Private Lease Vehical"), 
	CLV("Company Leased Vehical"), 
	FV("Foreign Vehical"), 
	FCV("Foreign Company Vehical"), 
	CV("Chauffeur Vehical"), 
	DV("Diploma tVehical");
	
	private String type;
	
	private OwnerShip(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return this.type;
	}
	
}
