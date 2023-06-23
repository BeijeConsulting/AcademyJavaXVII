package it.beije.xvii.exercises.Char;

public class EsProgCompleti {
	
	public static void main(String[] args) {
//		fabbricaBibita(700,8,30);
//		caffeina(6);
//	System.out.println(conversioneStringa("din")); 
//	System.out.println(persistenza(39));
	System.out.println(rowSumOddNumbers(3));

	}
	public static void fabbricaBibita(int content, int evapPerDay, int threshold) {
        int gg = 0;
        while (content > 0) {
            int mlEvap = (content * evapPerDay) / 100;
            content -= mlEvap;
            double contentPerc = ( content / 1000) * 100;
            if (contentPerc <= threshold) {
                System.out.println("La bibita scade dopo : " + gg + " giorni");
                break;
            }

            gg++;
        }

	}
	public static int rowSumOddNumbers(int n) {
		 int primoNum = 1 + (n - 1) * n; 
	        int somma = 0;

	        for (int i = 0; i < n; i++) {
	            somma += primoNum + 2 * i;  
	        }

	        return somma;
	}
	public static void caffeina(int n) {
		if(n % 3 == 0 && n % 4 != 0) {
			System.out.print("Java");
			if(n % 2 == 0)
				System.out.print("Script");
		}
			
		else if(n % 3 == 0 && n % 4 == 0) {
			System.out.print("Coffee");
			if(n % 2 == 0)
				System.out.print("Script");
		}
		else 
			System.out.println("match_missed!");
			
	}
	public static StringBuilder conversioneStringa(String s) {
		StringBuilder conversione = new StringBuilder();
		for(int i = 0; i< s.length(); i++) {
			StringBuilder parolaSenzaLettera = new StringBuilder();
			parolaSenzaLettera.append(s.substring(0,i));
			parolaSenzaLettera.append(s.substring(i + 1));
			if(parolaSenzaLettera.toString().contains(s.substring(i, i+1))) {
				conversione.append(")");
			}
			else conversione.append("(");
		}
		return conversione;
	}
	public static int persistenza(int num) {
		int persistenza = 0;

        while (num >= 10) {
            int pr = 1;
            while (num > 0) {
                int cifra = num % 10; // ultima cifra
                pr *= cifra; 
                num /= 10; // Rimuove l'ultima cifra
            }

            num = pr;
            persistenza++;
        }

        return persistenza;
	}
}
