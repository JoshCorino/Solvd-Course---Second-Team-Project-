package com.solvd.secondTeamProject.algorithm;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.secondTeamProject.App;

public class ParserResult {
	private Logger log = LogManager.getLogger(App.class);
	
	public void parserResult(Object bestTransports, String filePath) {
		ObjectMapper obj = new ObjectMapper();
		try {
			obj.writeValue(new File("src/main/resources/BestTransports.json"), bestTransports);
		} catch (IOException e) {
			log.error(e);
		}
	}
	
}
