package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

class FabbricaDiComandiFisarmonicaTest {
	private Comando comando;
	private FabbricaDiComandi fabbrica;
	@BeforeEach
	void setUp()  {
		fabbrica = new FabbricaDiComandiFisarmonica();
	}

	@Test
	void testCostruisciComandoVai() {
		comando = fabbrica.costruisciComando("vai nord");
		assertEquals("vai", comando.getNome());
		assertEquals("nord", comando.getParametro());
	}
	@Test
	void testCostruisciComandoVaiSenzaParametro() {
		comando = fabbrica.costruisciComando("vai");
		assertEquals("vai", comando.getNome());
		assertNull(comando.getParametro());
	}
	@Test
	void testCostruisciComandoPosa() {
		comando = fabbrica.costruisciComando("posa oggetto");
		assertEquals("posa", comando.getNome());
		assertEquals("oggetto", comando.getParametro());
	}
	@Test
	void testCostruisciComandoPosaSenzaParametro() {
		comando = fabbrica.costruisciComando("posa");
		assertEquals("posa", comando.getNome());
		assertNull(comando.getParametro());
	}
	@Test
	void testCostruisciComandoPrendi() {
		comando = fabbrica.costruisciComando("prendi oggetto");
		assertEquals("prendi", comando.getNome());
		assertEquals("oggetto", comando.getParametro());
	}
	@Test
	void testCostruisciComandoPrendiSenzaParametro() {
		comando = fabbrica.costruisciComando("prendi");
		assertEquals("prendi", comando.getNome());
		assertNull(comando.getParametro());
	}
	@Test
	void testCostruisciComandoNonValido() {
		comando = fabbrica.costruisciComando("non valido");
		assertEquals("non valido", comando.getNome());
		assertNull(comando.getParametro());
	}
	@Test
	void testCostruisciGuarda() {
		comando = fabbrica.costruisciComando("guarda");
		assertEquals("guarda", comando.getNome());
		assertNull(comando.getParametro());
	}
	@Test
	void testCostruisciFine() {
		comando = fabbrica.costruisciComando("fine");
		assertEquals("fine", comando.getNome());
		assertNull(comando.getParametro());
	}
	@Test
	void testCostruisciAiuto() {
		comando = fabbrica.costruisciComando("aiuto");
		assertEquals("aiuto", comando.getNome());
		assertNull(comando.getParametro());
	}
}
