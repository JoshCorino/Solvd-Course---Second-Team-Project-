package com.solvd.secondTeamProject.algorithm;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.solvd.secondTeamProject.App;

public class ParserResult {
	private static Logger log = LogManager.getLogger(App.class);
	
	public static void parserResult(Object bestTransports, String filePath) {
		ObjectMapper obj = new ObjectMapper();
		obj.configure(SerializationFeature.INDENT_OUTPUT, true);
		try {
			obj.writeValue(new File(filePath), bestTransports);
		} catch (IOException e) {
			log.error(e);
		}
	}
	
}
