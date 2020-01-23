package gamevaluate.bean;

import com.mysql.cj.jdbc.Blob;

public class Gioco {
	
	private String codice;
	private String nome;
	private String descrizione;
	private Blob immagine;
	
	
	public Gioco(String codice, String nome, String descrizione, Blob immagine) {
		super();
		this.codice = codice;
		this.nome = nome;
		this.descrizione = descrizione;
		this.immagine = immagine;
	}
	
	public Gioco() {
		
	}


	public String getCodice() {
		return codice;
	}


	public void setCodice(String codice) {
		this.codice = codice;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getDescrizione() {
		return descrizione;
	}


	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}


	public Blob getImmagine() {
		return immagine;
	}


	public void setImmagine(Blob immagine) {
		this.immagine = immagine;
	}


	@Override
	public boolean equals(Object o) {
		
		if (o == null)
			return false;
		if (o.getClass() != this.getClass())
			return false;
		
		Gioco g = (Gioco)o;
		return g.getCodice().equals(this.codice);
	}


	@Override
	public String toString() {
		return "Gioco [codice=" + codice + ", nome=" + nome + ", descrizione=" + descrizione + ", immagine=" + immagine
				+ "]";
	}
	
	
	
	

}
