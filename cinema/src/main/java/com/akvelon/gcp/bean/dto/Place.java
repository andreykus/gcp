package com.akvelon.gcp.bean.dto;

import com.akvelon.gcp.bean.PlaceStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * @author Andrey Kustov on 12.12.2021
 */
@ApiModel(description = "Place in hall")
public class Place extends Entity implements Serializable {

    public Place(){
        this.status = PlaceStatus.FREE;
    }

    @ApiModelProperty(value = "number", required = true)
    @NotNull
    private Integer number;

    @ApiModelProperty(value = "status place", allowableValues = "FREE,RESERVE" )
    private PlaceStatus status;

    @ApiModelProperty(value = "id cinema movie", required = true)
    @NotNull
    private Integer movieId;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public PlaceStatus getStatus() {
        return status;
    }

    public void setStatus(PlaceStatus status) {
        this.status = status;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }
}
