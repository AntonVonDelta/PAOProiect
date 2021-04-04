package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class Nota{
	public Date data;
	public int nota;
	public Materie materie;
	public Nota(Date data,int nota,Materie materia) {
		this.data=data;
		this.nota=nota;
		this.materie=materia;
	}
}
class Absenta{
	public Date data;
	public Materie materie;
	public Absenta(Date data,Materie materia) {
		this.data=data;
		this.materie=materia;
	}
}

public class Situatie {
	private Student student;
	private List<Nota> note=new ArrayList<Nota>();
	private List<Absenta> absente=new ArrayList<Absenta>();
	
	public Situatie(Student student) {
		this.student=student;
	}
	
	public void noteaza(Date data,int nota,Materie materia) {
		note.add(new Nota(data,nota,materia));
	}
	
	public void noteaza(Date data,Materie materia) {
		absente.add(new Absenta(data,materia));
	}
	
	public void motiveaza(Date data,Materie materia){
		Predicate<Absenta> pred=absenta -> !(absenta.data.equals(data) && absenta.materie.equals(materia));
		
		absente=absente.stream().filter(pred).collect(Collectors.toList());
	}
}
