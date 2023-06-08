package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.uniroma3.diadia.Direzioni;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import static it.uniroma3.diadia.Costanti.NUMERO_MASSIMO_ATTREZZI;
/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
 */

public class Stanza {

	static final private int NUMERO_MASSIMO_DIREZIONI = 4;
	

	private String nome;
	private Map<String,Attrezzo> attrezzi;
	private Map<Direzioni,Stanza> mappaAdiacenti;
	
	private AbstractPersonaggio personaggio;
	
	
	public Map<Direzioni, Stanza> getMapStanzeAdiacenti() {
		return mappaAdiacenti;
	}

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * @param nome il nome della stanza
	 */
	public Stanza(String nome) {
		this.nome = nome;
		this.mappaAdiacenti = new HashMap<>();
		this.attrezzi = new HashMap<>();
	}

	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
	 */
	public void impostaStanzaAdiacente(Direzioni direzione, Stanza stanza) {
		boolean aggiornato = false;
		if (mappaAdiacenti.containsKey(direzione)) {
			this.mappaAdiacenti.put(direzione, stanza);
			aggiornato = true;
		}
		if (!aggiornato)
			if (this.mappaAdiacenti.size() < NUMERO_MASSIMO_DIREZIONI) {
				this.mappaAdiacenti.put(direzione, stanza);
				mappaAdiacenti.size();
			}
	}
	public void impostaStanzaAdiacente(String direzioneS, Stanza stanza) {
		boolean aggiornato = false;
		Direzioni direzione;
		try{
		 direzione = Direzioni.valueOf(direzioneS.toUpperCase());
		 if (mappaAdiacenti.containsKey(direzione)) {
				this.mappaAdiacenti.put(direzione, stanza);
				aggiornato = true;
			}
			if (!aggiornato)
				if (this.mappaAdiacenti.size() < NUMERO_MASSIMO_DIREZIONI) {
					this.mappaAdiacenti.put(direzione, stanza);
					mappaAdiacenti.size();
				}
		}catch(Exception e){
			direzione = Direzioni.INESISTENTE;
		}
		
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente(Direzioni direzione) {
		return mappaAdiacenti.get(direzione);
	}
	public Stanza getStanzaAdiacente(String direzioneS) {
		Direzioni direzione;
		try{
		 direzione = Direzioni.valueOf(direzioneS.toUpperCase());
		}catch(Exception e){
			direzione = Direzioni.INESISTENTE;
		}
		return mappaAdiacenti.get(direzione);
	}
	

	/**
	 * Restituisce la nome della stanza.
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * @return la descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * @return la collezione di attrezzi nella stanza.
	 */
	public List<Attrezzo> getAttrezzi() {
		return new ArrayList <Attrezzo>(this.attrezzi.values());
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (attrezzi.size() < NUMERO_MASSIMO_ATTREZZI) {
			attrezzi.put(attrezzo.getNome(),attrezzo);
			
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Restituisce una rappresentazione stringa di questa stanza,
	 * stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);
		risultato.append("\nUscite: ");
		for (Direzioni direzione : this.getDirezioni())
			if (direzione!=null)
				risultato.append(" " + direzione.name());
		risultato.append("\nPersonaggi:");
		if(personaggio!=null)
			risultato.append(personaggio+"\n");
		risultato.append("\nAttrezzi nella stanza:");
		for (Attrezzo attrezzo : this.attrezzi.values()) {
			if(attrezzo!=null) {
				risultato.append(attrezzo.toString()+" ");
			}
		}
		return risultato.toString();
	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return attrezzi.containsKey(nomeAttrezzo);
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
	 * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param attrezzo, nome dell'attrezzo che si vuole rimuovere
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		return attrezzi.remove(attrezzo.getNome(),attrezzo);
	}


	public List<Direzioni> getDirezioni() {	
		return new ArrayList<Direzioni>(this.mappaAdiacenti.keySet());
	}
	
	@Override
	
	public boolean equals(Object o) {
		Stanza that = (Stanza)o;
		return this.getNome().equals(that.getNome());//&&this.mappaAdiacenti.equals(that.getMapStanzeAdiacenti())&&this.getAttrezzi().equals(that.getAttrezzi());
	}
	@Override
	public int hashCode() {
		return this.getNome().hashCode();
	}

	public AbstractPersonaggio getPersonaggio() {
		return personaggio;
	}

	public void setPersonaggio(AbstractPersonaggio personaggio) {
		this.personaggio = personaggio;
	}
	
}