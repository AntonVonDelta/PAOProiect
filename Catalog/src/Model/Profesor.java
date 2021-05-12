package Model;

import java.util.Date;

import Interfaces.Nameable;

public class Profesor implements Nameable{
	private String nume;
	private Materie materie_predata;
	
	public Profesor(String nume, Materie materie) {
		this.nume=nume;
		this.materie_predata=materie;
	}
	
	public void noteazaStudent(Student student,int nota) {
		student.getSchoolClass().getSituatie(this,student).noteaza(new Date(), nota);
	}
	public void absenteazaStudent(Student student) {
		student.getSchoolClass().getSituatie(this,student).absenteaza(new Date());
	}
	
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
