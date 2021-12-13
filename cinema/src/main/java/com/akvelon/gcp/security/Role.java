package com.akvelon.gcp.security;

import com.akvelon.gcp.bean.PlaceStatus;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Map;

/**
 * @author Andrey Kustov on 12.12.2021
 */
@ApiModel(description = "role")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Role {

    ADMIN("role add moves,hall; show all tickets; reserv place;  change status movie"),
    QUEST("show all movies, places, only own ticket");

    @ApiModelProperty(value = "role as text")
    public String getRole() {
        return this.name();
    }

    Role(String description) {
        this.description = description;
    }

    String description;

    public String getDescription() {
        return description;
    }

    @JsonCreator
    public static Role getEnumFromValue(Object value) {
        if (value instanceof String) {
            for (Role role : values()) {
                if (role.toString().equals(value)) {
                    return role;
                }
            }
        }
        if (value instanceof Integer) {
            for (Role role : values()) {
                if (Integer.valueOf(role.ordinal()).equals(value)) {
                    return role;
                }
            }
        }
        if (value instanceof Map) {
            Map<String, Object> objmap = (Map<String, Object>) value;
            String name = String.valueOf(objmap.get("role"));
            return getEnumFromValue(name);
        }
        throw new IllegalArgumentException();
    }
}
