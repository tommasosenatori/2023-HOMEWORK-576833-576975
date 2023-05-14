package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando {
	/**
	 * Stampa le informazioni sulla stanza corrente e sullo stato della partita
	 */
	@Override
	public void esegui(Partita partita) {
		StringBuilder descrizione = new StringBuilder("Cfu: "+partita.getGiocatore().getCfu());
		descrizione.append("\n"+partita.getStanzaCorrente().getDescrizione());
		descrizione.append("\n"+partita.getGiocatore().getBorsa().toString());
		partita.getConsole().mostraMessaggio(descrizione.toString());
	}

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getParametro() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNome() {
		return "guarda";
	}

}
