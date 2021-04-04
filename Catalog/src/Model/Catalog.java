package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Catalog {
	private HashMap<Student, HashMap<Materie,Situatie>> sem1=new HashMap<Student,HashMap<Materie,Situatie>>();
	private HashMap<Student, HashMap<Materie,Situatie>> sem2=new HashMap<Student,HashMap<Materie,Situatie>>();
	
	public HashMap<Student, HashMap<Materie,Situatie>> getSem1() {
		return sem1;
	}
	
	public HashMap<Student, HashMap<Materie,Situatie>> getSem2() {
		return sem2;
	}
}
