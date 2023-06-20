package stringhe;

//Scrivere un programma StampaMaiuscole che, dato un array di stringhe, ne stampa solo quelle con la prima lettera maiuscola
public class PrintCapitalLetter {
    public void printCatitalLetter() {

        String[] array = {"ciao", "Mondo", "Java"};

        System.out.println("Stringhe con la prima lettera maiuscola:");
        for (String str : array) {
            if (Character.isUpperCase(str.charAt(0))) {
                System.out.println(str);
            }
        }

    }

}
