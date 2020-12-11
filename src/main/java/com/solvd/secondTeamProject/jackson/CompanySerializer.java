package com.solvd.secondTeamProject.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.solvd.secondTeamProject.model.Company;

public class CompanySerializer extends JsonSerializer<Company>{

	@Override
	public void serialize(Company value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeString(value.getName());
	}


}
