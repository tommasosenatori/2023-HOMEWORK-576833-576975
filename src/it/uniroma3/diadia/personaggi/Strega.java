package it.uniroma3.diadia.personaggi;

import java.util.*;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {
	
	public Strega(String nome, String presentaz) {
		super(nome, presentaz);
		
	}

	@Override
	public String agisci(Partita partita) {
		List<Stanza> adiacenti = new ArrayList<>(partita.getStanzaCorrente().getMapStanzeAdiacenti().values());
		Collections.sort(adiacenti,new Comparator<Stanza>() {
			@Override
			public int compare(Stanza o1, Stanza o2) {
				return o1.getAttrezzi().size()-o2.getAttrezzi().size();
			}
		});
		if(this.haSalutato()) {
			partita.setStanzaCorrente(adiacenti.get(adiacenti.size()-1));
			return "siccome mi hai salutato...ti ricompenso";
		}else {
			partita.setStanzaCorrente(adiacenti.get(0));
			return "muahahaha";
		}
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		
		return "heheheha";
	}

}
