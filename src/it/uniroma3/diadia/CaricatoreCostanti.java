package it.uniroma3.diadia;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import 	static it.uniroma3.diadia.Costanti.*;
public class CaricatoreCostanti {
	public void caricaCostanti () {
		Properties costanti = new Properties();
		 
		 
			try(InputStream diaDiaCost = this.getClass().getClassLoader().getResourceAsStream("costanti/diadia.properties") ) {
				if(diaDiaCost!=null) {
				costanti.load(diaDiaCost);
				DEFAULT_PESO_MAX_BORSA = Integer.parseInt( costanti.getProperty("peso_max_borsa", "20"));
				NUMERO_MASSIMO_ATTREZZI = Integer.parseInt( costanti.getProperty("numero_massimo_attrezzi", "10"));
				CFU_INIZIALI = Integer.parseInt( costanti.getProperty("cfu_iniziali", "20"));
				}else {
					System.out.println("File non trovato uso i valori di default");
				}
			} catch (IOException e) {
				System.out.println("File non caricato uso i valori di default");
			}
			
		
	}
}
