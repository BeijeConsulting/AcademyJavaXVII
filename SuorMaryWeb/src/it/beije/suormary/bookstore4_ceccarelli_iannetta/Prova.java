package it.beije.suormary.bookstore4_ceccarelli_iannetta;

public class Prova {

	public static void main(String[] args) {
		EcommerceManager em = new EcommerceManager();
		
		String email = "alice.ceccarelli@gmail.com";
		String password  ="000001";
		
		em.isUser(email, password);

	}

}
