package com.backcountry.web.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;

import java.text.SimpleDateFormat;

public class JSONMapper extends ObjectMapper{
    public JSONMapper() {
        System.out.println("Setting JSON Mapper");
        registerModule(new Hibernate4Module());
        registerModule(new JSR310Module());
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX"));
        configure(SerializationFeature.INDENT_OUTPUT, true);
        configure(MapperFeature.AUTO_DETECT_SETTERS, true);
        setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }
}
