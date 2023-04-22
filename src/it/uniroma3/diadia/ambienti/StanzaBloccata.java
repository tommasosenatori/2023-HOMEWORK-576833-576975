package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza{
	String direzioneBloccata;
	String passepartout;
	
	public StanzaBloccata(String nome,String direzioneBloccata,String passepartout) {
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.passepartout = passepartout;
	}
	
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if(direzioneBloccata.equals(direzione)&&!this.hasAttrezzo(passepartout)) {
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
