package esempi.esempio3;

public class ContoBancario {
	  private String titolare;
	    private double saldo;

	    public ContoBancario(String titolare, double saldoIniziale) {
	        this.titolare = titolare;
	        this.saldo = saldoIniziale;
	    }

	    public void deposita(double importo) {
	        saldo += importo;
	    }

	    public void preleva(double importo) {
	        if (importo <= saldo) {
	            saldo -= importo;
	        } else {
	            throw new IllegalArgumentException("Saldo insufficiente");
	        }
	    }

	    public double getSaldo() {
	        return saldo;
	    }

		public String getTitolare() {
			return titolare;
		}
	    
}
