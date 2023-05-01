package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest {
    private Stanza stanzaTutteDirezioni; // true
	private Stanza stanza0Direzioni; // false
	private Stanza stanzaTuttiAttrezzi;
	private Stanza stanza0Attrezzi;
	
    @BeforeEach
	public void setUp() {
         stanzaTutteDirezioni = new Stanza("Tutte direzioni");
         stanza0Direzioni = new Stanza("0 Direzioni");
         stanzaTuttiAttrezzi = new Stanza("Tutti attrezzi");
         stanza0Attrezzi = new Stanza("0 Attrezzi");
         String[] direzioni = {"nord","sud","est","ovest"};
        //StanzaTuttiAttrezzi
        for(int i=0;i<11;i++) {
        	stanzaTuttiAttrezzi.addAttrezzo(new Attrezzo("Attrezzo "+i, i));
        }
        for(int i =0;i<direzioni.length;i++) {
        	stanzaTutteDirezioni.impostaStanzaAdiacente(direzioni[i], new Stanza("Stanza "+direzioni[i]));
        }
        
    }
	
	@Test
	void testImpostaStanzaAdiacenteStanza0DirezioniAggiungo() {
		Stanza nuovaStanzaAdd = new Stanza("Nuovo");
		stanza0Direzioni.impostaStanzaAdiacente("nord", nuovaStanzaAdd);
		assertEquals(nuovaStanzaAdd,stanza0Direzioni.getStanzaAdiacente("nord"));
	}
	@Test
	void testImpostaStanzaAdiacenteStanzaTutteDirezioniSovrascrivo() {
		Stanza nuovaStanzaAdd = new Stanza("Nuovo");
		stanzaTutteDirezioni.impostaStanzaAdiacente("nord",nuovaStanzaAdd);
		assertEquals(nuovaStanzaAdd,stanzaTutteDirezioni.getStanzaAdiacente("nord"));
	}
	@Test
	void testImpostaStanzaAdiacenteStanzaTutteDirezioniNuovaDirezione() {
		Stanza nuovaStanzaAdd = new Stanza("NuovuovaStanzaAdd = no");
		stanzaTutteDirezioni.impostaStanzaAdiacente("nord ovest",nuovaStanzaAdd);
		assertNull(stanzaTutteDirezioni.getStanzaAdiacente("nord ovest"));
	}
	
	
	//Test GetStanzaAdiacente
	@Test
	void testGetStanzaAdiacenteStanza0Direzioni() {
		assertNull(stanza0Direzioni.getStanzaAdiacente("nord"));
	}
	@Test
	void testGetStanzaAdiacenteStanzaTutteDirezioni() {
		assertNotNull(stanzaTutteDirezioni.getStanzaAdiacente("nord"));
	}
	@Test
	void testGetStanzaAdiacenteStanzaTutteDirezioniEDirezioneNonEsistente() {
		assertNull(stanzaTutteDirezioni.getStanzaAdiacente("nord ovest"));
	}
	
	
	//Test AddAttrezzo
	@Test
	void testAddAttrezzoStanzaTuttiAttrezzi() {
		assertFalse(stanzaTuttiAttrezzi.addAttrezzo(new Attrezzo("10", 10)));
	}
	void testAddAttrezzoStanza0Attrezzi() {
		assertTrue(stanza0Attrezzi.addAttrezzo(new Attrezzo("0", 0)));
	}
	
	
	//Test HasAttrezzo
	@Test
	void testHasAttrezzoStanzaTuttiAttrezziEAttrezzoPresente() {
		assertTrue(stanzaTuttiAttrezzi.hasAttrezzo("Attrezzo 1"));
	}
	@Test
	void testHasAttrezzoStanzaTuttiAttrezziEAttrezzoNonPresente() {
		assertFalse(stanzaTuttiAttrezzi.hasAttrezzo("Attrezzo -1"));
	}
	@Test
	void testHasAttrezzoStanza0Attrezzi() {
		assertFalse(stanza0Attrezzi.hasAttrezzo("Attrezzo 10"));
	}
	
	
	//Test Get Attrezzo
	@Test
	void testGetAttrezzoStanza0Attrezzi() {
		assertNull(stanza0Attrezzi.getAttrezzo("Attrezzo"));
	}
	@Test
	void testGetAttrezzoStanzaTuttiAttrezziEAttrezzoNonPresente() {
		assertNull(stanzaTuttiAttrezzi.getAttrezzo("Attrezzo -1"));
	}
	@Test
	void testGetAttrezzoStanzaTuttiAttrezziEAttrezzoPresente() {
		assertEquals(stanzaTuttiAttrezzi.getAttrezzi().get(1),stanzaTuttiAttrezzi.getAttrezzo("Attrezzo 1"));
	}
	//Test RemoveAttrezzo
	@Test
	void testRemoveAttrezzoStanza0Attrezzi() {
		assertFalse(stanza0Attrezzi.removeAttrezzo(new Attrezzo("Attrezzo qualsiasi", 1)));
	}
	@Test
	void testRemoveAttrezzoStanzaTuttiAttrezziEAttrezzoNonPresente() {
		assertFalse(stanzaTuttiAttrezzi.removeAttrezzo(new Attrezzo ("Attrezzo qualsiasi", 1)));
	}
	@Test
	void testRemoveAttrezzoStanzaTuttiAttrezziPrimoAttrezzo() {
		assertTrue(stanzaTuttiAttrezzi.removeAttrezzo(new Attrezzo ("Attrezzo 0", 1)));
	}
	@Test
	void testRemoveAttrezzoStanzaTuttiAttrezziUltimoAttrezzo() {
		assertTrue(stanzaTuttiAttrezzi.removeAttrezzo(new Attrezzo ("Attrezzo 9", 1)));
	}
	@Test
	void testRemoveAttrezzoStanzaTuttiAttrezziAttrezzoInMezzo() {
		assertTrue(stanzaTuttiAttrezzi.removeAttrezzo(new Attrezzo ("Attrezzo 4", 1)));
	}
	
}
