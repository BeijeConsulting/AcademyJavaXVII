package it.beije.xvii.exercises.trapani;

public class Media {

	public static void main(String[] args) {
		int[] num = {5,4,3,7,2,9,4,8,3,0};
		double media=0;
		
		for(int i=0; i<num.length; i++) {
			media+=num[i];
		}
		media=media/num.length;
		
		System.out.println(media);
		
	}

}
