package knihovna;

class Roman extends Kniha {
	enum Zanr {
		THRILLER,
		FANTASY,
		SCIFI,
		KOMEDIE,
		HISTORIE
	}
	
	private Zanr zanr;
	
	Roman(String nazev, String[] autori, int rokVydani, boolean vypujceno, Zanr zanr) {
		super(nazev, autori, rokVydani, vypujceno);
		this.zanr = zanr;
	}

	Zanr getZanr() {
		return zanr;
	}
	
	
}
