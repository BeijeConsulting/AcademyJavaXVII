package it.beije.suormary.lambda;

// Functional interface, it has only one abstract method inside. The annotation is optional
@FunctionalInterface
interface Addable{  
    int add(int a,int b);  
}  

public class LambdaExample1 {

	public static void main(String[] args) {
		
        // With Lambda
		
		Addable ad1=(a,b)->(a+b);  
		
        System.out.println("First: " + ad1.add(10,20));  
        
        
        // Without Lambda
        
        // Both methods that don't use Lambda are more verbose
        
        // We either need to implement Addable with an anonymous class
        
        Addable ad2 = new Addable(){  
            public int add(int a, int b){return (a+b);}  
        };  
        
        System.out.println("Second: " + ad2.add(10,20));  
        
        
        // Or create a class that implements Addable
        
        Addable ad3 = new NoLambda();
        
        System.out.println("Third: " + ad3.add(10,20));  
	}

}

class NoLambda implements Addable{
	@Override
	public int add(int a, int b) {
		return a + b;
	}
}