package it.beije.xvii.exercises.ceccarelli;
import java.util.Scanner;

public class SoloVocali {

	public SoloVocali() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.println("inserisci stringa: ");
		String s = scan.next();
		for(int i=0;i<s.length();i++) {
		
			if(s.charAt(i)=='a' || s.charAt(i)=='e' || s.charAt(i)=='i' || s.charAt(i)=='o' || s.charAt(i)=='u') {
				if(i== s.length()-1) {
					System.out.print(s.charAt(i));
				}else {
					System.out.print(s.charAt(i)+ ", ");
				}
			}
		}

	}

}

