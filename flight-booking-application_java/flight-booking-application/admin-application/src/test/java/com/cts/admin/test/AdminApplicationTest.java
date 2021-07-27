package com.cts.admin.test;

import java.util.Date;
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

import com.cts.admin.api.AdminApplication;
import com.cts.admin.modal.AirlineSchedules;
import com.cts.admin.modal.ManageAirlines;
import com.cts.admin.repository.AirlineScheduleRepository;
import com.cts.admin.repository.ManageAirlinesRepository;
import com.cts.admin.service.AirlineService;


@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AdminApplication.class)
public class AdminApplicationTest {

	public final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	@Autowired
	AirlineService airlineService; 
	
	@MockBean
	ManageAirlinesRepository airlinesRepository;
	
	@MockBean
	AirlineScheduleRepository airlineScheduleRepository;
	
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
		List<ManageAirlines> locationList = airlineService.getAllAirlinesDetails();
		logger.info("" + locationList);
		Assertions.assertEquals(4, locationList.size());

	}
	
	@Test
	public void shouldGetAllAirlineSchedulesDetails() {
		List<AirlineSchedules> dummyAirlines = Stream
				.of(new AirlineSchedules(new Long(101), "6E43576", "Vistara", new Date(), "KOL", "HYD", new Long(4)),
						new AirlineSchedules(new Long(101), "6E43576", "Vistara", new Date(), "KOL", "HYD",  new Long(4)),
						new AirlineSchedules(new Long(101), "6E43576", "Vistara", new Date(), "KOL", "HYD",  new Long(4)),
						new AirlineSchedules(new Long(101), "6E43576", "Vistara", new Date(), "KOL", "HYD",  new Long(4)))
				.collect(Collectors.toList());

		Mockito.when(airlineScheduleRepository.findAll()).thenReturn(dummyAirlines);
		List<AirlineSchedules> airlineScheduleObj = airlineService.getAllAirlineSchedule();
		logger.info("" + airlineScheduleObj);
		Assertions.assertEquals(4, airlineScheduleObj.size());

	}
}
