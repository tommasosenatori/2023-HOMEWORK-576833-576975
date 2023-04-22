package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

class ComandoVaiTest {
	private Stanza monolocale;
	private Stanza stanzaBilocale1;
	private Stanza stanzaBilocale2;
	
	private Partita partitaTest;
	
	private Comando vaiNord;
	private Comando vaiSud;
	@BeforeEach
	void setUp(){
		IOConsole console = new IOConsole();
		partitaTest = new Partita(console);
		vaiNord = new ComandoVai();
		vaiNord.setParametro("Nord");
		vaiSud = new ComandoVai();
		vaiSud.setParametro("Sud");
		monolocale = new Stanza("Stanza Monolocale");
		stanzaBilocale1 = new Stanza("Stanza Bilocale 1");
		stanzaBilocale2 = new Stanza("Stanza Bilocale 2");
		stanzaBilocale1.impostaStanzaAdiacente("Nord", stanzaBilocale2);
		stanzaBilocale2.impostaStanzaAdiacente("Sud", stanzaBilocale1);

	}

	@Test
	void testEseguiEStanzaSenzaUscite() {
		partitaTest.setStanzaCorrente(monolocale);
		vaiNord.esegui(partitaTest);
		assertEquals(monolocale, partitaTest.getStanzaCorrente());
	}
	@Test
	void testEseguiEDirezioneEsistente() {
		partitaTest.setStanzaCorrente(stanzaBilocale1);
		vaiNord.esegui(partitaTest);
		assertEquals(stanzaBilocale2, partitaTest.getStanzaCorrente());
	}
	@Test
	void testEseguiEDirezioneInesistente() {
		partitaTest.setStanzaCorrente(stanzaBilocale1);
		vaiSud.esegui(partitaTest);
		assertEquals(stanzaBilocale1, partitaTest.getStanzaCorrente());
	}


}
