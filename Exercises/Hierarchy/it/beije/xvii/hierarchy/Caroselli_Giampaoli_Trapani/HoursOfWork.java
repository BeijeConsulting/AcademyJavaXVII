package it.beije.xvii.hierarchy.Caroselli_Giampaoli_Trapani;


public interface HoursOfWork {
	
	public default double getBeginH() {
		return 9.00;
	}
	
	public default double getEndH() {
		return 18.00;
	}
	
	public default double getPauseH() {
		return 1.00;
	}
	
	public default double getWeekH() {
		return 40.00;
	}
	
	

}
