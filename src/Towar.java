class Towar {

	private String _nazwa;
	private double _cena;

	public Towar(String nazwa, double cena) {
		_nazwa = nazwa;
		_cena = cena;
	}

	public Towar(Towar towar) {
		String nazwa = towar.nazwa();
		_nazwa = new String(nazwa);
		_cena = towar.cena();
	}

	public String nazwa() {
		return _nazwa;
	}

	public double cena() {
		return _cena;
	}

}
