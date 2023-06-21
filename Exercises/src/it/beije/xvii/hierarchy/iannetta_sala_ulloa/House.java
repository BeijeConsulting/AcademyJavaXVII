package it.beije.xvii.hierarchy.iannetta_sala_ulloa;

public class House extends Building implements IsHabitable, Lift, Intercom{

	public House(String country, boolean maintenaince, int annodicostruzione, String address, int numberOfFloors, String colour) {
		this.country = country; 
		this.maintenance = maintenaince;
		this.annodicostruzione = annodicostruzione;
		
		this.address = address;
		this.numberOfFloors = numberOfFloors;
		this.colour = colour;
	}

	public House() {
		this("Italy", false, 1950, "Unknown", 1, "White");
	}

	@Override
	public void isHabitable() {
		System.out.println("This house is habitable");
	}

	@Override
	public void lift() {
		System.out.println("There is a lift");
		
	}

	@Override
	public void intercom() {
		System.out.println("There is an intercom");		
	}
	
	
	
}
