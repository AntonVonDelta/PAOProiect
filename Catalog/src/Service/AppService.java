package Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import Model.Catalog;
import Model.Clasa;
import Model.Materie;
import Model.Profesor;
import Model.Student;

public class AppService {
	private static List<Clasa> clase=new ArrayList<Clasa>();
	private static List<Profesor> profesori=new ArrayList<Profesor>();
	private static List<Student> studenti=new ArrayList<Student>();
	private static List<Materie> materii=new ArrayList<Materie>();

	public AppService() {

	}
	
	public void adaugaMaterii(List<Materie> materii_in) throws Exception {
		materii.addAll(materii_in);
		for(Clasa clasa:clase) {
			clasa.setMaterii(materii);
		}
	}
	
	public void adaugaMaterie(String materie_nume) throws Exception {
		Materie materie=new Materie(materie_nume);
		materii.add(materie);
		
		for(Clasa clasa:clase) {
			clasa.setMaterii(materii);
		}
	}
	
	public void adaugaClasa(String clasa_nume,String diriginte_nume) throws Exception {
		Clasa clasa=new Clasa(getProfesor(diriginte_nume));
		clasa.setName(clasa_nume);
		clasa.setMaterii(materii);
		clase.add(clasa);
	}
	
	public void adaugaProfesor(String nume,String materie) throws Exception {
		Profesor prof=new Profesor(nume,getMaterie(materie));
		profesori.add(prof);
	}
	
	public void adaugaStudent(String student_nume,String clasa_nume) throws Exception {
		Clasa clasa=getClasa(clasa_nume);
		Student student=new Student();
		student.setName(student_nume);
		
		clasa.addStudent(student);
		studenti.add(student);
	}

	
	
	public Profesor getProfesor(String diriginte) throws Exception {
		Predicate<Profesor> pred=profesor->profesor.getName().equals(diriginte);
		Optional<Profesor> prof= profesori.stream().filter(pred).findFirst();
		
		if(prof.isEmpty()) throw new Exception("Profesorul "+diriginte+" nu exista");
		return prof.get();
	}
	
	public Clasa getClasa(String clasa_nume) throws Exception {
		Predicate<Clasa> pred=clasa->clasa.getName().equals(clasa_nume);
		Optional<Clasa> clasa= clase.stream().filter(pred).findFirst();
		
		if(clasa.isEmpty()) throw new Exception("Clasa "+clasa_nume+" nu exista");
		return clasa.get();
	}
	
	public Materie getMaterie(String materie_nume) throws Exception {
		Predicate<Materie> pred=materie->materie.getName().equals(materie_nume);
		Optional<Materie> materie= materii.stream().filter(pred).findFirst();
		
		if(materie.isEmpty()) throw new Exception("Materia "+materie_nume+" nu exista");
		return materie.get();
	}
	
	public Student getStudent(String student_nume) throws Exception {
		Predicate<Student> pred=student->student.getName().equals(student_nume);
		Optional<Student> student= studenti.stream().filter(pred).findFirst();
		
		if(student.isEmpty()) throw new Exception("Studentul "+student_nume+" nu exista");
		return student.get();
	}
	
	
	public List<Profesor> getProfesori(){
		return profesori;
	}
	public List<Materie> getMaterii(){
		return materii;
	}
	public List<Student> getStudenti(){
		// Sort to ease on the manual search
		studenti.sort( (Student a,Student b)->a.getName().compareTo(b.getName()) );
		return studenti;
	}
	public List<Clasa> getClase(){
		return clase;
	}

	// Checks for csv files and loads them
	public void loadSavedData() throws Exception {
		DataReader loader=DataReader.getInstance(this);
		loader.loadMaterii();
		loader.loadProfesori();
		loader.loadClase();
		loader.loadStudenti();
	}
}
