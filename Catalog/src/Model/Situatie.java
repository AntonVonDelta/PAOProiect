package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Situatie {
	public class Nota{
		public Date data;
		public int nota;
		public Nota(Date data,int nota) {
			this.data=data;
			this.nota=nota;
		}
	}
	public class Absenta{
		public Date data;
		public Absenta(Date data) {
			this.data=data;
		}
	}
	
	private Student student;
	private List<Nota> note=new ArrayList<Nota>();
	private List<Absenta> absente=new ArrayList<Absenta>();
	
	public Situatie(Student student) {
		this.student=student;
	}
	
	public void noteaza(Date data,int nota) {
		note.add(new Nota(data,nota));
	}
	
	public void absenteaza(Date data) {
		absente.add(new Absenta(data));
	}
	
	public void motiveaza(Date data){
		Predicate<Absenta> pred=absenta -> !(absenta.data.equals(data));
		
		absente=absente.stream().filter(pred).collect(Collectors.toList());
	}
	
	public List<Nota> getNote(){
		return note;
	}
	
	public List<Absenta> getAbsente(){
		return absente;
	}
}
