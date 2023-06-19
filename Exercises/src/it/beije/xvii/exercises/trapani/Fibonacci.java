package it.beije.xvii.exercises.trapani;

public class Fibonacci {

	public static void main(String[] args) {
	
		int b=1;
		int a=0;
		int temp=0;
		
		System.out.print(a + " " + b + " ");
		
		for(int i=0; i<100; i++) {
			temp = a + b;
			a = b;
			b = temp;
			System.out.print(temp + " ");
		}

	}

}
