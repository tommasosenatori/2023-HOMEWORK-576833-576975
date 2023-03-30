package it.uniroma3.diadia;


import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai","prendi","posa","aiuto", "fine"};

	private Partita partita;
	private IOConsole io;

	public DiaDia(IOConsole console) {
		this.io = console;
		this.partita = new Partita();
	}

	public void gioca() {
		String istruzione; 
		io.mostraMessaggio(MESSAGGIO_BENVENUTO);		
		do		
			istruzione = io.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);
		if(comandoDaEseguire.sconosciuto()) {
			io.mostraMessaggio("Inserisci un comando");
			return false;
		}
		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if(comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		else if(comandoDaEseguire.getNome().equals("posa"))
			this.posa(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else
			io.mostraMessaggio("Comando sconosciuto");
		if (this.partita.vinta()) {
			io.mostraMessaggio("Hai vinto!");
			return true;
		} else
			return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			io.mostraMessaggio(elencoComandi[i]+" ");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null) {
			io.mostraMessaggio("Dove vuoi andare ?");
			return;
		}
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			io.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(cfu--);
		}
		io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}
	/**
	 * Cerca di prendere un oggetto dalla stanza corrente e metterlo nella borsa.
	 * Se l'attrezzo è presente nella stanza corrente e c'è spazio nella borsa lo rimuove dalla stanza, lo aggiunge nella borsa e stampa il contenuto della borsa.
	 * Altrimenti stampa un messaggio d'errore.
	 * @param nomeAttrezzo, nome dell'attrezzo che si vuole prendere
	 */
	private void prendi(String nomeAttrezzo) {
		if(nomeAttrezzo==null) {
			io.mostraMessaggio("Che attrezzo vuoi prendere?");
			return;
		}
		Attrezzo attrezzoDaPrendere = this.partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
		if(attrezzoDaPrendere==null) {
			io.mostraMessaggio("L'attrezzo che vuoi prendere non esiste");
		}else {
			if(this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzoDaPrendere)) {
				this.partita.getStanzaCorrente().removeAttrezzo(attrezzoDaPrendere);
				io.mostraMessaggio(attrezzoDaPrendere+" preso");
				io.mostraMessaggio(this.partita.getGiocatore().getBorsa().toString());
			}else {
				io.mostraMessaggio("Non c'è spazio per prendere l'attrezzo");
			}
		}
		
	}
	/**
	 * Cerca di posare un attrezzo della borsa nella stanza corrente. Se l'attrezzo è presente nella borsa
	 * e c'è spazio nella stanza corrente lo rimuove dalla borsa, lo aggiunge nella stanza corrente 
	 * e stampa il contenuto della borsa. Altrimenti stampa un messaggio d'errore
	 * @param nomeAttrezzo, nome dell'attrezzo da posare
	 */
	private void posa(String nomeAttrezzo) {
		if(nomeAttrezzo==null) {
			io.mostraMessaggio("Che attrezzo vuoi posare?");
			return;
		}
		Attrezzo attrezzoDaPosare = this.partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
		if(attrezzoDaPosare==null) {
			io.mostraMessaggio("L'attrezzo che vuoi posare non esiste");
		}else {
			if(this.partita.getStanzaCorrente().addAttrezzo(attrezzoDaPosare)) {
				this.partita.getGiocatore().getBorsa().removeAttrezzo(attrezzoDaPosare.getNome());
				io.mostraMessaggio(attrezzoDaPosare+" posato");
			}else {
				io.mostraMessaggio("Non c'è spazio per posare l'attrezzo");
			}
		}
	}
	
	

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		io.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		IOConsole console = new IOConsole();
		DiaDia gioco = new DiaDia(console);
		gioco.gioca();
	}
}