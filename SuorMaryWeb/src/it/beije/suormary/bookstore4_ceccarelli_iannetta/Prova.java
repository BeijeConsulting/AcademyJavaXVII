package it.beije.suormary.bookstore4_ceccarelli_iannetta;

public class Prova {

	public static void main(String[] args) {
		EcommerceManager em = new EcommerceManager();
		
		String email = "alice.ceccarelli@gmail.com";
		String password  ="000001";
		int userId = 1;
		String payment = "cash";
		String address = "via sconosciuta";
		
		em.buy(userId,address,  payment);

	}

}
