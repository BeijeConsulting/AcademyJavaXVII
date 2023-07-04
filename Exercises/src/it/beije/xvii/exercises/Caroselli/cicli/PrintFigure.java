package it.beije.xvii.exercises.Caroselli.cicli;

//  Stampare a video la seguente figura:
//******
//*****
//****
//***
//**
//*
public class PrintFigure {
    public void printFigure() {
        int row = 6;
        for (int i = row; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
