package  mai.lesson7.settings;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Settings {

	private static final Settings INSTANCE = new Settings();
	private final Properties properties = new Properties();

	String file;
	
	private Settings() {
		try {
			file = this.getClass().getClassLoader().getResource("univer.properties").getFile();
			properties.load(new FileInputStream(file));

		} catch (IOException e) {
			System.out.println("cannot load: " + file);
			e.printStackTrace();
		}
	}

	public static Settings getInstance() {
		return INSTANCE;
	}

	public String value(String key) {
		return this.properties.getProperty(key);
	}
}
