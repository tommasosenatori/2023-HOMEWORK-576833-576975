package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;

class IOSimulatorTest {
	// "Contenuto borsa(4kg/10kg): Osso (4kg) "
	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	private IOSimulator simulatore;
	private List<String> listaComandiVuota;

	@BeforeEach
	void setUp() throws Exception {
		simulatore = new IOSimulator();
		listaComandiVuota = new LinkedList<>(); 

	}

	@Test
	void testPartitaSempliceComandoFine() {
		Labirinto labSemplice = Labirinto.newBuilder()
				.addStanzaIniziale("Ingresso")
				.addStanzaVincente("Uscita")
				.addAdiacenza("Ingresso", "Uscita", "sud").getLabirinto();
		DiaDia d = new DiaDia(labSemplice, simulatore);
		d.gioca();
		Map<Integer, String> messaggi = simulatore.getMessaggiMostrati();
		assertEquals(messaggi.get(0),MESSAGGIO_BENVENUTO);
		assertEquals(messaggi.get(1),"Grazie di aver giocato!");

	}
	
	@Test
	void testPartitaSempliceVinciVaiSud() {
		Labirinto labSemplice = Labirinto.newBuilder()
				.addStanzaIniziale("Ingresso")
				.addStanzaVincente("Uscita")
				.addAdiacenza("Ingresso", "Uscita", "sud").getLabirinto();
		listaComandiVuota.add("vai sud");
		simulatore.setComandiLetti(listaComandiVuota);
		DiaDia d = new DiaDia(labSemplice, simulatore);
		d.gioca();
		Map<Integer, String> messaggi = simulatore.getMessaggiMostrati();
		assertEquals(messaggi.get(0),MESSAGGIO_BENVENUTO);
		assertEquals(messaggi.get(messaggi.size()-1),"Hai vinto!");
	}
	
	@Test
	void testPartitaSempliceGuarda() {
		Labirinto labSemplice = Labirinto.newBuilder()
				.addStanzaIniziale("Ingresso")
				.addAttrezzo("Osso",4)
				.addStanzaVincente("Uscita")
				.addAdiacenza("Ingresso", "Uscita", "sud").getLabirinto();
		listaComandiVuota.add("guarda");
		
		simulatore.setComandiLetti(listaComandiVuota);
		DiaDia d = new DiaDia(labSemplice, simulatore);
		d.gioca();
		Map<Integer, String> messaggi = simulatore.getMessaggiMostrati();
		assertEquals(messaggi.get(0),MESSAGGIO_BENVENUTO);
		assertEquals(messaggi.get(1),"Cfu: 20\n"+ "Ingresso\nUscite:  SUD\nPersonaggi:\nAttrezzi nella stanza:Osso (4kg) "+"\nBorsa vuota");
		assertEquals(messaggi.get(messaggi.size()-1),"Grazie di aver giocato!");
	}
	@Test
	void testPartitaSempliceAiuto() {
		Labirinto labSemplice = Labirinto.newBuilder()
				.addStanzaIniziale("Ingresso")
				.addStanzaVincente("Uscita")
				.addAdiacenza("Ingresso", "Uscita", "sud").getLabirinto();
		
		listaComandiVuota.add("aiuto");
		
		simulatore.setComandiLetti(listaComandiVuota);
		DiaDia d = new DiaDia(labSemplice, simulatore);
		d.gioca();
		Map<Integer, String> messaggi = simulatore.getMessaggiMostrati();
		assertEquals(messaggi.get(0),MESSAGGIO_BENVENUTO);
		assertEquals(messaggi.get(1),"guarda vai prendi posa aiuto fine ");
		assertEquals(messaggi.get(messaggi.size()-1),"Grazie di aver giocato!");
	}
	@Test
	void testPartitaSempliceNonValido() {
		Labirinto labSemplice = Labirinto.newBuilder()
				.addStanzaIniziale("Ingresso")		
				.addStanzaVincente("Uscita")
				.addAdiacenza("Ingresso", "Uscita", "sud").getLabirinto();
		
		listaComandiVuota.add("boh");
		
		simulatore.setComandiLetti(listaComandiVuota);
		DiaDia d = new DiaDia(labSemplice, simulatore);
		d.gioca();
		Map<Integer, String> messaggi = simulatore.getMessaggiMostrati();
		assertEquals(messaggi.get(0),MESSAGGIO_BENVENUTO);
		assertEquals(messaggi.get(1),"Comando non valido");
	}
	
	@Test
	void testPartitaSemplicePrendi() {
		Labirinto labSemplice = Labirinto.newBuilder()
				.addStanzaIniziale("Ingresso")
				.addAttrezzo("Osso",4)
				.addStanzaVincente("Uscita")
				.addAdiacenza("Ingresso", "Uscita", "sud").getLabirinto();
		listaComandiVuota.add("prendi Osso");
		
		simulatore.setComandiLetti(listaComandiVuota);
		DiaDia d = new DiaDia(labSemplice, simulatore);
		d.gioca();
		Map<Integer, String> messaggi = simulatore.getMessaggiMostrati();
		assertEquals(messaggi.get(0),MESSAGGIO_BENVENUTO);
		assertEquals(messaggi.get(1),"Osso (4kg) preso");
		assertEquals(messaggi.get(2),"Contenuto borsa (4kg/10kg): Osso (4kg) ");
		assertEquals(messaggi.get(messaggi.size()-1),"Grazie di aver giocato!");
	}
	@Test
	void testPartitaSemplicePrendiPosa() {
		Labirinto labSemplice = Labirinto.newBuilder()
				.addStanzaIniziale("Ingresso")
				.addAttrezzo("Osso",4)
				.addStanzaVincente("Uscita")
				.addAdiacenza("Ingresso", "Uscita", "sud").getLabirinto();
		listaComandiVuota.add("prendi Osso");
		listaComandiVuota.add("posa Osso");
		
		simulatore.setComandiLetti(listaComandiVuota);
		DiaDia d = new DiaDia(labSemplice, simulatore);
		d.gioca();
		Map<Integer, String> messaggi = simulatore.getMessaggiMostrati();
		assertEquals(messaggi.get(0),MESSAGGIO_BENVENUTO);
		assertEquals(messaggi.get(1),"Osso (4kg) preso");
		assertEquals(messaggi.get(2),"Contenuto borsa (4kg/10kg): Osso (4kg) ");
		assertEquals(messaggi.get(3),"Osso (4kg) posato");
		assertEquals(messaggi.get(messaggi.size()-1),"Grazie di aver giocato!");
	}
	@Test
	void testPartitaSempliceGuardaStanzaBuiaLanternaNonPresente() {
		Labirinto labSemplice = Labirinto.newBuilder()
				.addStanzaBuia("Ingresso", "Lanterna")
				.addStanzaIniziale("Ingresso")
				.addStanzaVincente("Uscita")
				.addAdiacenza("Ingresso", "Uscita", "sud").getLabirinto();
		
		listaComandiVuota.add("guarda");
		simulatore.setComandiLetti(listaComandiVuota);
		DiaDia d = new DiaDia(labSemplice, simulatore);
		d.gioca();
		Map<Integer, String> messaggi = simulatore.getMessaggiMostrati();
		assertEquals(messaggi.get(0),MESSAGGIO_BENVENUTO);
		assertEquals(messaggi.get(1), "Cfu: 20\nqui c'è buio pesto\nBorsa vuota");
		assertEquals(messaggi.get(messaggi.size()-1),"Grazie di aver giocato!");
	}
	@Test
	void testPartitaSempliceGuardaStanzaBuiaLanternaPresente() {
		Labirinto labSemplice = Labirinto.newBuilder()
				.addStanzaBuia("Ingresso", "Lanterna")
				.addAttrezzo("Lanterna", 1)
				.addStanzaIniziale("Ingresso")
				.addStanzaVincente("Uscita")
				.addAdiacenza("Ingresso", "Uscita", "sud").getLabirinto();
		
		listaComandiVuota.add("guarda");
		simulatore.setComandiLetti(listaComandiVuota);
		DiaDia d = new DiaDia(labSemplice, simulatore);
		d.gioca();
		Map<Integer, String> messaggi = simulatore.getMessaggiMostrati();
		assertEquals(messaggi.get(0),MESSAGGIO_BENVENUTO);
		assertEquals(messaggi.get(1), "Cfu: 20\n"+ "Ingresso\nUscite:  SUD\nPersonaggi:\nAttrezzi nella stanza:Lanterna (1kg) "+"\nBorsa vuota");
		assertEquals(messaggi.get(messaggi.size()-1),"Grazie di aver giocato!");
	}
	
	@Test
	void testPartitaSempliceGuardaStanzaBloccata() {
		Labirinto labSemplice = Labirinto.newBuilder()
				.addStanzaBloccata("Ingresso", "sud","Chiave")
				.addStanzaIniziale("Ingresso")
				.addStanzaVincente("Uscita")
				.addAdiacenza("Ingresso", "Uscita", "sud").getLabirinto();
		
		listaComandiVuota.add("guarda");
		simulatore.setComandiLetti(listaComandiVuota);
		DiaDia d = new DiaDia(labSemplice, simulatore);
		d.gioca();
		Map<Integer, String> messaggi = simulatore.getMessaggiMostrati();
		assertEquals(messaggi.get(0),MESSAGGIO_BENVENUTO);
		assertEquals(messaggi.get(1), "Cfu: 20\n"+ "Ingresso\nUscite:  SUD\nPersonaggi:\nAttrezzi nella stanza:"+"\nDirezione bloccata:SUD. Necessario:Chiave\n"+"\nBorsa vuota");
		assertEquals(messaggi.get(messaggi.size()-1),"Grazie di aver giocato!");
	}
	
	@Test
	void testPartitaTrilocaleEsaurimentoCfu() {
		Labirinto labTrilocale = Labirinto.newBuilder()
				.addStanzaIniziale("Ingresso")
				.addStanza("Corridoio")
				.addStanzaVincente("Uscita")
				.addAdiacenza("Ingresso", "Corridoio", "sud")
				.addAdiacenza("Corridoio","Ingresso","nord")
				.addAdiacenza("Corridoio", "Uscita", "sud").
				getLabirinto();
		for(int i = 0;i<10;i++) {
			//consumo tutti i cfu
			listaComandiVuota.add("vai sud");
			listaComandiVuota.add("vai nord");
		}
		simulatore.setComandiLetti(listaComandiVuota);
		DiaDia d = new DiaDia(labTrilocale, simulatore);
		d.gioca();
		Map<Integer, String> messaggi = simulatore.getMessaggiMostrati();
		assertEquals(messaggi.get(0),MESSAGGIO_BENVENUTO);
		assertEquals(messaggi.get(messaggi.size()-1),"Hai esaurito i CFU...");
	}

}
