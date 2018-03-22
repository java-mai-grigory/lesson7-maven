package mai.lesson7.IOTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import mai.lesson7.entity.Student;
import mai.lesson7.settings.Settings;

public class IOTest5 {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		FileOutputStream file;
		FileInputStream is;
		ObjectOutputStream oos;
		ObjectInputStream ios;
		String val;
		Settings setting = null;
		val = setting.getInstance().value("savfile");
		Student st = new Student("Иван", "Иванов", 5, 5, 5 );
		try {
			file= new FileOutputStream(val);
			oos = new ObjectOutputStream(file);
			is = new FileInputStream(val);
			ios = new ObjectInputStream(is);
			oos.writeObject(st);
			Object o = ios.readObject();
			System.out.println((Student)o);
		} catch (FileNotFoundException e) {
		
			e.printStackTrace();
		}

	}

}
