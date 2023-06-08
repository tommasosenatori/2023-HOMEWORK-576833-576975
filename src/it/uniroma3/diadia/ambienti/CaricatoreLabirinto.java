package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze:";       

	/* prefisso di una singola riga di testo contenente il nome della stanza buia */
	private static final String STANZE_BUIE_MARKER = "Buia:";      

	/* prefisso di una singola riga di testo contenente il nome della stanza bloccata */
	private static final String STANZE_BLOCCATE_MARKER = "Bloccata:"; 

	/* prefisso di una singola riga di testo contenente il nome della stanza magica */
	private static final String STANZE_MAGICHE_MARKER = "Magica:"; 

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio:";    

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente:";  

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi:";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite:";

	/* prefisso di una singola riga di testo contenente le specifiche da collocare nel formato <nomeMago> <saluto> <attrezzo> <stanza> */
	private static final String PERSONAGGI_MARKER_MAGO = "Mago:"; 

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeStrega> */
	private static final String PERSONAGGI_MARKER_STREGA = "Strega:";

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeCane> <saluto> <cibo preferito> <attrezzo>  <stanza> */
	private static final String PERSONAGGI_MARKER_CANE = "Cane:";
	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

		Stanze: biblioteca, N10, N11
		Inizio: N10
		Vincente: N11
		Attrezzi: martello 10 biblioteca, pinza 2 N10
		Uscite: biblioteca nord N10, biblioteca sud N11

	 */
	private LineNumberReader reader;
	private LabirintoBuilder builder;



	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this.builder = Labirinto.newBuilder();
		this.reader = new LineNumberReader(new FileReader(nomeFile));

	}
	public CaricatoreLabirinto(StringReader stringReader) throws FileNotFoundException {
		this.builder = Labirinto.newBuilder();
		this.reader = new LineNumberReader(stringReader);

	}

	public Labirinto carica() throws FormatoFileNonValidoException {
		Labirinto lab = new Labirinto();
		try {
			this.leggiECreaStanze();
			this.leggiECreaStanzeMagiche();
			this.leggiECreaStanzeBuie();
			this.leggiECreaStanzeBloccate();
			this.leggiInizialeEvincente();
			this.leggiECreaMaghi();
			this.leggiECreaStreghe();
			this.leggiECreaCani();
			this.leggiECollocaAttrezzi();
			this.leggiEImpostaUscite();
			lab = this.builder.getLabirinto();
		} finally {

			try {
				reader.close();
			} catch (IOException e) {

				return new Labirinto();
			}

		}
		return lab;
	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			this.builder.addStanza(nomeStanza);
		}
	}

	private void leggiECreaStanzeMagiche() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MAGICHE_MARKER);
		if(nomiStanze!=null) {
			for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {

				this.builder.addStanzaMagica(nomeStanza);
			}
		}
	}

	private void leggiECreaStanzeBuie() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_BUIE_MARKER);
		for(String specificheStanze : separaStringheAlleVirgole(nomiStanze)) {
			String nomeStanza;
			String lanterna;
			try (Scanner scannerLinea = new Scanner(specificheStanze)) {

				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di una stanza."));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della lanterna "+nomeStanza+"."));
				lanterna = scannerLinea.next();

			}

			this.builder.addStanzaBuia(specificheStanze, lanterna);
		}
	}
	private void leggiECreaStanzeBloccate() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_BLOCCATE_MARKER);
		for(String specificheStanze : separaStringheAlleVirgole(nomiStanze)) {
			String nomeStanza;
			String nomeChiave;
			String dirBloccata;
			try (Scanner scannerLinea = new Scanner(specificheStanze)) {

				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di una stanza."));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della direzione bloccata "+nomeStanza+"."));
				dirBloccata = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della chiave "+nomeStanza+"."));
				nomeChiave = scannerLinea.next();


			}

			this.builder.addStanzaBloccata(specificheStanze, dirBloccata,nomeChiave);
		}
	}

	private void leggiECreaMaghi() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER_MAGO);
		for(String specificheStanze : separaStringheAlleVirgole(nomiStanze)) {
			String nomeStanza;
			String nomeMago;
			String presentazione;
			String attrezzo;
			try (Scanner scannerLinea = new Scanner(specificheStanze)) {

				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un mago."));
				nomeMago = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la presentazione di "+nomeMago+"."));
				presentazione = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo non esiste."));
				attrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di una stanza."));
				nomeStanza = scannerLinea.next();

			}
			Mago personaggio = new Mago(nomeMago,presentazione, new Attrezzo(attrezzo,1));
			this.builder.getListaStanze().get(nomeStanza).setPersonaggio(personaggio);
		}
	}
	private void leggiECreaStreghe() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER_STREGA);
		for(String specificheStanze : separaStringheAlleVirgole(nomiStanze)) {
			String nomeStrega;
			String presentazione;
			String nomeStanza;
			try (Scanner scannerLinea = new Scanner(specificheStanze)) {

				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un mago."));
				nomeStrega = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la presentazione di "+nomeStrega+"."));
				presentazione = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di una stanza."));
				nomeStanza = scannerLinea.next();

			}
			Strega personaggio = new Strega(nomeStrega,presentazione);
			this.builder.getListaStanze().get(nomeStanza).setPersonaggio(personaggio);
		}
	}
	private void leggiECreaCani() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER_CANE);
		for(String specificheStanze : separaStringheAlleVirgole(nomiStanze)) {
			String nomeStanza;
			String nomeCane;
			String presentazione;
			String attrezzo;
			String ciboPreferito;
			try (Scanner scannerLinea = new Scanner(specificheStanze)) {

				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un cane."));
				nomeCane = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la presentazione di "+nomeCane+"."));
				presentazione = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("Il nome del cibo preferuto di"+nomeCane+"."));
				ciboPreferito = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo non esiste."));
				attrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di una stanza."));
				nomeStanza = scannerLinea.next();

			}
			Cane personaggio = new Cane(nomeCane,presentazione,ciboPreferito,new Attrezzo(attrezzo, 1));
			this.builder.getListaStanze().get(nomeStanza).setPersonaggio(personaggio);
		}
	}

	private List<String> separaStringheAlleVirgole(String string) {

		List<String> result = new LinkedList<>();
		if(string!=null) {
			Scanner scanner = new Scanner(string);
			scanner.useDelimiter(",");
			try (Scanner scannerDiParole = scanner){
				while(scannerDiParole.hasNext())
					result.add(scannerDiParole.next());
			}
		}
		return result;
	}


	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		this.builder.addStanzaIniziale(nomeStanzaIniziale);
		this.builder.addStanzaVincente(nomeStanzaVincente);

	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo non esiste."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+ "non esiste."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+ "non esiste."));
				nomeStanza = scannerLinea.next();
			}				
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			check(isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			this.builder.getListaStanze().get(nomeStanza).addAttrezzo(attrezzo);
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}


	private boolean isStanzaValida(String nomeStanza) {
		if(!this.builder.getListaStanze().containsKey(nomeStanza)) {
			System.out.println(nomeStanza);
		}
		return this.builder.getListaStanze().containsKey(nomeStanza);
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		List<String> specifiche = separaStringheAlleVirgole(specificheUscite);
		for(String specifica: specifiche) {
			try (Scanner scannerDiLinea = new Scanner(specifica)) {			
				while (scannerDiLinea.hasNext()) {
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
					String stanzaPartenza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
					String dir = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
					String stanzaDestinazione = scannerDiLinea.next();

					impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
				}
			}
		} 
	}

	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	private void impostaUscita(String stanzaDa, String dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+stanzaDa);
		check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ nomeA);
		Stanza partenzaDa = this.builder.getListaStanze().get(stanzaDa);
		Stanza arrivoA = this.builder.getListaStanze().get(nomeA);
		partenzaDa.impostaStanzaAdiacente(dir, arrivoA);
	}


	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
	}

	public Stanza getStanzaIniziale() {
		return this.builder.getStanzaIniziale();
	}

	public Stanza getStanzaVincente() {
		return this.builder.getStanzaFinale();
	}
}
