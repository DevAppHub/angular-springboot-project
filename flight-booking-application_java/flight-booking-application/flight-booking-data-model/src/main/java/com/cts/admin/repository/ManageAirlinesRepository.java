package com.cts.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.admin.modal.ManageAirlines;

public interface ManageAirlinesRepository extends JpaRepository<ManageAirlines, Long> {

	List<ManageAirlines> findByFromPlaceAndToPlace(String fromPlace, String toPlace);

}
