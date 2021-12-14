package com.akvelon.gcp.controllers;

import com.akvelon.gcp.bean.TicketStatus;
import com.akvelon.gcp.bean.dto.Ticket;
import com.akvelon.gcp.services.TicketService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * @author Andrey Kustov on 12.12.2021
 */
@Api(value = "Controller Tickets", description = "manage Tickets for movie show")
@RestControllerAdvice
@RequestMapping("/api/tickets")
public class TicketController extends AbstractRestController{

    @Autowired
    TicketService ticketService;

    /**
     * dictionary ticket status
     */
    @ApiOperation(
            value = "allow ticket status",
            notes = "method return allow ticket status")
    @ApiResponses(
            @ApiResponse(code = 200, message = "Ok", responseContainer = "List", response = TicketStatus.class)
    )
    @GetMapping("/status")
    public ResponseEntity<List<TicketStatus>> ticketStatus() {
        return ResponseEntity.ok(ticketService.getTicketStatus());
    }


    /**
     * ticket by  id
     */
    @ApiOperation(
            value = "get ticket",
            notes = "method return ticket by id")
    @ApiResponses(
            @ApiResponse(code = 200, message = "Ok", response = Ticket.class)
    )
    @GetMapping("/{ticketId}")
    public ResponseEntity<Ticket> ticket(@ApiParam(value = " ticket id", required = true) @PathVariable Integer ticketId) {
        return ResponseEntity.ok(ticketService.getTicket(ticketId));
    }

    /**
     * ticket buy
     */
    @ApiOperation(
            value = "buy ticket on  movie",
            notes = "method buy ticket on  movie")
    @ApiResponses(
            @ApiResponse(code = 200, message = "Ok", response = Integer.class)
    )
    @GetMapping("/buyOnMovie")
    public ResponseEntity<Integer> buyTicket(@ApiParam(value = "movie id", required = true) @RequestParam Integer movieId) {
        return ResponseEntity.ok(ticketService.buyTicketOnMovie(movieId));
    }

    /**
     * ticket buy
     */
    @ApiOperation(
            value = "buy ticket on  movie",
            notes = "method buy ticket on  movie")
    @ApiResponses(
            @ApiResponse(code = 200, message = "Ok", response = Integer.class)
    )
    @GetMapping("/buyOnMovieWithNumber")
    public ResponseEntity<Integer> buyTicketWithNumber(
            @ApiParam(value = "movie id", required = true) @RequestParam Integer movieId,
            @ApiParam(value = "number", required = true) @RequestParam Integer number
            ) {
        return ResponseEntity.ok(ticketService.buyTicket(movieId, number));
    }

    /**
     * ticket buy
     */
    @ApiOperation(
            value = "buy ticket on place",
            notes = "method buy ticket on place")
    @ApiResponses(
            @ApiResponse(code = 200, message = "Ok", response = Integer.class)
    )
    @GetMapping("/buyOnPlace")
    public ResponseEntity<Integer> buyTicketOnPlace(
            @ApiParam(value = "place id", required = true) @RequestParam Integer placeId
    ) {
        return ResponseEntity.ok(ticketService.buyTicket(placeId));
    }

}
