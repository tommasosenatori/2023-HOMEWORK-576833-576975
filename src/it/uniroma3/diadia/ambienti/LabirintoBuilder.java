package it.uniroma3.diadia.ambienti;


import java.util.HashMap;

import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder {
	private Map<String,Stanza> mappaStanze;
	private String ultimaStanzaAggiunta;
	private String stanzaIniziale;
	private String stanzaFinale;
	public LabirintoBuilder(){
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
		this.stanzaFinale=stanza;
		this.addStanza(stanza);
		return this;

	}
	public LabirintoBuilder addStanzaIniziale(String stanza){
		this.stanzaIniziale=stanza;
		this.addStanza(stanza);
		return this;

	}
	public Labirinto getLabirinto() {
		Labirinto nuovoLabirinto = new Labirinto();
		nuovoLabirinto.setIngresso(this.mappaStanze.get(stanzaIniziale));
		nuovoLabirinto.setUscita(this.mappaStanze.get(stanzaFinale));
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
}
