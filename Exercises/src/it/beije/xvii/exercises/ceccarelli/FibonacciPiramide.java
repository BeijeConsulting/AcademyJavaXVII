package it.beije.xvii.exercises.ceccarelli;

public class FibonacciPiramide {

	public FibonacciPiramide() {}
	public void calcolo(int num) {
		// TODO Auto-generated constructor stub
		int[] b = {0,1};
		int prec;
		System.out.print( b[1] + " ");
		for(int i=0;i<num;i++) {
			prec=b[1];
			int temp = b[0] + b[1];
			System.out.print(temp + " ");
			b[1]=temp;
			b[0] = prec;
		}
		System.out.println();
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FibonacciPiramide f = new FibonacciPiramide();
		
		for(int y=0;y<=3;y++) {
			f.calcolo(6);
		
	}

}
}
