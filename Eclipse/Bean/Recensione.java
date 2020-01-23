package gamevaluate.bean;

public class Recensione {
	
	private String codice;
	private String testo;
	
	public Recensione(String codice, String testo) {
		super();
		this.codice = codice;
		this.testo = testo;
	}
	
	public Recensione() {
		
	}
	
	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (o.getClass() != this.getClass())
			return false;
		
		Recensione r = (Recensione)o;
		
		return r.getCodice().equals(this.getCodice());
	}
	
	@Override
	public String toString() {
		return "Recensione [codice = " + codice + ", testo = " + testo + "]";
	}
	
	

}
