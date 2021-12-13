package com.akvelon.gcp.services;

import com.akvelon.gcp.bean.TicketStatus;
import com.akvelon.gcp.bean.dto.Ticket;
import com.akvelon.gcp.repository.mappers.MovieMapper;
import com.akvelon.gcp.repository.mappers.TicketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.ValidationException;
import java.util.Arrays;
import java.util.List;

/**
 * service for manager  ticket reserve
 */
@Service
public class TicketServica {

    @Autowired
    PlaceService placeService;

    @Resource
    TicketMapper ticketMapper;

    /**
     * dictionary ticket status
     */
    public List<TicketStatus> getTicketStatus() {
        return Arrays.asList(TicketStatus.values());
    }

    private Integer saveTicket(Integer placeId) {
        Ticket ticket = new Ticket();
        ticket.setPlaceId(placeId);
        ticketMapper.addTicket(ticket);
        //TODO PAID
        return ticket.getId();
    }

    /**
     * buy ticket
     *
     * @param placeId
     * @return
     */
    public Integer buyTicket(Integer placeId) {
        placeService.reservePlace(placeId);
        return saveTicket(placeId);
    }

    /**
     * buy ticket
     *
     * @param movieId
     * @return
     */
    public Integer buyTicketOnMovie(Integer movieId) {
        Integer placeId = placeService.reservePlaceOnMovie(movieId);
        return saveTicket(placeId);
    }

    /**
     * buy ticket
     *
     * @param moviesId
     * @param number
     * @return
     */
    public Integer buyTicket(Integer moviesId, Integer number) {
        Integer placeId = placeService.reservePlace(moviesId, number);
        return saveTicket(placeId);
    }

    /**
     * отмеена  билета
     *
     * @param ticketId
     */
    public void chancelTicket(Integer ticketId) {
        Ticket ticket = ticketMapper.ticketById(ticketId);
        if (ticket == null) throw new ValidationException("ticket not found");
        //check movies  -- must be PLAN
        placeService.freePlace(ticket.getPlaceId());
        //TODO UNPAID
    }


    /**
     * get ticket  By Id
     *
     * @param ticketId
     * @return
     */
    public Ticket getTicket(Integer ticketId) {
        return ticketMapper.ticketById(ticketId);
    }
}
