package it.beije.xvii.hierarchy.Caroselli_Giampaoli_Trapani;

import java.time.*;

public abstract class Contract implements HoursOfWork, Salary, Holidays, Tasks{
	
	private LocalDate start;
	private LocalDate end;
	private int idContract;
	private boolean leasingCompany;
	private String firstName;
	private String lastName;
	
	
	
	
	public Contract(LocalDate start, LocalDate end, int idContract, boolean leasingCompany) {	//costruttore per TypeOfWork
		super();
		this.start = start;
		this.end = end;
		this.idContract = idContract;
		this.leasingCompany = leasingCompany;
	}


	public Contract(int idContract, String firstName, String lastName) {  //costruttore per Worker
		super();
		this.idContract = idContract;
		this.firstName = firstName;
		this.lastName = lastName;
	}


	//getter astratti
	public abstract LocalDate getStart();

	public abstract LocalDate getEnd();
	
	public abstract int getIdContract();

	public abstract boolean isLeasingCompany();

	public abstract String getFirstName();
	
	public abstract String getLastName();

	//setter astratti
	public abstract void setStart(LocalDate start);

	public abstract void setEnd(LocalDate end);

	public abstract void setIdContract(int idContract);

	public abstract void setLeasingCompany(boolean leasingCompany);

	public abstract void setFirstName(String firstName);

	public abstract void setLastName(String lastName);
	
}
