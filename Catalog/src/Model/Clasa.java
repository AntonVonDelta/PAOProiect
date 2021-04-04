package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Interfaces.Nameable;

public class Clasa implements Nameable{
	protected String nume;
	protected List<Student> studenti=new ArrayList<Student>();
	protected Catalog catalog=new Catalog();
	protected Diriginte diriginte;
	protected HashMap<Profesor,Materie> ore=new HashMap<Profesor,Materie>();
	
	public Clasa(Diriginte diriginte) {
		this.diriginte=diriginte;
	}
	
	
	public void addStudent(Student student) {
		studenti.add(student);
	}
	
	public Diriginte getDiriginte(Diriginte diriginte) {
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
