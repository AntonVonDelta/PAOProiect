package Model;

import Interfaces.Nameable;

public class Student implements Nameable {
	protected String nume;
	private Clasa clasa;	// a private reference to the class
	
	public Clasa getSchoolClass() {
		return clasa;
	}
	public void setSchoolClass(Clasa clasa) {
		this.clasa=clasa;
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
