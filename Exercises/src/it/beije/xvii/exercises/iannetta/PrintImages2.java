package it.beije.xvii.exercises.iannetta;

public class PrintImages2 {

	public static void main(String[] args) {
		
		int min = 1;
		int max = 6;
		
		String s1 = "******"; //("*").repeat(i)
		for (int i = max; i>= min; i--) {
			System.out.println(s1.substring(max - i));
		}
		
		 System.out.println("");
		 String s2 = "";
		for (int i = min; i<= max; i++) {
			s2 += "#";
			System.out.println(s2);
		} 
		
		System.out.println("");
		String s3 = "";
		String s3Reverse = "654321";
		for (int i = min; i<= max; i++) {
			s3 += i;
			System.out.println(s3 + "    " + s3Reverse);
			s3Reverse = s3Reverse.substring(min);
		}

	}

}
