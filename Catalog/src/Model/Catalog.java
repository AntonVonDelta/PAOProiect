package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Catalog {
	public static final List<Materie> materii_default=new ArrayList<Materie>() {{
		add(new Materie("Romana"));
		add(new Materie("Matematica"));
		add(new Materie("Fizica"));
		add(new Materie("Engleza"));
		add(new Materie("Franceza"));
	}};
	
	private static List<Materie> materii=new ArrayList<Materie>();
	private HashMap<Student, HashMap<Materie,Situatie>> sem1=new HashMap<Student,HashMap<Materie,Situatie>>();

	public void setMaterii(List<Materie> materii) {
		this.materii=materii;
	}
	
	public void initSituatie(Student student) {
		HashMap<Materie,Situatie> temp1=new HashMap<Materie,Situatie>();
		materii.stream().forEach(materie->temp1.put(materie, new Situatie(student)));
		sem1.put(student, temp1);
	}
	
	public void initSituatie(Student student,Materie materie) {
		HashMap<Materie,Situatie> temp1=new HashMap<Materie,Situatie>();
		temp1.put(materie, new Situatie(student));
		sem1.put(student, temp1);
	}
	
	public HashMap<Student, HashMap<Materie,Situatie>> getSem() {
		return sem1;
	}
}
