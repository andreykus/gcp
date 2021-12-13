package com.akvelon.gcp.serializer;

import com.akvelon.gcp.Constant;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateTimeSerializer extends StdSerializer<LocalDateTime> {

    public DateTimeSerializer() {
        this(null);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    public DateTimeSerializer(Class<LocalDateTime> vc) {
        super(vc);
    }

    @Override
    public void serialize(LocalDateTime dateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (dateTime != null) {
            jsonGenerator.writeNumber(
                    dateTime.atZone(ZoneId.of(Constant.TIME_ZONE)).toInstant().toEpochMilli());
        }
    }
}