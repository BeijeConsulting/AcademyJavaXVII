package it.beije.xvii.exercises.ceccarelli;

public class Media {

	public Media() {
		// TODO Auto-generated constructor stub
	}
	public String [] addString(String s, String[] a) {
		String[] b = new String[a.length+1];
		for(int i=0; i<a.length;i++) {
			b[i] = a[i];
		}
		b[a.length] = s;
		return b;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {3,7,3,6,5,7,3,6,3,9,13,75,88};
		int somma =0;
		Media m = new Media();
		String[] arrayS = {"ciao", "come", "va", "funziona", "o", "no"};
		String s = "?";
		for(int i=0;i<array.length;i++) {
			somma+=array[i];
		}
		System.out.println("la media è: " + (somma/array.length));
		System.out.println("array stringhe: ");
		for(String st: m.addString(s, arrayS)) {
			System.out.print(st + " ");
		}
	}

}
