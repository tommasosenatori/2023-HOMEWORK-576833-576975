package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;

class ComandoTest {
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testGetParametro() {
		assertNull(new  Comando() {

			@Override
			public void esegui(Partita partita) {				
			}
			@Override
			public String getNome() {	
				return "fake";
			}
			
		}.getParametro());
	}

}
