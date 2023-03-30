package it.uniroma3.diadia.giocatore;
/**
 * Classe Giocatore
 * Un  giocatore in un gioco di ruolo
 * Rappresenta il giocatore all'interno di diadia
 * 
 *@author 576833 576975
 *@See Borsa
 */
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
	/**
	 * Restituisce un riferimento alla borsa trasportata dal giocatore
	 * @return riferimento alla borsa del giocatore
	 */
	public Borsa getBorsa() {
		return borsa;
	}
	public void setBorsa(Borsa borsa) {
		this.borsa = borsa;
	}
}
