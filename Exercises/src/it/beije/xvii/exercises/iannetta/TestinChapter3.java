package it.beije.xvii.exercises.iannetta;

public class TestinChapter3 {

	public static void main(String[] args) {
		String s = new String ("test 12345");
		String ss = "test 12345";
		System.out.println(s == ss);
		System.out.println(s.equals(ss));
		System.out.println(s.length());
		
		String test = s;
		System.out.println(test == s);
		System.out.println(test.equals(s));
		
		int[][] array = new int[2][];
		array[0] = new int[2];
		array[1] = new int[3];
		array[0][1] = 3;
	}

}
