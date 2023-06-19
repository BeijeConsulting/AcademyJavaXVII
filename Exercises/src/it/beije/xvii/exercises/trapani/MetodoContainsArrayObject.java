package it.beije.xvii.exercises.trapani;

public class MetodoContainsArrayObject {
	
		protected static boolean contains(Object e, Object[] array) {
			boolean presente=false;
			for(int i=0; i<array.length; i++) {
				if(array[i]==e) {
					presente=true;
					break;
				}
			}
			return presente;	
		}

		
		public static void main(String[] args) {
			Object[] num = {1,2,3,6,4,5};
			Object n = 9;
			
			
			System.out.print(contains(n,num));		
		}
		

	}


