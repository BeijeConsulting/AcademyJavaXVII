package it.beije.xvii.exercises.Caroselli.stringhe;

import java.util.Scanner;

//  Scrivere un metodo che, data una stringa in input,
//  assuma questa come un nome di variabile e stampi per questa variabile il suo metodo “setter” e "getter"

public class ExString {
    public void setMethod() {
        System.out.println("Inserisci il nome della variabile");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        generateSetter(str);
        generateGetter(str);

    }

    public void generateSetter(String str) {
        String strUpper = str.substring(0, 1).toUpperCase() + str.substring(1);
        String setterName = "set" + strUpper;

        System.out.println("public void " + setterName + "(String " + str + ") {");
        System.out.println("    this." + str + " = " + str + ";");
        System.out.println("}");
    }

    public void generateGetter(String str) {
        String strUpper = str.substring(0, 1).toUpperCase() + str.substring(1);
        String getterName = "get" + strUpper;

        System.out.println("public String " + getterName + "(String " + str + ") {");
        System.out.println("   return this." + str + " = " + str + "; ");
        System.out.println("}");
    }

}
