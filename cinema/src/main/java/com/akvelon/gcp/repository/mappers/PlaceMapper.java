package com.akvelon.gcp.repository.mappers;


import com.akvelon.gcp.bean.PlaceFilter;
import com.akvelon.gcp.bean.PlaceStatus;
import com.akvelon.gcp.bean.dto.Place;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Andrey Kustov on 12.12.2021
 */
@Mapper
public interface PlaceMapper {

    List<Place> searchPlacesByParam(@Param("filter") PlaceFilter filter, @Param("pageable") Pageable pageable);

    Long countPlacesByParam(@Param("filter") PlaceFilter filter);

    @Select("SELECT EXISTS(SELECT 1 FROM place WHERE id=#{id})")
    Boolean checkPlaceExists(@Param("id") Integer id);

    void deletePlaceById(@Param("id") Integer id);

    void updatePlaceStatus(@Param("id") Integer id, @Param("status") PlaceStatus status);

    Place placeById(@Param("id") Integer id);

    Place placeByNumber(@Param("moviesId") Integer moviesId, @Param("number") Integer number);

    void addPlace(@Param("place") Place place);
}
