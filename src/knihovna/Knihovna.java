package knihovna;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import knihovna.Roman.Zanr;

public class Knihovna {
	Map<String, Kniha> seznam = new TreeMap<>();
	Scanner sc = new Scanner(System.in);
	int volba;
	String volbaString;
	
	public int vratPocetKnih() {
		return seznam.size();
	}
	
	public String vratKlicNaPozici(int pozice) {
		String klic;
		int i = 0;
		for(Map.Entry<String, Kniha> kniha : seznam.entrySet()) {
			klic = kniha.getKey();
			
			if(i == pozice) {
				return klic;
			}
			i++;
		}
		
		return null;
	}
	
	public String vratNazevPodleKlice(String id) {
		Kniha kniha = seznam.get(id);
		return kniha.getNazev();
		
	}
	
	public String vratAutoryPodleKlice(String id) {
		Kniha kniha = seznam.get(id);
		String[] autori = kniha.getAutori();
		
		String autoriDohromady = "";
		for(String autor : autori) {
			autoriDohromady = autoriDohromady + autor + ";";
		}
		
		return autoriDohromady.substring(0, autoriDohromady.length() - 1);
	}
	
	public int vratRokVydaniPodleKlice(String id) {
		Kniha kniha = seznam.get(id);
		return kniha.getRokVydani();
	}
	
	public int vratVypujcenoPodleKlice(String id) {
		Kniha kniha = seznam.get(id);
		
		if(kniha.isVypujceno()) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
	public String vratTypPodleKlice(String id) {
		Kniha kniha = seznam.get(id);
		if(kniha instanceof Roman) {
			return "roman";
		}
		else if(kniha instanceof Ucebnice) {
			return "ucebnice";
		}
		else {
			System.out.println("Pri zjistovani typu knihy doslo k chybe");
			return null;
		}
	}
	
	public String vratZanrPodleKlice(String id) {
		Roman roman = (Roman) seznam.get(id);
		return roman.getZanr().toString();
	}
	
	public int vratRocnikPodleKlice(String id) {
		Ucebnice ucebnice = (Ucebnice) seznam.get(id);
		return ucebnice.getVhodneProRocnik();
		
	}
	
	public boolean pridejRoman(String id, String nazev, String[] autori, int rokVydani, boolean vypujceno, String zanr) {
		if(zanr.equals("THRILLER")) {
			Roman roman = new Roman(nazev, autori, rokVydani, vypujceno, Roman.Zanr.THRILLER);
			seznam.put(id, roman);
		}
		else if(zanr.equals("FANTASY")) {
			Roman roman = new Roman(nazev, autori, rokVydani, vypujceno, Roman.Zanr.FANTASY);
			seznam.put(id, roman);
		}
		else if(zanr.equals("SCIFI")) {
			Roman roman = new Roman(nazev, autori, rokVydani, vypujceno, Roman.Zanr.SCIFI);
			seznam.put(id, roman);
		}
		else if(zanr.equals("KOMEDIE")) {
			Roman roman = new Roman(nazev, autori, rokVydani, vypujceno, Roman.Zanr.KOMEDIE);
			seznam.put(id, roman);
		}
		else if(zanr.equals("HISTORIE")) {
			Roman roman = new Roman(nazev, autori, rokVydani, vypujceno, Roman.Zanr.HISTORIE);
			seznam.put(id, roman);
		}
		else {
			System.out.println("Pri cteni zanru doslo k chybe");
			return false;
		}
		
		return true;
	}
	
	public boolean pridejUcebnici(String id, String nazev, String[] autori, int rokVydani, boolean vypujceno, int rocnik) {
		Ucebnice ucebnice = new Ucebnice(nazev, autori, rokVydani, vypujceno, rocnik);
		seznam.put(id, ucebnice);
		
		return true;
	}
	
	public boolean pridejKnihu() {		
		System.out.println("Jaky typ knihy si prejete pridat?");
		System.out.println("0...roman");
		System.out.println("1...ucebnice");
		try {
			volba = sc.nextInt();
		}
		catch(java.util.InputMismatchException e) {
			System.out.println("Neplatna volba");
			volba = 999;
			sc.next();
			return false;
		}
		
		boolean jeRoman = false;
		if(volba == 0) {
			jeRoman = true;
		}
		
		if(volba == 1) {
			jeRoman = false;
		}
		
		if(volba != 0 && volba != 1){
			System.out.println("Neplatna volba");
			return false;
		}
		
		System.out.println("Zadejte nazev knihy");
		String nazevKnihy = sc.next();
		
		String nazevLowercase = nazevKnihy.toLowerCase();
		
		if(seznam.get(nazevLowercase) != null) {
			System.out.println("V knihovne jiz existuje kniha s timto nazvem");
			return false;
		}
		
		System.out.println("Kolik ma kniha autoru?");
		try {
			volba = sc.nextInt();
		}
		catch(java.util.InputMismatchException e) {
			System.out.println("Neplatna volba");
			volba = 999;
			sc.next();
			return false;
		}
		String[] autori = new String[volba];
		for(int i = 1; i <= volba; i++) {
			System.out.println("Zadejte jmeno autora cislo " + i);
			autori[i-1] = sc.next();
		}
		
		System.out.println("V jakem roce byla kniha vydana?");
		try {
			volba = sc.nextInt();
		}
		catch(java.util.InputMismatchException e) {
			System.out.println("Neplatna volba");
			volba = 999;
			sc.next();
			return false;
		}
		int rokVydani = volba;
		
		if(jeRoman) {
			System.out.print("Jaky je zanr romanu?");
			System.out.println();
			int i = 0;
			for(Roman.Zanr zanr : Roman.Zanr.values()) {
				System.out.print(i+"...");
				System.out.println(zanr);
				i++;
			}
			
			try {
				volba = sc.nextInt();
			}
			catch(java.util.InputMismatchException e) {
				System.out.println("Neplatna volba");
				volba = 999;
				sc.next();
				return false;
			}
			
			Roman roman;
			switch(volba) {
				case 0:
					roman = new Roman(nazevKnihy, autori, rokVydani, false, Roman.Zanr.THRILLER);
					seznam.put(nazevKnihy.toLowerCase(), roman);
					break;
				
				case 1:
					roman = new Roman(nazevKnihy, autori, rokVydani, false, Roman.Zanr.FANTASY);
					seznam.put(nazevKnihy.toLowerCase(), roman);
					break;
					
				case 2:
					roman = new Roman(nazevKnihy, autori, rokVydani, false, Roman.Zanr.SCIFI);
					seznam.put(nazevKnihy.toLowerCase(), roman);
					break;
					
				case 3:
					roman = new Roman(nazevKnihy, autori, rokVydani, false, Roman.Zanr.KOMEDIE);
					seznam.put(nazevKnihy.toLowerCase(), roman);
					break;
					
				case 4:
					roman = new Roman(nazevKnihy, autori, rokVydani, false, Roman.Zanr.HISTORIE);
					seznam.put(nazevKnihy.toLowerCase(), roman);
					break;
					
				default:
					System.out.println("Neplatna volba");
					break;
			}
		}
		else {			
			System.out.println("Pro jaky rocnik je ucebnice urcena?");
			try {
				volba = sc.nextInt();
			}
			catch(java.util.InputMismatchException e) {
				System.out.println("Neplatna volba");
				volba = 999;
				sc.next();
				return false;
			}
			int rocnik = volba;
			
			Ucebnice ucebnice = new Ucebnice(nazevKnihy, autori, rokVydani, false, rocnik);
			seznam.put(nazevKnihy.toLowerCase(), ucebnice);
		}
	
		return true;
	}

	public boolean upravKnihu() {
		System.out.println("Zadejte nazev knihy k uprave");
		String nazev = sc.next().toLowerCase();
		
		if(seznam.get(nazev) == null) {
			System.out.println("V knihovne neni zadna kniha s timto nazvem");
			return false;
		}
		
		System.out.println("Jaky parametr si prejete upravit?");
		System.out.println("0...Autora/autory knihy");
		System.out.println("1...Rok vydani");
		System.out.println("2...Stav dostupnosti");
		
		try {
			volba = sc.nextInt();
		}
		catch(java.util.InputMismatchException e) {
			System.out.println("Neplatna volba");
			volba = 999;
			sc.next();
			return false;
		}
		
		switch(volba) {
			case 0:
				System.out.println("Kniha ma aktualne " + seznam.get(nazev).getAutori().length + " autoru");
				System.out.print("Jsou jimi tito autori: ");
				String[] autori = seznam.get(nazev).getAutori();
				for(int i = 0; i < autori.length-1; i++) {
					String autor = autori[i];
					System.out.print(autor + ", ");
				}
				System.out.print(autori[autori.length-1]);
				System.out.println();
				
				System.out.println("Kolik autoru ma nove kniha mit?");
				try {
					volba = sc.nextInt();
				}
				catch(java.util.InputMismatchException e) {
					System.out.println("Neplatna volba");
					volba = 999;
					sc.next();
					return false;
				}
				String[] autoriNovy = new String[volba];
				for(int i = 1; i <= volba; i++) {
					System.out.println("Zadejte jmeno autora cislo " + i);
					autoriNovy[i-1] = sc.next();
				}
				
				seznam.get(nazev).setAutori(autoriNovy);
				
				break;
				
			case 1:
				System.out.println("Aktualni rok vydani je " + seznam.get(nazev).getRokVydani());
				System.out.println("Zadejte novy rok vydani: ");
				try {
					volba = sc.nextInt();
				}
				catch(java.util.InputMismatchException e) {
					System.out.println("Neplatna volba");
					volba = 999;
					sc.next();
					return false;
				}
				seznam.get(nazev).setRokVydani(volba);
				
				break;
				
			case 2:
				if(seznam.get(nazev).isVypujceno()) {
					System.out.println("Kniha je aktualne vypujcena");
					System.out.println("Prejete si zmenit jeji status na dostupnou? (y/n)");
					volbaString = sc.next().toLowerCase();
					
					if(volbaString.equals("y")) {
						seznam.get(nazev).setVypujceno(false);
					}
					else if (volbaString.equals("n")){
						seznam.get(nazev).setVypujceno(true);
					}
					else {
						System.out.println("Neplatna volba");
						return false;
					}
				}
				else {
					System.out.println("Kniha je aktualne k dispozici");
					System.out.println("Prejete si zmenit jeji status na vypujcenou? (y/n)");
					volbaString = sc.next().toLowerCase();
					
					if(volbaString.equals("y")) {
						seznam.get(nazev).setVypujceno(true);
					}
					else if (volbaString.equals("n")){
						seznam.get(nazev).setVypujceno(false);
					}
					else {
						System.out.println("Neplatna volba");
						return false;
					}
				}
				
				break;
				
			default:
				System.out.println("Neplatna volba");
				return false;
		}
		
		
		return true;
	}
	
	public boolean smazKnihu() {
		System.out.println("Zadejte nazev knihy ke smazani");
		String nazev = sc.next().toLowerCase();
		
		if(seznam.get(nazev) == null) {
			System.out.println("V knihovne neni zadna kniha s timto nazvem");
			return false;
		}
		
		System.out.println("Opravdu si prejete vymazat knihu " + seznam.get(nazev).getNazev() + "? (y/n)");
		volbaString = sc.next().toLowerCase();
		
		if(volbaString.equals("y")) {
			seznam.remove(nazev);
		}
		else {
			return false;
		}
		
		return true;
	}
	
	public boolean upravStavVypujceni() {
		System.out.println("Zadejte nazev knihy k uprave stavu dostupnosti");
		String nazev = sc.next().toLowerCase();
		
		if(seznam.get(nazev) == null) {
			System.out.println("V knihovne neni zadna kniha s timto nazvem");
			return false;
		}
		
		if(seznam.get(nazev).isVypujceno()) {
			System.out.println("Kniha je aktualne vypujcena");
			System.out.println("Prejete si zmenit jeji status na dostupnou (vracenou)? (y/n)");
			volbaString = sc.next().toLowerCase();
			
			if(volbaString.equals("y")) {
				seznam.get(nazev).setVypujceno(false);
			}
			else if (volbaString.equals("n")){
				seznam.get(nazev).setVypujceno(true);
			}
			else {
				System.out.println("Neplatna volba");
				return false;
			}
		}
		else {
			System.out.println("Kniha je aktualne k dispozici");
			System.out.println("Prejete si zmenit jeji status na vypujcenou? (y/n)");
			volbaString = sc.next().toLowerCase();
			
			if(volbaString.equals("y")) {
				seznam.get(nazev).setVypujceno(true);
			}
			else if (volbaString.equals("n")){
				seznam.get(nazev).setVypujceno(false);
			}
			else {
				System.out.println("Neplatna volba");
				return false;
			}
		}
		
		return true;
	}
	
	public boolean vypisKnihy() {	
		if(seznam.isEmpty()) {
			return false;
		}
		
		System.out.println("Vypis knih");
		int i = 1;
		for(Map.Entry<String, Kniha> kniha : seznam.entrySet()) {
			System.out.print(i + "...");
			
			System.out.print(kniha.getValue().getNazev());
			System.out.print(", ");
			
			String[] autori = kniha.getValue().getAutori();
			for(String autor : autori) {
				System.out.print(autor + ", ");
			}
			
			if(kniha.getValue() instanceof Roman) {
				Roman roman = (Roman) kniha.getValue();
				System.out.print(roman.getZanr());
			}
			else {
				Ucebnice ucebnice = (Ucebnice) kniha.getValue();
				System.out.print(ucebnice.getVhodneProRocnik() + ". ročník");
			}
			System.out.print(", ");
			
			
			System.out.print(kniha.getValue().getRokVydani());
			System.out.print(", ");
			
			if(kniha.getValue().isVypujceno()) {
				System.out.println("vypujceno");
			}
			else {
				System.out.println("nevypujceno");
			}
			
			i++;
			
		}

		return true;
	}
	
	public boolean vyhledaniKnihy() {
		System.out.println("Zadejte nazev knihy k vyhledani");
		String nazev = sc.next().toLowerCase();
		
		if(seznam.get(nazev) == null) {
			System.out.println("V knihovne neni zadna kniha s timto nazvem");
			return false;
		}
		
		Kniha kniha = seznam.get(nazev);
		
		System.out.print(kniha.getNazev());
		System.out.print(", ");
		
		String[] autori = kniha.getAutori();
		for(String autor : autori) {
			System.out.print(autor + ", ");
		}
		
		if(kniha instanceof Roman) {
			Roman roman = (Roman) kniha;
			System.out.print(roman.getZanr());
		}
		else {
			Ucebnice ucebnice = (Ucebnice) kniha;
			System.out.print(ucebnice.getVhodneProRocnik() + ". ročník");
		}
		System.out.print(", ");
		
		
		System.out.print(kniha.getRokVydani());
		System.out.print(", ");
		
		if(kniha.isVypujceno()) {
			System.out.println("vypujceno");
		}
		else {
			System.out.println("nevypujceno");
		}
		
		return true;
	}
	
	public boolean vypisKnihyAutora() {
		System.out.println("Zadejte nazev autora, jehoz knihy si prejete vyhledat");
		String autor = sc.next().toLowerCase();
		
		Map<String, Integer> seznamKnihAutora = new TreeMap<>();
		boolean existujeKnihaAutora = false;
		
		for(Map.Entry<String, Kniha> kniha : seznam.entrySet()) {		
			String[] autori = kniha.getValue().getAutori();
			for(String aktualniAutor : autori) {
				if(aktualniAutor.toLowerCase().equals(autor)) {
					existujeKnihaAutora = true;
					seznamKnihAutora.put(kniha.getKey(), kniha.getValue().getRokVydani());
				}
			}
		}
		
		
		List<Map.Entry<String, Integer>> serazenySeznam = new ArrayList<>(seznamKnihAutora.entrySet());
		
		Collections.sort(serazenySeznam, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
                return a.getValue().compareTo(b.getValue());
            }
        });
		
		
		int i = 1;
		System.out.println("Vypis knih autora " + autor);
		for (Map.Entry<String, Integer> aktualniKniha : serazenySeznam) {
			System.out.print(i + "...");
			
			System.out.print(seznam.get(aktualniKniha.getKey()).getNazev());
			System.out.print(", ");
			
			String[] autori = seznam.get(aktualniKniha.getKey()).getAutori();
			for(String aktualniAutor : autori) {
				System.out.print(aktualniAutor + ", ");
			}
			
			if(seznam.get(aktualniKniha.getKey()) instanceof Roman) {
				Roman roman = (Roman) seznam.get(aktualniKniha.getKey());
				System.out.print(roman.getZanr());
			}
			else {
				Ucebnice ucebnice = (Ucebnice) seznam.get(aktualniKniha.getKey());
				System.out.print(ucebnice.getVhodneProRocnik() + ". ročník");
			}
			System.out.print(", ");
			
			System.out.print(seznam.get(aktualniKniha.getKey()).getRokVydani());
			System.out.print(", ");
			
			if(seznam.get(aktualniKniha.getKey()).isVypujceno()) {
				System.out.println("vypujceno");
			}
			else {
				System.out.println("nevypujceno");
			}
			
			i++;
        }

		
		if(existujeKnihaAutora) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean vypisKnihyDleZanru() {
		System.out.print("Knihy jakeho zanru si prejete vypsat?");
		System.out.println();
		int i = 0;
		for(Roman.Zanr zanr : Roman.Zanr.values()) {
			System.out.print(i+"...");
			System.out.println(zanr);
			i++;
		}
		
		try {
			volba = sc.nextInt();
		}
		catch(java.util.InputMismatchException e) {
			System.out.println("Neplatna volba");
			volba = 999;
			sc.next();
			return false;
		}
		
		boolean existujeKnihaDanehoZanru = false;
		
		switch(volba) {
			case 0:
				System.out.println("Vypis knih zanru THRILLER:");
				i = 1;
				for(Map.Entry<String, Kniha> kniha : seznam.entrySet()) {
					if(kniha.getValue() instanceof Roman) {
						Roman roman = (Roman) kniha.getValue();
						if(roman.getZanr() == Roman.Zanr.THRILLER) {
							existujeKnihaDanehoZanru = true;
							System.out.print(i + "...");
							
							System.out.print(kniha.getValue().getNazev());
							System.out.print(", ");
							
							String[] autori = kniha.getValue().getAutori();
							for(String autor : autori) {
								System.out.print(autor + ", ");
							}					
							
							System.out.print(kniha.getValue().getRokVydani());
							System.out.print(", ");
							
							if(kniha.getValue().isVypujceno()) {
								System.out.println("vypujceno");
							}
							else {
								System.out.println("nevypujceno");
							}
							
							i++;
						}
						
						
					}
					
				}
				
				break;
				
			case 1:
				System.out.println("Vypis knih zanru FANTASY:");
				i = 1;
				for(Map.Entry<String, Kniha> kniha : seznam.entrySet()) {
					if(kniha.getValue() instanceof Roman) {
						Roman roman = (Roman) kniha.getValue();
						if(roman.getZanr() == Roman.Zanr.FANTASY) {
							existujeKnihaDanehoZanru = true;
							System.out.print(i + "...");
							
							System.out.print(kniha.getValue().getNazev());
							System.out.print(", ");
							
							String[] autori = kniha.getValue().getAutori();
							for(String autor : autori) {
								System.out.print(autor + ", ");
							}					
							
							System.out.print(kniha.getValue().getRokVydani());
							System.out.print(", ");
							
							if(kniha.getValue().isVypujceno()) {
								System.out.println("vypujceno");
							}
							else {
								System.out.println("nevypujceno");
							}
							
							i++;
						}
						
						
					}
					
				}
				
				break;
				
			case 2:
				System.out.println("Vypis knih zanru SCIFI:");
				i = 1;
				for(Map.Entry<String, Kniha> kniha : seznam.entrySet()) {
					if(kniha.getValue() instanceof Roman) {
						Roman roman = (Roman) kniha.getValue();
						if(roman.getZanr() == Roman.Zanr.SCIFI) {
							existujeKnihaDanehoZanru = true;
							System.out.print(i + "...");
							
							System.out.print(kniha.getValue().getNazev());
							System.out.print(", ");
							
							String[] autori = kniha.getValue().getAutori();
							for(String autor : autori) {
								System.out.print(autor + ", ");
							}					
							
							System.out.print(kniha.getValue().getRokVydani());
							System.out.print(", ");
							
							if(kniha.getValue().isVypujceno()) {
								System.out.println("vypujceno");
							}
							else {
								System.out.println("nevypujceno");
							}
							
							i++;
						}
						
						
					}
					
				}
				
				break;
				
			case 3:
				System.out.println("Vypis knih zanru KOMEDIE:");
				i = 1;
				for(Map.Entry<String, Kniha> kniha : seznam.entrySet()) {
					if(kniha.getValue() instanceof Roman) {
						Roman roman = (Roman) kniha.getValue();
						if(roman.getZanr() == Roman.Zanr.KOMEDIE) {
							existujeKnihaDanehoZanru = true;
							System.out.print(i + "...");
							
							System.out.print(kniha.getValue().getNazev());
							System.out.print(", ");
							
							String[] autori = kniha.getValue().getAutori();
							for(String autor : autori) {
								System.out.print(autor + ", ");
							}					
							
							System.out.print(kniha.getValue().getRokVydani());
							System.out.print(", ");
							
							if(kniha.getValue().isVypujceno()) {
								System.out.println("vypujceno");
							}
							else {
								System.out.println("nevypujceno");
							}
							
							i++;
						}
						
						
					}
					
				}
				
				break;
				
			case 4:
				System.out.println("Vypis knih zanru HISTORIE:");
				i = 1;
				for(Map.Entry<String, Kniha> kniha : seznam.entrySet()) {
					if(kniha.getValue() instanceof Roman) {
						Roman roman = (Roman) kniha.getValue();
						if(roman.getZanr() == Roman.Zanr.HISTORIE) {
							existujeKnihaDanehoZanru = true;
							System.out.print(i + "...");
							
							System.out.print(kniha.getValue().getNazev());
							System.out.print(", ");
							
							String[] autori = kniha.getValue().getAutori();
							for(String autor : autori) {
								System.out.print(autor + ", ");
							}					
							
							System.out.print(kniha.getValue().getRokVydani());
							System.out.print(", ");
							
							if(kniha.getValue().isVypujceno()) {
								System.out.println("vypujceno");
							}
							else {
								System.out.println("nevypujceno");
							}
							
							i++;
						}
						
						
					}
					
				}
				
				break;
				
			default:
				System.out.println("Neplatna volba");
				return false;
		}
		
		
		if(existujeKnihaDanehoZanru) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean vypisVypujceneKnihy() {
		if(seznam.isEmpty()) {
			return false;
		}
		
		boolean existujeVypujcena = false;
		System.out.println("Vypis vypujcenych knih");
		int i = 1;
		for(Map.Entry<String, Kniha> kniha : seznam.entrySet()) {
			if(kniha.getValue().isVypujceno()) {
				existujeVypujcena = true;
				System.out.print(i + "...");
				
				System.out.print(kniha.getValue().getNazev());
				System.out.print(", ");
				
				String[] autori = kniha.getValue().getAutori();
				for(String autor : autori) {
					System.out.print(autor + ", ");
				}
				
				if(kniha.getValue() instanceof Roman) {
					Roman roman = (Roman) kniha.getValue();
					System.out.print(roman.getZanr());
				}
				else {
					Ucebnice ucebnice = (Ucebnice) kniha.getValue();
					System.out.print(ucebnice.getVhodneProRocnik() + ". ročník");
				}
				System.out.print(", ");
				
				
				System.out.print(kniha.getValue().getRokVydani());
				System.out.print(", ");
				
				if(kniha.getValue().isVypujceno()) {
					System.out.println("vypujceno");
				}
				else {
					System.out.println("nevypujceno");
				}
				
				i++;
			}
			
		}

		if(existujeVypujcena) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean ulozKnihuDoSouboru() {
		System.out.println("Zadejte nazev knihy k vyhledani");
		String nazev = sc.next().toLowerCase();
		
		if(seznam.get(nazev) == null) {
			System.out.println("V knihovne neni zadna kniha s timto nazvem");
			return false;
		}
		
		Kniha kniha = seznam.get(nazev);
		
		try {
			File out = new File(nazev + ".txt");
		    if (out.createNewFile()) {
		    	;
		    } else {
		    	System.out.println("Soubor jiz existuje. Prejete si ho prespsat? (y/n)");
				volbaString = sc.next().toLowerCase();
				
				if(volbaString.equals("y")) {
					;
				}
				else {
					return false;
				}
		    }
		} catch (IOException e) {
			System.out.println("Pri vytvareni souboru doslo k chybe");
		    return false;
		}
		
		try {
			FileWriter writer = new FileWriter(nazev + ".txt", false);
		    writer.write(kniha.getNazev());
		    writer.write(System.lineSeparator());
		    
		    writer.write(String.valueOf(kniha.getAutori().length));
		    writer.write(System.lineSeparator());
		    
		    String[] autori = kniha.getAutori();
			for(String autor : autori) {
				writer.write(autor);
			    writer.write(System.lineSeparator());
			}
			
			if(kniha instanceof Roman) {
				Roman roman = (Roman) kniha;
				writer.write("roman");
				writer.write(System.lineSeparator());
				writer.write(roman.getZanr().toString());
				writer.write(System.lineSeparator());
			}
			else {
				Ucebnice ucebnice = (Ucebnice) kniha;
				writer.write("ucebnice");
				writer.write(System.lineSeparator());
				writer.write(String.valueOf(ucebnice.getVhodneProRocnik()));
				writer.write(System.lineSeparator());
			}
			
			writer.write(String.valueOf(kniha.getRokVydani()));
		    writer.write(System.lineSeparator());
			
			if(kniha.isVypujceno()) {
				writer.write("vypujceno");
			}
			else {
				writer.write("nevypujceno");
			}
		      
		    writer.close();
		} catch (IOException e) {
		    System.out.println("Pri psani do souboru doslo k chybe");
		    return false;
		}
		
		return true;
	}
	
	public boolean nactiKnihuZeSouboru() {
		System.out.println("Zadejte nazev knihy k nacteni");
		String nazevKNacteni = sc.next().toLowerCase();
		
		if(seznam.get(nazevKNacteni) != null) {
			System.out.println("V knihovne jiz existuje kniha s timto nazvem");
			return false;
		}
		
		try {
			File in = new File(nazevKNacteni + ".txt");
			Scanner scanner = new Scanner(in);
			
			String nazevKnihy = scanner.next();
			
			int pocetAutoru = scanner.nextInt();
			
			String[] autori = new String[pocetAutoru];
			for(int i = 0; i < pocetAutoru; i++) {
				autori[i] = scanner.next();
			}
			
			String zanr = "";
			int rocnik = 0;
			String typ = scanner.next();
			if(typ.equals("roman")) {
				zanr = scanner.next();
			} else if (typ.equals("ucebnice")) {
				rocnik = scanner.nextInt();
			} else {
				return false;
			}
			
			int rokVydani = scanner.nextInt();
			
			String stavVypujceni = scanner.next();
			boolean vypujceno = false;
			if(stavVypujceni.equals("vypujceno")) {
				vypujceno = true;
			} else if (stavVypujceni.equals("nevypujceno")) {
				vypujceno = false;
			} else {
				return false;
			}
			
			scanner.close();
			
			if(typ.equals("roman")) {
				Roman roman;
				if(zanr.equals("THRILLER")){
					roman = new Roman(nazevKnihy, autori, rokVydani, false, Roman.Zanr.THRILLER);
					seznam.put(nazevKnihy.toLowerCase(), roman);
				} else if(zanr.equals("FANTASY")) {
					roman = new Roman(nazevKnihy, autori, rokVydani, false, Roman.Zanr.FANTASY);
					seznam.put(nazevKnihy.toLowerCase(), roman);
				} else if(zanr.equals("SCIFI")) {
					roman = new Roman(nazevKnihy, autori, rokVydani, false, Roman.Zanr.SCIFI);
					seznam.put(nazevKnihy.toLowerCase(), roman);
				} else if(zanr.equals("KOMEDIE")) {
					roman = new Roman(nazevKnihy, autori, rokVydani, false, Roman.Zanr.KOMEDIE);
					seznam.put(nazevKnihy.toLowerCase(), roman);
				} else if(zanr.equals("HISTORIE")) {
					roman = new Roman(nazevKnihy, autori, rokVydani, false, Roman.Zanr.HISTORIE);
					seznam.put(nazevKnihy.toLowerCase(), roman);
				} else {
					return false;
				}
				
				
			} else if (typ.equals("ucebnice")) {
				Ucebnice ucebnice = new Ucebnice(nazevKnihy, autori, rokVydani, vypujceno, rocnik);
				seznam.put(nazevKnihy.toLowerCase(), ucebnice);
			} else {
				return false;
			}
			
		}
		catch (FileNotFoundException e) {
			System.out.println("Soubor s timto nazvem neexistuje");
			return false;
		}
		catch (InputMismatchException e) {
			System.out.println("Chyba ve strukture souboru");
			return false;
		}
		
		return true;
	}
}
