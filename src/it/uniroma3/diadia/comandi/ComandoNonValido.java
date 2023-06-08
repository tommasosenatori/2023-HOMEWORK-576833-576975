package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends Comando {

	@Override
	public void esegui(Partita partita) {
		partita.getConsole().mostraMessaggio("Comando non valido");

	}
	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return "non valido";
	}

}
