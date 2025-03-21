package com.airline.controller;

import com.airline.entity.Flight;
import com.airline.entity.Ticket;
import com.airline.repository.FlightRepository;
import com.airline.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private FlightRepository flightRepository;

    // ✅ Book a ticket
    @PostMapping
    public Ticket bookTicket(@RequestParam Long flightId, @RequestBody Ticket ticket) {
        Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new RuntimeException("Flight not found"));
        ticket.setFlight(flight);
        return ticketRepository.save(ticket);
    }

    // ✅ Get ticket by ID
    @GetMapping("/{id}")
    public Ticket getTicketById(@PathVariable Long id) {
        return ticketRepository.findById(id).orElseThrow(() -> new RuntimeException("Ticket not found"));
    }

    // ✅ Cancel ticket
    @DeleteMapping("/{id}")
    public String cancelTicket(@PathVariable Long id) {
        ticketRepository.deleteById(id);
        return "Ticket Cancelled Successfully!";
}
}
