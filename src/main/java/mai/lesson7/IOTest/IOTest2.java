package mai.lesson7.IOTest;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import mai.lesson7.settings.Settings;

public class IOTest2 {

	public static void main(String[] args) {
		String val;
		Settings setting = null;
		val = setting.getInstance().value("savfile");
		FileInputStream myFile = null;
		BufferedInputStream buff = null;
		try {
			myFile = new FileInputStream(val);
			buff = new BufferedInputStream(myFile);
			boolean eof = false;
			while (!eof) {
				int byteValue = buff.read();
				System.out.print(byteValue + " ");
				if (byteValue == -1)
					eof = true;
			}
		} catch (IOException e) { System.out.println("Не могу прочесть: " + e.toString() ); }
		finally{
			if (myFile !=null){
				try{
					buff.close();
					myFile.close();
				} catch (Exception e1){
					e1.printStackTrace();
				}
			}
		}

	}

}
