package Service;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Model.Clasa;
import Model.Materie;
import Model.Profesor;
import Model.Situatie;
import Model.Situatie.Nota;
import Model.Student;
import Program.Main;

public class DataWriter {
	private static DataWriter instance;
	private AppService service;
	
	private DataWriter() {}
	
	public static DataWriter getInstance(AppService service) {
		if(instance==null) {
			instance=new DataWriter();
		}
		instance.service=service;
		return instance;
	}
	
	// WARNING: erasure of method writeCSV(List<Profesor>) is the same as another method in type DataWriter. This happens because the compiler only see List argument and does not differentiate
	// SOLUTION: Don't overload but change member name
	
	public void writeMaterii(List<Materie> materii) {
		StringBuilder result = new StringBuilder();
		
		try(PrintWriter printWriter = new PrintWriter("materii.csv")){
			
			// Build the header
			addRecord(result,new String[] {"nume"});
			
			for(Materie obj:materii) {
				addRecord(result,new String[] {obj.getName()});
			}
			
			printWriter.write(result.toString());
			Main.o("Materii salvate!");
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		
	}
	
	public void writeProfesori(List<Profesor> profesori) {
		StringBuilder result = new StringBuilder();
		
		try(PrintWriter printWriter = new PrintWriter("profesori.csv")){
				
			// Build the header
			addRecord(result,new String[] {"nume","materie"});
			
			for(Profesor obj:profesori) {
				addRecord(result,new String[] {obj.getName(),obj.getMaterie().getName()});
			}
			
			printWriter.write(result.toString());
			Main.o("Profesori salvati!");
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void writeClase(List<Clasa> clase) {
		StringBuilder result = new StringBuilder();
		
		try(PrintWriter printWriter = new PrintWriter("clase.csv")){
				
			// Build the header
			addRecord(result,new String[] {"nume","diriginte","studenti"});
			
			for(Clasa obj:clase) {
				String studenti_nume=obj.getStudenti().stream().map(item->item.getName()).collect(Collectors.joining(";"));
				
				addRecord(result,new String[] {obj.getName(),obj.getDiriginte().getName(), studenti_nume});
			}
			
			printWriter.write(result.toString());
			Main.o("Clase salvate!");
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void writeStudenti(List<Student> studenti) {
		StringBuilder result = new StringBuilder();
		
		try(PrintWriter printWriter = new PrintWriter("studenti.csv")){
				
			// Build the header
			addRecord(result,new String[] {"nume","note","absente"});
			
			for(Student obj:studenti) {
				List<String> note_materie=new ArrayList<String>();
				List<String> absente_materie=new ArrayList<String>();
				SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
				
				for(Materie materie:service.getMaterii()){
					Situatie situatie=obj.getSchoolClass().getSituatie(materie,obj);
					note_materie.add(materie.getName()+":"+situatie.getNote().stream().map(item->String.valueOf(item.nota)).collect(Collectors.joining("+")));
					absente_materie.add(materie.getName()+":"+situatie.getAbsente().stream().map(item->String.valueOf(formatter.format(item.data))).collect(Collectors.joining("+")));
				}
				
				addRecord(result,new String[] {obj.getName(),note_materie.stream().collect(Collectors.joining("#")),absente_materie.stream().collect(Collectors.joining("#"))});
			}
			
			printWriter.write(result.toString());
			Main.o("Studenti salvati!");
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	private static StringBuilder addRecord(StringBuilder base, String[] row) {
		for(String data:row) {
			base.append(data);
			base.append(",");
		}
		
		// Remove last character
		base.setLength(base.length()-1);
		base.append("\n");
		return base;
	}
}
