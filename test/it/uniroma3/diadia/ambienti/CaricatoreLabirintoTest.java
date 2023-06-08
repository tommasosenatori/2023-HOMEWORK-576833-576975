package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.StringReader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.FormatoFileNonValidoException;

class CaricatoreLabirintoTest {

	@BeforeEach
	void setUp() throws Exception {
		
	}

	@Test
	void testCaricaBilocale() {
		String lab=	"Stanze:N10,N11\n"+
				"Magica:\n"+
				"Buia:\n"+
				"Bloccata:\n"+
				"Inizio:N10\n"+
				"Vincente:N11\n"+
				"Mago:\n"+
				"Strega:\n"+
				"Cane:\n"+
				"Attrezzi:\n"+
				"Uscite:";
		try {
			CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(lab));
			Labirinto bilocale = caricatore.carica();
			assertEquals(new Stanza("N10"),bilocale.getIngresso());
			assertEquals(new Stanza("N11"),bilocale.getUscita());
		} catch (FileNotFoundException | FormatoFileNonValidoException e) {
			
			e.printStackTrace();
		}
				
	}
	@Test
	void testCaricaBilocale_Attrezzi() throws FormatoFileNonValidoException, FileNotFoundException {
		String lab=	"Stanze:N10,N11\n"+
				"Magica:\n"+
				"Buia:\n"+
				"Bloccata:\n"+
				"Inizio:N10\n"+
				"Vincente:N11\n"+
				"Mago:\n"+
				"Strega:\n"+
				"Cane:\n"+
				"Attrezzi:Attrezzo 10 N10\n"+
				"Uscite:";
		
			CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(lab));
			Labirinto bilocale = caricatore.carica();
			assertEquals(new Stanza("N10"),bilocale.getIngresso());
			assertTrue(bilocale.getIngresso().hasAttrezzo("Attrezzo"));
			assertEquals(new Stanza("N11"),bilocale.getUscita());
	
				
	}
	@Test
	void testCaricaBilocale_Mago() throws FormatoFileNonValidoException, FileNotFoundException {
		String lab=	"Stanze:N10,N11\n"+
				"Magica:\n"+
				"Buia:\n"+
				"Bloccata:\n"+
				"Inizio:N10\n"+
				"Vincente:N11\n"+
				"Mago:mago ciao attrezzo N10\n"+
				"Strega:\n"+
				"Cane:\n"+
				"Attrezzi:\n"+
				"Uscite:";
		
			CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(lab));
			Labirinto bilocale = caricatore.carica();
	
			assertEquals("mago",bilocale.getIngresso().getPersonaggio().getNome());
			assertEquals("Ciao, io sono mago.ciao",bilocale.getIngresso().getPersonaggio().saluta());			
	}
	@Test
	void testCaricaBilocale_Cane() throws FormatoFileNonValidoException, FileNotFoundException {
		String lab=	"Stanze:N10,N11\n"+
				"Magica:\n"+
				"Buia:\n"+
				"Bloccata:\n"+
				"Inizio:N10\n"+
				"Vincente:N11\n"+
				"Mago:\n"+
				"Strega:\n"+
				"Cane:cane wof osso legnetto N10\n"+
				"Attrezzi:\n"+
				"Uscite:";
		
			CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(lab));
			Labirinto bilocale = caricatore.carica();
	
			assertEquals("cane",bilocale.getIngresso().getPersonaggio().getNome());
			assertEquals("Ciao, io sono cane.wof",bilocale.getIngresso().getPersonaggio().saluta());			
	}
	@Test
	void testCaricaBilocale_Strega() throws FormatoFileNonValidoException, FileNotFoundException {
		String lab=	"Stanze:N10,N11\n"+
				"Magica:\n"+
				"Buia:\n"+
				"Bloccata:\n"+
				"Inizio:N10\n"+
				"Vincente:N11\n"+
				"Mago:\n"+
				"Strega:strega eheh N10\n"+
				"Cane:\n"+
				"Attrezzi:\n"+
				"Uscite:";
		
			CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(lab));
			Labirinto bilocale = caricatore.carica();
	
			assertEquals("strega",bilocale.getIngresso().getPersonaggio().getNome());
			assertEquals("Ciao, io sono strega.eheh",bilocale.getIngresso().getPersonaggio().saluta());			
	}

}
