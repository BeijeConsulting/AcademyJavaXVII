package it.beije.xvii.exercises.Caroselli.exercises;

public class Caffeine {

//    Scrivere la funzione caffeina(int n) che prende un numero positivo come argomento e:
//    Se il numero è divisibile per 3, stampa “Java”
//    Se il numero è divisibile per 3 e per 4, stampa “Coffee”
//    Se il numero appartiene ad uno dei due casi precedenti ed è pari, aggiunge “Script” in fondo alla stringa
//    Se non appartiene a nessuno dei casi precedenti stampa “match_missed!”

    public static void main(String[] args) {

        caffeine(12);
    }

    public static void caffeine(int n) {


        StringBuilder result = new StringBuilder();

        String java = "Java";
        String coffee = "Coffee";
        String script = "Script";

        if (n % 3 == 0) {
            System.out.println(java);
        }

        if (n % 3 == 0 && n %4 == 0) {
            System.out.println(coffee);
        }

        //se n deve essere divisibile sia per 3 che per 4 ne basta vedere una delle 2
        if (n % 3 == 0 && n % 2 == 0) {
            result.append(java).append(" ").append(coffee).append(" ").append(script);
        } else {
            System.out.println("match_missed!");
        }

        System.out.println(result);


    }

}
