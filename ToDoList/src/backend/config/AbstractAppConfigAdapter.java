package backend.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import main.Main;

public abstract class AbstractAppConfigAdapter {
	
	public static final String 
			KEYNAME_VERSION = "version",
			KEYNAME_FIRSTRUN = "firstRun",
			KEYNAME_PATHTOLASTLIST = "pathToLastList";
	private HashMap<String, String> propertiesMap;
	
	
	/**
	 * Located at base of classpath, outside src folder or compiled jar
	 */
	private String pathToAppConfig = "AppConfig.json";
	
	private AppConfigLoadState loadState;
	
	/**
	 * 
	 * @param key
	 * @param value
	 * @return
	 * The previous value associated with key, or null if there was no mapping for key. 
	 * (A null return can also indicate that the map previously associated null with key.)
	 */
	public String setProperty(String key, String value) {
		return propertiesMap.put(key, value);
	}
	
	public String getProperty(String key) {
		return propertiesMap.get(key);
	}
	
	public void setAppConfigPath(String path) {
		pathToAppConfig = path;
	}
	
	public boolean loadAppConfig() {
		HashMap<String, String> temp = new HashMap<String, String>();
		boolean success = false;
		
		Gson gson = Main.gsonBuilder.create();
		try {
			temp = gson.fromJson(new FileReader(pathToAppConfig), temp.getClass());
			
			success = true;//Exceptions will be thrown before this statement, leaving success false
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			setLoadState(AppConfigLoadState.JSON_SYNTAX_ERROR);
			e.printStackTrace();
		} catch (JsonIOException e) {
			// TODO Auto-generated catch block
			setLoadState(AppConfigLoadState.JSON_IO_ERROR);
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			setLoadState(AppConfigLoadState.FILE_NOT_FOUND);
			e.printStackTrace();
		}
		
		if (temp != null) {
			propertiesMap = temp;
			setLoadState(AppConfigLoadState.NORMAL);
			success = true;
		} else {
			setLoadState(AppConfigLoadState.UNKNOWN_ERROR);
		}
		return success;		
	}
	
	public boolean saveAppConfig() {
		File file = new File(pathToAppConfig);
		try (Writer writer = new FileWriter(file)){
			Gson gson = Main.gsonBuilder.create();
			gson.toJson(propertiesMap, writer);
			//writer.flush();
			
			System.out.println("SAVED " + file.getName());
			return true;
		} catch(IOException i) {
			i.printStackTrace();//for debugging
			return false;
		}
	}

	
	public HashMap<String, String> getPropertiesMap() {
		return propertiesMap;
	}
	
	protected abstract void initialize();
	
	public abstract String getErrorMessage();
	
	protected void setLoadState(AppConfigLoadState state) {
		loadState = state;
	}
	
	public AppConfigLoadState getLoadState() {
		return loadState;
	}
}
