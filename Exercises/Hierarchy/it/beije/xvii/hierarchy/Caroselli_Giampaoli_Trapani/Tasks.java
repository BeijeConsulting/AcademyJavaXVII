package it.beije.xvii.hierarchy.Caroselli_Giampaoli_Trapani;

public interface Tasks {
	
	public default String getMainTask() {
		return "Programmare in Java";
	}
	public default  String getSecondaryTask () {
		return "Pulire la scrivania";
	}
	public default  String getReferent () {
		return "Ivo";
	}
	
	
}