package it.beije.xvii.exercises.mancuso;

public class InitializationOrder {
	static { add(2); }
	static void add(int num) { System.out.print(num + " "); }
	
	InitializationOrder() { add(5); }
	
	static { add(4); }
	
	{ add(6); }
	
	static { new InitializationOrder(); }
	
	static { add(8); }
	
	public static void main(String[] args) { } 
}