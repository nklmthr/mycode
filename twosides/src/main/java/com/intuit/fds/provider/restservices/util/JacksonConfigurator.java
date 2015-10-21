package com.intuit.fds.provider.restservices.util;

import javax.ws.rs.ext.ContextResolver;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.DeserializationConfig;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.Produces; 
import java.text.SimpleDateFormat;

@Provider
@Produces("application/json")
public class JacksonConfigurator implements ContextResolver<ObjectMapper> {

	private static final Logger logger = Logger
			.getLogger(JacksonConfigurator.class);
	
    private ObjectMapper mapper = new ObjectMapper();

    public JacksonConfigurator() {
    	logger.debug("JacksonConfigurator is used");
        SerializationConfig serConfig = mapper.getSerializationConfig();
        serConfig = serConfig.withDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
        mapper.setSerializationConfig(serConfig);
        DeserializationConfig deserializationConfig = mapper.getDeserializationConfig();
        deserializationConfig = deserializationConfig.withDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
        mapper.setDeserializationConfig(deserializationConfig);
        mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    public ObjectMapper getContext(Class<?> arg0) {
        return mapper;
    }

}
