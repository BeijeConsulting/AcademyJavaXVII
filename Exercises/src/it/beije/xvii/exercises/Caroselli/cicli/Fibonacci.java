package it.beije.xvii.exercises.Caroselli.cicli;

import java.util.Scanner;

//first n rows of Fibonacci's series
public class Fibonacci {
    public void printFibonacci() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci il numero di elementi che vuoi stampare della serie di Fibonacci");
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(fibonacciEx(j) + " ");
            }
            System.out.println();
        }
    }

    //fibonacci's series
    public int fibonacciEx(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fibonacciEx(n - 1) + fibonacciEx(n - 2);
        }
    }


}
