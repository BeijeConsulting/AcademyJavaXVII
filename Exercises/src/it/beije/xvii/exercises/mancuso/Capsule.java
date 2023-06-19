package it.beije.xvii.exercises.mancuso;

public class Capsule {
	
	private String name;
	private String surname;
	private int age;
	
	public String getName() {return name;}
	public void setName(String n) {name=n;}
	
	public String getSurname() {return surname;}
	public void setSurname(String sn) {surname=sn;}
	
	public int getAge() {return age;}
	public void setAge(int a) {
		if(a>=0) {
			age=a;
		}
	}
	
	public boolean isAdult() {
		if(getAge() > 18) {
			return true;
		}else {
			return false;
		}
	}
	
	public String toString() {
		String s = "My name is " + name + " " + surname + ", I am " + age + " years old.\n";
		if(isAdult()) {
			s += "I am an adult.\n";
		}else {
			s += "I am a kid.\n";
		}
		return s;
	}
	
	public static void main(String[] args) {
		Capsule kid = new Capsule();
		
		kid.setAge(12);
		kid.setName("John");
		kid.setSurname("Smith");
		
		Capsule adult = new Capsule();
		
		adult.setAge(44);
		adult.setName("Jenny");
		adult.setSurname("Kimmel");
		
		System.out.println(kid.toString());
		System.out.println(adult.toString());

	}

}
