package Projekt;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import knihovna.Knihovna;
import sql.Connect;

public class Main {

	public static void main(String[] args) {
		Knihovna knihovna = new Knihovna();
		Scanner sc = new Scanner(System.in);
		
		
		Connect con = new Connect();
		
		System.out.println("******************************");
		if(con.connect()) {
			System.out.println("K databazi se podarilo pripojit");
		}
		else {
			System.out.println("K databazi se nepodarilo pripojit");
			return;
		}
		
		if(con.createTable()) {
			System.out.println("Tabulka byla vytvorena nebo jiz existuje");
		}
		else {
			System.out.println("Tabulka nebyla vytvorena nebo nastala nejaka jina chyba");
		}
		
		if(con.selectAll(knihovna)) {
			System.out.println("Nacteni knih z databaze probehlo uspesne");
		}
		else {
			System.out.println("Pri nacitani knih z databaze doslo k chybe");
		}
		System.out.println("******************************");
		
		int volba = 0;
		do {
			System.out.println("Sprava knihovny - Menu");
			System.out.println("0...Ukoncit program");
			System.out.println("1...Pridat novou knihu");
			System.out.println("2...Upravit knihu");
			System.out.println("3...Smazat knihu");
			System.out.println("4...Upravit stav vypujceni u knihy");
			System.out.println("5...Vypis knih");
			System.out.println("6...Vyhledani knihy");
			System.out.println("7...Vypis knih autora");
			System.out.println("8...Vypis knih urciteho zanru");
			System.out.println("9...Vypis vypujcenych knih");
			System.out.println("10...Ulozeni knihy do souboru");
			System.out.println("11...Nacteni knihy ze souboru");
			
			try {
				volba = sc.nextInt();
			}
			catch(java.util.InputMismatchException e) {
				volba = 999;
				sc.next();
				continue;
			}
			
			
			
			switch(volba) {
				case 0:
					System.out.println("******************************");
					if(con.insertRecord(knihovna)) {
						System.out.println("Ulozeni knih do databaze probehlo uspesne");
					}
					else {
						System.out.println("Pri ukladani knih do databaze doslo k chybe");
					}
					
					con.disconnect();
					
					System.out.println("Program skoncil");
					System.out.println("******************************");
					
					break;
				
				case 1:
					System.out.println("******************************");
					if(knihovna.pridejKnihu()) {
						System.out.println("Vlozeni knihy se zdarilo");
					}
					else {
						System.out.println("Vlozeni knihy se nezdarilo");
					}
					System.out.println("******************************");
					
					break;
				
				case 2:
					System.out.println("******************************");
					if(knihovna.upravKnihu()) {
						System.out.println("Uprava knihy se zdarila");
					}
					else {
						System.out.println("Uprava knihy se nezdarila");
					}
					System.out.println("******************************");
					
					break;
					
				case 3:
					System.out.println("******************************");
					if(knihovna.smazKnihu()) {
						System.out.println("Vymazani knihy se zdarilo");
					}
					else {
						System.out.println("Vymazani knihy se nezdarilo");
					}
					System.out.println("******************************");
					break;
					
				case 4:
					System.out.println("******************************");
					if(knihovna.upravStavVypujceni()) {
						System.out.println("Uprava stavu dostupnosti knihy se zdarila");
					}
					else {
						System.out.println("Uprava stavu dostupnosti knihy se nezdarila");
					}
					System.out.println("******************************");
					
					break;
	
				case 5:
					System.out.println("******************************");
					if(knihovna.vypisKnihy()) {
					}
					else {
						System.out.println("V knihovne nejsou zadne knihy");
					}
					System.out.println("******************************");
					break;
					
				case 6:
					System.out.println("******************************");
					if(knihovna.vyhledaniKnihy()) {
						;
					}
					else {
						System.out.println("Knihu se nepodarilo vyhledat");
					}
					System.out.println("******************************");
					
					break;
					
				case 7:
					System.out.println("******************************");
					if(knihovna.vypisKnihyAutora()) {
						;
					}
					else {
						System.out.println("V knihovne nejsou zadne knihy zvoleneho autora nebo doslo k jine chybe");
					}
					System.out.println("******************************");
					
					break;
					
				case 8:
					System.out.println("******************************");
					if(knihovna.vypisKnihyDleZanru()) {
						;
					}
					else {
						System.out.println("V knihovne nejsou zadne knihy zvoleneho zanru nebo doslo k jine chybe");
					}
					System.out.println("******************************");
					
					break;
					
				case 9:
					System.out.println("******************************");
					if(knihovna.vypisVypujceneKnihy()) {
						;
					}
					else {
						System.out.println("V knihovne nejsou zadne vypujcene knihy nebo doslo k jine chybe");
					}
					System.out.println("******************************");
					
					break;
					
				case 10:
					System.out.println("******************************");
					if(knihovna.ulozKnihuDoSouboru()) {
						System.out.println("Kniha byla uspesne ulozena do souboru");
					}
					else {
						System.out.println("Pri ukladani knihy do souboru doslo k chybe");
					}
					System.out.println("******************************");
					
					break;
					
				case 11:
					System.out.println("******************************");
					if(knihovna.nactiKnihuZeSouboru()) {
						System.out.println("Kniha byla uspesne nactena ze souboru");
					}
					else {
						System.out.println("Pri nacitani knihy ze souboru doslo k chybe");
					}
					System.out.println("******************************");
					
					break;
					
				default:
					
					break;
			}
		} while(volba != 0);
	}
}
