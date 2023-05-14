package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoFine implements Comando {
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
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getParametro() {
		return null;
	}

	@Override
	public String getNome() {
		return "fine";
	}

}
