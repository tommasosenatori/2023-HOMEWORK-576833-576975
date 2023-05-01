package it.uniroma3.diadia.giocatore;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import it.uniroma3.diadia.attrezzi.Attrezzo;
/**
 * Classe Borsa
 * Una Borsa è un contenitore di Attrezzi
 * La Borsa è portata da un giocatore.
 * Il peso e il numero degli oggetti contenuti in una borsa non può eccedere un certo limite
 * @author 576833 576975
 * @see Attrezzo
 */
public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10; 
	private List<Attrezzo> attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;

	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA); 
	}
	//Aggiungere descrizione
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new ArrayList<>();
		this.numeroAttrezzi = 0;
	}
	/**
	 * Mette un attrezzo nella borsa
	 * @param attrezzo, attrezzo da mettere nella borsa
	 * @return true se riesce ad aggiungere l'attrezzo, false altrimenti
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		this.attrezzi.add(attrezzo);
		this.numeroAttrezzi++;
		return true;
	}
	public int getPesoMax() {
		return pesoMax;
	}
	/**
	 * * Restituisce l'attrezzo nomeAttrezzo se presente nella borsa.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza, null se l'attrezzo non è presente
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		Attrezzo attrezzoCorrente = null;
		Iterator <Attrezzo> i = this.attrezzi.iterator();
		while(i.hasNext()) {
			attrezzoCorrente = i.next();
			if(attrezzoCorrente.getNome().equals(nomeAttrezzo)) {
				a = attrezzoCorrente;
				break;
			}
			
		}
		return a; 
	}
	/**
	 * Funzione che calcola il peso totale degli attrezzi nella stanza
	 * @return ritorna il peso totale degli attrezzi nella stanza
	 */
	public int getPeso() {
		int peso = 0;


		Iterator <Attrezzo> i = this.attrezzi.iterator();
		while(i.hasNext()) {
			Attrezzo a = i.next();
			peso += a.getPeso();
		}
		return peso;
	}
	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}
	/**
	 * Controlla se un attrezzo esiste nella borsa.
	 * @param nomeAttrezzo, nome dell'attrezzo del quale si vuole verificare l'esistenza.
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	/**
	 * Rimuove un attrezzo dalla borsa(ricerca in base al nome)
	 * @param nomeAttrezzo, nome dell'attrezzo che si vuole rimuovere
	 * @return true se l'attrezzo e stato rimosso, false altrimnenti.
	 */
	public Attrezzo removeAttrezzo (String nomeAttrezzo) {
		Attrezzo a = null;
		Iterator<Attrezzo> i = attrezzi.iterator();
		while(i.hasNext()) {
			a = i.next();
			if(a.getNome().equals(nomeAttrezzo)) {
				i.remove();
				return a;
			}	
		}
		return a;
	}
	/**
	 * Restituisce una rappresentazione stringa della borsa,
	 * ne stampa il contenuto e il peso totale della borsa.
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (int i= 0; i<this.attrezzi.size(); i++)
				s.append(attrezzi.get(i).toString()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
}
