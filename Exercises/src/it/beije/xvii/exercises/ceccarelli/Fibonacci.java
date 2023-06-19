package it.beije.xvii.exercises.ceccarelli;

public class Fibonacci {

	public Fibonacci() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] b = {0,1};
		int prec;
		System.out.print(b[0] + " " + b[1] + " ");
		for(int i=0;i<100;i++) {
			prec=b[1];
			int temp = b[0] + b[1];
			System.out.print(temp + " ");
			b[1]=temp;
			b[0] = prec;
		}
	}

}
