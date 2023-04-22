package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando {
	/**
	 * Stampa le informazioni sulla stanza corrente e sullo stato della partita
	 */
	@Override
	public void esegui(Partita partita) {
		partita.getConsole().mostraMessaggio("Cfu: "+partita.getGiocatore().getCfu());
		partita.getConsole().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		partita.getConsole().mostraMessaggio(partita.getGiocatore().getBorsa().toString());
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
