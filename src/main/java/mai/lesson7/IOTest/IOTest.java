package mai.lesson7.IOTest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import mai.lesson7.settings.Settings;

public class IOTest {

	public static void main(String[] args) {

		FileInputStream myFile = null;
		FileOutputStream myOut = null;
		Settings setting = null;
		String val = setting.getInstance().value("savfile");
		try {
			myFile = new FileInputStream(val);
			myOut = new FileOutputStream("out.txt");
			boolean eof = false;
			while (!eof) {
				int byteValue = myFile.read();
				System.out.print(byteValue +  " ");
				if (byteValue != -1)
					myOut.write(byteValue);
				if (byteValue == -1){
					eof = true;
				}
			}
			// myFile.close(); // Помещайте в блок finally
		} catch (IOException e) {
			System.out.println("Не могу прочесть: " + e.toString());
		} finally{
			if (myFile !=null){
				try{
					myFile.close();
				} catch (Exception e1){
					e1.printStackTrace();
				}
			}
		}
	}

}


