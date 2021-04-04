package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Catalog {
	public static List<Materie> materii=new ArrayList<Materie>() {{
		add(new Materie("Romana"));
		add(new Materie("Matematica"));
		add(new Materie("Fizica"));
		add(new Materie("Engleza"));
		add(new Materie("Franceza"));
	}};
	
	private HashMap<Student, HashMap<Materie,Situatie>> sem1=new HashMap<Student,HashMap<Materie,Situatie>>();
	private HashMap<Student, HashMap<Materie,Situatie>> sem2=new HashMap<Student,HashMap<Materie,Situatie>>();

	public void initSituatie(Student student) {
		HashMap<Materie,Situatie> temp1=new HashMap<Materie,Situatie>();
		materii.stream().forEach(materie->temp1.put(materie, new Situatie(student)));
		sem1.put(student, temp1);
	
		HashMap<Materie,Situatie> temp2=new HashMap<Materie,Situatie>();
		materii.stream().forEach(materie->temp2.put(materie, new Situatie(student)));
		sem1.put(student, temp2);
	}
	
	
	public HashMap<Student, HashMap<Materie,Situatie>> getSem1() {
		return sem1;
	}
	
	public HashMap<Student, HashMap<Materie,Situatie>> getSem2() {
		return sem2;
	}
}
