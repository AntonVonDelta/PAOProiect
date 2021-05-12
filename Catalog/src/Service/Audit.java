package Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Audit {
	private static Audit instance;
	
	private Audit() {}
	
	public static Audit getInstance() {
		if(instance==null) {
			instance=new Audit();
		}
		return instance;
	}
	
	public void log(String actiune) {
		SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
		File logfile=new File("audit.csv");
		StringBuilder result=new StringBuilder();
		
		try {
			if(logfile.createNewFile()) {
				// File does not exist so create header
				addRecord(result,"nume_actiune","timestamp" );
			}
			
			PrintWriter printWriter = new PrintWriter(new FileOutputStream(logfile,true));
			addRecord(result,actiune,formatter.format(new Date()));
			
			printWriter.write(result.toString());
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static StringBuilder addRecord(StringBuilder base, String col1,String col2) {
		base.append(col1+","+col2+"\n");
		return base;
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
