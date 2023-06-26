package it.beije.xvii.exercises.trapani;

public class ControlloOrdineArrays {
	
	public static boolean isCrescente(int[] array) {
		boolean checked=false;
		int temp = array[0];
		
		for(int i=1; i<array.length; i++) {
			if(temp<array[i]) {
				temp=array[i];
			} else {
				break;
			}
		}
		if(temp == array[(array.length-1)]) {
			checked = true;
		}
		
		return checked;
	}

	public static void main(String[] args) {
		int[] num = {2, 4, 8, 1, 6, 4};
		int[] num2 = {2, 4, 6, 8};
		
		System.out.print(isCrescente(num));
		System.out.println();
		System.out.print(isCrescente(num2));
		

	}

}
