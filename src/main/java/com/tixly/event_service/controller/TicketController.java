package com.tixly.event_service.controller;

import com.tixly.event_service.service.impl.TicketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController {
   @Autowired
   private TicketServiceImpl ticketServiceImpl;
    @GetMapping
    public ResponseEntity<?> getTicket(){
        ticketServiceImpl.getTicket();
        return ResponseEntity.ok("Send");
    }
}
