package it.uniroma3.diadia;


import java.io.FileNotFoundException;
import java.util.Scanner;

import it.uniroma3.diadia.ambienti.CaricatoreLabirinto;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaComandiRiflessiva;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;

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



	private Partita partita;
	private IO io;

	public DiaDia(IO console) {
		this.io = console;
		this.partita = new Partita(this.io);
	}
	public DiaDia(Labirinto labirinto, IO console) {
		this.io = console;
		this.partita = new Partita(labirinto,this.io);
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
		Comando comandoDaEseguire;
		FabbricaDiComandi factory = new FabbricaComandiRiflessiva();
		comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita);
		if (this.partita.vinta())
			this.io.mostraMessaggio("Hai vinto!");
			
		if (!this.partita.giocatoreIsVivo()) 
			this.io.mostraMessaggio("Hai esaurito i CFU...");
			
		return this.partita.isFinita();
	}   



	public static void main(String[] argc) {
		/* N.B. unica istanza di IOConsole
		di cui sia ammessa la creazione */
		Labirinto lab  = new Labirinto();
		try {
			CaricatoreLabirinto labC = new CaricatoreLabirinto("resources/labirinto/Lab.txt");
			lab = labC.carica();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (FormatoFileNonValidoException e) {
			// TODO Auto-generated catch block
			System.out.println("Scritto male"+e.getMessage());
		}
		new CaricatoreCostanti().caricaCostanti();
		try(Scanner scanner = new Scanner(System.in);){
			IO io = new IOConsole(scanner);
			DiaDia gioco = new DiaDia(lab, io);
			gioco.gioca();
		}

		
		
	}
}