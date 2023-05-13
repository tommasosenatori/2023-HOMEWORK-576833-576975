package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaMagicaTest {
	StanzaMagica stanzaSogliaMagicaNonSuperata;
	StanzaMagica stanzaSogliaMagicaSuperata;
	Attrezzo attrezzo ;
	@BeforeEach
	void setUp() {
		stanzaSogliaMagicaNonSuperata = new StanzaMagica("StanzaMagica");
		stanzaSogliaMagicaSuperata = new StanzaMagica("StanzaMagica",-1);
		attrezzo = new Attrezzo("attrezzo", 0);
	}

	@Test
	void testAddAttrezzoSogliaMagicaSuperata() {
		stanzaSogliaMagicaSuperata.addAttrezzo(attrezzo);
		assertTrue(stanzaSogliaMagicaSuperata.hasAttrezzo("ozzertta"));
	}
	@Test
	void testAddAttrezzoSogliaMagicaNonSuperata() {
		stanzaSogliaMagicaNonSuperata.addAttrezzo(attrezzo);
		assertTrue(stanzaSogliaMagicaNonSuperata.hasAttrezzo("attrezzo"));
	}

}
