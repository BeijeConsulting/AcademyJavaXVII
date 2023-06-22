package it.beije.suormary.rubrica.exFiles;

import java.io.*;
import java.util.Scanner;

public class Ex3 {

//    Realizzare un tool che faccia la copia esatta di un file,
//    sia esso di testo che di altro formato.Il programma dovrà
//    ricevere in input il path del file da duplicare ed il path di
//    destinazione della copia; deve essere possibile fornire i 2 parametri
//    sia a linea di comando (es: java Duplicator /temp/pippo.txt /temp/pippo2.txt)
//    sia da console tramite scanner.


    //paths: /home/flaviana/proveFile.txt /home/flaviana/proveFile
    public static void main(String[] args) {


        if (args.length == 0) {
            System.out.println("Inserisci i path dei due file di destinazione e di copia separati da uno spazio");
            Scanner scanner = new Scanner(System.in);
            String path1 = scanner.next();
            String path2 = scanner.next();
            copyFile(path1, path2);

        } else {
            copyFile(args[0], args[1]);
        }

    }

    public static void copyFile(String path1, String path2) {

        if (path1.isEmpty() || path2.isEmpty()) {
            System.out.println("I path sono vuoti");
        }

        File fileToCopy = new File(path1);
        File fileToWrite = new File(path2, "copia.txt");


        if (fileToCopy.isFile() && fileToCopy.canRead() && fileToWrite.getParentFile().canWrite()) {
            try {
                FileReader reader = new FileReader(fileToCopy);
                BufferedReader bufferedReader = new BufferedReader(reader);
                FileWriter fileWriter = new FileWriter(fileToWrite);

                while (bufferedReader.ready()) {
                    String r = bufferedReader.readLine();
                    fileWriter.write(r);
                    fileWriter.write(System.lineSeparator());
                    fileWriter.flush();
                }

                bufferedReader.close();
                reader.close();
                fileWriter.close();

                System.out.println("Copia completata con successo");
            } catch (IOException e) {
                System.out.println("Si è verificato un errore durante la copia del file: " + e.getMessage());
            }

        } else {
            System.out.println("Il file di origine non esiste o non è leggibile, o il file di destinazione non è scrivibile");
        }
    }

}
