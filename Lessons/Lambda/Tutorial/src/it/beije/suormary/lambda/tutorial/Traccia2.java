package it.beije.suormary.lambda.tutorial;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

interface AverageCalculator {
    Double average(List<Integer> numbers);
}

interface Sum {
	Integer add(Integer n1, Integer n2);
}

public class Traccia2 {

	public static void main(String[] args) {
		
		boolean correct = false;
		
		// -1 = errato
		// 0 = parziale
		// 1 = corretto
		Integer p1 = -1;
		Integer p2 = -1; 
		
		Scanner keyboard = new Scanner(System.in);
		
		while(!correct) {
			
			StringBuilder exercise = new StringBuilder();
			
			System.out.println("\n-----------------------------");
			
			exercise
			.append("\n\ninterface AverageCalculator {\r\n"
					+ "\tDouble average(List<Integer> numbers);\r\n"
					+ "}")
			.append("\n")
			.append("interface Sum {\r\n"
					+ "\tInteger add(Integer n1, Integer n2);\r\n"
					+ "}")
			.append("\n")
			.append("public class Traccia2 {")
			.append("\n")
			.append("\tpublic static void main(String[] args) {")
			.append("\n")
			.append("\n")
			.append("\t\tList<Integer> numbers = Arrays.asList(10, 20, 30, 40, 50);")
			.append("\n\n")
			.append("\t\t// CODE 1 //\n")
			.append("\t\t double sum = 0;\n\n")
			.append("\t\t// CODE 2 //\n\n")
			.append("\t\treturn sum / numList.size();\n")
			.append("\t\t};")
			.append("\n\t\tdouble media = averageCalculator.average(numbers);")
			.append("\n\t\tSystem.out.println(\"La media dei numeri è: \" + media);\n")
			.append("\t}")
			.append("\n}")
			.append("\n");
			
			System.out.println(exercise.toString());
			
			//correct = true;
			
			System.out.println("Quale delle seguenti opzioni è corretta (risposta multipla) (CODE 1)?");
			System.out.println("Scrivi in questo formato: A,B,C la risposta\n");
			System.out.println("\n");
			
			System.out.println("A) AverageCalculator averageCalculator = List<Integer> numList -> {");
			System.out.println("B) AverageCalculator averageCalculator = (List<int> numList) -> {");
			System.out.println("C) AverageCalculator averageCalculator = numList -> {");
			System.out.println("D) AverageCalculator averageCalculator = (List<Integer> numList) -> {");
			
			System.out.println("\n");

			String input = keyboard.nextLine();
			
			input = input.trim();
			
			if(input.equals("C,D") || input.equals("D,C")) {
				p1 = 1;
			}else if(input.contains("B") || input.contains("D")) {
				p1 = 0;
			}else {
				p1 = -1;
			}
			
			System.out.println("Quale delle seguenti opzioni è corretta (risposta multipla) (CODE 2)?");
			System.out.println("Scrivi in questo formato: A,B,C la risposta\n");
			
			System.out.println("A) for (int num : numList) {\r\n"
					+ "                sum += num;\r\n"
					+ "            }");
			System.out.println("\n");
			System.out.println("B) for (int num : numList) {\r\n"
					+ "            	   Sum doSum = (n1, n2) -> (n1+n2);\r\n"
					+ "            	   sum = doSum.add(sum, num);\r\n"
					+ "            }");
			System.out.println("\n");
			System.out.println("C) for (int num : numList) {\r\n"
					+ "                Sum += num;\r\n"
					+ "            }");
			System.out.println("\n");
			System.out.println("D) for (int num : numList) {\r\n"
					+ "            	   Sum doSum = (n1, n2) -> (n1+n2);\r\n"
					+ "            	   sum += doSum.add(sum, num);\r\n"
					+ "            }");
			
			System.out.println("\n");
			System.out.println("\n");
			
			input = keyboard.nextLine();
			
			input = input.trim();
			
			if(input.equals("A,B") || input.equals("B,A")) {
				p2 = 1;
			}else if(input.contains("A") || input.contains("B")) {
				p2 = 0;
			}else {
				p2 = -1;
			}
			
			if(p1 == 1 && p2 == 1) {
				correct = true;
				System.out.println("\nRisposta corretta! :D");
			}else if(p1 == 0 && p2 == 0) {
				System.out.println("\nRisposta errata :(");
				System.out.println("Riproviamo!!!\n");
			}else {
				System.out.println("\nRisposta parzialmente corretta... :|");
				System.out.println("Riproviamo!!!\n");
			}
			
		}
		
		
		/*List<Integer> numbers = Arrays.asList(10, 20, 30, 40, 50);

        AverageCalculator averageCalculator = (List<Integer> numList) -> {
            Integer sum = 0;
            /*for (int num : numList) {
                sum += num;
            }
            
            double result = 0;
            
            for (int num : numList) {
            	Sum doSum = (n1, n2) -> (n1+n2);
            	sum = doSum.add(sum, num);
            }
            
            result = (double) (sum / numList.size());
            return result;
        };

        double media = averageCalculator.average(numbers);
        System.out.println("La media dei numeri è: " + media);*/

	}

}
