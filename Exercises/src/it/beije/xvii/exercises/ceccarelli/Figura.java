package it.beije.xvii.exercises.ceccarelli;

public class Figura {
	 public int velocity = 10;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num = 6;
		
		for(int i=0;i<num;i++) {
			int y;
			for(y=0;y<i+1;y++) {
				
				System.out.print(y+1);
				
			}
			System.out.print(" ");
			
			for(int h=0;h<num-i;h++) {
				//System.out.println("y " + y);
				System.out.print((num-h-y+1));
			}
			
			System.out.println();
		}
	}

}
