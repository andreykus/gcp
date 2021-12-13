package com.akvelon.gcp.repository.mappers;


import com.akvelon.gcp.bean.dto.Ticket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TicketMapper {

    void addTicket(@Param("ticket") Ticket ticket);

    Ticket ticketById(@Param("id") Integer id);
}
