package mai.lesson7.IOTest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import mai.lesson7.entity.Student;
import mai.lesson7.settings.Settings;

public class IOTest3 {

	public static void main(String[] args) {
		String val;
		Settings setting = null;
		val = setting.getInstance().value("savfile");
		FileOutputStream myFile = null;
		Student st = new Student("Иван", "Иванов", 5, 5, 5 );
		DataOutputStream stream = null;
		try {
			myFile = new FileOutputStream(val);
			stream = new DataOutputStream(myFile);
			stream.writeUTF(st.getFname());
			stream.writeUTF(st.getSname());
			stream.writeInt(st.getRatePhys());
			stream.writeInt(st.getRateMath());
			stream.writeInt(st.getRateProg());
			
		} catch (IOException e) { System.out.println("Не могу писать: " + e.toString() ); }
		finally{
			if (myFile !=null){
				try{
					stream.close();
					myFile.close();
				} catch (Exception e1){
					e1.printStackTrace();
				}
			}
		}

	}
}
