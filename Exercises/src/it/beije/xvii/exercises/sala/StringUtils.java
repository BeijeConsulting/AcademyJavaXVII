package it.beije.xvii.exercises.sala;
import java.util.Scanner;
/*
 * String indexOf(String s, char c)
String indexOf(String s, char c, index fromIndex)
String indexOf(String s, String str)
String indexOf(String s, String str, index fromIndex)
String substring(String s, int beginIndex)
String substring(String s, int beginIndex, int endIndex)
String toLowerCase(String s)
String toUpperCase(String s)
boolean equals(String s1, String s2)
boolean equalsIgnoreCase(String s1, String s2)
boolean contains(String s, String str)
boolean startsWith(String s, String prefix)
boolean endsWith(String s, String suffix)
String replace(String s, char oldChar, char newChar)
String replace(String s, String oldChar, String newChar)
String trim(String s)
 */
public class StringUtils {

	public static void main(String[] args) {
		Scanner ts = new Scanner(System.in);
		System.out.println("inserisci tre frasi o parole");
		String s1 = ts.nextLine();
		String s2 = ts.nextLine();
		String s3 = ts.nextLine();
		System.out.println("inserisci due lettere");
		char c1 = ts.next().charAt(0); //per convertire da String a char
		char c2 = ts.next().charAt(0);
		System.out.println("inserisci due indici");
		int fromBegin = ts.nextInt();
		int endIndex = ts.nextInt();
		
		//System.out.println("metodo indexOf(String s, char c): "+StringUtils.indexOf(s1, c1));
		//System.out.println("metodo indexOf(String s, char c, index fromIndex): "+StringUtils.indexOf(s1, c1, fromBegin));
		//System.out.println("metodo indexOf(String s, String str): "+StringUtils.indexOf(s1, s2));
		//System.out.println("metodo indexOf(String s, String str, index fromIndex: "+StringUtils.indexOf(s1, s2, fromBegin));
		System.out.println("metodo subString(String, index: "+StringUtils.substring(s1, fromBegin));
		System.out.println("metodo subString(String, beginIndex, endIndex: "+StringUtils.substring(s1, fromBegin, endIndex));
		//System.out.println("metodo toLowerCase(String): "+StringUtils.toLowerCase(s1));
		//System.out.println("metodo toUpperCase(String):" +StringUtils.toUpperCase(s1));
		//System.out.println("metodo equals(String s1, String s2):" +StringUtils.equals(s1,s2));
		//System.out.println("metodo equalsIgnoreCase(String s1, String s2):" +StringUtils.equalsIgnoreCase(s1,s2));
		//System.out.println("metodo contains(String s, String str):" +StringUtils.contains(s1,s2));
		//System.out.println("metodo startsWith(String s, String prefix):" +StringUtils.startsWith(s1,s2));
		//System.out.println("metodo endsWith(String s, String prefix):" +StringUtils.endsWith(s1,s2));
		//System.out.println("metodo replace(String s, String prefix):" +StringUtils.replace(s1,c1,c2));
		//System.out.println("metodo replace(String s, String prefix):" +StringUtils.replace(s1,s2,s3));
		System.out.println("metodo trim(String s):" +StringUtils.trim(s1));
	}
	
	public static int indexOf(String s, char c) {
		/*int indice = 0;
		for(int i=0; i<s.length(); i++) {
			if(s.charAt(i)==c) {
				indice = i;
			}
		}
		return indice;*/
		StringUtils s1 = new StringUtils();
		return s1.indexOf(s, c, 0);
	}
	
	
	public static int indexOf(String s, char c, int fromIndex) {
		int indice = -1;
		
		if(fromIndex>-1) {
			for(int i=fromIndex; i<s.length(); i++) {
				if(s.charAt(i)==c) {
					indice = i;
					return indice;
					
				}
			}
		}
		
		return indice;
	}
	
	public static int indexOf(String s, String str) {
		/*boolean trovato=false;
		String copia="";
		//str è quella più breve, mentre s è la più lunga
		for(int i=0; i<str.length(); i++) {
			trovato=false;
			for(int j=0; j<s.length() && !trovato; j++) {
				if(str.charAt(i)==s.charAt(j)) {
					trovato=true;
					copia=copia+str.charAt(i);
				}
			}
		} */
		
		//codice commentato perchè riutilizzato da metodo indxOf(String s, String s)
		/*int indice = -1;
		int lunghezzamax=0;
		boolean fine=false;
		
		if(s.length()==str.length()) {
			int i=0;
			int j=0;
			
			
			while(s.charAt(i)==str.charAt(j) && i<s.length() && j<s.length() && !fine) {
				i++;
				j++;
				lunghezzamax++;
				
				if(lunghezzamax == s.length()) {
					fine=true;
					indice=0;
				}
			}
			
			
		} else if(str.length()<s.length()) {
			String copia=""; //non ricordo perchè l'ho messo
			
			

			for(int i=0; i<str.length(); i++) {
				for(int j=0; j<s.length(); j++) {
					if(s.charAt(i)==str.charAt(j)) {
						while(s.charAt(i)==str.charAt(j) && i<s.length() && j<s.length() && !fine) {
							i++;
							j++;
							lunghezzamax++;
							if(lunghezzamax==str.length()) {
								//cosi controllo se tutta la parola corta è presente in quella lunga
								fine=true;
								//sottraendo nella stringa più lunga dall'ultima lettera della
								//stringa più corta la lunghezza-1 di quella più corta
								//trovo l'indice di inizio di quella più corta in quella più lunga
								indice = j-str.length()-1;
							}
						}	
					}
					
				}
			}
		} else if(s.length()<str.length()) {
			for(int i=0; i<s.length(); i++) {
				for(int j=0; j<str.length(); j++) {
					if(s.charAt(i)==str.charAt(j)) {
						while(s.charAt(i)==str.charAt(j) && i<str.length() && j<str.length() && !fine) {
							i++;
							j++;
							lunghezzamax++;
							if(lunghezzamax==s.length()) {
								//cosi controllo se tutta la parola corta è presente in quella lunga
								fine=true;
								//sottraendo
								indice = j-s.length()-1;
							}
						}	
					}
					
				}
			}
		}
		
		
		//non ricordo perchè lo stavo scrivendo
		//if(fine) {
			//StringUtils s1 = new StringUtils();
			//return s1.
			
		//}
		
		return indice;
		*/
		
		
		return StringUtils.indexOf(s, str, 0);
		
	}
	
	//comincia a cercare la sottostringa dalla posizione fromIndex 
	public static int indexOf(String s, String str, int fromIndex) {
		int indice = -1;
		int lunghezzamax=0;
		boolean fine=false;
		
		
		
		if(s.length()==str.length() && fromIndex==0) {
			
			int i=0;
			int j=0;
			
			
			while(i<s.length() && j<s.length() && s.charAt(i)==str.charAt(j) && !fine) {
				i++;
				j++;
				lunghezzamax++;
				
				if(lunghezzamax == s.length()) {
					fine=true;
					indice=0;
				}
			}
			
			
		} else if(s.length()>str.length() && (fromIndex>-1 && fromIndex<str.length())) { //prima più lunga della seconda
			String copia=""; //non ricordo perchè l'ho messo
			

			for(int i=fromIndex; i<str.length(); i++) {
				for(int j=0; j<s.length(); j++) {
					if(i<str.length() && j<s.length() && str.charAt(i)==s.charAt(j)) {
						while(i<str.length() && j<s.length() && str.charAt(i)==s.charAt(j) && !fine) {
							i++;
							j++;
							lunghezzamax++;
							if(lunghezzamax==str.length()) {
								//cosi controllo se tutta la parola corta è presente in quella lunga
								fine=true;
								//sottraendo nella stringa più lunga dall'ultima lettera della
								//stringa più corta la lunghezza-1 di quella più corta
								//trovo l'indice di inizio di quella più corta in quella più lunga
								indice = j-str.length();
							}
						}	
					}
					
				}
			}
		} else if(s.length()<str.length() && (fromIndex>-1 && fromIndex<str.length())) { //seconda più lunga della prima
			
			for(int i=fromIndex; i<s.length(); i++) {
				for(int j=0; j<str.length(); j++) {
					if(i<s.length() && j<str.length() && s.charAt(i)==str.charAt(j)) {
						while(i<s.length() && j<str.length() && s.charAt(i)==str.charAt(j) && !fine) {
							i++;
							j++;
							lunghezzamax++;
							if(lunghezzamax==s.length()) {
								//cosi controllo se tutta la parola corta è presente in quella lunga
								fine=true;
								//sottraendo
								indice = j-s.length();
							}
						}	
					}
					
				}
			}
		}
		
		
		
		//if(fine) {
			//StringUtils s1 = new StringUtils();
			//return s1.
			
		//}
		
		return indice;
	}
	
	
	public static String substring(String s, int beginIndex) throws StringIndexOutOfBoundsException {
		
		return StringUtils.substring(s, beginIndex, s.length());
		
	}
	
	public static String substring(String s, int beginIndex, int endIndex) throws StringIndexOutOfBoundsException{
		String finale="";
		
		if((beginIndex>-1 && endIndex<=s.length()) && beginIndex<endIndex) {
			for(int i=beginIndex; i<endIndex; i++){
				finale=finale+s.charAt(i);
			}
	}else throw new StringIndexOutOfBoundsException();
	
	
	return finale;
	}
	
	public static String toLowerCase(String s) {
		int indice=0;
		char letteraNuova;
		String nuova="";
		
		for(int i=0; i<s.length(); i++) {
			if(s.charAt(i)>=65 && s.charAt(i)<=90) {
				indice=s.charAt(i)+32;
				letteraNuova=(char)indice;
				nuova=nuova+letteraNuova;
			} else {
				nuova=nuova+s.charAt(i);
			}
		}
		return nuova;
	}
	
	public static String toUpperCase(String s) {
		int indice=0;
		char nuovaLettera;
		String nuova="";
		
		for(int i=0; i<s.length(); i++) {
			if(s.charAt(i)>=97 && s.charAt(i)<=122) {
				indice=s.charAt(i)-32;
				nuovaLettera=(char)indice;
				nuova=nuova+nuovaLettera;
			} else {
				nuova=nuova+s.charAt(i);
			}
		}
		return nuova;
	}
	
	public static boolean equals(String s1, String s2) {
		int i=0;
		int j=0;
		if(s1.length()==s2.length()) {
			while(i<s1.length() && j<s2.length() && s1.charAt(i)==s2.charAt(j)) {
				i++;
				j++;
			}
			if(i==s1.length() && j<=s2.length()) {
				return true;
			} else {
				return false;
			}
		}
	
		return false;
	}
	
	
	public static boolean equalsIgnoreCase(String s1, String s2) {
		String nuovas1 = StringUtils.toLowerCase(s1);
		String nuovas2 = StringUtils.toLowerCase(s2);
		
		return StringUtils.equals(nuovas1, nuovas2);
		
	}
	
	public static boolean contains(String s, String str) {
		int indice=StringUtils.indexOf(s, str);
		if(indice!=-1) {
			return true;
		}
		return false;
	}
	
	public static boolean startsWith(String s, String prefix) {
		if(prefix.length()<=s.length()) {
			String stringa = StringUtils.substring(s, 0, prefix.length());
			System.out.println("string vale: "+stringa);
			return StringUtils.equals(stringa, prefix);
			
			/*int i=0;
			int j=0;
			int finePrefix=0;;
			while(j<prefix.length() && s.charAt(i)==prefix.charAt(j)) {
				i++;
				j++;
				finePrefix++;
			}
			
			if(finePrefix==prefix.length()) {
				return true;
			} */
		}
		

		return false;
	}
	
	
	
	public static boolean endsWith(String s, String suffix) {
		if(suffix.length()<=s.length()) {
			String nuova = StringUtils.substring(s, s.length()-suffix.length());
			System.out.println("nuova vale: "+nuova);
			return StringUtils.equals(nuova, suffix);
		}
		return false;
	}
	
	public static String replace(String s, char oldChar, char newChar) {
		String nuova="";
		for(int i=0; i<s.length(); i++) {
			if(s.charAt(i)==oldChar) {
				nuova=nuova+newChar;
			} else {
				nuova=nuova+s.charAt(i);
			}
		}
		return nuova;
	}
	
	public static String replace(String s, String oldChar, String newChar) {
		int indiceOccorrenza=-1;
		String nuova="";
		int fineOccorrenza;
		int indice=0;
		
		//scorro su s, i=indice perchè se non trova un'occorrenza aumenta di una posizione sulla stringa s, altrimenti mi metto
		//nella posizione successiva alla fine dell'occorrenza
		for(int i=0; i<s.length(); i=indice) {	
			
				indiceOccorrenza=StringUtils.indexOf(s, oldChar, indice);
				
				if(indiceOccorrenza!=-1 && indiceOccorrenza==indice) { //per capire se posso appendere direttamente la parola
					fineOccorrenza=indiceOccorrenza+(oldChar.length());
					for(int j=indiceOccorrenza; j<fineOccorrenza; i++) {
						nuova=nuova+newChar;
					}
					indice=fineOccorrenza;
				}
				
				else {
					nuova=nuova+s.charAt(i);
					indice++;
				}	
		}
		return nuova;
	}
	
	//elimina tutti gli spazi bianchi prima dell'inizio della parola e dopo la sua fine
	public static String trim(String s) {
		String nuova="";
		int contaSpaziInizio=0;
		int contaSpaziFine=0;
		for(int i=0; i<s.length() && s.charAt(i)==32; i++) {
			contaSpaziInizio++;
		}
		System.out.println(contaSpaziInizio);
		for(int i=s.length()-1; i>=0 && s.charAt(i)==32; i--) {
			contaSpaziFine++;
		}
		System.out.println(contaSpaziFine);
		if(contaSpaziInizio==s.length() && contaSpaziFine==s.length()) { //la stringa ha solo sapzi
				System.out.println("la stringa conteneva solo spazi");
				return nuova;
		}else if(contaSpaziInizio!=0 || contaSpaziFine!=0) {
			nuova=nuova+StringUtils.substring(s, contaSpaziInizio, s.length());
			nuova=StringUtils.substring(nuova, 0, nuova.length()-contaSpaziFine);
		}else if(contaSpaziInizio!=0 && contaSpaziFine==0){ //tolgo spazi solo all'inizio
			nuova=nuova+StringUtils.substring(s, contaSpaziInizio, s.length());
		} else if(contaSpaziFine!=0 && contaSpaziInizio==0){ //tolgo spazi solo alla fine
			nuova=nuova+StringUtils.substring(s, 0, s.length()-contaSpaziFine);
		} else if(contaSpaziInizio==0 && contaSpaziFine==0){ //la stringa non ha spazi
			nuova=s;
		} 
		System.out.println("lunghezza s: "+s.length()+" lunghezza nuova: "+nuova.length());
		return nuova;
		
	}
}

