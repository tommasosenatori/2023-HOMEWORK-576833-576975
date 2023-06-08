package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Direzioni;
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
		stanzaBloccata = new StanzaBloccata("Stanza Bloccata", Direzioni.NORD, "chiave");
		stanzaNormale = new Stanza("Stanza Normale");
		stanzaBloccata.impostaStanzaAdiacente(Direzioni.NORD,stanzaNormale);
		stanzaBloccata.impostaStanzaAdiacente(Direzioni.EST, stanzaNormaleDirezioneSbloccata);
		stanzaNormale.impostaStanzaAdiacente(Direzioni.SUD,stanzaBloccata);
		
	}

	@Test
	void testVaiInStanzaBloccataEChiaveNonPresente() {
		assertEquals(stanzaBloccata, stanzaBloccata.getStanzaAdiacente("NORD"));
		
	}
	@Test
	void testVaiInStanzaBloccataEChiavePresente() {
		stanzaBloccata.addAttrezzo(chiave);
		assertEquals(stanzaNormale, stanzaBloccata.getStanzaAdiacente("NORD"));
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
