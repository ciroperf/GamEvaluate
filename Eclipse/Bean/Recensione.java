package gamevaluate.bean;

public class Recensione {
	
	private String data,testo,gioco,username;
	
	public Recensione(String testo, String gioco, String username, String data) {
		super();
		this.testo = testo;
		this.gioco = gioco;
		this.username = username;
		this.data = data;
	}
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public String getGioco() {
		return gioco;
	}

	public void setGioco(String gioco) {
		this.gioco = gioco;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Recensione() {
		
	}
	


	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (o.getClass() != this.getClass())
			return false;
		
		Recensione r = (Recensione)o;
		
		return r.getUsername().equals(this.getUsername()) && r.getGioco().equals(this.getGioco()) && r.getData().equals(this.getData());
	}
	
	@Override
	public String toString() {
		return "Recensione [testo = " + testo + ", gioco = "+ gioco + ", username = "+ username +", data = "+data+"]";
	}
	
	

}
