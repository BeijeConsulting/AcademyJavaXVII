package it.beije.xvii.hierarchy.iannetta_sala_ulloa;

public class Office extends Building implements Lift, Intercom, ClosedDuringNights{

	public int numberOfWorkers;
	
	public Office(String country, boolean maintenaince, int annodicostruzione,
					String address, int numberOfFloors, String colour,
						int numberOfWorkers) {
		
		this.country = country; 
		this.maintenance = maintenaince;
		this.annodicostruzione = annodicostruzione;
		
		this.address = address;
		this.numberOfFloors = numberOfFloors;
		this.colour = colour;
		
		this.numberOfWorkers = numberOfWorkers;
	}

	public Office() {
		this("Italy", false, 1950, "Unknown", 1, "White", 5);
	}
	
	@Override
	public void lift() {
		System.out.println("There is a lift");
		
	}
	
	@Override
	public void intercom() {
		System.out.println("There is an intercom");		
	}
	
	@Override
	public void closedDuringNights() {
		System.out.println("Religious building is closed during nights");
		
	}

}
