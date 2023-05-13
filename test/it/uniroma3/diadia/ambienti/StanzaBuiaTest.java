package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBuiaTest {
	StanzaBuia stanzaBuia;
	private Attrezzo lampada;
	@BeforeEach
	void setUp()  {
		stanzaBuia = new StanzaBuia("Stanza Buia","lampada");
		lampada = new Attrezzo("lampada", 0);
	}

	@Test
	void testGetDescrizioneStanzaBuiaSenzaLampada() {
		assertEquals("qui c'è buio pesto",stanzaBuia.getDescrizione());
	}
	@Test
	void testGetDescrizioneStanzaBuiaConLampada() {
		stanzaBuia.addAttrezzo(lampada);
		assertNotEquals("qui c'è buio pesto",stanzaBuia.getDescrizione());
	}

}
