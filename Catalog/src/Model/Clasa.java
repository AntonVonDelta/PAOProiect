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
	
	public Clasa(Profesor diriginte) {
		this.diriginte=diriginte;
	}
	
	public Catalog getCatalog() {
		return catalog;
	}
	
	// Seteaza materiile predate in aceasta clasa
	public void setMaterii(List<Materie> materii) {
		catalog.setMaterii(materii);
	}
	
	// Metoda accesibila doar profesorului aka accesata din perspectiva lui
	protected Situatie getSituatie(Profesor profesor,Student student) {
		HashMap<Materie,Situatie> result= catalog.getSem().get(student);
		return result.get(profesor.getMaterie());
	}
	
	// Metoda folosita de persoane terte eg parinti
	public Situatie getSituatie(Materie materie,Student student) {
		HashMap<Materie,Situatie> result= catalog.getSem().get(student);
		return result.get(materie);
	}
	
	public void addStudent(Student student) {
		studenti.add(student);
		student.setSchoolClass(this);
		catalog.initSituatie(student);
	}
	public List<Student> getStudenti(){
		return studenti;
	}
	
	public Profesor getDiriginte() {
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
