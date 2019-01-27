package com.braincorps.passwdservice.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Validator {
	
	@Value("${filepath.user}")
	private String userFilePath;
	
	
	public boolean test() {
		System.out.println("User: " + userFilePath);
		return true;
	}

}
