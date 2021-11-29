package com.cts.estock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.estock.modal.StockExchange;

public interface StockExchangeRepository extends JpaRepository<StockExchange, Long> {

	
}
