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

interface Square {
	Integer square(Integer number);
}

public class Quiz {

	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_RESET = "\u001B[0m";
	
	public static void main(String[] args) {
		
		boolean keepGoing = true;
		
		Scanner keyboard = new Scanner(System.in);
		
		while(keepGoing) {
			
			StringBuilder exercise = new StringBuilder();
			
			System.out.println("\n-----------------------------");
			
			exercise
			.append("\n\ninterface Square {\r\n"
					+ "	Integer square(Integer number);\r\n"
					+ "}")
			.append("\n")
			.append("\n")
			.append("public class Esercizio1 {")
			.append("\n")
			.append("\tpublic static void main(String[] args) {")
			.append("\n")
			.append("\n")
			.append("\t\t// COMPLETE CODE HERE WITH LAMBDA //")
			.append("\n")
			.append("\t\tSystem.out.println(number.square(10));")
			.append("\n")
			.append("\t}")
			.append("\n}")
			.append("\n");
			System.out.println(exercise.toString());
			
			System.out.println("Quale delle seguenti opzioni è corretta? (risposta multipla)");
			System.out.println("Scrivi in questo formato: A,B,C la risposta\n");
			
			System.out.println("A) Square number = Integer n -> { n*n;};");
			System.out.println("B) Square number = (n) -> (n*n);");
			System.out.println("C) Square number = n -> return n*n;");
			System.out.println("D) Square number = n -> { return n*n;};");
			
			System.out.println("\n");
			
			
			String input1 = keyboard.nextLine();
			
			input1 = input1.trim();
			
			
			exercise
			.append("\n\ninterface AverageCalculator {\r\n"
					+ "\tDouble average(List<Integer> numbers);\r\n"
					+ "}")
			.append("\n")
			.append("interface Sum {\r\n"
					+ "\tInteger add(Integer n1, Integer n2);\r\n"
					+ "}")
			.append("\n")
			.append("public class Esercizio2 {")
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
			
			System.out.println("Quale delle seguenti opzioni compila? (risposta multipla) (CODE 1)?");
			System.out.println("Scrivi in questo formato: A,B,C la risposta\n");
			System.out.println("\n");
			
			System.out.println("A) AverageCalculator averageCalculator = List<Integer> numList -> {");
			System.out.println("B) averageCalculator AverageCalculator = (List<int> numList) -> {");
			System.out.println("C) AverageCalculator averageCalculator = numList -> {");
			System.out.println("D) AverageCalculator averageCalculator = (List<Integer> numList) -> {");
			
			System.out.println("\n");

			String input2 = keyboard.nextLine();
			
			input2 = input2.trim();
			
			System.out.println("Quale delle seguenti opzioni compila e ha un risultato corretto? (risposta multipla) (CODE 2)?");
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
			System.out.println("E) numList.forEach((num) ->  {\r\n"
					+ "            	   Sum doSum = (n1, n2) -> (n1+n2);\r\n"
					+ "            	   sum += doSum.add(sum, num);\r\n"
					+ "            }");
			
			System.out.println("\n");
			System.out.println("\n");
			
			String input3 = keyboard.nextLine();
			
			input3 = input3.trim();
			
			String explain;
			
			System.out.println("\nLe tue risposte: ");
			
			System.out.println("\n1) " + input1);
			
			System.out.println("Spiegazione:");
			
			explain = ANSI_RED 
					 + "A) Errata; il parametro è tipato fuori dalle parentesi e nel body tra graffe manca il return\n"
					 + ANSI_GREEN
					 + "B) Corretta; parametro scritto correttamente, body corretto perchè non sono necessari ; e return quando non sono specificate le parentesi graffe.\n "
					 + "Non è necessario scrivere le parentesi graffe perchè l'operazione eseguita è una sola\n"
					 + ANSI_RED
					 + "C) Errata; Se il body omette le parentesi graffe non bisogna scrivere return\n"
					 + ANSI_GREEN
					 + "D) Corretta; Sintassi corretta, parentesi non necessarie al parametro singolo non tipato, sintassi del body corretta con graffe, return e ;\n"
					 + ANSI_RESET;
			
			System.out.println(explain);
			
			System.out.println("\n2 - CODE 1) " + input2);
			
			System.out.println("Spiegazione:");
			
			explain = ANSI_RED 
						   + "A) Errata; il parametro numList è scritto con il suo tipo ma non tra parentesi\n"
						   + "B) Errata; Non è possibile dichiarare una List<int>, bisogna usare la classe wrapper. Inoltre classe e nome variabile cui"
						   + "viene fatta l'assegnazione sono invertiti\n" 
						   + ANSI_RESET
						   + ANSI_GREEN 
						   + "C) Corretta; non è necessario mettere il parametro tra parentesi se è un parametro singolo e il tipo non è scritto\n"
						   + "D) Corretta; Sintassi corretta, parametro tipato e tra parentesi\n" 
						   + ANSI_RESET;
			
			System.out.println(explain);
			
			System.out.println("\n2 - CODE 2) " + input3);
			
			System.out.println("Spiegazione:");
			explain = ANSI_GREEN + "A) Corretta; La sintassi è corretta, la somma viene calcolata nel for\n"
						   + "B) Corretta; Sinstassi del for corretta, viene usata l'interfaccia funzionale Sum per effettuare la somma "
						   + "tra sum e num\n" + ANSI_RESET
						   + ANSI_RED + "C) Errata; Sintassi del for corretta ma 'Sum' è l'interfaccia, non la variabile, quindi non compila\n"
						   + "D) Errata; Compila, tuttavia effettua la somma due volte, prima con '+=' e poi con doSum.add, quindi il"
						   + "risultato è errato\n"
						   + "E) Errata; sum è una variabile dichiarata esternamente al forEach, quindi non può essere incrementata al suo interno\n" + ANSI_RESET;
			
			System.out.println(explain);
			
			System.out.println("Vuoi riprovare? (s/N)");
			String response = keyboard.nextLine();
			if(!response.equals("s")) {
				keepGoing = false;
			}
		}
		
		System.out.println("Premi un tasto per uscire...");
		keyboard.nextLine();
		
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
