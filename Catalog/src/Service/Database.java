package Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import Model.Clasa;
import Model.Materie;
import Model.Profesor;
import Model.Situatie;
import Model.Situatie.Absenta;
import Model.Situatie.Nota;
import Model.Student;
import Program.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	private static Database instance;
	private AppService service;
	private Connection con;
	
	private Database() {}
	
	public static Database getInstance(AppService service) throws SQLException {
		if(instance==null) {
			instance=new Database();
		}
		instance.service=service;
		instance.con=DriverManager.getConnection("jdbc:mysql://localhost:3306/catalog","mdsuser","verdeatademare"	);
		
		return instance;
	}
	
	public List<Materie> readMaterii() {
		try {
			Statement stmt = con.createStatement();
			ResultSet result_query=stmt.executeQuery("Select Nume from materii");
			ArrayList<Materie> result=new ArrayList<Materie>();
			
			while(result_query.next()) {
				Materie result_obj=new Materie(result_query.getString(1));
				result.add(result_obj);
			}
			return result;
			
		} catch (SQLException e) {
			Main.o(e.getMessage());
		}
		
		return null;
	}
	public void addMaterie(Materie obj) {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("INSERT into materii values ('"+obj.getName()+"')");
			
		} catch (SQLException e) {
			Main.o(e.getMessage());
		}
	}
	public void deleteMaterie(Materie obj) {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM materii where Nume='"+obj.getName()+"'");
			
		} catch (SQLException e) {
			Main.o(e.getMessage());
		}
	}
	public void updateMaterie(Materie obj,Materie updated) {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("UPDATE materii SET Nume='"+updated.getName()+"' where Nume='"+obj.getName()+"'");
		} catch (SQLException e) {
			Main.o(e.getMessage());
		}
	}
	
	
	
	
	public List<Profesor> readProfesori() {
		try {
			Statement stmt = con.createStatement();
			ResultSet result_query=stmt.executeQuery("Select Nume,MaterieId from profesori");
			ArrayList<Profesor> result=new ArrayList<Profesor>();
			
			while(result_query.next()) {				
				Profesor result_obj=new Profesor(result_query.getString(1),service.getMaterie(result_query.getString(2)));
				result.add(result_obj);
			}
			return result;
			
		} catch (Exception e) {
			Main.o(e.getMessage());
		}
		
		return null;
	}
	public void addProfesor(Profesor obj) {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("INSERT into profesori values ('"+obj.getName()+"','"+obj.getMaterie().getName()+"')");
			
		} catch (SQLException e) {
			Main.o(e.getMessage());
		}
	}
	public void deleteProfesor(Profesor obj) {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM profesori where Nume='"+obj.getName()+"'");
			
		} catch (SQLException e) {
			Main.o(e.getMessage());
		}
	}
	public void updateProfesor(Profesor obj,Profesor updated) {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("UPDATE profesori SET Nume='"+updated.getName()+"',MaterieId='"+updated.getMaterie().getName()+"' where Nume='"+obj.getName()+"'");
		} catch (SQLException e) {
			Main.o(e.getMessage());
		}
	}
	
	
	
	public List<Clasa> readClase() {
		try {
			Statement stmt = con.createStatement();
			ResultSet result_query=stmt.executeQuery("Select clase.Nume,profesori.Nume from clase join profesori on (clase.DiriginteId=profesori.Nume)");
			ArrayList<Clasa> result=new ArrayList<Clasa>();
			
			while(result_query.next()) {				
				String clasa_nume=result_query.getString(1);
				String clasa_diriginte=result_query.getString(2);
				Clasa result_obj=new Clasa(service.getProfesor(clasa_diriginte));
				
				result_obj.setName(clasa_nume);
				result_obj.setMaterii(service.getMaterii());
				
				result.add(result_obj);
			}
			return result;
			
		} catch (Exception e) {
			Main.o(e.getMessage());
		}
		
		return null;
	}
	public void addClasa(Clasa obj) {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("INSERT into clase values ('"+obj.getName()+"','"+obj.getDiriginte().getName()+"')");
			
		} catch (SQLException e) {
			Main.o(e.getMessage());
		}
	}
	public void deleteClasa(Clasa obj) {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM clase where Nume='"+obj.getName()+"'");
			
		} catch (SQLException e) {
			Main.o(e.getMessage());
		}
	}
	public void updateClasa(Clasa obj,Clasa updated) {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("UPDATE clase SET Nume='"+updated.getName()+"',DiriginteId='"+updated.getDiriginte().getName()+"' where Nume='"+obj.getName()+"'");
		} catch (SQLException e) {
			Main.o(e.getMessage());
		}
	}
	
	
	
	public List<Student> readStudenti() {
		try {
			Statement stmt = con.createStatement();
			ResultSet result_query=stmt.executeQuery("Select studenti.Nume,clase.Nume from studenti join clase on (studenti.ClasaId=clase.Nume)");
			ArrayList<Student> result=new ArrayList<Student>();
			
			while(result_query.next()) {				
				String student_nume=result_query.getString(1);
				String student_clasa=result_query.getString(2);
				Student result_obj=new Student();
				
				result_obj.setName(student_nume);
				result_obj.setSchoolClass(service.getClasa(student_clasa));
				
				result.add(result_obj);
			}
			return result;
			
		} catch (Exception e) {
			Main.o(e.getMessage());
		}
		
		return null;
	}
	public List<Student> readSituatie() {
		try {
			Statement stmt = con.createStatement();
			ArrayList<Student> result=new ArrayList<Student>();
			
			ResultSet situatie_query=stmt.executeQuery("Select studenti.Nume,situatie.* from situatie join studenti on (situatie.StudentId=studenti.Nume)");
			while(situatie_query.next()) {
				String student_nume=situatie_query.getString(1);
				Student result_obj=service.getStudent(student_nume);
				int valoare=situatie_query.getInt(4);
				Date date=situatie_query.getDate(5);
				String materie=situatie_query.getString(6);
				if(valoare==0) {
					// Absenta
					result_obj.getSituatie(service.getMaterie(materie)).absenteaza(date);
				}else {
					// Nota
					result_obj.getSituatie(service.getMaterie(materie)).noteaza(date, valoare);
				}
				result.add(result_obj);
			}
			
			return result;
			
		} catch (Exception e) {
			Main.o(e.getMessage());
		}
		
		return null;
	}
	public void addStudent(Student obj) {
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("INSERT into studenti values ('"+obj.getName()+"','"+obj.getSchoolClass().getName()+"')");
			for(Materie materie:service.getMaterii()) {
				Situatie situatie=obj.getSituatie(materie);
				for(Nota nota:situatie.getNote()) {
					stmt.executeUpdate("INSERT into situatie(StudentId,Valoare,Data,MaterieId) values ('"+obj.getName()+"','"+nota.nota+"','"+simpleDateFormat.format(nota.data)+"','"+materie.getName()+"')");
				}
				for(Absenta absenta:situatie.getAbsente()) {
					stmt.executeUpdate("INSERT into situatie(StudentId,Valoare,Data,MaterieId) values ('"+obj.getName()+"','0','"+simpleDateFormat.format(absenta.data)+"','"+materie.getName()+"')");
				}
			}
			
		} catch (SQLException e) {
			Main.o(e.getMessage());
		}
	}
	public void deleteStudent(Student obj) {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM studenti where Nume='"+obj.getName()+"'");
			
		} catch (SQLException e) {
			Main.o(e.getMessage());
		}
	}
	public void updateStudent(Student obj,Student updated) {
		deleteStudent(obj);
		addStudent(updated);
	}
}
