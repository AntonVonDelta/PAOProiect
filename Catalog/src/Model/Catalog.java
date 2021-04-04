package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Catalog {
	private HashMap<Student, List<Situatie>> sem1=new HashMap<Student,List<Situatie>>();
	private HashMap<Student, List<Situatie>> sem2=new HashMap<Student,List<Situatie>>();
	
	public HashMap<Student, List<Situatie>> getSem1() {
		return sem1;
	}
	
	public HashMap<Student, List<Situatie>> getSem2() {
		return sem2;
	}
}
