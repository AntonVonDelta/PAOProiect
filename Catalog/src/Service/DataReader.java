package Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import Model.Situatie;
import Model.Student;

public class DataReader {
	private static DataReader instance;
	private AppService service;
	
	private DataReader() {}
	
	public static DataReader getInstance(AppService service) {
		if(instance==null) {
			instance=new DataReader();
		}
		instance.service=service;
		return instance;
	}
	
	public void loadMaterii() throws Exception {
        try(Scanner sc = new Scanner(new File("materii.csv"))){
        	sc.nextLine();
        	
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String [] dataLine = line.split(",");

                service.adaugaMaterie(dataLine[0]);
            }
        }catch (Exception ex){
            throw new Exception("Nu s-a putut incarca un fisier");
        }
	}
	
	public void loadProfesori() throws Exception {
        try(Scanner sc = new Scanner(new File("profesori.csv"))){
        	sc.nextLine();
        	
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String [] dataLine = line.split(",");

                service.adaugaProfesor(dataLine[0],dataLine[1]);
            }
        }catch (Exception ex){
            throw new Exception("Nu s-a putut incarca un fisier");
        }
	}
	
	public void loadClase() throws Exception {
        try(Scanner sc = new Scanner(new File("clase.csv"))){
        	sc.nextLine();
        	
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String [] dataLine = line.split(",");
                String[] studenti=dataLine[2].split(";");
                
                service.adaugaClasa(dataLine[0], dataLine[1]);
                for(String student:studenti) {
                	service.adaugaStudent(student,dataLine[0]);
                }
            }
        }catch (Exception ex){
            throw new Exception("Nu s-a putut incarca un fisier");
        }
	}
	
	public void loadStudenti() throws Exception {
		SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
		
        try(Scanner sc = new Scanner(new File("studenti.csv"))){
        	sc.nextLine();
        	
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String [] dataLine = line.split(",");
                String [] note_materie=dataLine[1].split("#");
                String [] absente_materie=dataLine[2].split("#");
                
                Student student=service.getStudent(dataLine[0]);
                
                for(String data_note:note_materie) {
                	if(data_note.split(":").length<2) continue;
                	
                	String materie=data_note.split(":")[0];
                	String[] note=data_note.split(":")[1].split("\\+");
                	Situatie situatie=student.getSchoolClass().getSituatie(service.getMaterie(materie), student);
                	
                	for(String nota:note) {
                		situatie.noteaza(new Date(), Integer.parseInt(nota));
                	}
                }
                for(String data_absente:absente_materie) {
                	if(data_absente.split(":").length<2) continue;
                	
                	String materie=data_absente.split(":")[0];
                	String[] absente=data_absente.split(":")[1].split("+");
                	Situatie situatie=student.getSchoolClass().getSituatie(service.getMaterie(materie), student);
                	
                	for(String absenta:absente) {
                		situatie.absenteaza(formatter.parse(absenta));
                	}
                }
            }
        }catch (Exception ex){
            throw new Exception("Nu s-a putut incarca un fisier");
        }
	}
}
