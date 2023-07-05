package it.beije.suormary.rubrica;

public class Singleton {
	
	private Singleton() {
		System.out.println("creo l'oggetto...");
	}
	
	private static Singleton instance;
	
	public static Singleton getInstance() {
		if (instance == null) {
			instance = new Singleton();
		}
		
		return instance;
	}

}
