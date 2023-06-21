package it.beije.xvii.hierarchy.iannetta_sala_ulloa;

public class ReligiousBuilding extends Building implements IsHabitable, ClosedDuringNights{

	public String religion;
	
	public ReligiousBuilding(String religion) {
		this.country = "Italy"; 
		this.maintenance = false;
		this.annodicostruzione = 1500;
		
		this.address = "Unknown";
		this.numberOfFloors = 1;
		this.colour = "White";
		
		this.religion = religion;
	}

	@Override
	public void isHabitable() {
		System.out.println("This religious building is habitable");
		
	}

	@Override
	public void closedDuringNights() {
		System.out.println("Religious building is closed during nights");
		
	}

	
	
}
