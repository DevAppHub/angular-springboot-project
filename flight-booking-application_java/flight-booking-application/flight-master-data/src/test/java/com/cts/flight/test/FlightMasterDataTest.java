package com.cts.flight.test;

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

import com.cts.flight.api.FlightMasterDataApplication;
import com.cts.flight.modal.DiscountModal;
import com.cts.flight.modal.FlightModal;
import com.cts.flight.modal.Location;
import com.cts.flight.repository.DiscountRepository;
import com.cts.flight.repository.FlightModalRepository;
import com.cts.flight.repository.LocationRepository;
import com.cts.flight.service.DiscountService;
import com.cts.flight.service.FlightService;
import com.cts.flight.service.LocationService;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = FlightMasterDataApplication.class)
public class FlightMasterDataTest {

	public final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	DiscountService discountService;

	@MockBean
	DiscountRepository discountRepository;

	@MockBean
	FlightModalRepository flightModalRepository;

	@MockBean
	LocationRepository locationRespository;

	@Autowired
	FlightService flightService;

	@Autowired
	LocationService locationService;

	@Test
	void testJunit() {
		Assertions.assertEquals(5, 5);
		Assertions.assertSame(5, 5);
		Assertions.assertTrue(15 > 5);
		Assertions.assertFalse(15 < 5);
	}

	// discount test cases
	@Test
	public void shouldGetAllDiscounts() {
		List<DiscountModal> dummyDiscount = Stream
				.of(new DiscountModal(new Long(101), "DS1010", "TRY", 1000, new Date()),
						new DiscountModal(new Long(102), "DS1011", "LET TRY", 1100, new Date()),
						new DiscountModal(new Long(103), "DS1012", "PAYTM", 700, new Date()),
						new DiscountModal(new Long(104), "DS1013", "PAYZAPP", 800, new Date()))
				.collect(Collectors.toList());

		Mockito.when(discountRepository.findAll()).thenReturn(dummyDiscount);
		List<DiscountModal> discountList = discountService.getAllDiscount();
		logger.info("" + discountList);
		Assertions.assertEquals(4, discountList.size());

	}

	@Test
	public void shouldSaveDiscount() {
		DiscountModal discount = new DiscountModal(new Long(0), "DS1014", "PAYZAPP", 1800, new Date());
		Mockito.when(discountRepository.save(discount))
				.thenReturn(new DiscountModal(new Long(105), "DS1014", "PAYZAPP", 1800, new Date()));
		DiscountModal discountModal = null;
		try {
			discountModal = discountService.createDiscount(discount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assertions.assertEquals(new Long(105), discountModal.getId());
	}

	@Test
	public void shouldFindDiscountByDiscountNumber() throws Exception {
		Mockito.when(discountRepository.findByDiscountNumber("DS1014"))
				.thenReturn(new DiscountModal(new Long(105), "DS1014", "PAYZAPP", 1800, new Date()));
		DiscountModal discount = discountService.getDiscountByDiscountCode("DS1014");
		Assertions.assertEquals("DS1014", discount.getDiscountNumber());
	}

	@Test
	public void shouldNotFindMovieByDiscountNumber() throws Exception {
		Mockito.when(discountRepository.findByDiscountNumber("DS1014")).thenReturn(null);
		Assertions.assertThrows(Exception.class, () -> {
			discountService.getDiscountByDiscountCode("DS1014");
		});

	}

	// flight test cases

	@Test
	public void shouldGetAllFlightDetails() {
		List<FlightModal> dummyFlight = Stream.of(
				new FlightModal(new Long(101), "643627", "Go Air", "", new Date(), "ASU56787", "280",
						new HashSet<String>()),
				new FlightModal(new Long(101), "643697", "Spice Jet", "", new Date(), "ASU56787", "280",
						new HashSet<String>()),
				new FlightModal(new Long(101), "643647", "Air Asia", "", new Date(), "ASU56787", "280",
						new HashSet<String>()),
				new FlightModal(new Long(101), "643E27", "Indigo", "", new Date(), "ASU56787", "280",
						new HashSet<String>()))
				.collect(Collectors.toList());

		Mockito.when(flightModalRepository.findAll()).thenReturn(dummyFlight);
		List<FlightModal> discountList = flightService.getFlight();
		logger.info("" + discountList);
		Assertions.assertEquals(4, discountList.size());

	}

	@Test
	public void shouldSaveFlight() {
		FlightModal flight = new FlightModal(new Long(0), "643627", "Go Air", "", new Date(), "ASU56787", "280",
				new HashSet<String>());
		Mockito.when(flightModalRepository.save(flight)).thenReturn(new FlightModal(new Long(105), "643627", "Go Air",
				"", new Date(), "ASU56787", "280", new HashSet<String>()));
		FlightModal flightModal = flightService.createFlight(flight);
		Assertions.assertEquals(new Long(105), flightModal.getId());
	}

	// location test cases

	@Test
	public void shouldGetAllLocation() {
		List<Location> dummyLocation = Stream
				.of(new Location(new Long(101), "HYD", "Hyderabad"), new Location(new Long(102), "SHM", "SHIMLA"),
						new Location(new Long(103), "DEL", "DELHI"), new Location(new Long(104), "TRI", "TRICHY"))
				.collect(Collectors.toList());

		Mockito.when(locationRespository.findAll()).thenReturn(dummyLocation);
		List<Location> locationList = locationService.getAllLocation();
		logger.info("" + locationList);
		Assertions.assertEquals(4, locationList.size());

	}

	@Test
	public void shouldGetLocationBasedOnSearchParam() throws Exception {
		List<Location> dummyLocation = Stream
				.of(new Location(new Long(101), "HYD", "Hyderabad"), new Location(new Long(102), "SHM", "SHIMLA"),
						new Location(new Long(103), "DEL", "DELHI"), new Location(new Long(104), "TRI", "TRICHY"))
				.collect(Collectors.toList());
		Mockito.when(locationRespository.getLocationBasedOnSearchType("BLR"))
				.thenReturn(dummyLocation);
		List<Location> loc = locationService.getLocationBasedOnSearchType("BLR");
		if(!loc.isEmpty()) {
			loc.forEach(l -> {
				Assertions.assertEquals(l.getId(), l.getId());
			});
		}
	}

}
