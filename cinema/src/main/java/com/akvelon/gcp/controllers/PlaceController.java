package com.akvelon.gcp.controllers;

import com.akvelon.gcp.bean.MovieFilter;
import com.akvelon.gcp.bean.PlaceFilter;
import com.akvelon.gcp.bean.PlaceStatus;
import com.akvelon.gcp.bean.dto.Movie;
import com.akvelon.gcp.bean.dto.Place;
import com.akvelon.gcp.services.PlaceService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author Andrey Kustov on 12.12.2021
 */
@Api(value = "Controller Places", description = "manage place in  hall")
@RestControllerAdvice
@RequestMapping("/api/places")
public class PlaceController {

    @Autowired
    PlaceService placeService;


    /**
     * dictionary Place status
     */
    @ApiOperation(
            value = "allow status place",
            notes = "method return allow status place")
    @ApiResponses(
            @ApiResponse(code = 200, message = "Ok", responseContainer = "List", response = PlaceStatus.class)
    )
    @GetMapping("/status")
    public ResponseEntity<List<PlaceStatus>> paymentStatus() {
        return ResponseEntity.ok(placeService.getPlaceStatus());
    }

    /**
     * list places on movie by param
     */
    @ApiOperation(
            value = "list places on movie by param",
            notes = "method return places on movie by param")
    @ApiResponses(
            @ApiResponse(code = 200, message = "Ok", responseContainer = "List", response = Place.class)
    )
    @PostMapping("/list")
    public ResponseEntity<org.springframework.data.domain.Page<Place>> getPlaces(@ApiParam("filter") @RequestBody(required = false) PlaceFilter filter,
                                                                                 @ApiParam("page num") @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                                                 @ApiParam("page size") @RequestParam(value = "pagesize", required = false, defaultValue = "10") Integer pagesize
    ) {
        return ResponseEntity.ok(
                placeService.placesByParam(filter, org.springframework.data.domain.PageRequest.of(page, pagesize))
        );
    }

}
