package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa extends Comando {
	private String nomeAttrezzo;
	/**
	 * Cerca di posare un attrezzo della borsa nella stanza corrente. Se l'attrezzo è presente nella borsa
	 * e c'è spazio nella stanza corrente lo rimuove dalla borsa, lo aggiunge nella stanza corrente 
	 * e stampa il contenuto della borsa. Altrimenti stampa un messaggio d'errore
	 * @param nomeAttrezzo, nome dell'attrezzo da posare
	 */
	@Override
	public void esegui(Partita partita) {

		if(nomeAttrezzo==null) {
			partita.getConsole().mostraMessaggio("Che attrezzo vuoi posare?");
			return;
		}
		Attrezzo attrezzoDaPosare = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
		if(attrezzoDaPosare==null) {
			partita.getConsole().mostraMessaggio("L'attrezzo che vuoi posare non esiste");
		}else {
			if(partita.getStanzaCorrente().addAttrezzo(attrezzoDaPosare)) {
				partita.getGiocatore().getBorsa().removeAttrezzo(attrezzoDaPosare.getNome());
				partita.getConsole().mostraMessaggio(attrezzoDaPosare+" posato");
			}else {
				partita.getConsole().mostraMessaggio("Non c'è spazio per posare l'attrezzo");
			}
		}
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;

	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}

	@Override
	public String getNome() {
		return "posa";
	}

}
