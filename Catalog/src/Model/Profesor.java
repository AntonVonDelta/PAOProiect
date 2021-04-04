package Model;

import Interfaces.Nameable;

public class Profesor implements Nameable{
	protected String nume;
	protected Materie materie_predata;
	
	public void setMaterie(Materie materie) {
		materie_predata=materie;
	}
	public Materie getMaterie() {
		return materie_predata;
	}
	
	
	@Override
	public void setName(String name) {
		nume=name;	
	}

	@Override
	public String getName() {
		return nume;
	}

}
