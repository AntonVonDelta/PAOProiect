package Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import Model.Clasa;
import Model.Diriginte;
import Model.Materie;
import Model.Profesor;
import Model.Student;

public class AppService {
	private static List<Clasa> clase=new ArrayList<Clasa>();
	private static List<Profesor> profesori=new ArrayList<Profesor>();
	private static List<Student> studenti=new ArrayList<Student>();
	private static List<Materie> materii=new ArrayList<Materie>();

	public AppService() {
		materii.add(new Materie("Romana"));
		materii.add(new Materie("Matematica"));
		materii.add(new Materie("Fizica"));
		materii.add(new Materie("Engleza"));
		materii.add(new Materie("Franceza"));
	}
	
	public void adaugaClasa(String clasa_numa,String diriginte_nume) {
		Clasa clasa=new Clasa(getDiriginte(diriginte_nume));
		clasa.setName(clasa_numa);
		clase.add(clasa);
	}
	public void adaugaProfesor(String nume) {
		Profesor prof=new Profesor();
		prof.setName(nume);
	}
	
	private Profesor getDiriginte(String diriginte) {
		Predicate<Profesor> pred=profesor->profesor.getName().equals(diriginte);
		return profesori.stream().filter(pred).findFirst().get();
	}
	
	private Clasa getClasa(String clasa_nume) {
		Predicate<Clasa> pred=clasa->clasa.getName().equals(clasa_nume);
		return clase.stream().filter(pred).findFirst().get();
	}
}
