package Program;

import java.util.Scanner;

import Model.Profesor;
import Model.Student;
import Service.AppService;

public class Main {
	public static void o(String in) {
		System.out.println(in);
	}
	public static void o(int in) {
		System.out.println(in);
	}
	public static void o(boolean in) {
		System.out.println(in);
	}
	
	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		AppService service=new AppService();
		int semestru=1;
		
		String menu=
				"Optiuni posibile:\n"
				+ "1) Creeaza clasa\n"
				+ "2) Creeaza profesor\n"
				+ "3) Creeaza student\n"
				+ "4) Noteaza student\n"
				+ "5) Pune absenta\n"
				+ "6) Seteaza semestru";
		
		while(true) {
			o(menu);
			int option=Integer.parseInt(scanner.nextLine());
			
			try {
				switch(option) {
				case 1:{
					o("Nume clasa: ");
					String nume=scanner.nextLine();
					
					o("Diriginte: ");
					String diriginte=scanner.nextLine();
					
					service.adaugaClasa(nume,diriginte);
					break;
				}
				case 2:{
					o("Nume profesor:");
					String prof=scanner.nextLine();
					
					o("Materia profesata: ");
					String materie=scanner.nextLine();
					
					service.adaugaProfesor(prof, materie);
					break;
				}
				case 3:{
					o("Nume student: ");
					String student_nume=scanner.nextLine();
					
					o("Clasa: ");
					String clasa_nume=scanner.nextLine();
					service.adaugaStudent(student_nume,clasa_nume);
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
					profesor.noteazaStudent(semestru,student,nota);
					break;
				}
				case 5:
					break;
				case 6:{
					semestru=Integer.parseInt(scanner.nextLine());
					break;
				}
				default:
					o("Optiune invalida");
				}
			}catch(Exception ex) {
				o("Intrare gresita: serviciul refuza.");
			}
		}
	}
}
