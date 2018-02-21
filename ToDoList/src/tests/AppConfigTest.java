package tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import backend.config.AbstractAppConfigAdapter;
import backend.config.AppConfigBuilder;

public class AppConfigTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*String configPath = "./src/backend/config/AppConfig.properties";
		Properties appProps = new Properties();
		try {
			appProps.load(new FileInputStream(configPath));
			System.out.println("Version: " + appProps.getProperty("version"));
			boolean firstRun = Boolean.parseBoolean(appProps.getProperty("firstRun"));
			System.out.println("First Run(?): " + firstRun);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		test1();
		

	}
	
	public static void test1() {
		AbstractAppConfigAdapter appProps = new AppConfigBuilder().create();
		
		System.out.println("Version: " + appProps.getProperty("version"));
		boolean firstRun = Boolean.parseBoolean(appProps.getProperty("firstRun"));
		System.out.println("First Run(?): " + firstRun);
		System.out.println("Path of Last List: " + appProps.getProperty(AbstractAppConfigAdapter.KEYNAME_PATHTOLASTLIST));
	}

}
