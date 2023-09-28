package esercizi.esercizio2;

/* 
 Implementa una classe di Test per questa calcolatrice andando a gestire anche le varie eccezioni
 */

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

	       return a / b;
	 }
	 public double radiceQuadrata(double numero) {
		 
	        return Math.sqrt(numero);
	 }
}
