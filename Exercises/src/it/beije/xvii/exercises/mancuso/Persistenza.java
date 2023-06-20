package it.beije.xvii.exercises.mancuso;

public class Persistenza {
	
	public static int persistence(int n) {
		if(n>=0) {
			String cifre = String.valueOf(n);
			int count = 0;
			while(cifre.length()>1) {
				int product = 1;
				for(int i=0; i<cifre.length(); i++) {
					int c = Character.getNumericValue(cifre.charAt(i));
					product *= c;
				}
				count ++;
				cifre = "" + product;
			}
			return count;
		}else {
			return -1;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(persistence(999));

	}

}
