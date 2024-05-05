package knihovna;

class Kniha {
	private String nazev;
	private String[] autori;
	private int rokVydani;
	private boolean vypujceno;
	
	Kniha(String nazev, String[] autori, int rokVydani, boolean vypujceno) {
		super();
		this.nazev = nazev;
		this.autori = autori;
		this.rokVydani = rokVydani;
		this.vypujceno = vypujceno;
	}
	
	boolean upravKnihu() {
		return false;
	}
	
	boolean oznacKnihu() {
		return false;
	}
	
	boolean ulozKnihuDoSouboru() {
		return false;
	}
	
	boolean nactiKnihuZeSouboru() {
		return false;
	}

	String getNazev() {
		return nazev;
	}

	String[] getAutori() {
		return autori;
	}

	void setAutori(String[] autori) {
		this.autori = autori;
	}

	int getRokVydani() {
		return rokVydani;
	}
	
	void setRokVydani(int rokVydani) {
		this.rokVydani = rokVydani;
	}

	boolean isVypujceno() {
		return vypujceno;
	}

	void setVypujceno(boolean vypujceno) {
		this.vypujceno = vypujceno;
	}
}
