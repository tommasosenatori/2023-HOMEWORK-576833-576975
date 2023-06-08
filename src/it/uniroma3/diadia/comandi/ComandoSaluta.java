package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoSaluta extends Comando {
	private static final String MESSAGGIO_CON_CHI =
			"Chi dovrei salutare?...";
	@Override
	public void esegui(Partita partita) {
		IO io = partita.getConsole();
		Stanza corrente = partita.getStanzaCorrente();
		AbstractPersonaggio personaggio = corrente.getPersonaggio();
		if(personaggio!=null) {
			io.mostraMessaggio(personaggio.saluta());
		}else {
			io.mostraMessaggio(MESSAGGIO_CON_CHI);
		}

	}

	@Override
	public String getNome() {
		return "saluta";
	}

}
