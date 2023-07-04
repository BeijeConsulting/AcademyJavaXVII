package it.beije.suormary.rubrica.caroselli;

import java.util.Scanner;

//gestore della rubrica
public class RubricaManager {

    public static void main(String[] args) throws Exception {

        System.out.println("Inserisci 1 per vedere la lista dei contatti,");
        System.out.println("Inserisci 2 per cercare un contatto,");
        System.out.println("Inserisci 3 per inserire un nuovo contatto,");
        System.out.println("Inserisci 4 per modificare un contatto");
        System.out.println("Inserisci 5 per cancellare un contatto,");
        System.out.println("Inserisci 6 per trovare i contatti duplicati,");
        System.out.println("Inserisci 7 per unire contatti duplicati");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch(choice) {
            case 1 :
                System.out.println(RubricaUtils.readContactsFromDb());
                break;
            case 2 :
                System.out.println("Inserisci il valore (esempio Mario o Rossi) per cercare i contatti desiderati");
                RubricaUtils.findContactFromInsertedValue(RubricaUtils.useScanner());
                break;
            case 3 :
                RubricaUtils.insertContact();
                break;
            case 4 :
                RubricaUtils.changeContact();
                break;
            case 5 :
                RubricaUtils.deleteContact();
                break;
            case 6 :
                System.out.println(RubricaUtils.findDuplicatesContactByValue());   
                break;
            case 7 :
                RubricaUtils.mergeDuplicatesContact();
                break;
        }

        scanner.close();

    }
    

}
