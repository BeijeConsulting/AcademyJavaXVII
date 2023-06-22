package it.beije.xvii.exercises.trapani;

public class MediaDiMultipliDiTre {

	public static void main(String[] args) {
		int[] num = {18, 15, 6, 12, 33, 23, 9, 4, 21};
		double media = 0; 
		int cont=0;
		
		System.out.println(num.length);;
		
		for(int i=0; i<num.length; i++) {
			if((num[i]%3)==0) {
				media+=num[i];
				cont++;
			}
		}
		media=media/cont;
		
		System.out.println(media);
	}

}
