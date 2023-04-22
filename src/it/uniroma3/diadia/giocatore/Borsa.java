package it.uniroma3.diadia.giocatore;

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
	private Attrezzo[] attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;

	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA); 
	}
	//Aggiungere descrizione
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new Attrezzo[10]; // speriamo bastino...
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
		if (this.numeroAttrezzi==10)
			return false;
		this.attrezzi[this.numeroAttrezzi] = attrezzo;
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
		for (int i= 0; i<this.numeroAttrezzi; i++)
			if (this.attrezzi[i].getNome().equals(nomeAttrezzo))
				a = attrezzi[i];
		return a; 
	}
	/**
	 * Funzione che calcola il peso totale degli attrezzi nella stanza
	 * @return ritorna il peso totale degli attrezzi nella stanza
	 */
	public int getPeso() {
		int peso = 0;
		for (int i= 0; i<this.numeroAttrezzi; i++)
			peso += this.attrezzi[i].getPeso();
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
		int indice=-1;
		if(!this.isEmpty()&&hasAttrezzo(nomeAttrezzo)) {
			for(int i=0; i<this.numeroAttrezzi; i++) {
				if(this.attrezzi[i].getNome().equals(nomeAttrezzo)) {
					indice=i;
					break;
				}
			}
			a = this.attrezzi[indice];
			this.attrezzi[indice]=null;
			this.numeroAttrezzi--;
			for(int i=indice; i<this.numeroAttrezzi; i++) {
				this.attrezzi[i]=this.attrezzi[i+1];
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
			for (int i= 0; i<this.numeroAttrezzi; i++)
				s.append(attrezzi[i].toString()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
}
