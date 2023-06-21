package it.beije.xvii.hierarchy.Caroselli_Giampaoli_Trapani;

public interface Salary {
	
	public default double getNetSalary() {
		return 1500.00;
	}
	
	public default double getGrossSalary() {
		return 1880.00;
	}
	
	public default double getHourlyPay() {
		return 10.00;
	}
	
	public default double getOverTimePay() {
		return 12.00;
	}


}
