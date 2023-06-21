package it.beije.xvii.hierarchy.Caroselli_Giampaoli_Trapani;

import java.time.LocalDate;

public abstract class Worker extends Contract {
	
	private LocalDate birth;
	private String firstName;
	private String lastName;
	private int idContract;
	
	
	public Worker(int idContract, String firstName, String lastName) {
		super(idContract, firstName, lastName);
	}


	public LocalDate getBirth() {
		return birth;
	}


	public void setBirth(LocalDate birth) {
		this.birth = birth;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public int getIdContract() {
		return idContract;
	}


	public void setIdContract(int idContract) {
		this.idContract = idContract;
	}
	
}
