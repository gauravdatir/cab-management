package com.cabsys.CabManagementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cabsys.CabManagementRepository.CabEventLogRepository;
import com.cabsys.CabManagementRepository.TripRequestRepository;
import com.cabsys.cabManagement.CabEventLog;
import com.cabsys.cabManagement.TripRequest;

@Service 
public class CabEventLogService {

	@Autowired 
	CabEventLogRepository cabEventLogRepository;
	
	
	public CabEventLog saveOrUpdate(CabEventLog event)   
	{  
		int id = (int) cabEventLogRepository.count();
		event.setEvent_id(++id);
		return cabEventLogRepository.save(event);  
	}  
	
}
