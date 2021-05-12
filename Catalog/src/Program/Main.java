package Program;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import Model.Catalog;
import Model.Profesor;
import Model.Situatie.Absenta;
import Model.Situatie.Nota;
import Model.Student;
import Service.AppService;
import Service.Audit;
import Service.DataWriter;

public class Main {
	public static void main(String[] args) {
		SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
		Scanner scanner=new Scanner(System.in);
		AppService service=new AppService();
		Audit logger=Audit.getInstance();
		boolean loop=true;
		
		String menu=
				"Optiuni posibile:\n"
				+ "0) Iesire program\n"
				+ "1) Creeaza clasa\n"
				+ "2) Creeaza profesor\n"
				+ "3) Creeaza student\n"
				+ "4) Noteaza student\n"
				+ "5) Pune absenta\n"
				+ "6) Afiseaza clasele\n"
				+ "7) Afiseaza profesorii\n"
				+ "8) Afiseaza materiile\n"
				+ "9) Afiseaza studentii\n"
				+ "10) Afiseaza note student\n"
				+ "11) Afiseaza absente student\n"
				+ "12) Adauga materie\n"
				+ "13) Adauga materii default\n"
				+ "20) Scrie in CSV\n";
		
		
		o("Loading from files....");
		try{
			service.loadSavedData();
		}catch(Exception ex) {
			o("Eroare: "+ex.getMessage());
		}
		
		while(loop) {
			o();
			o(menu);
			int option=Integer.parseInt(scanner.nextLine());
			
			try {
				switch(option) {
				case 0: {
					loop=false;
					break;
				}
				case 1:{
					o("Nume clasa: ");
					String nume=scanner.nextLine();
					
					o("Diriginte: ");
					String diriginte=scanner.nextLine();
					
					service.adaugaClasa(nume,diriginte);
					
					logger.log("Clasa creata");
					break;
				}
				case 2:{
					o("Nume profesor:");
					String prof=scanner.nextLine();
					
					o("Materia profesata: ");
					String materie=scanner.nextLine();
					
					service.adaugaProfesor(prof, materie);
					
					logger.log("Profesor creat");
					break;
				}
				case 3:{
					o("Nume student: ");
					String student_nume=scanner.nextLine();
					
					o("Clasa: ");
					String clasa_nume=scanner.nextLine();
					service.adaugaStudent(student_nume,clasa_nume);
					
					logger.log("Student creat");
					break;
				}
				case 4:{
					o("Nume profesor: ");
					String profesor_nume=scanner.nextLine();
					Profesor profesor=service.getProfesor(profesor_nume);
					
					o("Nume student:");
					String student_nume=scanner.nextLine();
					Student student=service.getStudent(student_nume);
					
					o("Nota:");
					int nota=Integer.parseInt(scanner.nextLine());
					profesor.noteazaStudent(student,nota);
					
					logger.log("Student notat");
					break;
				}
				case 5:{
					o("Nume profesor: ");
					String profesor_nume=scanner.nextLine();
					Profesor profesor=service.getProfesor(profesor_nume);
					
					o("Nume student:");
					String student_nume=scanner.nextLine();
					Student student=service.getStudent(student_nume);
					
					profesor.absenteazaStudent(student);
					logger.log("Studentat absentat");
					break;
				}
				case 6:{
					String result="Clase: ";
					result+=service.getClase().stream().map(item->item.getName()+" cu diriginte "+item.getDiriginte().getName()).collect(Collectors.joining("\n"));
					o(result);
					
					logger.log("Interogare clase");
					break;
				}
				case 7:{
					String result="Profesori: ";
					result+=service.getProfesori().stream().map(item->item.getName()+" de "+item.getMaterie().getName()).collect(Collectors.joining("\n"));
					o(result);
					
					logger.log("Interogare profesori");
					break;
				}
				case 8:{
					String result="Materii: ";
					result+=service.getMaterii().stream().map(item->item.getName()).collect(Collectors.joining(","));
					o(result);
					
					logger.log("Interogare materii");
					break;
				}
				case 9:{
					String result="Clase: ";
					result+=service.getStudenti().stream().map(item->item.getName()+" in clasa "+item.getSchoolClass().getName()).collect(Collectors.joining("\n"));
					o(result);
					
					logger.log("Interogare studenti");
					break;
				}
				case 10:{
					o("Nume student:");
					String student_nume=scanner.nextLine();
					Student student=service.getStudent(student_nume);
					
					o("Materia: ");
					String materie=scanner.nextLine();
					
					List<Nota> note=student.getSchoolClass().getSituatie(service.getMaterie(materie), student).getNote();
					String result="Note: ";
					result+=note.stream().map(item->String.valueOf(item.nota)+" pe data de "+formatter.format(item.data)).collect(Collectors.joining("\n"));
					o(result);
					
					logger.log("Interogare note student");
					break;
				}
				case 11:{
					o("Nume student:");
					String student_nume=scanner.nextLine();
					Student student=service.getStudent(student_nume);
					
					o("Materia: ");
					String materie=scanner.nextLine();
					
					List<Absenta> note=student.getSchoolClass().getSituatie(service.getMaterie(materie), student).getAbsente();
					String result="Absente: ";
					result+=note.stream().map(item->formatter.format(item.data)).collect(Collectors.joining("\n"));
					o(result);
					
					logger.log("Interogare absente student");
					break;
				}
				case 12:{
					o("Nume materie:");
					String materie_nume=scanner.nextLine();
					service.adaugaMaterie(materie_nume);
					
					logger.log("O materie adaugata");
					break;
				}
				case 13:{
					service.adaugaMaterii(Catalog.materii_default);
					break;
				}
				case 20:{
					DataWriter data_manager=DataWriter.getInstance(service);
					
					data_manager.writeProfesori(service.getProfesori());
					data_manager.writeMaterii(service.getMaterii());
					data_manager.writeClase(service.getClase());
					data_manager.writeStudenti(service.getStudenti());
					break;
				}
				default:
					o("Optiune invalida");
				}
			}catch(Exception ex) {
				o("Intrare gresita: serviciul refuza.");
				o("Motiv: "+ex.getMessage());
			}
		}
		
		scanner.close();
	}
	
	
	// Helper methods
	public static void o() {
		System.out.println();
	}
	public static void o(String in) {
		System.out.println(in);
	}
	public static void o(int in) {
		System.out.println(in);
	}
	public static void o(boolean in) {
		System.out.println(in);
	}
}
