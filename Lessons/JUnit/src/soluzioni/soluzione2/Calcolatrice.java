package soluzioni.soluzione2;

public class Calcolatrice {
	 public int somma(int a, int b) {
	       return a + b;
	 }

	 public int sottrazione(int a, int b) {
	        return a - b;
	 }

	 public int moltiplicazione(int a, int b) {
	        return a * b;
	 }

	 public double divisione(int a, int b) {
		   if (b == 0) {
	            throw new ArithmeticException("Divisione per zero non consentita");
	        }
	       return a / b;
	 }
	 public double radiceQuadrata(double numero) {
	        if (numero < 0) {
	            throw new IllegalArgumentException("Impossibile calcolare la radice quadrata di un numero negativo");
	        }
	        return Math.sqrt(numero);
	 }
}
