package com.akvelon.gcp.security;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * @author Andrey Kustov on 12.12.2021
 */
@Mapper
public interface UserMapper {

    User getUserByUserName(@Param("userName") String userName);
    void addUser(User user);
}
