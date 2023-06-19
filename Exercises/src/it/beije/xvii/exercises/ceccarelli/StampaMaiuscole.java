package it.beije.xvii.exercises.ceccarelli;

public class StampaMaiuscole {

	public StampaMaiuscole() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] arrayStringhe = {"Questa" , "è" , "Maiuscola", "Ma", "questa", "no"};
		for(int i=0;i<arrayStringhe.length;i++) {
			char c = arrayStringhe[i].charAt(0);
			if(c>'A' && c<'Z'){
				System.out.println(arrayStringhe[i]);
			}
		}

	}

}
