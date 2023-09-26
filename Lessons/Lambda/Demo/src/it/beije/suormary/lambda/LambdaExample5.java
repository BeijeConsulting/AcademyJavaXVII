package it.beije.suormary.lambda;

import java.util.function.Function;

public class LambdaExample5 {
	
	// Another example of how a function can be passed as a parameter
	public String manipulate(String string, Function<String, String> fn) {
	    return fn.apply(string);
	}
	
	public static void main(String[] args) {

		// The first function concatenates two strings
		Function<String, String> fn = parameter -> parameter + " from lambda";
		
		String result = new LambdaExample5().manipulate("Message", fn);
		
		System.out.println(result);
		System.out.println("----------------");
		
		// The second function replaces the parameter string from "This is a Message" with "Warning"
		fn = parameter -> "This is a Message".replace(parameter, "Warning");
		result = new LambdaExample5().manipulate("Message", fn);
		
		System.out.println(result);
		System.out.println("----------------");
		
		fn = parameter -> "This is a Message".replace(parameter, "Warning");
		result = new LambdaExample5().manipulate("This is", fn);
		
		System.out.println(result);
	}

}
