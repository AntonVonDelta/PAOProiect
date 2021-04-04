package Program;

import java.util.Scanner;

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
		
		String menu=
				"Optiuni posibile:\n"
				+ "1) Creeaza clasa\n"
				+ "2) Creeaza profesor\n"
				+ "3) Creeaza student\n"
				+ "4) Noteaza student\n"
				+ "5) Pune absenta\n";
		
		while(true) {
			o(menu);
			
			int option=Integer.parseInt(scanner.nextLine());
			switch(option) {
			case 1:
				
				break;
			case 2:
				
				break;
				
			case 3:
				
				break;
			
			case 4:
				
				break;
				
			case 5:
				break;
			}
		}
	}
}
