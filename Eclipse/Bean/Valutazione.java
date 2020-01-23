package gamevaluate.bean;

public class Valutazione {
	
	private String id;
	private int gameplay;
	private int trama;
	private int grafica;
	private int creativita;
	private int innovazione;
	private int coinvolgimento;
	private int realismo;
	private int rigiocabilita;
	private int difficolta;
	
	
	public Valutazione(String id, int gameplay, int trama, int grafica, int creativita, int innovazione,
			int coinvolgimento, int realismo, int rigiocabilita, int difficolta) {
		this.id = id;
		this.gameplay = gameplay;
		this.trama = trama;
		this.grafica = grafica;
		this.creativita = creativita;
		this.innovazione = innovazione;
		this.coinvolgimento = coinvolgimento;
		this.realismo = realismo;
		this.rigiocabilita = rigiocabilita;
		this.difficolta = difficolta;
	}
	
	public Valutazione() {
		
	}


	public String getID_Valutazione() {
		return id;
	}

	public void setID_Valutazione(String iD_Valutazione) {
		id = iD_Valutazione;
	}

	public int getGameplay() {
		return gameplay;
	}


	public void setGameplay(int gameplay) {
		this.gameplay = gameplay;
	}


	public int getTrama() {
		return trama;
	}


	public void setTrama(int trama) {
		this.trama = trama;
	}


	public int getGrafica() {
		return grafica;
	}


	public void setGrafica(int grafica) {
		this.grafica = grafica;
	}


	public int getCreativita() {
		return creativita;
	}


	public void setCreativita(int creativita) {
		this.creativita = creativita;
	}


	public int getInnovazione() {
		return innovazione;
	}


	public void setInnovazione(int innovazione) {
		this.innovazione = innovazione;
	}


	public int getCoinvolgimento() {
		return coinvolgimento;
	}


	public void setCoinvolgimento(int coinvolgimento) {
		this.coinvolgimento = coinvolgimento;
	}


	public int getRealismo() {
		return realismo;
	}


	public void setRealismo(int realismo) {
		this.realismo = realismo;
	}


	public int getRigiocabilita() {
		return rigiocabilita;
	}


	public void setRigiocabilita(int rigiocabilita) {
		this.rigiocabilita = rigiocabilita;
	}


	public int getDifficolta() {
		return difficolta;
	}


	public void setDifficolta(int difficolta) {
		this.difficolta = difficolta;
	}


	@Override
	public boolean equals(Object o) {
		
		if (o == null)
			return false;
		if (o.getClass() != this.getClass())
			return false;
		
		Valutazione v = (Valutazione)o;
		return v.getID_Valutazione().equals(this.id);
	}


	@Override
	public String toString() {
		return "Valutazione [id =" + id + ", gameplay=" + gameplay + ", trama=" + trama + ", grafica=" + grafica
				+", creativita=" + creativita + ", innovazione=" + innovazione + ", coinvolgimento=" + coinvolgimento
				+ "realismo=" + realismo + ", rigiocabilita=" + rigiocabilita + ", difficolta=" + difficolta + "]";
	}

	
	

}
