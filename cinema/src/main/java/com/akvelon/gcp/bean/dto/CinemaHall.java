package com.akvelon.gcp.bean.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author Andrey Kustov on 12.12.2021
 */
@ApiModel(description = "Cinema hall")
public class CinemaHall extends Entity implements Serializable {

    @ApiModelProperty(value = "name hall")
    private String name;

    @ApiModelProperty(value = "capacity hall")
    private Integer capacity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
