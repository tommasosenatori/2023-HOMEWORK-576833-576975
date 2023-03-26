package it.uniroma3.diadia.giocatore;

public class Giocatore {
	private int cfu;
	private Borsa borsa;
	public Giocatore(int cfu) {
		this.cfu=cfu;
		this.borsa = new Borsa();
	}
	public int getCfu() {
		return cfu;
	}
	public void setCfu(int cfu) {
		this.cfu = cfu;
	}
	public Borsa getBorsa() {
		return borsa;
	}
	public void setBorsa(Borsa borsa) {
		this.borsa = borsa;
	}
}
