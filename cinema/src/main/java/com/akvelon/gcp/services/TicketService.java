package com.akvelon.gcp.services;

import com.akvelon.gcp.bean.TicketStatus;
import com.akvelon.gcp.bean.dto.Ticket;
import com.akvelon.gcp.repository.mappers.MovieMapper;
import com.akvelon.gcp.repository.mappers.TicketMapper;
import com.akvelon.gcp.security.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.ValidationException;
import java.util.Arrays;
import java.util.List;

/**
 * @author Andrey Kustov on 12.12.2021
 * service for manager  ticket reserve
 */
@Service
public class TicketService extends AbstractServices {

    private static final Logger logger = LoggerFactory.getLogger(TicketService.class);

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
        User user = getCurrentUser();
        if (user != null)
            logger.info("USER:" + user.getId());
        Ticket ticket = new Ticket();
        ticket.setPlaceId(placeId);
        ticket.setUserId(user.getId());
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
