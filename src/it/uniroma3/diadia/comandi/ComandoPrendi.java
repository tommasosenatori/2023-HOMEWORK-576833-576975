package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi extends Comando {
	private String nomeAttrezzo;
	/**
	 * Cerca di prendere un oggetto dalla stanza corrente e metterlo nella borsa.
	 * Se l'attrezzo è presente nella stanza corrente e c'è spazio nella borsa lo rimuove dalla stanza, lo aggiunge nella borsa e stampa il contenuto della borsa.
	 * Altrimenti stampa un messaggio d'errore.
	 * @param nomeAttrezzo, nome dell'attrezzo che si vuole prendere
	 */
	@Override
	public void esegui(Partita partita) {

		if(nomeAttrezzo==null) {
			partita.getConsole().mostraMessaggio("Che attrezzo vuoi prendere?");
			return;
		}
		Attrezzo attrezzoDaPrendere = partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
		if(attrezzoDaPrendere==null) {
			partita.getConsole().mostraMessaggio("L'attrezzo che vuoi prendere non esiste");
		}else {
			if(partita.getGiocatore().getBorsa().addAttrezzo(attrezzoDaPrendere)) {
				partita.getStanzaCorrente().removeAttrezzo(attrezzoDaPrendere);
				partita.getConsole().mostraMessaggio(attrezzoDaPrendere+" preso");
				partita.getConsole().mostraMessaggio(partita.getGiocatore().getBorsa().toString());
			}else {
				partita.getConsole().mostraMessaggio("Non c'è spazio per prendere l'attrezzo");
			}			
		}
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo=parametro;

	}

	@Override
	public String getParametro() {
		
		return this.nomeAttrezzo;
	}

	@Override
	public String getNome() {
		return "prendi";
	}

}
