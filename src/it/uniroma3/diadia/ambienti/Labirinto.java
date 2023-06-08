package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;
/**
 * Classe Labirinto
 * Rappresenta un labirinto all'interno del gioco
 * Ne gestice la creazione e ne contiene la topologia
 * @author 576833 576975
 * @see Stanza
 */
public class Labirinto {
	private Stanza ingresso;
	private Stanza uscita;

	public Stanza getIngresso() {
		return ingresso;
	}
	public void setIngresso(Stanza ingresso) {
		this.ingresso = ingresso;
	}
	public Stanza getUscita() {
		return uscita;
	}
	public void setUscita(Stanza uscita) {
		this.uscita = uscita;
	}
	/**
     * Crea tutte le stanze e le porte di collegamento e imposta ingresso e uscita del labirinto
     */
	public static LabirintoBuilder newBuilder(){
		return new LabirintoBuilder();
	}
	public Labirinto() {
		/* crea gli attrezzi */
		Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);

		/* crea stanze del labirinto */
		Stanza atrio = new Stanza("Atrio");
		Stanza aulaN11 = new Stanza("Aula N11");
		Stanza aulaN10 = new Stanza("Aula N10");
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");

		/* collega le stanze */
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		biblioteca.impostaStanzaAdiacente("sud", atrio);

		/* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);

		// il gioco comincia nell'atrio
		ingresso = atrio;  
		uscita = biblioteca;

	}
	
	public static class LabirintoBuilder {
		private Map<String,Stanza> mappaStanze;
		private String ultimaStanzaAggiunta;
		private Stanza stanzaIniziale;
		private Stanza stanzaFinale;
		private LabirintoBuilder(){
			this.mappaStanze=new HashMap<>();
		}
		public LabirintoBuilder addAdiacenza(String stanza1,String stanza2,String direzione){
			this.mappaStanze.get(stanza1).impostaStanzaAdiacente(direzione, this.mappaStanze.get(stanza2));
			return this;
		}
		public LabirintoBuilder addStanza(String stanza){
			if(!this.mappaStanze.containsKey(stanza)) {
				this.mappaStanze.put(stanza, new Stanza(stanza));
				this.ultimaStanzaAggiunta=stanza;
			}
			return this;
		}
		public LabirintoBuilder addStanzaVincente(String stanza){
			
			this.addStanza(stanza);
			this.stanzaFinale = this.mappaStanze.get(stanza);
			return this;

		}
		public LabirintoBuilder addStanzaIniziale(String stanza){

			this.addStanza(stanza);
			this.stanzaIniziale = this.mappaStanze.get(stanza);
			return this;

		}
		public Labirinto getLabirinto() {
			Labirinto nuovoLabirinto = new Labirinto();
			nuovoLabirinto.setIngresso(stanzaIniziale);
			nuovoLabirinto.setUscita(stanzaFinale);
			return nuovoLabirinto;
		}
		public LabirintoBuilder addAttrezzo(String nomeAttrezzo,int peso) {
			this.mappaStanze.get(this.ultimaStanzaAggiunta).addAttrezzo(new Attrezzo(nomeAttrezzo,peso));
			return this;
		}
		public LabirintoBuilder addStanzaBloccata(String stanza, String direzioneBloccata, String passepartout) {
			if(!this.mappaStanze.containsKey(stanza)) {
				this.mappaStanze.put(stanza, new StanzaBloccata(stanza, direzioneBloccata, passepartout));
				this.ultimaStanzaAggiunta=stanza;
			}
			return this;
		}
		public LabirintoBuilder addStanzaMagica(String stanza, int i) {
			if(!this.mappaStanze.containsKey(stanza)) {
				this.mappaStanze.put(stanza, new StanzaMagica(stanza,i));
				this.ultimaStanzaAggiunta=stanza;
			}
			return this;
		}
		public LabirintoBuilder addStanzaMagica(String stanza) {
			if(!this.mappaStanze.containsKey(stanza)) {
				this.mappaStanze.put(stanza, new StanzaMagica(stanza));
				this.ultimaStanzaAggiunta=stanza;
			}
			return this;
		}
		public LabirintoBuilder addStanzaBuia(String stanza, String lanterna) {
			if(!this.mappaStanze.containsKey(stanza)) {
				this.mappaStanze.put(stanza, new StanzaBuia(stanza,lanterna));
				this.ultimaStanzaAggiunta=stanza;
			}
			return this;
		}
		public Map<String,Stanza> getListaStanze() {
			return this.mappaStanze;
		}
		public Stanza getStanzaIniziale() {
			return stanzaIniziale;
		}

		public Stanza getStanzaFinale() {
			return stanzaFinale;
		}
		
	}
}
