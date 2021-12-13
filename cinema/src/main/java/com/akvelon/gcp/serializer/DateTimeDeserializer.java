package com.akvelon.gcp.serializer;

import com.akvelon.gcp.Constant;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateTimeDeserializer extends StdDeserializer<LocalDateTime> {

    public DateTimeDeserializer() {
        this(null);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    public DateTimeDeserializer(Class<LocalDateTime> vc) {
        super(vc);
    }

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.getTextLength() == 0) {
            return null;
        }
        return Instant.ofEpochMilli(jsonParser.getLongValue()).atZone(ZoneId.of(Constant.TIME_ZONE)).toLocalDateTime();
    }
}