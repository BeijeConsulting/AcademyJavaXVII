package it.beije.xvii.exercises.Char;

import java.io.FileWriter;
public class EsFileFibonacci {

	public static void main(String[] args) throws Exception {
		FileWriter fileWriter = new FileWriter("/v/bi.txt");
		long[] arr = new long[50000000];
		arr[0] = 1;
		arr[1] = 1;
		fileWriter.write(arr[0] + "\n");
		fileWriter.write(arr[1] + "\n");
		for(int i = 2; i < arr.length; i++) {
			arr[i] = arr[i - 1] + arr[i - 2];
			fileWriter.write(arr[i] + "\n");
		}
		fileWriter.close();

	}

}