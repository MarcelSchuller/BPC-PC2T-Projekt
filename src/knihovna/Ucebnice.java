package knihovna;

public class Ucebnice extends Kniha {
	private int vhodneProRocnik;
	
	Ucebnice(String nazev, String[] autori, int rokVydani, boolean vypujceno, int vhodneProRocnik) {
		super(nazev, autori, rokVydani, vypujceno);
		this.vhodneProRocnik = vhodneProRocnik;
	}

	int getVhodneProRocnik() {
		return vhodneProRocnik;
	}

	
}
