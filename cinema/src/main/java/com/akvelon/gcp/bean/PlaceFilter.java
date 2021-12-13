package com.akvelon.gcp.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Andrey Kustov on 12.12.2021
 */
@ApiModel(description = "filter for places")
public class PlaceFilter implements Serializable {

    @ApiModelProperty(value = "status place", allowableValues = "FREE,RESERVE" )
    private PlaceStatus status;

    @ApiModelProperty(value = "id cinema movie", required = true)
    @NotNull
    private Integer movieId;

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public PlaceStatus getStatus() {
        return status;
    }

    public void setStatus(PlaceStatus status) {
        this.status = status;
    }
}
