package com.akvelon.gcp.repository.mappers;

import com.akvelon.gcp.bean.dto.CinemaHall;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Andrey Kustov on 12.12.2021
 */
@Mapper
public interface CinemaHallMapper {

    List<CinemaHall> all();

    @Select("SELECT EXISTS(SELECT 1 FROM cinemahall WHERE id=#{id})")
    Boolean checkHallExists(@Param("id") Integer id);

    CinemaHall checkHallById(@Param("id") Integer id);
}
