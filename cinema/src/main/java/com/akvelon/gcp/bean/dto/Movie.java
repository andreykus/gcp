package com.akvelon.gcp.bean.dto;

import com.akvelon.gcp.bean.MovieStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Andrey Kustov on 12.12.2021
 */
@ApiModel(description = "Cinema movie")
public class Movie extends Entity implements Serializable {

    public Movie() {
        this.status = MovieStatus.PLAN;
        this.date =  LocalDateTime.now();
    }

    @ApiModelProperty(value = "name hall")
    private String name;

    @ApiModelProperty(value = "statusmovie", allowableValues = "FINISH,PLAN,IN_ACTION,CANCEL")
    private MovieStatus status;

    @ApiModelProperty(value = "data and time movie in  ms", required = true)
    @NotNull
    private LocalDateTime date;

    @ApiModelProperty(value = "cinema hall id", required = true)
    @NotNull
    private Integer cinemaHallId;

    @ApiModelProperty(value = "duration movie")
    private Integer duration = 0 ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MovieStatus getStatus() {
        return status;
    }

    public void setStatus(MovieStatus status) {
        this.status = status;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getCinemaHallId() {
        return cinemaHallId;
    }

    public void setCinemaHallId(Integer cinemaHallId) {
        this.cinemaHallId = cinemaHallId;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
