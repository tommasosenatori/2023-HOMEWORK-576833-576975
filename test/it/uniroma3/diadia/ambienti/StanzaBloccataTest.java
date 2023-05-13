package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import it.uniroma3.diadia.attrezzi.*;


class StanzaBloccataTest {
	private StanzaBloccata stanzaBloccata;
	private Stanza stanzaNormale;
	private Stanza stanzaNormaleDirezioneSbloccata;
	private  Attrezzo chiave;
	

	@BeforeEach
	void setUp()  {
	;
		chiave = new Attrezzo("chiave",1);
		stanzaNormaleDirezioneSbloccata= new Stanza("Stanza est");
		stanzaBloccata = new StanzaBloccata("Stanza Bloccata", "nord", "chiave");
		stanzaNormale = new Stanza("Stanza Normale");
		stanzaBloccata.impostaStanzaAdiacente("nord",stanzaNormale);
		stanzaBloccata.impostaStanzaAdiacente("est", stanzaNormaleDirezioneSbloccata);
		stanzaNormale.impostaStanzaAdiacente("sud",stanzaBloccata);
		
	}

	@Test
	void testVaiInStanzaBloccataEChiaveNonPresente() {
		assertEquals(stanzaBloccata, stanzaBloccata.getStanzaAdiacente("nord"));
		
	}
	@Test
	void testVaiInStanzaBloccataEChiavePresente() {
		stanzaBloccata.addAttrezzo(chiave);
		assertEquals(stanzaNormale, stanzaBloccata.getStanzaAdiacente("nord"));
	}
	@Test
	void testVaiInStanzaNonBloccataEChiaveNonPresente() {
		assertEquals(stanzaNormaleDirezioneSbloccata, stanzaBloccata.getStanzaAdiacente("est"));
	}
	@Test
	void testVaiInStanzaNonBloccataEChiavePresente() {
		stanzaBloccata.addAttrezzo(chiave);
		assertEquals(stanzaNormaleDirezioneSbloccata, stanzaBloccata.getStanzaAdiacente("est"));
	}
	

}
