package org.zetrahytes.restapi.jersey;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Provider
public class CustomObjectMapper implements ContextResolver<ObjectMapper> {

    private ObjectMapper objectMapper;

    public CustomObjectMapper() {
        objectMapper = new ObjectMapper();
        
        // pretty-print json
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        
        // ignore unknown fields while parsing
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        
        // ISO 8601 date/timestamp format
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sss'Z'");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        objectMapper.setDateFormat(df);
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return objectMapper;
    }

}
