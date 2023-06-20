package it.beije.xvii.exercises.ulloa;

/*
 * Scrivere un programma che stampi i primi 100 elementi della successione di Fibonacci. 
 */

public class Esercizio7 {

	public static void main(String[] args) {
		int n1=0,n2=1;
		int n3;    
		 System.out.print(n1+" "+n2);  
		 for(int i=2;i<=100;i++)   
		 {    
		  n3=n1+n2;    
		  System.out.print(" "+n3);    
		  n1=n2;    
		  n2=n3;    
		 }    

	}

}
