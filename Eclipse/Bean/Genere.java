package gamevaluate.bean;

public class Genere {
	
	private String nome;
	private String descrizione;
	
	
	
	public Genere(String nome, String descrizione) {
		this.nome = nome;
		this.descrizione = descrizione;
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


	@Override
	public boolean equals(Object o) {
			
		if (o == null)
			return false;
		if (o.getClass() != this.getClass())
			return false;
		Genere g = (Genere) o;
		
		return g.getNome().equals(this.getNome());

	}



	@Override
	public String toString() {
		return "Genere [nome=" + nome + ", descrizione=" + descrizione + "]";
	}
	
	
	

}
