package com.cts.estock.test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.estock.main.EstockCompanyApplication;
import com.cts.estock.modal.Company;
import com.cts.estock.modal.StockExchange;
import com.cts.estock.repository.CompanyRepository;
import com.cts.estock.repository.StockExchangeRepository;
import com.cts.estock.service.MasterDataService;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = EstockCompanyApplication.class)
public class EstockCompanyApplicationTest {
	
	@MockBean
	CompanyRepository companyRepository;
	
	@Autowired
	MasterDataService companyService;
	
	@MockBean
	StockExchangeRepository stockExchangeRepo;

	public final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	@Test
	void testJunit() {
		Assertions.assertEquals(5, 5);
		Assertions.assertSame(5, 5);
		Assertions.assertTrue(15 > 5);
		Assertions.assertFalse(15 < 5);
	}
	
	@Test
	public void shouldGetAllCompanyDetails() {
		List<Company> dummyCompany = Stream
				.of(new Company(new Long(101), "C00101", "CTS"),
						new Company(new Long(102), "C00101", "CTS"),
						new Company(new Long(103), "C00101", "CTS"),
				new Company(new Long(104), "C00101", "CTS"))
				.collect(Collectors.toList());

		Mockito.when(companyRepository.findAll()).thenReturn(dummyCompany);
		List<Company> locationList = companyService.getCompany();
		logger.info("" + locationList);
		Assertions.assertEquals(4, locationList.size());

	}
	
	@Test
	public void shouldGetAllStockDetails() {
		List<StockExchange> dummyStock = Stream
				.of(new StockExchange(new Long(101), "ST005", "BSE"),
						new StockExchange(new Long(102), "ST007", "NSE"))
				.collect(Collectors.toList());
		Mockito.when(stockExchangeRepo.findAll()).thenReturn(dummyStock);
		List<StockExchange> airlineScheduleObj = companyService.getStockExchangeList();
		logger.info("" + airlineScheduleObj);
		Assertions.assertEquals(2, airlineScheduleObj.size());

	}
}
