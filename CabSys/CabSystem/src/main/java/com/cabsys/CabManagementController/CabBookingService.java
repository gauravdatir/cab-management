package com.cabsys.CabManagementController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cabsys.cabManagement.CabSystem;
import com.cabsys.cabManagement.TripRequest;
import com.cabsys.CabManagementService.CabManagementService;
import com.cabsys.CabManagementService.TripRequestService;

@RestController
@RequestMapping("/CabBooking")
public class CabBookingService {

	@Autowired
	CabManagementService cabManagementService;
	
	@Autowired
	TripRequestService tripRequestService;
	//creating a get mapping that retrieves all the cab_State detail from the database
	
	@GetMapping
	public List<CabSystem> getAllBookedCabs()
	{
		return cabManagementService.getAllBookedCabs();
	}
	
	@PostMapping
	public CabSystem bookCab(@RequestBody TripRequest request)
	{
		tripRequestService.saveOrUpdate(request);
		return cabManagementService.bookCab(request);
	}
	
}
