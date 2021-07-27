package com.cts.user.test;

import java.util.Date;
import java.util.HashSet;
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

import com.cts.admin.modal.BookFlight;
import com.cts.admin.modal.ManageAirlines;
import com.cts.admin.modal.PassengerDetails;
import com.cts.admin.repository.BookFlightRepository;
import com.cts.admin.repository.ManageAirlinesRepository;
import com.cts.user.main.UserApplication;
import com.cts.user.service.BookFlightService;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = UserApplication.class)
public class UserApplicationTest {
	
	public final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	@Autowired
	BookFlightService bookFlightService;
	
	@MockBean
	ManageAirlinesRepository airlinesRepository;
	
	@MockBean
	BookFlightRepository bookFlightRepository;

	@Test
	void testJunit() {
		Assertions.assertEquals(5, 5);
		Assertions.assertSame(5, 5);
		Assertions.assertTrue(15 > 5);
		Assertions.assertFalse(15 < 5);
	}
	
	@Test
	public void shouldGetAllManageAirlineDetails() {
		List<ManageAirlines> dummyAirlines = Stream
				.of(new ManageAirlines(new Long(101), "6E43576", "Vistara", "ASDJS", "280", "KOL", "HYD", "India", new Date(), 6000.00, new Date()),
						new ManageAirlines(new Long(102), "6E5432", "Indigo", "ASDJS", "280", "KOL", "HYD", "India", new Date(), 6000.00, new Date()),
						new ManageAirlines(new Long(103), "6E596", "Air Asia", "ASDJS", "280", "KOL", "HYD", "India", new Date(), 6000.00, new Date()),
						new ManageAirlines(new Long(104), "6E987", "GO Air", "ASDJS", "280", "KOL", "HYD", "India", new Date(), 6000.00, new Date()))
				.collect(Collectors.toList());

		Mockito.when(airlinesRepository.findAll()).thenReturn(dummyAirlines);
		List<ManageAirlines> locationList = bookFlightService.getAllFlight();
		logger.info("" + locationList);
		Assertions.assertEquals(4, locationList.size());

	}
	
	@Test
	public void shouldGetAllBookedFlight() {
		List<BookFlight> dummyFlight = Stream
				.of(new BookFlight(new Long(101), "user1@gmail.com", "9962820254", "6E43576", "Vistara", new Long(2), "DS006", 6700, new HashSet<PassengerDetails>(), new Date(), "KOL", "HYD", "3456789876", "UPI"),
						new BookFlight(new Long(101), "user1@gmail.com", "9962820254", "6E43576", "Vistara", new Long(4), "DS006", 6700, new HashSet<PassengerDetails>(), new Date(), "HYD", "KOL", "2345678", "UPI"),
						new BookFlight(new Long(101), "user1@gmail.com", "9962820254", "6E43576", "Vistara", new Long(3), "DS006", 6700, new HashSet<PassengerDetails>(), new Date(), "PUN", "BLR", "456788", "UPI"),
						new BookFlight(new Long(101), "user1@gmail.com", "9962820254", "6E43576", "Vistara", new Long(1), "DS006", 6700, new HashSet<PassengerDetails>(), new Date(), "BLR", "MAS", "9876556787", "UPI"))
				.collect(Collectors.toList());

		Mockito.when(bookFlightRepository.getByEmailId("user1@gmail.com")).thenReturn(dummyFlight);
		List<BookFlight> flightList = bookFlightService.getHistory("user1@gmail.com");
		if(!flightList.isEmpty()) {
			flightList.forEach(l -> {
				Assertions.assertEquals(l.getId(), l.getId());
			});
		}
	}
}
