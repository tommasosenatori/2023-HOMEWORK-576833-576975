package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoFine extends Comando {
	/**
	 * Comando "Fine".
	 * Si desidera smettere
	 */
	@Override
	public void esegui(Partita partita) {
		
		partita.getConsole().mostraMessaggio("Grazie di aver giocato!");  
		partita.setFinita();
	}


	@Override
	public String getNome() {
		return "fine";
	}

}
