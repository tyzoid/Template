package tk.tyzoid.plugins.Template;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Properties;

public class Settings {
	private Properties props = new Properties();
	
	private final HashMap<String, String> settingsHolder = new HashMap<String, String>();
	private String pluginname;
	private Template plugin;
	
	public Settings(Template instance){
		this.plugin = instance;
		this.pluginname = plugin.pluginname;
	}
	
	public void readSettings(){
		try{
			String path = "plugins/Template";
			File propertiesFile = new File(path + "/Template.properties");
    		if(!propertiesFile.exists()){
    			(new File(path)).mkdir();
    			propertiesFile.createNewFile();
    		}
		    
			FileInputStream propertiesStream = new FileInputStream(propertiesFile);
			
			props.load(propertiesStream);
			System.out.println("[" + pluginname + "] Properties loaded.");
			propertiesStream.close();
			
			//commands
			loadProperty("command-Template", "/Template");
			//loadProperty("", "");
			
			verifySettings();
			
			FileOutputStream propertiesOutputStream = new FileOutputStream(propertiesFile);
			props.store(propertiesOutputStream, "");
		} catch(Exception e){
			System.out.println("[" + pluginname + "] Failed to load properties. Aborting.");
			System.out.println("[" + pluginname + "] Error: " + e.toString());
		}
	}

	public String getProperty(String property){
		return settingsHolder.get(property);
	}
	
	public void reloadData(){
		readSettings();
	}
	
	private void loadProperty(String property, String defaultValue){
		settingsHolder.put(property, lProperty(property, defaultValue));
	}
	
	private String lProperty(String property, String defaultValue){
		String currentProperty;
		currentProperty = props.getProperty(property);
		String value;
    	if(currentProperty == null){
    		System.out.println("[" + pluginname + "] Property not found: " + property + ". Resetting to: " + defaultValue);
    		props.setProperty(property, defaultValue);
    		value = defaultValue;
    	} else {
    		value = currentProperty;
    	}
    	return value;
	}
	

	private void verifySettings() {
		
	}
	
	@SuppressWarnings("unused")
	private void setCProperty(String property, String value){
		props.setProperty(property, value);		
	}
}