package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaProtected extends StanzaProtected {

	final static private int SOGLIA_MAGICA_DEFAULT = 3;
	private int contatoreAttrezziPosati;
	private int sogliaMagica;
	public StanzaMagicaProtected(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}
	public StanzaMagicaProtected(String nome, int soglia) {
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
		if (this.numeroAttrezzi<this.attrezzi.length) {
			this.attrezzi[this.numeroAttrezzi] = attrezzo;
			this.numeroAttrezzi++;
			return true;
		}
		else return false;
	}

	private Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		StringBuilder nomeCapovolto = new StringBuilder(attrezzo.getNome()).reverse();
		Attrezzo ozzertta = new Attrezzo(nomeCapovolto.toString(), attrezzo.getPeso()*2);
		return ozzertta;

	}
}
