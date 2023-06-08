package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {
	Attrezzo attrezzo;
	private String ciboPreferito;
	public Cane(String nome, String presentaz,String ciboPreferito,Attrezzo attrezzo) {
		super(nome, presentaz);
		this.attrezzo = attrezzo;
		this.ciboPreferito = ciboPreferito;

	}

	@Override
	public String agisci(Partita partita) {
		int cfu = partita.getGiocatore().getCfu();
		partita.getGiocatore().setCfu(--cfu);		
		return "Grrrr";
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if(ciboPreferito.equals(attrezzo.getNome())) {
			if(this.attrezzo!=null) {
				partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
				this.attrezzo = null;
			}
			return "Grrr";
		}else {
			int cfu = partita.getGiocatore().getCfu();
			partita.getGiocatore().setCfu(--cfu);
			return "Woof Woof";
		}		
	}
}
