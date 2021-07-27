package com.cts.flight.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.flight.modal.DiscountModal;

public interface DiscountRepository extends JpaRepository<DiscountModal, Long>{

	DiscountModal findByDiscountNumber(String discountCode);

}
