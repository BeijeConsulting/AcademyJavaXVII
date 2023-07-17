package esercitazioni;
import java.util.Scanner;
public class RettangoloMain {

	public static void main(String[] args) {
		System.out.println("inserisci valori per base e altezza");
		Scanner ts = new Scanner(System.in);
		System.out.print("base: ");
		int base = Integer.valueOf(ts.next());
		System.out.print("altezza: ");	
		int altezza = Integer.valueOf(ts.next());
		Rettangolo r = new Rettangolo(base, altezza);
		System.out.println("sto calcolando...");
		System.out.println("perimetro: "+r.calcolaPerimetro(base, altezza));
		System.out.print("area: "+r.calcolaArea(base, altezza));
		ts.close();
	}

}
