package it.beije.xvii.exercises.ceccarelli;

public class MassimoArray {
	int number;

	public MassimoArray() {
		// TODO Auto-generated constructor stub
		
		
	}
	public boolean contains(int e, int[] array) {
		for(int i =0; i<array.length;i++) {
			if(array[i]==e) {
				return true;
			}
		}
		return false;
			
		}
	public boolean contains2(Object e, Object[] array) {
		for(int i =0; i<array.length;i++) {
			//così non funziona
			/*if(array[i]==e) {
				return true;
			}*/
			if(array[i].equals(e)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isCrescente(int [] array) {
		for(int i=1;i<array.length;i++) {
			if(array[i]<array[i-1]) {
				return false;
			}
		}
		return true;
	}
	
	public int mostRecurrent(int [] array) {
		int count=0;
		Integer[][] ricorr = new Integer[array.length][2];
		for(int i=0;i<array.length;i++) {
			for(int y=0;y<ricorr.length;y++) {
				if(ricorr[y][0]==null) {
					
					ricorr[y][0]=array[i];
					ricorr[y][1]= 1;
				}else if(array[i]==ricorr[y][0]){
					ricorr[y][1]=ricorr[y][1]+1;
				}
			}
		}
		System.out.println("lunghezza array: " + ricorr.length);
		for(int h=0; h<ricorr.length;h++) {
				if(count<ricorr[h][1]) {
					count = ricorr[h][1];
					number = ricorr[h][0];
					System.out.println("numero:" + number);
				}
			}
		return number;
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {2,2,-1,0,0,0};
		int max=array[0];
		int min = array[0];
		int indiceMax =0;
		int indiceMin =0;
		Integer num = new Integer(1);
		Integer[] arrayO = {0,3,6,9,6,23,8,7};
		MassimoArray m = new MassimoArray();
		for(int i =0; i<array.length; i++) {
			if(array[i]>max) {
				max = array[i];
				indiceMax = i;
			} else if(array[i]< min) {
				min = array[i];
				indiceMin = i;
			}
		}
		System.out.println("max: " + max + ", min: " + min);
		System.out.println("indice del max: " + indiceMax);
		System.out.println("indice del min: " + indiceMin);
		System.out.println("true o false? " + m.contains(3, array));
		System.out.println("true o false?? " + m.contains2(num, arrayO));
		System.out.println("sono in ordine crescente? " + m.isCrescente(array));
		System.out.println("numero ricorrente " + m.mostRecurrent(array));
		
	}

}
