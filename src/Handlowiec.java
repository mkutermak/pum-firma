import java.util.ArrayList;
import java.util.ListIterator;

class Handlowiec {
	
	private Magazyn _magazyn;
	private ArrayList _klienci, _tranzakcje, _upusty;

	public Handlowiec(Magazyn magazyn) {
		_magazyn = magazyn;
		_klienci = new ArrayList();
		_tranzakcje = new ArrayList();
		_upusty = new ArrayList();
	}

	public void dodajTowar(String nazwa, double cena) {
		Towar towar = new Towar(nazwa, cena);
		_magazyn.dodaj(towar);
	}

	private boolean istniejeKlient(String nazwisko) {
		ListIterator iterator = _klienci.listIterator();
		while (iterator.hasNext()) {
			Klient biezacy = (Klient) iterator.next();
			if (nazwisko.equals(biezacy.nazwisko()))
				return true;
		}
		return false;
	}
	
	private Klient znajdz(String nazwisko) {
		ListIterator iterator = _klienci.listIterator();
		while (iterator.hasNext()) {
			Klient biezacy = (Klient) iterator.next();
			if (nazwisko.equals(biezacy.nazwisko()))
				return new Klient(biezacy);
		}
		return null;
	}
	
	public void dodajKlienta(String nazwisko) {
		if (istniejeKlient(nazwisko)) return;
		Klient nowy = new Klient(nazwisko);
		_klienci.add(nowy);
	}

	public boolean dodajTranzakcje(String nazwisko, String nazwa, int ilosc) {
		if (!istniejeKlient(nazwisko) || !_magazyn.towarIstnieje(nazwa)) return false;
		_tranzakcje.add(new Tranzakcja(znajdz(nazwisko),_magazyn.znajdz(nazwa),ilosc));
		return true;
	}

	public boolean dodajUpust(String nazwisko, String nazwa, double procent) {
		if (!istniejeKlient(nazwisko) || !_magazyn.towarIstnieje(nazwa)) return false;
		_upusty.add(new Upust(znajdz(nazwisko),_magazyn.znajdz(nazwa),procent));
		return true;
	}

	public double faktura(String nazwisko, String nazwa) {
		ListIterator tranzakcja = _tranzakcje.listIterator();
		Tranzakcja szukana = null;
		while (tranzakcja.hasNext()) {
			Tranzakcja biezaca = (Tranzakcja) tranzakcja.next();
			if (nazwisko.equals(biezaca.nazwisko()) && nazwa.equals(biezaca.nazwa())) {
				szukana = biezaca;
				break;
			}
		}
		if (szukana == null) return 0.0;
		ListIterator upust = _upusty.listIterator();
		double procent = 0;
		boolean flag = false;
		while (upust.hasNext()) {
			Upust biezacy = (Upust) upust.next();
			if (nazwisko.equals(biezacy.nazwisko()) && nazwa.equals(biezacy.nazwa())) {
				procent += biezacy.procent();
				flag = true;
				if (procent > 0.15) {
					procent = 0.15;
					break;
				}
			}
		}
		double cena = szukana.lacznie();
		if ( !flag ) return cena;
		return Math.floor(cena * (1 - procent) * 100) / 100;
	}

	public void przekaz(Handlowiec handlowiec, String nazwisko) {
		handlowiec.dodajKlienta(nazwisko);
		ListIterator upust = _upusty.listIterator();
		while (upust.hasNext()) {
			Upust bierzacy = (Upust) upust.next();
			if (nazwisko.equals(bierzacy.nazwisko())) {
				ListIterator sprawdzacz = handlowiec._upusty.listIterator();
				boolean ok = false;
				while (sprawdzacz.hasNext()) {
					Upust szukany = (Upust) sprawdzacz.next();
					if (szukany == bierzacy) {
						ok = true;
						break;
					}
				}
				if ( !ok )
					handlowiec._upusty.add(bierzacy);
			}
		}

	}
}

