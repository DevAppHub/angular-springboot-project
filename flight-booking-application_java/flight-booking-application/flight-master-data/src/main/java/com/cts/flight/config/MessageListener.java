package com.cts.flight.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.cts.flight.modal.FlightModal;
import com.cts.flight.modal.FlightPojo;
import com.cts.flight.service.FlightService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MessageListener {
	
	@Autowired
	FlightService flightService;

	private static final Logger logger = LoggerFactory.getLogger(MessageListener.class);

	public static final String GROUP_ID_STRING = "group-id-string-1";
	public static final String TOPIC_NAME = "flight_application";
	
	@KafkaListener(groupId = MessageListener.GROUP_ID_STRING, topics = MessageListener.TOPIC_NAME)
	public void receivedMessage(String message) throws JsonProcessingException {
		logger.info("Json message received using Kafka listener " + message);
		ObjectMapper mapper = new ObjectMapper();
		FlightPojo flightPojo = mapper.readValue(message, FlightPojo.class);
		FlightModal flightModal=new FlightModal();
		flightModal.setFlightName(flightPojo.getFlightName());
		flightModal.setFlightNumber(flightPojo.getFlightNumber());
		flightModal.setManufacturedDate(flightPojo.getManufacturedDate());
		flightModal.setModalNumber(flightPojo.getModalNo());
		flightModal.setNoOfSeats(flightPojo.getNoOfSeats());
		flightService.createFlight(flightModal);
	}
}
