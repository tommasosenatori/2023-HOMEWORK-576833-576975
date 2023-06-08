package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends Comando {
	static final private String[] elencoComandi = {"guarda","vai","prendi","posa","aiuto", "fine"};
	/**
	 * Stampa informazioni di aiuto.
	 */


		

		/**
		 * Stampa informazioni di aiuto.
		 */
		@Override
		public void esegui(Partita partita) {
			StringBuilder listaComandi = new StringBuilder();
			for(int i=0; i< elencoComandi.length; i++) 
				listaComandi.append(elencoComandi[i]+" ");
			partita.getConsole().mostraMessaggio(listaComandi.toString());
				
		}



	@Override
	public String getNome() {
		return "aiuto";
	}

	

}
