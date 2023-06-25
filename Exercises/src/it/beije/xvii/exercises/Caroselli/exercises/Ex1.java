package it.beije.xvii.exercises.Caroselli.exercises;

import static java.lang.Math.log;

public class Ex1 {

//    Una fabbrica alimentare produce una bibita gassata molto apprezzata. Sfortunatamente, il gas utilizzato
//    per fare le caratteristiche bollicine è molto volatile ed ogni giorno ne evapora un po’.
//    Si vuole un programma che calcoli il numero di giorni utile per l’imbottigliamento della bibita stessa, conoscendo:
//    La quantità di bibita pronta nella cisterna (‘content‘, espresso in ml)
//    la percentuale di gas che evapora in 24 ore (‘evapPerDay‘)
//    la soglia percentuale oltre la quale la bibita non è più apprezzabile (‘threshold‘) più
//    Tutti i numeri sono interi positivi. Il programma restituirà il numero dei giorni oltre il quale la bibita non sarà
//    più vendibile (non più sufficientemente gasata)

    public static double day() {
        Drink drink = new Drink(10.0, 2.0, 6);
        double content = drink.getContent();
        double threshold = drink.getThreshold();
        double evapPerDay = drink.getEvapPerDay();

        //f(x) = content * (evapPerDay / 100) * days
        //f(x) = threshold
        //threshold = content * (evapPerDay / 100) * days
        //days = (content * (evapPerDay / 100))/ (threshold / 100)

        return (content * (evapPerDay / 100) / (threshold / 100));
    }

    public static void main(String[] args) {
        System.out.println(day());
    }


}
