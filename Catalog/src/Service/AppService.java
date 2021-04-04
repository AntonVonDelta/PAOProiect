package Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import Model.Catalog;
import Model.Clasa;
import Model.Diriginte;
import Model.Materie;
import Model.Profesor;
import Model.Student;

public class AppService {
	private static List<Clasa> clase=new ArrayList<Clasa>();
	private static List<Profesor> profesori=new ArrayList<Profesor>();
	private static List<Student> studenti=new ArrayList<Student>();
	private static List<Materie> materii=Catalog.materii;

	public AppService() {

	}
	
	public void adaugaClasa(String clasa_numa,String diriginte_nume) {
		Clasa clasa=new Clasa(getProfesor(diriginte_nume));
		clasa.setName(clasa_numa);
		clase.add(clasa);
	}
	
	public void adaugaProfesor(String nume,String materie) {
		Profesor prof=new Profesor();
		prof.setMaterie(getMaterie(materie));
		prof.setName(nume);
		profesori.add(prof);
	}
	
	public void adaugaStudent(String student_nume,String clasa_nume) {
		Clasa clasa=getClasa(clasa_nume);
		Student student=new Student();
		student.setName(student_nume);
		
		clasa.addStudent(student);
		studenti.add(student);
	}
	
	
	public Profesor getProfesor(String diriginte) {
		Predicate<Profesor> pred=profesor->profesor.getName().equals(diriginte);
		return profesori.stream().filter(pred).findFirst().get();
	}
	
	public Clasa getClasa(String clasa_nume) {
		Predicate<Clasa> pred=clasa->clasa.getName().equals(clasa_nume);
		return clase.stream().filter(pred).findFirst().get();
	}
	
	public Materie getMaterie(String materie_nume) {
		Predicate<Materie> pred=materie->materie.getName().equals(materie_nume);
		return materii.stream().filter(pred).findFirst().get();
	}
	
	public Student getStudent(String student_nume) {
		Predicate<Student> pred=student->student.getName().equals(student_nume);
		return studenti.stream().filter(pred).findFirst().get();
	}
}
