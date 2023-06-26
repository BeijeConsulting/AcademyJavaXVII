package it.beije.xvii.exercises.iannetta;


public class TestChapter6 {

	public static void main(String[] args) throws Exception {
//		String s = "";
//		try {
//		 s += "t";
//		 throw new Exception();
//		// s += "t";
//		} catch(Exception e) {
//		 s += "c";
//		} finally {
//		 s += "f";
//		}
//		s += "a";
//		System.out.print(s);
//
//		 try {
//			 throw new RuntimeException();
//		 } catch (RuntimeException e) {
//			 //throw new RuntimeException();
//		 } finally {
//			 throw new Exception();
//		 }
		
		
		String result = "";
		String v = null;
		try {
		try {
		result += "before";
		v.length();
		result += "after";
		} catch (NullPointerException e) {
		result += "catch";
		throw new RuntimeException();
		} finally {
		result += "finally";
		throw new Exception();
		}
		} catch (Exception e) {
		result += "done";
		}
		System.out.println(result);
	}
}
