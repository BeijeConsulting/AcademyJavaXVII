package packageb;

import java.util.Random;
import java.lang.System;
import java.util.Arrays;


public class RandomArray{
	private Random rand = new Random();
	private final int upperbound = 10;
	private int[] array;

	public RandomArray(int n){
		array = new int [n];
		for (int i = 0; i < n; i++){
			this.array[i] = rand.nextInt(upperbound);
		}
	System.out.println(Arrays.toString(this.array));
	}
}