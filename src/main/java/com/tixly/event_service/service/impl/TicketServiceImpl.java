package com.tixly.event_service.service.impl;

import com.tixly.event_service.service.TicketService;
import com.tixly.event_service.config.KafkaConfigProps;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    @Autowired
    private KafkaConfigProps kafkaConfigProps;


    @Override
    public void getTicket() {
        Map<String, String> test = new HashMap<>();
        test.put("Hello", "hi");
        kafkaTemplate.send(kafkaConfigProps.getTopic(), test);
    }
}
