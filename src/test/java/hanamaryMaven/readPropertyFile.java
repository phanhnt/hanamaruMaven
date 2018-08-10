package hanamaryMaven;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class readPropertyFile {
	public Properties prop = null;
	//constructor
	public readPropertyFile() throws IOException{
		//BufferedReader input; //BufferedReader(new FileReader(filePath));
	try{
	    FileInputStream input = new FileInputStream("src/test/java//hanamaryMaven//propertyFile.properties");
	    prop = new Properties();
		prop.load(input);
	} catch (IOException e){
		e.printStackTrace();
		}
	}
	
	public String getDBConnectionString(){
		return prop.getProperty("DBConnectionString");
	}
	public String getUrl(){
		return prop.getProperty("url");
	}
	public String getAdmin_username(){
		return prop.getProperty("admin_username");
	}
	public String getAdmin_password(){
		return prop.getProperty("admin_password");
	}
	public String getDBusername(){
		return prop.getProperty("DBusername");
	}
	public String getDBpassword(){
		return prop.getProperty("DBpassword");
	}
	
	public String getPort(){
		return prop.getProperty("portnumber");
	}
	public String getQuery(){
		return prop.getProperty("query");
	}
}
