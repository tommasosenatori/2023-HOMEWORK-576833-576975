package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoRegala extends Comando {
	private String oggetto;
	@Override
	public void esegui(Partita partita) {
		IO io = partita.getConsole();
		Stanza corrente = partita.getStanzaCorrente();
		AbstractPersonaggio personaggio = corrente.getPersonaggio();
		if(personaggio!=null) {
			Attrezzo dono = partita.getGiocatore().getBorsa().removeAttrezzo(this.oggetto);
			if(dono!=null) {
				String risposta = personaggio.riceviRegalo(dono, partita);
				io.mostraMessaggio(risposta);
			}else {
				io.mostraMessaggio("Non puoi donare qualcosa che non hai");
			}
			
		}else {
			io.mostraMessaggio("Non c'è nessuno in questa stanza");
		}

	}
	@Override
	public void setParametro(String parametro) {
		this.oggetto = parametro;
	}
	@Override
	public String getParametro() {
	
		return this.oggetto;
	}
	@Override
	public String getNome() {
		return "regala";
	}

}
