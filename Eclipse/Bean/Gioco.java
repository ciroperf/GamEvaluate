package gamevaluate.bean;


public class Gioco {
	
	private int id;
	private String nome;
	private String descrizione;
	private String immagine;
	private String genere;
	private String piattaforma;
	private int valutazione;

	public Gioco(String nome, String descrizione, String immagine, String genere, String piattaforma) {
		this.nome = nome;
		this.descrizione = descrizione;
		this.immagine = immagine;
		this.genere = genere;
		this.piattaforma = piattaforma;
	}
	
	public Gioco() {
		
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
	
	public int getValutazione() {
		return valutazione;
	}


	public void setValutazione(int valutazione) {
		this.valutazione = valutazione;
	}


	public String getImmagine() {
		return immagine;
	}

	public void setImmagine(String immagine) {
		this.immagine = immagine;
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
				+ ", genere=" + genere + ", piattaforma=" + piattaforma + "]";
	}

}
