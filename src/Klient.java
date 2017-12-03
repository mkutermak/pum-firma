class Klient {
	
	private String _nazwisko;

	public Klient(String nazwisko) {
		_nazwisko = nazwisko;
	}

	public Klient(Klient klient) {
		String nazwisko = klient.nazwisko();
		_nazwisko = new String(nazwisko);
	}
	
	public String nazwisko() {
		return _nazwisko;
	}
	
}

