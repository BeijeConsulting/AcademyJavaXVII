package it.beije.xvii.exercises.Caroselli.myRubrica;

import java.util.Scanner;

//gestore della rubrica
public class RubricaManager {

    public static void main(String[] args) {

        System.out.println("Inserisci 1 per vedere la lista dei contatti,");
        System.out.println("Inserisci 2 per cercare un contatto,");
        System.out.println("Inserisci 3 per inserire un nuovo contatto,");
        System.out.println("Inserisci 4 per modificare un contatto");
        System.out.println("Inserisci 5 per cancellare un contatto,");
        System.out.println("Inserisci 6 per trovare i contatti duplicati,");
        System.out.println("Inserisci 7 per unire contatti duplicati");

//        Scanner scanner = new Scanner(System.in);
//        int choice = scanner.nextInt();


//        RubricaUtils.readContactsFromDb("cognome");
//        RubricaUtils.insertContact("mario", "Rossi", "344353", "", "");
//        System.out.println(RubricaUtils.findContacts("mario", "rossi", " ", " "));
        RubricaUtils.changeContact("mario");
//        RubricaUtils.deleteContact("Mario");
//        RubricaUtils.findContactFromInsertedValue("Mario");
//        RubricaUtils.deleteContacts("mario");

    }
}
