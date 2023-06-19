package it.beije.xvii.exercises.ceccarelli;

public class NomeStringa {
	String setName;
	String getName;
	public NomeStringa() {
		// TODO Auto-generated constructor stub
		
		
	}
	
	public String setnome(String s) {
		//char a = s.charAt(0);
		s.substring(0).toUpperCase();
		this.setName = "set" + s.replace(s.charAt(0), Character.toUpperCase(s.charAt(0)));
		return setName;
	}
	
	public String getnome(String s) {
		this.getName = "get" + s.replace(s.charAt(0), Character.toUpperCase(s.charAt(0)));
		return getName;
	}

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NomeStringa n = new NomeStringa();
		System.out.println(n.setnome("alice"));
		System.out.println(n.getnome("keinny"));

	}

}
