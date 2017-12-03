class Tranzakcja {

	private Klient _klient;
	private Towar _towar;
	private int _ilosc;

	public Tranzakcja(Klient klient, Towar towar, int ilosc) {
		_klient = klient;
		_towar = towar;
		_ilosc = ilosc;
	}

	public Tranzakcja(Tranzakcja tranzakcja) {
		_klient = new Klient(tranzakcja.nazwisko());
		_towar = new Towar(tranzakcja.nazwa(),tranzakcja.cena());
		_ilosc = tranzakcja.ilosc();
	}

	public String nazwisko() {
		return _klient.nazwisko();
	}

	public String nazwa() {
		return _towar.nazwa();
	}

	public double cena() {
		return _towar.cena();
	}

	public int ilosc() {
		return _ilosc;
	}

	public double lacznie() {
		return _ilosc * _towar.cena();
	}
	
}
