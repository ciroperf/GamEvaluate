package gamevaluate.bean;

import com.mysql.cj.jdbc.Blob;

public class Gioco {
	
	private int id;
	private String nome;
	private String descrizione;
	private Blob immagine;
	private String genere;
	private String piattaforma;
	private String valutazione;
	
	

	
	public Gioco(int id, String nome, String descrizione, Blob immagine, String genere, String piattaforma,
			String valutazione) {
		super();
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
		this.immagine = immagine;
		this.genere = genere;
		this.piattaforma = piattaforma;
		this.valutazione = valutazione;
	}



	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public String getPiattaforma() {
		return piattaforma;
	}

	public void setPiattaforma(String piattaforma) {
		this.piattaforma = piattaforma;
	}



	public String getValutazione() {
		return valutazione;
	}


	public void setValutazione(String valutazione) {
		this.valutazione = valutazione;
	}

	public Gioco() {
		
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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


	public void setImmagine(java.sql.Blob blob) {
		this.immagine = (Blob) blob;
	}


	@Override
	public boolean equals(Object o) {
		
		if (o == null)
			return false;
		if (o.getClass() != this.getClass())
			return false;
		
		Gioco g = (Gioco)o;
		return g.getId() == this.id;
	}

	@Override
	public String toString() {
		return "Gioco [id=" + id + ", nome=" + nome + ", descrizione=" + descrizione + ", immagine=" + immagine
				+ ", genere=" + genere + ", piattaforma=" + piattaforma + ", valutazione=" + valutazione + "]";
	}

}
