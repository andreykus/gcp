package com.akvelon.gcp.config;

import com.akvelon.gcp.serializer.DateTimeDeserializer;
import com.akvelon.gcp.serializer.DateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.time.LocalDateTime;

/**
 * @author Andrey Kustov on 12.12.2021
 */
@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);

        JavaTimeModule javaDateTimeModule = new JavaTimeModule();
        javaDateTimeModule.addSerializer(LocalDateTime.class, new DateTimeSerializer());
        javaDateTimeModule.addDeserializer(LocalDateTime.class, new DateTimeDeserializer());

        objectMapper.registerModules(javaDateTimeModule, new Jdk8Module());

        return objectMapper;
    }

}
