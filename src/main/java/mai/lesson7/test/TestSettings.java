package mai.lesson7.test;

import mai.lesson7.settings.Settings;

public class TestSettings {
	public static void main( String[] args )
    {
        String val = Settings.getInstance().value("address");
		System.out.println( val);
    }
}
