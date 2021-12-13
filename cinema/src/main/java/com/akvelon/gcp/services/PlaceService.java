package com.akvelon.gcp.services;

import com.akvelon.gcp.bean.MovieStatus;
import com.akvelon.gcp.bean.PlaceFilter;
import com.akvelon.gcp.bean.PlaceStatus;
import com.akvelon.gcp.bean.dto.CinemaHall;
import com.akvelon.gcp.bean.dto.Movie;
import com.akvelon.gcp.bean.dto.Place;
import com.akvelon.gcp.repository.mappers.CinemaHallMapper;
import com.akvelon.gcp.repository.mappers.MovieMapper;
import com.akvelon.gcp.repository.mappers.PlaceMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.ValidationException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;


/**
 * @author Andrey Kustov on 12.12.2021
 * service for manager  place on cinema  hall
 */
@Service
@Transactional
public class PlaceService {

    @Resource
    MovieMapper movieMapper;

    @Resource
    PlaceMapper placeMapper;

    @Resource
    CinemaHallMapper cinemaHallMapper;

    /**
     * dictionary payment status
     */
    public List<PlaceStatus> getPlaceStatus() {
        return Arrays.asList(PlaceStatus.values());
    }

    /**
     * places on movies  show
     *
     * @param filter
     * @param page
     * @return
     */
    public org.springframework.data.domain.Page<Place> placesByParam(PlaceFilter filter, Pageable page) {
        //need  id  movies
        if (filter.getMovieId() == null)
            throw new ValidationException("need set  id  movies");
        //check movie
        if (!movieMapper.checkMovieExists(filter.getMovieId()))
            throw new ValidationException("movie not found");
        List<Place> content = placeMapper.searchPlacesByParam(filter, page);
        Long total = placeMapper.countPlacesByParam(filter);
        return new PageImpl(content, page, total);
    }

    /**
     * reserve place  by place id
     *
     * @param placeId
     */
    public void reservePlace(Integer placeId) {
        //check place
        Place place = placeMapper.placeById(placeId);
        reservePlace(place);
    }

    private void reservePlace(Place place) {
        if (place == null)
            throw new ValidationException("place not found");
        if (PlaceStatus.RESERVE.equals(place.getStatus()))
            throw new ValidationException("place is reserve");
        //check movies  -- must be PLAN
        Movie movie = movieMapper.movietById(place.getMovieId());
        if(!MovieStatus.PLAN.equals(movie.getStatus()))
            throw new ValidationException("movie  is  not PLAN");
        placeMapper.updatePlaceStatus(place.getId(), PlaceStatus.RESERVE);
    }

    /**
     * place set free
     * @param placeId
     */
    public void freePlace(Integer placeId) {
        placeMapper.updatePlaceStatus(placeId, PlaceStatus.FREE);
    }

    /**
     * reserve place  by number
     *
     * @param moviesId
     * @param number
     */
    public Integer reservePlace(Integer moviesId, Integer number) {
        //need  id  movies
        if (moviesId == null)
            throw new ValidationException("need set  id  movies");
        //check movie exist
        if (!movieMapper.checkMovieExists(moviesId))
            throw new ValidationException("movie not found");
        //check place
        Place place = placeMapper.placeByNumber(moviesId, number);
        reservePlace(place);
        return place.getId();
    }

    /**
     * reserve place  on movie
     * @param moviesId
     * @return
     */
    public Integer reservePlaceOnMovie(Integer moviesId){
        //need  id  movies
        if (moviesId == null)
            throw new ValidationException("need set  id  movies");
        //check movie exist
        if (!movieMapper.checkMovieExists(moviesId))
            throw new ValidationException("movie not found");
        //check place
        PlaceFilter filter = new PlaceFilter();
        filter.setMovieId(moviesId);
        filter.setStatus(PlaceStatus.FREE);
        List<Place> content = placeMapper.searchPlacesByParam(filter,  org.springframework.data.domain.PageRequest.of(0, 2));
        if(content == null || content.isEmpty())
            throw new ValidationException("free place not found");
        Place place = content.get(0);
        reservePlace(place);
        return place.getId();
    }

    /**
     * add places for movie
     * @param movie
     */
    public void addPlaceOnMovie(Movie movie){
        if(movie.getCinemaHallId() ==  null)
            throw new ValidationException("hall for  movie not set");
        CinemaHall hall = cinemaHallMapper.checkHallById(movie.getCinemaHallId());
        if(hall== null)
            throw new ValidationException("hall not found");
        if(hall== null)
            throw new ValidationException("hall not found");
        Integer capacity = hall.getCapacity();
        if(capacity == null)
            throw new ValidationException("capacity hall not set");

        IntStream.range(1,capacity).forEach(num -> {
            Place place =  new Place();
            place.setNumber(num);
            place.setMovieId(movie.getId());
            placeMapper.addPlace(place);
        });

    };

}
