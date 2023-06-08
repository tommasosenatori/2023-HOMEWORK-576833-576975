package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;
import static it.uniroma3.diadia.Costanti.CFU_INIZIALI;
/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

	
	private IO console;
	private Stanza stanzaCorrente;
	private boolean finita;
	private Giocatore giocatore;
	private Labirinto labirinto;

	public Partita(IO console){
		this(new Labirinto(),console);
	}
	
	public Partita(Labirinto labirinto,IO console){
		this.console = console;
		this.giocatore = new Giocatore(CFU_INIZIALI);
		this.labirinto = labirinto;
		this.stanzaCorrente = this.labirinto.getIngresso();
		this.finita = false;
	}

	public Stanza getStanzaVincente() {
		return this.labirinto.getUscita();
	}

	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}

	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.getStanzaCorrente()== this.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (this.giocatore.getCfu() == 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}

	public Giocatore getGiocatore() {
		return giocatore;
	}
	public Boolean giocatoreIsVivo() {
		return this.giocatore.isVivo();
	}

	public IO getConsole() {
		return console;
	}

	public void setLabirinto(Labirinto labirinto) {
		this.labirinto = labirinto;
	}
}
