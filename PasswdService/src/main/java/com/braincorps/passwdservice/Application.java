package com.braincorps.passwdservice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.braincorps.passwdservice.utils.Constants;
import com.braincorps.passwdservice.utils.Error;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Component
@SpringBootApplication
@EnableSwagger2
public class Application {
	
	public static void main(String[] args) {
		if(isValidFile())
			SpringApplication.run(Application.class, args);
	}
	
	private static boolean isValidFile() {
		Properties properties = new Properties();
		InputStream inputStream = null;

		try {
			inputStream = Application.class.getClassLoader().getResourceAsStream(Constants.APPLICATION_PROPERTIES);
    		if(inputStream == null){
    			System.err.println(Error.PROPERTIES_FILE_NOT_FOUND);
    		    return false;
    		}

    		properties.load(inputStream);
    		File file = new File(properties.getProperty(Constants.USER_FILE_PROPERTY));
    		if(file == null || !file.exists()) {
    			System.err.println(Error.INVALID_USER_FILEPATH);
    			return false;
    		}
    		
    		if(!isValidUserFileFormat(file)) {
    			System.err.println(Error.INVALID_USER_FILE);
    			return false;
    		}
    		
    		file = new File(properties.getProperty(Constants.GROUP_FILE_PROPERTY));
    		if(file == null || !file.exists()) {
    			System.err.println(Error.INVALID_GROUP_FILEPATH);
    			return false;
    		}
    		
    		if(!isValidGroupFileFormat(file)) {
    			System.err.println(Error.INVALID_GROUP_FILE);
    			return false;
    		}
    		
		} catch (IOException ex) {
			ex.printStackTrace();
			return false;
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return true;

	}
	
	/**
	 * A valid user file has entries in the format:
	 * nobody:*:-2:-2:Unprivileged User:/var/empty:/usr/bin/false
	 * Each entry is separated by newline.
	 * @param file
	 * @return false if the entries in the file are not in the specified
	 * format.
	 */
	private static boolean isValidUserFileFormat(File file) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String line;
			if(br != null) {
				while((line = br.readLine()) != null) {
					if(!line.startsWith("#")) {
						String[] lineParts = line.split(":");
						if(lineParts.length < 7) {
							br.close();
							return false;
						}

						try {
							Long.parseLong(lineParts[2]);
							Long.parseLong(lineParts[3]);
						} catch(Exception e) {
							br.close();
							return false;
						}
					}
				}
			}				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return true;
	}
	
	/**
	 * A valid group file has entries in the format:
	 * certusers:*:29:root,_jabber,_postfix,_cyrus,_calendar,_dovecot
	 * Each entry is separated by newline.
	 * @param file
	 * @return false if the entries in the file are not in the specified
	 * format.
	 */
	private static boolean isValidGroupFileFormat(File file) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			
			if(br != null) {
				String line;
				while((line = br.readLine()) != null) {
					if(!line.startsWith("#")) {
						String[] lineParts = line.split(":");
						if(lineParts.length < 3) {
							br.close();
							return false;
						}
						try {
							Long.parseLong(lineParts[2]);
						} catch(Exception e) {
							br.close();
							return false;
						}
					}
				}
			}				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return true;
	}
		
}

