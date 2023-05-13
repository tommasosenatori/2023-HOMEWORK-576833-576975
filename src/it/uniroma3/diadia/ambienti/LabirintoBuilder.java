package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder {
	private Map<String,Stanza> mappaStanze;
	private String ultimaStanzaAggiunta;
	private String StanzaIniziale;
	private String StanzaFinale;
    public LabirintoBuilder(){
   	
        this.mappaStanze=new HashMap<>();
    }
    public LabirintoBuilder StanzaAdiacente(String stanza1,String stanza2,String direzione){
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
    public LabirintoBuilder addStanzaVicente(String stanza){
    	this.StanzaFinale=stanza;
    	this.addStanza(stanza);
    	return this;
        
    }
        public LabirintoBuilder addStanzaIniziale(String stanza){
        	this.StanzaIniziale=stanza;
        	this.addStanza(stanza);
        	return this;
       
    }
		public Labirinto getNuovoLabirinto() {
			Labirinto nuovoLabirinto = new Labirinto();
			nuovoLabirinto.setIngresso(this.mappaStanze.get(StanzaIniziale));
			nuovoLabirinto.setUscita(this.mappaStanze.get(StanzaFinale));
			return nuovoLabirinto;
		}
    public LabirintoBuilder addAttrezzo(String nomeAttrezzo,int peso) {
    	this.mappaStanze.get(this.ultimaStanzaAggiunta).addAttrezzo(new Attrezzo(nomeAttrezzo,peso));
    	return this;
    }
}
