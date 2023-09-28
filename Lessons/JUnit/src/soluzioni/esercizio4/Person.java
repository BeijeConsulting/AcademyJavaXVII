package soluzioni.esercizio4;

import java.util.Objects;

public class Person {

	private String name;
	private String surname;
	private int age;
	private String city;
	
	
	
	public Person() {
		super();
	}

	public Person(String name, String surname, int age, String city) {
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.city = city;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getCity() {
		return this.city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return age == other.age && Objects.equals(name, other.name) && Objects.equals(surname, other.surname) &&
				Objects.equals(city, other.city);
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", surname=" + surname + ", age=" + age + ", city=" + city + "]";
	}
	
	

	
}
