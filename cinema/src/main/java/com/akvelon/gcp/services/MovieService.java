package com.akvelon.gcp.services;

import com.akvelon.gcp.bean.MovieFilter;
import com.akvelon.gcp.bean.MovieStatus;
import com.akvelon.gcp.bean.dto.CinemaHall;
import com.akvelon.gcp.bean.dto.Movie;
import com.akvelon.gcp.repository.mappers.CinemaHallMapper;
import com.akvelon.gcp.repository.mappers.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class MovieService {

    @Resource
    CinemaHallMapper cinemaHallMapper;

    @Resource
    MovieMapper movieMapper;

    @Autowired
    PlaceService placeService;

    /**
     * all cinema hall
     *
     * @return
     */
    public List<CinemaHall> allCinemaHall() {
        return cinemaHallMapper.all();
    }

    /**
     * movies by param  on page
     *
     * @param filter
     * @return
     */
    public Page<Movie> moviesByParam(MovieFilter filter, Pageable page) {
        List<Movie> content = movieMapper.searchMoviesByParam(filter, page);
        Long total = movieMapper.countMoviesByParam(filter);
        return new PageImpl(content, page, total);

    }

    //TODO only admin may add
    /**
     * add movie
     *
     * @param movie
     */
    public Integer addMovie(Movie movie){
        //add only with status PLAN
        if(!MovieStatus.PLAN.equals(movie.getStatus()))
            throw new ValidationException("only  plan movies may add");
        //check exist hall
        if(!cinemaHallMapper.checkHallExists(movie.getCinemaHallId()))
             throw new ValidationException("cinema hall not found");
        //not add with 0 duration
        if(Integer.valueOf(0).equals( movie.getDuration()))
            throw new ValidationException("cinema hall not found");
        //check exist movie with interval data + duration in  hall and status <> CANCEL
        LocalDateTime start = movie.getDate();
        LocalDateTime end = movie.getDate().plusSeconds(movie.getDuration());
        movieMapper.addMovie(movie);
        placeService.addPlaceOnMovie(movie);
        return movie.getId();
    }

}
