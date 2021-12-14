package com.akvelon.gcp.controllers;

import com.akvelon.gcp.bean.MovieFilter;
import com.akvelon.gcp.bean.dto.CinemaHall;
import com.akvelon.gcp.bean.dto.Movie;
import com.akvelon.gcp.services.MovieService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Andrey Kustov on 12.12.2021
 * movie show controller
 */
@Api(value = "Controller Movie show", description = "manage Movie show")
@RestControllerAdvice
@RequestMapping("/api/movies")
public class MovieController extends AbstractRestController{

    @Autowired
    MovieService movieService;

    /**
     * all cinema  hall
     */
    @ApiOperation(
            value = "allow cinema  halls",
            notes = "method return allow cinema  halls")
    @ApiResponses(
            @ApiResponse(code = 200, message = "Ok", responseContainer = "List", response = CinemaHall.class)
    )
    @GetMapping("/halls")
    public ResponseEntity<List<CinemaHall>> allowHalls() {
        return ResponseEntity.ok(movieService.allCinemaHall());
    }


    /**
     * list movies by param
     */
    @ApiOperation(
            value = "list movies by param",
            notes = "method return movies by param")
    @ApiResponses(
            @ApiResponse(code = 200, message = "Ok", responseContainer = "List", response = Movie.class)
    )
    @PostMapping("/list")
    public ResponseEntity<org.springframework.data.domain.Page<Movie>> getMovies(@ApiParam("filter") @RequestBody(required = false) MovieFilter filter,
                                                                                 @ApiParam("page num") @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                                                 @ApiParam("page size") @RequestParam(value = "pagesize", required = false, defaultValue = "10") Integer pagesize
    ) {
        return ResponseEntity.ok(
                movieService.moviesByParam(filter, org.springframework.data.domain.PageRequest.of(page, pagesize))
        );
    }

    //TODO ONLY FOR ROLE ADMIN
    /**
     * add movie
     */
    @ApiOperation(
            value = "add movie ",
            notes = "method add movie")
    @ApiResponses(
            @ApiResponse(code = 200, message = "Ok", response = Integer.class)
    )
    @PostMapping("/add")
    public ResponseEntity<Integer> addMovie(@ApiParam("movie") @RequestBody(required = true) Movie movie
    ) {
        return ResponseEntity.ok(
                movieService.addMovie(movie));
    }

}
