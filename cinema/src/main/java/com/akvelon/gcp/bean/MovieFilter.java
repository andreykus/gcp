package com.akvelon.gcp.bean;

import com.akvelon.gcp.serializer.DateTimeDeserializer;
import com.akvelon.gcp.serializer.DateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Andrey Kustov on 12.12.2021
 */
@ApiModel(description = "filter for movies")
public class MovieFilter implements Serializable {

    @ApiModelProperty(value = "search by string")
    private String searchString;

    @ApiModelProperty(value = "status movie", allowableValues = "FINISH,PLAN,IN_ACTION,CANCEL" )
    private MovieStatus status;

    @ApiModelProperty(value = "start date")
  //  @JsonSerialize(using = DateTimeSerializer.class)
  //  @JsonDeserialize(using = DateTimeDeserializer.class)
    private LocalDateTime start;

    @ApiModelProperty(value = "end date")
 //   @JsonSerialize(using = DateTimeSerializer.class)
 //   @JsonDeserialize(using = DateTimeDeserializer.class)
    private LocalDateTime end;


    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public MovieStatus getStatus() {
        return status;
    }

    public void setStatus(MovieStatus status) {
        this.status = status;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
}
