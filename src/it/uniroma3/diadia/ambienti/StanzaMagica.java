package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagica extends Stanza{

	final static private int SOGLIA_MAGICA_DEFAULT = 3;
	private int contatoreAttrezziPosati;
	private int sogliaMagica;
	public StanzaMagica(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}
	public StanzaMagica(String nome, int soglia) {
		super(nome);
		this.contatoreAttrezziPosati = 0;
		this.sogliaMagica = soglia;
	}
	@Override
	public boolean addAttrezzo(Attrezzo attrezzo){
		contatoreAttrezziPosati++;
		if(contatoreAttrezziPosati>=sogliaMagica) {
			attrezzo = modificaAttrezzo(attrezzo);
		}		
		return super.addAttrezzo(attrezzo);
	}
	private Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		StringBuilder nomeCapovolto = new StringBuilder(attrezzo.getNome()).reverse();
		Attrezzo ozzertta = new Attrezzo(nomeCapovolto.toString(), attrezzo.getPeso()*2);
		return ozzertta;
	}
}
