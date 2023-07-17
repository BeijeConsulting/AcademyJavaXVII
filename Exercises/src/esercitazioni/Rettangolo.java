package esercitazioni;

public class Rettangolo {
	private int base;
	private int altezza;
	
	public Rettangolo(int base, int altezza) {
		setBase(base);
		setAltezza(altezza);
	}

	public int getBase() {
		return base;
	}

	public void setBase(int base) {
		this.base = base;
	}

	public int getAltezza() {
		return altezza;
	}

	public void setAltezza(int altezza) {
		this.altezza = altezza;
	}
	
	public int calcolaPerimetro(int base, int altezza) {
		return (base*2)+(altezza*2);
	}
	
	public int calcolaArea(int base, int altezza) {
		return base*altezza;
	}
	
}
