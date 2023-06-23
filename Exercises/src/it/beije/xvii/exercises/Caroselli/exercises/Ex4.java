package it.beije.xvii.exercises.Caroselli.exercises;

import java.util.Arrays;

public class Ex4 {

    //n e' il numero di sfumature di grigio

//    Scrivere una funzione che prende un numero intero n come parametro e ritorna un array di sfumature di grigio in codice esadecimale
//    (#aaaaaa, per esempio). L’array dovrebbe essere ordinato in senso ascendente (#010101, #020202, ecc),
//    usando le lettere minuscole.  public class ShadesOfGrey {
//                                  static String[] shadesOfGrey(int num) {
////                                    returns n shades of grey in an array
//                                      }
//                                }
//   Ricorda che: il grigio è un colore composto dalla stessa quantità di rosso, verde e blu: #010101,
//   #aeaeae, #555555. Inoltre: #000000 e #FFFFFF non sono valori accettati.Se n è negativo ritornare
//   un array vuoto, se n è maggiore di 254, ritornare un array di 254 elementi.

    public static void main(String[] args) {
        System.out.println(Arrays.toString(shades(4)));
    }


    public static String[] shades(int n) {


        String[] grayShades = new String[n];

        for (int i = 0; i < n; i++) {
            //value = il valore della sfumatura corrente per tutti i componenti R, G e B
            int value = i + 1;
//          %02x rappresenta il placeholder per il valore esadecimale di due cifre (%x),
//          con la possibilità di inserire zeri iniziali se il valore esadecimale ha meno di due cifre (%02x).
//          i 3 valori %02x consecutivi rappresentano i tre componenti: rosso (R), verde (G) e blu (B).

            String hexValue = String.format("#%02x%02x%02x", value, value, value);
            grayShades[i] = hexValue;
        }



        return grayShades;
    }


}
