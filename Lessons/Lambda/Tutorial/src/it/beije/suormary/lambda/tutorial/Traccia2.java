package it.beije.suormary.lambda.tutorial;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

interface AverageCalculator {
    Double average(List<Integer> numbers);
}

public class Traccia2 {

	public static void main(String[] args) {
		
		boolean correct = false;
		
		//Scanner keyboard = new Scanner(System.in);
		
		while(!correct) {
			StringBuilder exercise = new StringBuilder();
			
			System.out.println("\n-----------------------------");
			
			exercise
			.append("\n\ninterface AverageCalculator {\r\n"
					+ "\tDouble average(List<Integer> numbers);\r\n"
					+ "}")
			.append("\n")
			.append("\n")
			.append("public class Traccia2 {")
			.append("\n")
			.append("\tpublic static void main(String[] args) {")
			.append("\n")
			.append("\n")
			.append("\t\tList<Integer> numbers = Arrays.asList(10, 20, 30, 40, 50);")
			.append("\n")
			.append("\t}")
			.append("\n}")
			.append("\n");
			
			System.out.println(exercise.toString());
			
			correct = true;
		}
		
		
		List<Integer> numbers = Arrays.asList(10, 20, 30, 40, 50);

        AverageCalculator averageCalculator = (numList) -> {
            double sum = 0;
            for (int num : numList) {
                sum += num;
            }
            return sum / numList.size();
        };

        double media = averageCalculator.average(numbers);
        System.out.println("La media dei numeri è: " + media);

	}

}
