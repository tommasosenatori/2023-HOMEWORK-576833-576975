package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PartitaTest {
	private Partita partitaNuova;
	private Partita partita0CFU;
	private Partita partitaVinta;
	private Partita partitaFinita;

	@BeforeEach
	public void setUp() {
		IO console = new IOSimulator();
		partitaNuova = new Partita(console);
		partita0CFU = new Partita(console);
		partitaVinta = new Partita(console);
		partitaFinita = new Partita(console);
		//Setup Partita0CFU
		partita0CFU.getGiocatore().setCfu(0);
		//Setup Partita Vinta
		partitaVinta.setStanzaCorrente(partitaVinta.getStanzaVincente());
		//Setup PartitaFinita
		partitaFinita.setFinita();
	}
	@Test
	void testVintaPartitaNuova() {
		assertFalse(partitaNuova.vinta());
	}
	@Test
	void testVintaPartitaVinta() {
		assertTrue(partitaVinta.vinta());
	}
	@Test
	void testVintaPartita0CFU() {
		assertFalse(partita0CFU.vinta());
	}

	@Test
	void testIsFinitaPartitaNuova() {
		assertFalse(partitaNuova.isFinita());
	}
	@Test
	void testIsFinitaPartita0CFU() {
		assertTrue(partita0CFU.isFinita());
	}
	@Test
	void testIsFinitaPartitaFinita() {
		assertTrue(partitaFinita.isFinita());
	}
	@Test
	void testIsFinitaPartitaVinta() {
		assertTrue(partitaVinta.isFinita());
	}


}
