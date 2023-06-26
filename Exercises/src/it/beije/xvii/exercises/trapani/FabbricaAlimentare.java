package it.beije.xvii.exercises.trapani;

//		Giorno "Scadenza" vendibilita' bibita: DD/MM/YYYY
//		conoscendo quantita in cisterna CONTENT (ml),
//		percentuale gas evaporato in un giorno EVAP_PER_DAY(%),
//		soglia percentuale massima di liquido accettata THRESHOLD
//		---------------------------------------------------------



public class FabbricaAlimentare {
	
	private int content;
	private int evapPerDay;
	private int thresHold;	
	
	public FabbricaAlimentare(int content, int evapPerDay, int thresHold) {
		setContent(content);
		setEvapPerDay(evapPerDay);
		setThresHold(thresHold);
	}
	//Content
	public int getContent() {
		return content;
	}
	public void setContent(int content) {
		this.content = content;
	}	
	//EvapPerDay
	public int getEvapPerDay() {
		return evapPerDay;
	}
	public void setEvapPerDay(int evapPerDay) {
		this.evapPerDay = evapPerDay;
	}
	//ThresHold
	public int getThresHold() {
		return thresHold;
	}
	public void setThresHold(int thresHold) {
		this.thresHold = thresHold;
	}

	public static void main(String[] args) {
		
		
		

	}

}
