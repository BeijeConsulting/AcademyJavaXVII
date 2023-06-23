package esercizi;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;


public class Lettura {

	public static void main(String[] args) throws Exception {
		File f = new File("C:\\Users\\Padawan\\Desktop\\azienda.txt");
		System.out.println(f.exists());
		
		FileReader fr = new FileReader(f);
		
		BufferedReader bf = new BufferedReader(fr);
		
		List<String> l = new ArrayList <String>();
		
		while(bf.ready()) {
			String s = bf.readLine();
			l.add(s);
		}
		
		double sommaB=0.0;
		double sommaS=0.0;
		int tot=0;
		int totErr=0;
		List<String> l1 = new ArrayList <String>();
		
		for(String st : l) {
		
			String [] info = st.split(" ");
			
			
			Lettura lettura = new Lettura();
			boolean check = lettura.checkToDo(info);
			
			if(check==true) {
			
				if(info[3].equals("B")) {
					sommaB=sommaB+(Double.parseDouble(info[1])*Double.parseDouble(info[2]));
					tot++;
				} 
				if(info[3].equals("S")) {
					sommaS=sommaS+(Double.parseDouble(info[1])*Double.parseDouble(info[2]));
					tot++;
				} 
			} else {
				l1.add(st);
				
				totErr++;
			}
			
			System.out.println(Arrays.toString(info));
			
			
		}
		
		System.out.println("Op: "+tot+" Buy: "+sommaB+" Sell: "+sommaS);
		System.out.println("Err "+totErr);
		
		
		
		for(String lista:l1) {
			//System.out.println(l1);
			String [] errori = new String[1];
			errori[0]=lista;
			System.out.println(errori[0]);
		}
		
		bf.close();
		
	}

	public boolean checkToDo(String[] s) {
		
		
		//controllo che ci siano 4 campi sulla base dello split con lo spazio
		if(s.length!=4)
			return false;
		
		
		
		//controllo che l'ultimo campo sia B o S
		if (!(s[3].equals("B")) && !(s[3].equals("S")))
			return false;
		
	
		
			//controllo i prezzi...E' qui l'errore
			String prezzo=s[1];
			boolean trovatoErrore=false;
			for(int i=0; i<prezzo.length() && !trovatoErrore; i++) {
					int c = prezzo.charAt(i);
		        if ((c < 48 || c > 57) && c!=46) {
		        	trovatoErrore=true;
		        }
			}
			
			if(trovatoErrore) {
				return false;
			}
			
			//controllo numero azioni
			String azioni=s[2];
			for(int i=0; i<azioni.length() && !trovatoErrore; i++) {
				int c = azioni.charAt(i);
	        if (c < 48 || c > 57) {
	        	trovatoErrore=true;
	        }
		}
		
			if(trovatoErrore) {
				return false;
			}
			
			
			//controllo che il primo campo abbia esattamente 3 caratteri
			if(s[0].length()!=3)
				return false;
			
			// controllo che i caratteri del primo campo siano tutte lettere maiuscole
			/*String maiuscole = s[0].toUpperCase();
			if(!(maiuscole.equals(s[0])))
				return false;
			*/
			
			//controllo che i caratteri siano solo Lettere e che siano maiuscole
			for(int i=0; i<s[0].length(); i++) {
				int c = s[0].charAt(i);
				if(c<65 || c>90) {
					trovatoErrore=true;
				}
			}
			if(trovatoErrore) {
				return false;
			}
			
	return true;
	}	
		/*try {
		Double.parseDouble(info[1]);
		Double.parseDouble(info[2]);
	} catch (Exception e) {
		totErr++;
		System.out.println("valori numerici incorretti");
	}*/
		
}
