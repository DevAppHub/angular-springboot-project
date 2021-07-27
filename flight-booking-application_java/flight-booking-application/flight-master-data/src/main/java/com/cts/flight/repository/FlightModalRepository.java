package com.cts.flight.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.flight.modal.FlightModal;

public interface FlightModalRepository extends JpaRepository<FlightModal, Long> {

	FlightModal findByFlightNumberAndFlightName(String flightNumber, String flightName);

}
