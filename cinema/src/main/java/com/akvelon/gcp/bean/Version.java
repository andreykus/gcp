package com.akvelon.gcp.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author Andrey Kustov on 12.12.2021
 */
@ApiModel(description = "version")
public class Version implements Serializable {

    public Version() {
    }

    public Version(String number, String module) {
        this.number = number;
        this.module = module;
    }

    /**
     * number version
     */
    @ApiModelProperty(value = "version number")
    private String number;

    /**
     * name  service
     */
    @ApiModelProperty(value = "name module")
    private String module;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


}
