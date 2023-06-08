package it.uniroma3.diadia;

import java.util.Scanner;
public class IOConsole implements IO {
	private Scanner scannerDiLinee;
	public IOConsole(Scanner scanner) {
		super();
		scannerDiLinee = scanner;
	}
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}
	public String leggiRiga() {
		
		String riga = scannerDiLinee.nextLine(); 
		//scannerDiLinee.close();
		return riga;
	}
	
}
