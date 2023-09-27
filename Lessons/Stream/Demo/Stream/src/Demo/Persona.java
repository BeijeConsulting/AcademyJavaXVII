package Demo;

public class Persona {
	
	private String nome;
	private int eta;
	private String citta;
	
	public Persona(String nome, int eta, String citta) {
		this.nome = nome;
		this.eta = eta;
		this.citta = citta;
	}
	
	public String getNome() {
		return nome;
	}

	public int getEta() {
		return eta;
	}

	public String getCitta() {
		return citta;
	}

	@Override
	public String toString() {
		return "Persona [nome=" + nome + ", eta=" + eta + ", citta=" + citta + "]";
	}

	
	
}
