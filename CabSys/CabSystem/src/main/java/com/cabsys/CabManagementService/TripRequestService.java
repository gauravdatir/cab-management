package com.cabsys.CabManagementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cabsys.cabManagement.CabEventLog;
import com.cabsys.cabManagement.CabSystem;
import com.cabsys.cabManagement.TripRequest;
import com.cabsys.cabManagement.CabStateMachine.CabEvent;
import com.cabsys.CabManagementRepository.TripRequestRepository;


@Service 
public class TripRequestService {

	@Autowired 
	TripRequestRepository tripRequestRepository;
	
	
	
	public TripRequest saveOrUpdate(TripRequest request)   
	{  
		int id = (int) tripRequestRepository.count();
		request.setReq_id(++id);
		return tripRequestRepository.save(request);  
	}  
	
	
	public void processEvent(CabEventLog event)
	{
		if (event.getEvent() == CabEvent.TRIP_COMPLETED)
		{
			TripRequest req = tripRequestRepository.findById(event.getRequest_id()).get();
			req.setRequestStatus("Completed");	
		}
	}
}