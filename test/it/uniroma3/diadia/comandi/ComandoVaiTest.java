package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;


class ComandoVaiTest {
	private Labirinto monolocale;
	private Labirinto bilocale;
	private Labirinto trilocale;


	private Comando vaiNord;
	private Comando vaiSud;
	
	private IO console;
	
	@BeforeEach
	void setUp(){

		console = new IOSimulator();
	
		vaiNord = new ComandoVai();
		vaiNord.setParametro("Nord");
		vaiSud = new ComandoVai();
		vaiSud.setParametro("Sud");
		monolocale = new LabirintoBuilder()
				.addStanzaIniziale("Monolocale")
				.addStanzaVincente("Monolocale")
				.getLabirinto();
		bilocale = new LabirintoBuilder()
				.addStanzaIniziale("Stanza Bilocale 1")
				.addStanzaVincente("Stanza Bilocale 2")
				.addAdiacenza("Stanza Bilocale 1", "Stanza Bilocale 2", "Nord")
				.addAdiacenza("Stanza Bilocale 2", "Stanza Bilocale 1", "Sud")
				.addStanzaVincente("Stanza Bilocale 2")
				.getLabirinto();
		trilocale = new LabirintoBuilder()
				.addStanzaIniziale("Ingresso")
				.addStanza("Corridoio")
				.addStanzaVincente("Uscita")
				.addAdiacenza("Ingresso", "Corridoio", "sud")
				.addAdiacenza("Corridoio","Ingresso","nord")
				.addAdiacenza("Corridoio", "Uscita", "sud").
				getLabirinto();

	}

	@Test
	void testEseguiEStanzaSenzaUscite() {
		Partita partitaTest = new Partita(monolocale, console);
		vaiNord.esegui(partitaTest);
		assertEquals(monolocale.getIngresso(), partitaTest.getStanzaCorrente());
	}
	@Test
	void testEseguiEDirezioneEsistente() {
		Partita partitaTest = new Partita(bilocale, console);
		vaiNord.esegui(partitaTest);
		assertEquals(bilocale.getUscita(), partitaTest.getStanzaCorrente());
	}
	@Test
	void testEseguiEDirezioneInesistente() {
		Partita partitaTest = new Partita(bilocale, console);
		vaiSud.esegui(partitaTest);
		assertEquals(bilocale.getIngresso(), partitaTest.getStanzaCorrente());
	}

	@Test
	void testEseguiEDirezioneBloccata() {
		bilocale = new LabirintoBuilder()
				.addStanzaBloccata("Stanza Bilocale 1", "Nord", "Chiave")
				.addStanzaIniziale("Stanza Bilocale 1")
				.addStanzaVincente("Stanza Bilocale 2")
				.addAdiacenza("Stanza Bilocale 1", "Stanza Bilocale 2", "Nord")
				.addAdiacenza("Stanza Bilocale 2", "Stanza Bilocale 1", "Sud")
				.addStanzaVincente("Stanza Bilocale 2")
				.getLabirinto();
		Partita partitaTest = new Partita(bilocale, console);
		vaiNord.esegui(partitaTest);
		assertEquals(bilocale.getIngresso(), partitaTest.getStanzaCorrente());
		assertEquals(20, partitaTest.getGiocatore().getCfu());
	}
	@Test
	void testEseguiEDirezioneSbloccata() {
		bilocale = new LabirintoBuilder()
				.addStanzaBloccata("Stanza Bilocale 1", "Nord", "Chiave")
				.addAttrezzo("Chiave", 1)
				.addStanzaIniziale("Stanza Bilocale 1")
				.addStanzaVincente("Stanza Bilocale 2")
				.addAdiacenza("Stanza Bilocale 1", "Stanza Bilocale 2", "Nord")
				.addAdiacenza("Stanza Bilocale 2", "Stanza Bilocale 1", "Sud")
				.addStanzaVincente("Stanza Bilocale 2")
				.getLabirinto();
		Partita partitaTest = new Partita(bilocale, console);
		vaiNord.esegui(partitaTest);
		assertEquals(bilocale.getUscita(), partitaTest.getStanzaCorrente());
		assertEquals(19, partitaTest.getGiocatore().getCfu());
	}


}
