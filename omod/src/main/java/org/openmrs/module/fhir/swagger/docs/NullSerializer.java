package org.openmrs.module.fhir.swagger.docs;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class NullSerializer extends JsonSerializer<Object> {

	@Override
	public void serialize(Object arg0, JsonGenerator arg1, SerializerProvider arg2) throws IOException {
		arg1.writeFieldName("");
	}
}
