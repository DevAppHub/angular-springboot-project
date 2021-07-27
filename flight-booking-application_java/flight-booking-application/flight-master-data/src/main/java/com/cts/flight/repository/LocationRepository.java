package com.cts.flight.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cts.flight.modal.Location;

public interface LocationRepository extends JpaRepository<Location, Long>{

	@Query("select loc from Location loc where stateName like %:search_param% ")
	List<Location> getLocationBasedOnSearchType(@Param("search_param")String searchParam);

}
