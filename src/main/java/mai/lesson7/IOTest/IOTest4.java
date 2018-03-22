package mai.lesson7.IOTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import mai.lesson7.entity.Student;
import mai.lesson7.settings.Settings;

public class IOTest4 {

	static public void main(String[] args) throws IOException 
	{
		String val;
		val = Settings.getInstance().value("savfile");
		Student st = new Student("Иван", "Иванов", 5, 5, 5 );
		BufferedReader stream = null;
		try {
			File file = new File(val);
			stream = new BufferedReader(new FileReader(file) );
			for(String str = stream.readLine(); str != null; str = stream.readLine())
					System.out.println(str);

		} catch (IOException e) { System.out.println("Не могу записать: " + e.toString() ); }
		finally {
			stream.close();
		}
	}
}
