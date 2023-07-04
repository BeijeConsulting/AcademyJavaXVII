package it.beije.xvii.hierarchy.iannetta_sala_ulloa;

public abstract class Building extends Infrastructure implements Stairs{
	
	public String address;
	public int numberOfFloors;
	public String colour;
	
	public void buildAnotherFloor() {
		numberOfFloors++;
	}
	
	public void changeColour(String newColour) {
		colour = newColour;
	}
	
	public void stairs() {
		System.out.println("This building has stairs");
	}
}
