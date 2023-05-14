package it.uniroma3.diadia;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class IOSimulator implements IO {
	
	private Map<Integer,String> messaggiMostrati;
	private List <String> comandiLetti;
	
	
	public IOSimulator() {
		this.messaggiMostrati = new HashMap<>();
		this.comandiLetti = new LinkedList<>();
	}
	public IOSimulator(List<String> listaInput) {
		this.messaggiMostrati = new HashMap<>();
		this.comandiLetti = listaInput;
	}
	
	@Override
	public void mostraMessaggio(String messaggio) {
		this.messaggiMostrati.put(this.messaggiMostrati.size(),messaggio);
		
	}

	@Override
	public String leggiRiga() {
		if(this.comandiLetti.size()>0) {
			return this.comandiLetti.remove(0);
		}
		return "fine";
		
	}
	
	public void setComandiLetti(List<String> comandiLetti) {
		this.comandiLetti = comandiLetti;
	}
	
	public Map<Integer, String> getMessaggiMostrati() {
		return messaggiMostrati;
	}

}
