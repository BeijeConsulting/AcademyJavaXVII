package packagea;

import packageb.RandomArray;
import java.lang.System;
import java.util.Scanner;

public class Test{

	public static void main(String [] args){
		System.out.println("Enter an integer number");
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		RandomArray ra = new RandomArray(n);
	}
}
