package gamevaluate.bean;

public class Piattaforma {
	
	private String nome;
	
	
	public Piattaforma(String nome) {
		this.nome = nome;
	}
	
	public Piattaforma() {
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (o.getClass() != this.getClass())
			return false;
		
		Piattaforma p = (Piattaforma) o;
		
		return p.getNome().equals(this.nome);
	}

	
	@Override
	public String toString() {
		return "Piattaforma [nome =" + nome + "]";
	}

}
