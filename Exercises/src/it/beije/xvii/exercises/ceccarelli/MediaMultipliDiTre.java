package it.beije.xvii.exercises.ceccarelli;

public class MediaMultipliDiTre {

	public MediaMultipliDiTre() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int sum =0;
		int count = 0;
		int[] array = { 3, 9, 7, 5, 4, 8};
		for(int i=0;i<array.length;i++) {
			if(array[i]%3==0) {
				sum += array[i];
				count+=1;
			}
		}
		System.out.println("media numeri divisibili per 3: " + (sum/count));

	}

}
