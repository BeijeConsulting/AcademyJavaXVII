package it.beije.xvii.exercises.Caroselli.myRubrica;

import java.util.Objects;

public class Contact {

    public Contact() {
    }

    public Contact( String name, String surname, String phone, String email, String note) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.note = note;
    }

    public Contact(int id, String name, String surname, String phone, String email, String note) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.note = note;
    }

    private int id;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String note;


    public int getId() {
        return id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", note='" + note + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(name, contact.name) && Objects.equals(surname, contact.surname) && Objects.equals(phone, contact.phone) && Objects.equals(email, contact.email) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, phone, email, note);
    }
}
