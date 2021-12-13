package com.akvelon.gcp.bean.dto;

import com.akvelon.gcp.bean.TicketStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Andrey Kustov on 12.12.2021
 */
@ApiModel(description = "Ticket on movie")
public class Ticket extends Entity implements Serializable {

    public Ticket(){
        this.status = TicketStatus.ON_PAID;
    }

    @ApiModelProperty(value = "statusmovie", allowableValues = "ON_PAID,PAID(,CANCEL")
    private TicketStatus status;

    @ApiModelProperty(value = "id place", required = true)
    @NotNull
    private Integer placeId;

    @ApiModelProperty(value = "number")
    private Integer number;

    @ApiModelProperty(value = "cinema movie show name")
    private String movieName;

    @ApiModelProperty(value = "hall name")
    private String hallName;

    @ApiModelProperty(value = "data event")
    private LocalDateTime date;

    @ApiModelProperty(value = "user id")
    private Integer userId;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public Integer getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Integer placeId) {
        this.placeId = placeId;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
