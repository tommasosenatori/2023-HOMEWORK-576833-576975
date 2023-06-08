package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public abstract class Comando {

	/**
	 * esecuzione del comando
	 */
	public abstract void esegui(Partita partita);
	/**
	 * set parametro del comando
	 */
	public void setParametro(String parametro) {
		
	}
	public String getParametro() {
	 return null;	
	}
	public abstract String getNome();
}
