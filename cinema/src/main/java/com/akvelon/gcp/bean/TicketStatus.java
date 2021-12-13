package com.akvelon.gcp.bean;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Map;

/**
 * @author Andrey Kustov on 12.12.2021
 */
@ApiModel(description = "status ticket")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TicketStatus {

    PAID("ticket is paid"),
    CANCEL("ticket  cancel"),
    ON_PAID("ticket for payment");

    @ApiModelProperty(value = "status as text")
    public String getStatus() {
        return this.name();
    }

    TicketStatus(String description) {
        this.description = description;
    }

    String description;

    public String getDescription() {
        return description;
    }

    @JsonCreator
    public static TicketStatus getEnumFromValue(Object value) {
        if (value instanceof String) {
            for (TicketStatus status : values()) {
                if (status.toString().equals(value)) {
                    return status;
                }
            }
        }
        if (value instanceof Integer) {
            for (TicketStatus status : values()) {
                if (Integer.valueOf(status.ordinal()).equals(value)) {
                    return status;
                }
            }
        }
        if (value instanceof Map) {
            Map<String, Object> objmap = (Map<String, Object>) value;
            String name = String.valueOf(objmap.get("status"));
            return getEnumFromValue(name);
        }
        throw new IllegalArgumentException();
    }


}
