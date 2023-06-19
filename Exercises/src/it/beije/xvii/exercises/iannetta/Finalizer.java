package it.beije.xvii.exercises.iannetta;

public class Finalizer {
	
	public static int count = 0;
	
	protected void finalize() {
	 System.out.println("Calling finalize");
	 count--;
	 } 
	
	 public static void main(String[] args) {
		 
		 for (int i = 0; i < 50000; i++) {
			 new Finalizer();
		 }
		 
		 System.out.println(count);
		} 

}
