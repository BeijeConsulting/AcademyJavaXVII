package it.beije.xvii.exercises.iannetta;

public class Finalizer {
	
	public static int count = 0;
	
	protected void finalize() {
		System.out.println("Calling finalize");
		 
	}
	
	 public static void main(String[] args) {
		 Finalizer f = new Finalizer();
		 f.finalize();
	 } 

}
