package it.beije.xvii.exercises.Caroselli.myRubrica;

import java.io.*;
import java.util.Scanner;

//        Realizzare un programma che, ottenuto in input il percorso di una directory,
//        ne elenchi il contenuto in un file di testo.
//        Se all'interno della directory sono presenti ulteriori directories,
//        il programma dovrà elencare anche il contenuto di queste e delle eventuali
//        directories interne, in modo ricorsivo.
//        Il file dovrebbe chiamarsi come il nome della directory ricevuta in
//        input.

public class Ex2 {
    public static void main(String[] args) throws IOException {


        //percorso file: /home/flaviana/proveFileEsercizio

        int level = 0;

        System.out.println("Scrivi il percorso di una directory per visualizzarne il contenuto in un file");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();

        try {

            File file = new File(path);

            if (!file.exists() || !file.isDirectory() || !file.canRead()) {
                System.out.println("Il percorso inserito non esiste o non e' leggibile");
            }

            String directoryName = file.getName();
            String path2 = file.getParent();
            String fileName = path2 + "/p/" + directoryName + ".txt";


            FileWriter outputFile = new FileWriter(fileName);
            System.out.println("File creato con successo: " + fileName);

            writeInFile(file, outputFile, level);

            outputFile.close();

        } catch (IOException e) {
            System.out.println("Si è verificato un errore durante la scrittura del file: " + e.getMessage());
        }


    }

    public static void writeInFile(File directory, FileWriter outputFile, int level) throws IOException {

        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                StringBuilder indentation = new StringBuilder();

                for (int i = 0; i < level; i++) {
                    indentation.append("  ");  // Aggiunge due spazi per ogni livello
                }
                if (!file.isDirectory()) {
                    outputFile.write(indentation + file.getName());
                    outputFile.write(System.lineSeparator());
                    outputFile.flush();
                } else {
                    outputFile.write(file.getName() + "(dir)");
                    outputFile.write(System.lineSeparator());
                    outputFile.flush();
                    level = level + 1;
                    writeInFile(file, outputFile, level + 1);
                }
            }
        }


    }

}


