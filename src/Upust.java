class Upust {

	private Klient _klient;
	private Towar _towar;
	private double _procent;

	public Upust(Klient klient, Towar towar, double procent) {
		_klient = klient;
		_towar = towar;
		_procent = procent;
	}

	public Upust(Upust upust) {
		_klient = new Klient(upust.nazwisko());
		_towar = new Towar(upust.nazwa(),upust.cena());
		_procent = upust.procent();
	}
	
	public String nazwisko() {
		return _klient.nazwisko();
	}

	public String nazwa() {
		return _towar.nazwa();
	}

	public double procent() {
		return _procent;
	}

	private double cena() {
		return _towar.cena();
	}
}

