package it.uniroma3.diadia.giocatore;

import java.util.List;
import static it.uniroma3.diadia.Costanti.DEFAULT_PESO_MAX_BORSA;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatoreAttrezziPerNome;
import it.uniroma3.diadia.attrezzi.ComparatoreAttrezziPerPesoENome;
/**
 * Classe Borsa
 * Una Borsa è un contenitore di Attrezzi
 * La Borsa è portata da un giocatore.
 * Il peso e il numero degli oggetti contenuti in una borsa non può eccedere un certo limite
 * @author 576833 576975
 * @see Attrezzo
 */

public class Borsa {

	private Map<String,Attrezzo> attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;

	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA); 
	}
	//Aggiungere descrizione
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new HashMap<>();
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
		this.attrezzi.put(attrezzo.getNome(),attrezzo);
		this.numeroAttrezzi = this.attrezzi.size();
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
		return attrezzi.get(nomeAttrezzo);
	}
	/**
	 * Funzione che calcola il peso totale degli attrezzi nella stanza
	 * @return ritorna il peso totale degli attrezzi nella stanza
	 */
	public int getPeso() {
		int peso = 0;
		Iterator <Attrezzo> i = this.attrezzi.values().iterator();
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
		return this.attrezzi.containsKey(nomeAttrezzo);
	}
	/**
	 * Rimuove un attrezzo dalla borsa(ricerca in base al nome)
	 * @param nomeAttrezzo, nome dell'attrezzo che si vuole rimuovere
	 * @return true se l'attrezzo e stato rimosso, false altrimnenti.
	 */
	public Attrezzo removeAttrezzo (String nomeAttrezzo) {
		return attrezzi.remove(nomeAttrezzo);
	}
	/**
	 * 
	 * @return lista di attrezzi ordinata per peso e a parità di peso per nome
	 */
	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		List<Attrezzo> listaAttrezziOrdinata = new ArrayList<>(this.attrezzi.values());
		Collections.sort(listaAttrezziOrdinata,new ComparatoreAttrezziPerPesoENome());
		return listaAttrezziOrdinata;
	}
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		SortedSet<Attrezzo> setOrdinatoPerNome = new TreeSet<>(new ComparatoreAttrezziPerNome());
		setOrdinatoPerNome.addAll(this.attrezzi.values());
		return setOrdinatoPerNome;
	}
	
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		Map<Integer, Set<Attrezzo>> contenutoRaggruppatoPerPeso = new TreeMap<>();
		Set<Attrezzo> attrezziPerPeso;
		for(Attrezzo a: this.attrezzi.values()) {
			attrezziPerPeso = contenutoRaggruppatoPerPeso.get(a.getPeso());
			if(attrezziPerPeso==null) {
				attrezziPerPeso = new HashSet<>();
				contenutoRaggruppatoPerPeso.put(a.getPeso(), attrezziPerPeso);
			}
			attrezziPerPeso.add(a);
		}	
		return contenutoRaggruppatoPerPeso;
	}
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		SortedSet<Attrezzo> ordinatoPerPeso = new TreeSet<>(new ComparatoreAttrezziPerPesoENome());
		ordinatoPerPeso.addAll(this.attrezzi.values());
		return ordinatoPerPeso;
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
			for (String k : this.attrezzi.keySet())
				s.append(attrezzi.get(k).toString()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
	
}
