package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class BorsaTest {
	private Borsa borsa10Attrezzi;
	private Borsa borsa0Attrezzi;
	private Borsa borsa3Attrezzi;
	@BeforeEach
	public void setUp() {
		borsa10Attrezzi = new Borsa();
		for(int i =0;i<10;i++) {
			borsa10Attrezzi.addAttrezzo(new Attrezzo("Attrezzo "+(i+1), 1));
		}
		borsa0Attrezzi = new Borsa();
		borsa3Attrezzi = new Borsa();
		for(int i =0;i<3;i++) {
			borsa3Attrezzi.addAttrezzo(new Attrezzo("Attrezzo "+(i+1), 1));
		}
	}
	//Test RemoveAttrezzo
	@Test
	void testRemoveAttrezzoBorsa10Attrezzi () {
		Attrezzo attrezzo1 = borsa10Attrezzi.getAttrezzo("Attrezzo 1");
		assertEquals(attrezzo1,borsa10Attrezzi.removeAttrezzo(attrezzo1.getNome()));
	}
	@Test
	void testRemoveUltimoAttrezzoBorsa10Attrezzi () {
		Attrezzo attrezzo10 = borsa10Attrezzi.getAttrezzo("Attrezzo 10");
		assertEquals(attrezzo10,borsa10Attrezzi.removeAttrezzo(attrezzo10.getNome()));
	}
	@Test
	void testRemoveAttrezzoInternoBorsa3Attrezzi () {
		Attrezzo attrezzo2 = borsa3Attrezzi.removeAttrezzo("Attrezzo 2");
		assertEquals("contenuto borsa (2kg/10kg): Attrezzo 1 (1kg) Attrezzo 3 (1kg) ",borsa3Attrezzi.toString());
	}
	@Test
	void testRemoveAttrezzoBorsa0Attrezzi () {
		assertNull(borsa0Attrezzi.removeAttrezzo("Attrezzo qualsiasi"));
	}
	//Test GetPeso
	@Test
	void testGetPesoBorsa10Attrezzi () {
		assertEquals(10,borsa10Attrezzi.getPeso());
	}
	@Test
	void testGetPesoBorsa0Attrezzi () {
		assertEquals(0,borsa0Attrezzi.getPeso());
	}
	//Test GetAttrezzo
	@Test
	void testGetAttrezzoBorsa0Attrezzi() {
		assertNull(borsa0Attrezzi.getAttrezzo("Attrezzo qualsiasi"));
	}
	@Test
	void testGetAttrezzoBorsa10AttrezziEAttrezzoNonPresente() {
		assertNull(borsa10Attrezzi.getAttrezzo("Attrezzo 11"));
	}
	@Test
	void testGetAttrezzoBorsa10AttrezziEAttrezzoPresente() {
		assertNotNull(borsa10Attrezzi.getAttrezzo("Attrezzo 1"));//Da rivedere
	}
	//TEST AddAttrezzo
	@Test
	void testAddAttrezzoBorsa10Attrezzi () {
		assertFalse(borsa10Attrezzi.addAttrezzo(new Attrezzo("Attrezzo 11", 1)));
	}
	@Test
	void testAddAttrezzoBorsa0Attrezzi () {
		assertTrue(borsa0Attrezzi.addAttrezzo(new Attrezzo("Attrezzo 1", 1)));
	}
}
