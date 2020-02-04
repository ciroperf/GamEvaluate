package gamevaluate.bean;

public class Valutazione {
	
	private int id;
	private int gameplay;
	private int trama;
	private int grafica;
	private int creativita;
	private int innovazione;
	private int coinvolgimento;
	private int realismo;
	private int rigiocabilita;
	private int difficolta;
	private int counter;
	
	
	public Valutazione(int gameplay, int trama, int grafica, int creativita, int innovazione,
			int coinvolgimento, int realismo, int rigiocabilita, int difficolta, int counter) {
		this.gameplay = gameplay;
		this.trama = trama;
		this.grafica = grafica;
		this.creativita = creativita;
		this.innovazione = innovazione;
		this.coinvolgimento = coinvolgimento;
		this.realismo = realismo;
		this.rigiocabilita = rigiocabilita;
		this.difficolta = difficolta;
		this.counter = counter;
	}
	
	public Valutazione() {
		this.gameplay = 0;
		this.trama = 0;
		this.grafica = 0;
		this.creativita = 0;
		this.innovazione = 0;
		this.coinvolgimento = 0;
		this.realismo = 0;
		this.rigiocabilita = 0;
		this.difficolta = 0;
		this.counter = 0;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}
	
	public void addCounter() {
		this.counter++;
	}

	@Override
	public boolean equals(Object o) {
		
		if (o == null)
			return false;
		if (o.getClass() != this.getClass())
			return false;
		
		Valutazione v = (Valutazione)o;
		return v.getId() == this.id;
	}


	@Override
	public String toString() {
		return "Valutazione [id =" + id + ", gameplay=" + gameplay + ", trama=" + trama + ", grafica=" + grafica
				+", creativita=" + creativita + ", innovazione=" + innovazione + ", coinvolgimento=" + coinvolgimento
				+ "realismo=" + realismo + ", rigiocabilita=" + rigiocabilita + ", difficolta=" + difficolta + ", counter="+counter+"]";
	}

	
	

}
