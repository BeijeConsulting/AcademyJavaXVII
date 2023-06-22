package it.beije.suormary.rubrica.exFiles;

import com.sun.media.jfxmediaimpl.HostUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Ex1 {

    public static void main(String[] args) throws Exception {

        Calculation calculation = new Calculation();

        File file = new File("/home/flaviana/dev/corso-beije/AcademyJavaXVII/SuorMary/src/it/beije/suormary/rubrica/exFiles/actions.txt");

        FileReader fileReader = new FileReader(file);

        List<String> lines = readFromFile(fileReader);

        amountCalculation(lines, calculation);

        checkErrors(lines, calculation);

    }

    public static List<String> readFromFile(FileReader fileReader) throws IOException {
        BufferedReader reader = new BufferedReader(fileReader);
        List<String> listOfActions = new ArrayList<>();
        while (reader.ready()) {
            String line = Arrays.toString(reader.readLine().split("\";\""));
            listOfActions.add(line);
        }
        System.out.println(listOfActions);
        return listOfActions;

    }

    public static void amountCalculation(List<String> lines, Calculation calculation) throws IOException {

        //count rappresenta il numero di operazioni lette
        int count = 0;
//        double buyTotal = 0.0;
//        double sellTotal = 0.0;

        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i).replace("\"", "");
            String[] parts = line.split(",");

            calculation.setNameOfAction(parts[0].trim().replace("[", ""));
            calculation.setOperation(parts[3].trim().replace("]", ""));
//            System.out.println(calculation.getOperation());
            System.out.println(Arrays.toString(parts));

            calculation.setAmount(Double.parseDouble(parts[1].trim()));
            calculation.setQuantity(Double.parseDouble(parts[2].trim()));

            count++;

            if (calculation.getOperation().equals("B")) {
                calculation.setBuyTotal(calculation.getAmount() * calculation.getQuantity());
            } else if (calculation.getOperation().equals("S")) {
                calculation.setSellTotal(calculation.getAmount() * calculation.getQuantity());
            }
        }

        System.out.println("Op: " + count + " Buy: " + calculation.getBuyTotal() + " Sell: " + calculation.getSellTotal());

    }


    public static void checkErrors(List<String> lines, Calculation calculation) {

        int errors = 0;

        if (!calculation.getNameOfAction().equals(calculation.getNameOfAction().toUpperCase())) {

        }

        if (lines.isEmpty()) {

        }


    }
}
