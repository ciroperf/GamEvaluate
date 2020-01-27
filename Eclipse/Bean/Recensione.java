package gamevaluate.bean;

public class Recensione {
	
	private String data,testo,username;
	private int gioco;
	
	public Recensione(String testo, int gioco, String username, String data) {
		this.testo = testo;
		this.gioco = gioco;
		this.username = username;
		this.data = data;
	}
	
	public Recensione(String testo, int gioco, String username) {
		this.testo = testo;
		this.gioco = gioco;
		this.username = username;
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

	public int getGioco() {
		return gioco;
	}

	public void setGioco(int gioco) {
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
		
		return r.getUsername().equals(this.username) && r.getGioco() == this.gioco && r.getData().equals(this.data);
	}
	
	@Override
	public String toString() {
		return "Recensione [testo = " + testo + ", gioco = "+ gioco + ", username = "+ username +", data = "+data+"]";
	}
	
	

}
