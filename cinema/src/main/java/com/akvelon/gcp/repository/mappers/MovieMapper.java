package com.akvelon.gcp.repository.mappers;

import com.akvelon.gcp.bean.MovieFilter;
import com.akvelon.gcp.bean.dto.Movie;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Andrey Kustov on 12.12.2021
 */
@Mapper
public interface MovieMapper {

    List<Movie> searchMoviesByParam(@Param("filter") MovieFilter filter, @Param("pageable") Pageable pageable);

    Long countMoviesByParam(@Param("filter") MovieFilter filter);

    void addMovie(@Param("movie") Movie movie);

    @Select("SELECT EXISTS(SELECT 1 FROM movie WHERE id=#{id})")
    Boolean checkMovieExists(@Param("id") Integer id);

    void updateMovie(@Param("movie") Movie movie);

    Movie movietById(@Param("id") Integer id);
}
