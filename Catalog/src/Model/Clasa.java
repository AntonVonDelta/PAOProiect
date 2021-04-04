package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Interfaces.Nameable;

public class Clasa implements Nameable{
	protected String nume;
	protected List<Student> studenti=new ArrayList<Student>();
	protected Catalog catalog=new Catalog();
	protected Profesor diriginte;
	protected HashMap<Profesor,Materie> ore=new HashMap<Profesor,Materie>();
	
	public Clasa(Profesor diriginte) {
		this.diriginte=diriginte;
	}
	
	
	public void addStudent(Student student) {
		studenti.add(student);
	}
	
	public Profesor getDiriginte(Profesor diriginte) {
		return diriginte;
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
