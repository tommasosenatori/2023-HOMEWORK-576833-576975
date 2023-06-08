package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;


class ComandoPrendiTest {
	private Stanza stanzaVuota;
	private Borsa borsaVuota;
	private Stanza stanzaConOggettoSottoLimitePeso;
	private Stanza stanzaConOggettoSopraLimitePeso;
	private Attrezzo attrezzoSopraPesoLimite;
	private Attrezzo attrezzoSottoPesoLimite;
	private Partita partitaTest;
	
	
	@BeforeEach
	void setUp()  {
		IOConsole console = new IOConsole(new Scanner(System.in));
		partitaTest = new Partita(console);
		borsaVuota = new Borsa();
	 
		stanzaVuota = new Stanza("Stanza Vuota");
		stanzaConOggettoSottoLimitePeso = new Stanza("Stanza con oggetto peso<Max");
		stanzaConOggettoSopraLimitePeso = new Stanza("Stanza con oggetto peso>Max");
		attrezzoSopraPesoLimite = new Attrezzo("Attrezzo pesante", borsaVuota.getPesoMax()+1);
		attrezzoSottoPesoLimite = new Attrezzo("Attrezzo leggero", 1);
		
		stanzaConOggettoSottoLimitePeso.addAttrezzo(attrezzoSottoPesoLimite);
		stanzaConOggettoSopraLimitePeso.addAttrezzo(attrezzoSopraPesoLimite);
		
		
	}	

	@Test
	void testEseguiBorsaVuotaEOggettoConPesoMinoreMassimo() {
		partitaTest.getGiocatore().setBorsa(borsaVuota);
		partitaTest.setStanzaCorrente(stanzaConOggettoSottoLimitePeso);
		ComandoPrendi prendiAttrezzoLeggero = new ComandoPrendi();
		prendiAttrezzoLeggero.setParametro("Attrezzo leggero");
		prendiAttrezzoLeggero.esegui(partitaTest);
		assertTrue(borsaVuota.hasAttrezzo("Attrezzo leggero"));
		assertFalse(stanzaConOggettoSottoLimitePeso.hasAttrezzo("Attrezzo leggero"));
	}
	
	@Test
	void testEseguiBorsaVuotaEOggettoConPesoMaggioreMassimo() {
		partitaTest.getGiocatore().setBorsa(borsaVuota);
		partitaTest.setStanzaCorrente(stanzaConOggettoSopraLimitePeso);
		ComandoPrendi prendiAttrezzoPesante = new ComandoPrendi();
		prendiAttrezzoPesante.setParametro("Attrezzo pesante");
		prendiAttrezzoPesante.esegui(partitaTest);
		assertFalse(borsaVuota.hasAttrezzo("Attrezzo pesante"));
		assertTrue(stanzaConOggettoSopraLimitePeso.hasAttrezzo("Attrezzo pesante"));
	}
	
	@Test
	void testEseguiSenzaParametro() {
		partitaTest.getGiocatore().setBorsa(borsaVuota);
		partitaTest.setStanzaCorrente(stanzaConOggettoSopraLimitePeso);
		ComandoPrendi prendiAttrezzoPesante = new ComandoPrendi();
		prendiAttrezzoPesante.esegui(partitaTest);
	
	}
	@Test
	void testEseguiEOggettoNonPresente() {
		partitaTest.getGiocatore().setBorsa(borsaVuota);
		partitaTest.setStanzaCorrente(stanzaVuota);
		ComandoPrendi prendiAttrezzoInesistente = new ComandoPrendi();
		prendiAttrezzoInesistente.setParametro("Attrezzo");
		prendiAttrezzoInesistente.esegui(partitaTest);
		assertEquals("Borsa vuota", borsaVuota.toString());
		
	}

	
	
	//attrezzo nella stanza e la borsa è piena
		//massimo numero di attrezzi√
		//massimo peso √
	//attrezzo nella stanza e la borsa è vuota √
	//attrezzo non è nella stanza √
	
}
