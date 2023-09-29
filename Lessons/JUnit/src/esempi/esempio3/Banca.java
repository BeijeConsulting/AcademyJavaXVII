package esempi.esempio3;

import java.util.HashMap;
import java.util.Map;

public class Banca {
	  private Map<String, ContoBancario> conti;
	  public Banca() {
		  conti = new HashMap<>();
	  }

	    public void apriConto(String titolare, double saldoIniziale) {
	        ContoBancario conto = new ContoBancario(titolare, saldoIniziale);
	        conti.put(titolare, conto);
	    }

	    public ContoBancario trovaConto(String titolare) {
	        return conti.get(titolare);
	    }

		public Map<String, ContoBancario> getConti() {
			return conti;
		}
	    
}
