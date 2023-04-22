package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuia extends Stanza{
	static final private String LANTERNA_DEFAULT = "lanterna";
	private String lanterna;
	public StanzaBuia(String nome,String lanterna) {
		super(nome);
		this.lanterna = lanterna;
		
	}
	public StanzaBuia(String nome) {
		this(nome,LANTERNA_DEFAULT);
		
	}
	@Override
	public String getDescrizione() {
		if(this.hasAttrezzo(lanterna)) {
			return super.getDescrizione();
		}
		return "qui c'è buio pesto";
	}
}
