package it.beije.xvii.exercises.Caroselli.exercises;

public class Persistence {

//    Scrivere la funzione “persistenza”, che prende un parametro intero positivo e ritorna la sua
//        “persistenza moltiplicativa”, che è il numero di volte per cui bisogna moltiplicare le cifre fra loro
//    fino ad avere un unico carattere.Per esempio:persistence(39) == 3 // perché 3*9 = 27, 2*7 = 14, 1*4=4
//    // e 4 è diuna cifra solapersistence(999) == 4 // perché 9*9*9 = 729, 7*2*9 = 126,
//       1*2*6 = 12, e 1*2 = 2
//    persistence(4) == 0 // perché 4 è già una cifra singola

    public static int persistence(int n) {

        //n e' gia ad una cifra, quindi non bisogna fare nessuna moltiplicazione
        if (n < 10) {
            return 0;
        }

        int count = 0;

        int n1 = n / 10;
        int n2 = n % 10;
        int multiplication = n1 * n2;

        while (multiplication != 0) {
            n1 = multiplication / 10;
            n2 = multiplication % 10;
            multiplication = n1 * n2;
            count ++;
        }

        return count;
    }


    public static void main(String[] args) {
        System.out.println(persistence(25));
    }
}
