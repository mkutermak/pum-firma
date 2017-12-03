import java.util.ArrayList;
import java.util.ListIterator;

class Magazyn {

	private ArrayList _towary;
	
	public Magazyn() {
		_towary = new ArrayList();
	}

	public boolean towarIstnieje(String nazwa) {
		ListIterator iterator = _towary.listIterator();
		while (iterator.hasNext()) {
		      Towar biezacy = (Towar) iterator.next();
		      if (nazwa.equals(biezacy.nazwa()))
			      return true;
		}
		return false;
	}
	
	public void dodaj(Towar towar) {
		if (towarIstnieje(towar.nazwa())) return;
		_towary.add(towar);
	}

	public Towar znajdz(String nazwa) {
		ListIterator iterator = _towary.listIterator();
		while (iterator.hasNext()) {
			Towar biezacy = (Towar) iterator.next();
			if (nazwa.equals(biezacy.nazwa()))
				return new Towar(biezacy);
		}
		return null;
	}
}

