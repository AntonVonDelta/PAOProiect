package Model;

import Interfaces.Nameable;

public class Materie implements Nameable{
	protected String nume;
	
	@Override
	public void setName(String name) {
		nume=name;	
	}

	@Override
	public String getName() {
		return nume;
	}
}
