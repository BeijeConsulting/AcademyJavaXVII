package it.beije.xvii.exercises.trapani;

public class StampaZigZag {

	public static void main(String[] args) {
		int[] num = {0,1,2,3,4,5,6,7,8,9};
		
		for(int i=0, j=(num.length-1) ; i<num.length && j>i; i++, j--) {
			System.out.print(num[i] + ", " + num[j] +", ");
			}
		}

}
