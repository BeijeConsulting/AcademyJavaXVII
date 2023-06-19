package it.beije.xvii.exercises.iannetta;

public class Substring {

	public static String getSubstring(String s, int start, int end) {
		
		if (end < 0 || start < 0 ||
			end > s.length() ||
			end < start ) return null;
		
		String result = "";
		for (int i = start; i < end; i++) result += s.charAt(i);
		return result;
	}
	
	public static void main(String[] args) {
		String string = "test string to find substring";
		int start = 4;
		int end = 9;
		System.out.println(string);
		System.out.println("Start: " + start + "\tEnd: " + end);
		System.out.println(getSubstring(string, start, end));
	}

}
