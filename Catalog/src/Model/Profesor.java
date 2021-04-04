package Model;

import java.util.Date;

import Interfaces.Nameable;

public class Profesor implements Nameable{
	private String nume;
	private Materie materie_predata;
	
	
	public void noteazaStudent(int semestru,Student student,int nota) {
		student.getSchoolClass().getSituatie(semestru,this,student).noteaza(new Date(), nota);
	}
	public void absenteazaStudent(int semestru,Clasa clasa,Student student) {
		clasa.getSituatie(semestru,this,student).absenteaza(new Date());
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
