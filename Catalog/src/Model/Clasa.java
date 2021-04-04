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
	
	protected Situatie getSituatie(int semestru,Profesor profesor,Student student) {
		if(semestru==1) {
			HashMap<Materie,Situatie> result= catalog.getSem1().get(student);
			return result.get(profesor.getMaterie());
		}else {
			HashMap<Materie,Situatie> result= catalog.getSem2().get(student);
			return result.get(profesor.getMaterie());	
		}
	}
	
	public void addStudent(Student student) {
		studenti.add(student);
		student.setSchoolClass(this);
		catalog.initSituatie(student);
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
