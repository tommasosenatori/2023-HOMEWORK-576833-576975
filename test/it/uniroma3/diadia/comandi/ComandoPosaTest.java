package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

class ComandoPosaTest {
	private Stanza stanzaVuota;
	private Stanza stanzaPiena;
	private Borsa borsaVuota;
	private Borsa borsa1Oggetto;
	private Attrezzo attrezzoInBorsa;
	private Partita partitaTest;
	
	@BeforeEach
	void setUp()  {
		IOConsole console = new IOConsole();
		partitaTest = new Partita(console);
		borsaVuota = new Borsa();
		borsa1Oggetto = new Borsa();
		attrezzoInBorsa = new Attrezzo("Attrezzo Borsa",1);
		borsa1Oggetto.addAttrezzo(attrezzoInBorsa);
		stanzaVuota = new Stanza("Stanza Vuota");
		stanzaPiena = new Stanza("Stanza Piena");
		
		for(int i = 0; i<10;i++) {
			stanzaPiena.addAttrezzo(new Attrezzo("Attrezzo Piena", 0));
		}
	}

	@Test
	void testEseguiStanzaVuotaEBorsaVuota() {
		partitaTest.getGiocatore().setBorsa(borsaVuota);
		partitaTest.setStanzaCorrente(stanzaVuota);
		ComandoPosa posaAttrezzo = new ComandoPosa();
		posaAttrezzo.setParametro("Attrezzo Borsa");
		posaAttrezzo.esegui(partitaTest);
		assertFalse(borsaVuota.hasAttrezzo("Attrezzo Borsa"));
		assertFalse(stanzaVuota.hasAttrezzo("Attrezzo Borsa"));
	}
	
	@Test
	void testEseguiStanzaVuotaEBorsa1Oggetto() {
		partitaTest.getGiocatore().setBorsa(borsa1Oggetto);
		partitaTest.setStanzaCorrente(stanzaVuota);
		ComandoPosa posaAttrezzo = new ComandoPosa();
		posaAttrezzo.setParametro("Attrezzo Borsa");
		posaAttrezzo.esegui(partitaTest);
		assertFalse(borsaVuota.hasAttrezzo("Attrezzo Borsa"));
		assertTrue(stanzaVuota.hasAttrezzo("Attrezzo Borsa"));
	}
	// stanza vuota e borsa vuota √
	// stanza vuota e borsa 1 oggetto √
	// stanza piena e borsa 1 oggetto
	// stanza piena e borsa 0 oggetto
	@Test
	void testEseguiStanzaPienaEBorsa1Oggetto() {
		partitaTest.getGiocatore().setBorsa(borsa1Oggetto);
		ComandoPosa posaAttrezzo = new ComandoPosa();
		partitaTest.setStanzaCorrente(stanzaPiena);
		posaAttrezzo.setParametro("Attrezzo Borsa");
		posaAttrezzo.esegui(partitaTest);
		assertTrue(borsa1Oggetto.hasAttrezzo("Attrezzo Borsa"));
		assertFalse(stanzaPiena.hasAttrezzo("Attrezzo Borsa"));
		
	
	}
	@Test
	void testEseguiStanzaPienaEBorsaVuota() {
		partitaTest.getGiocatore().setBorsa(borsaVuota);
		ComandoPosa posaAttrezzo = new ComandoPosa();
		partitaTest.setStanzaCorrente(stanzaPiena);
		posaAttrezzo.setParametro("Attrezzo Borsa");
		posaAttrezzo.esegui(partitaTest);
		assertFalse(borsaVuota.hasAttrezzo("Attrezzo Borsa"));
		assertFalse(stanzaPiena.hasAttrezzo("Attrezzo Borsa"));
		
	
	}
	
}
