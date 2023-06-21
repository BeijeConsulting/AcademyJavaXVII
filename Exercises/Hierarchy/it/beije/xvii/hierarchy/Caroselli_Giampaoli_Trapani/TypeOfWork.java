package it.beije.xvii.hierarchy.Caroselli_Giampaoli_Trapani;

import java.time.LocalDate;

public abstract class TypeOfWork extends Contract{ //implements HoursOfWork{
	
	private LocalDate start;
	private LocalDate end;
	private int idContract;
	private boolean leasingCompany;
	private String description;
	private boolean vat;		//partita IVA
	
	
	
	public TypeOfWork(LocalDate start, LocalDate end, int idContract, boolean leasingCompany) {
		super(start, end, idContract, leasingCompany);
	}

	public LocalDate getStart() {
		return start;
	}

	public void setStart(LocalDate start) {
		this.start = start;
	}

	public LocalDate getEnd() {
		return end;
	}

	public void setEnd(LocalDate end) {
		this.end = end;
	}

	public int getIdContract() {
		return idContract;
	}

	public void setIdContract(int idContract) {
		this.idContract = idContract;
	}

	public boolean isLeasingCompany() {
		return leasingCompany;
	}

	public void setLeasingCompany(boolean leasingCompany) {
		this.leasingCompany = leasingCompany;
	}

	
	public String getDescription() {
		return description;
	}

	
	public void setDescription(String description) {
		this.description = description;
	}

	
	public boolean isVat() {
		return vat;
	}

	
	public void setVat(boolean vat) {
		this.vat = vat;
	}
	
}
