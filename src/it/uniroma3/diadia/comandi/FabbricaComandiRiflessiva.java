package it.uniroma3.diadia.comandi;

import java.util.Scanner;

public class FabbricaComandiRiflessiva implements FabbricaDiComandi {

	@SuppressWarnings("deprecation")
	@Override
	public Comando costruisciComando(String istruzione) {
		try (Scanner scannerDiParole = new Scanner(istruzione)) {
			String nomeComando = null; // es. ‘vai’
			String parametro = null; // es. ‘sud’
			Comando comando = null;
			if (scannerDiParole.hasNext())
				nomeComando = scannerDiParole.next();//prima parola: nome del comando
			if (scannerDiParole.hasNext())
				parametro = scannerDiParole.next();//seconda parola: eventuale parametro
			StringBuilder nomeClasse = new StringBuilder("it.uniroma3.diadia.comandi.Comando");
			if(nomeComando!=null) {
			nomeClasse.append( Character.toUpperCase(nomeComando.charAt(0)) );
			// es. nomeClasse: ‘it.uniroma3.diadia.comandi.ComandoV’
			nomeClasse.append( nomeComando.substring(1) ) ;
			// es. nomeClasse: ‘it.uniroma3.diadia.comandi.ComandoVai’
			}
			try {
				comando = (Comando)Class.forName(nomeClasse.toString()).newInstance();
				comando.setParametro(parametro);	
			}
			catch (Exception e) {
				comando = new ComandoNonValido();
			}		
			return comando;
		}
		}
	

}
