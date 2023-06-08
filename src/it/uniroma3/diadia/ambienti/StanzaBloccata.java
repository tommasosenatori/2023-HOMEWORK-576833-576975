package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.Direzioni;

public class StanzaBloccata extends Stanza{
	Direzioni direzioneBloccata;
	String passepartout;
	
	public StanzaBloccata(String nome,Direzioni direzioneBloccata,String passepartout) {
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.passepartout = passepartout;
	}
	public StanzaBloccata(String nome,String direzioneBloccataS,String passepartout) {
		super(nome);
		Direzioni direzioneBloccata;
		try{
		 direzioneBloccata = Direzioni.valueOf(direzioneBloccataS.toUpperCase());
		}catch(Exception e){
			direzioneBloccata = Direzioni.NORD;
		}
		this.direzioneBloccata = direzioneBloccata;
		this.passepartout = passepartout;
	}
	
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if(direzioneBloccata.name().equals(direzione.toUpperCase())&&!this.hasAttrezzo(passepartout)) {
			return this;
		}
		return super.getStanzaAdiacente(Direzioni.valueOf(direzione.toUpperCase()));
	}
	@Override
	public Stanza getStanzaAdiacente(Direzioni direzione) {
		if(direzioneBloccata.name().equals(direzione)&&!this.hasAttrezzo(passepartout)) {
			return this;
		}
		return super.getStanzaAdiacente(direzione);
	}
	
	@Override
	public String getDescrizione() {
		StringBuilder descrizioneAggiuntiva  = new StringBuilder("\nDirezione bloccata:"+this.direzioneBloccata+". Necessario:"+this.passepartout+"\n");
		return super.getDescrizione() + descrizioneAggiuntiva ;
	}
}
