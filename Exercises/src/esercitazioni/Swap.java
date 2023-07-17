package esercitazioni;
import java.util.Scanner;
public class Swap {

	public static void main(String[] args) {
		Scanner ts = new Scanner(System.in);
		System.out.println("inserisci due valori: ");
		System.out.println("primo: ");
		int primo = Integer.valueOf(ts.next());
		System.out.print("secondo: ");
		int secondo = Integer.valueOf(ts.next());
		int temp= primo;
		primo=secondo;
		secondo=temp;
		System.out.println("eseguendo lo swap... ");
		System.out.println("primo: "+primo);
		System.out.println("secondo: "+secondo);
		ts.close();
	}

}
