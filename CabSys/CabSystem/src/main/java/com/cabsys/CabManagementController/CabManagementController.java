package com.cabsys.CabManagementController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cabsys.cabManagement.CabEventLog;
import com.cabsys.cabManagement.CabStateMachine;
import com.cabsys.cabManagement.CabStateMachine.CabEvent;
import com.cabsys.cabManagement.CabStateMachine.CabStates;
import com.cabsys.cabManagement.CabSystem;
import com.cabsys.CabManagementService.CabEventLogService;
import com.cabsys.CabManagementService.CabManagementService;
import com.cabsys.CabManagementService.TripRequestService;


@RestController
@RequestMapping("/CabSystem")
public class CabManagementController {
	
	@Autowired
	CabManagementService cabManagementService;
	//creating a get mapping that retrieves all the cab_State detail from the database
	@Autowired
	TripRequestService tripRequestService;
	
	@Autowired
	CabEventLogService cabEventLogService;
	
	@GetMapping
	
	public List<CabSystem> getAllCabSystems()
	{
		return cabManagementService.getAllCabSystems();
	}
	
	//creating a get mapping that retrieves the detail of a specific cab_State
	@GetMapping("/{id}")  
	public CabSystem getCabSys(@PathVariable("id") int id)   
	{  
	return cabManagementService.geCabSysById(id);  
	}

	
	//creating post mapping that post the cab_State details in the database  
	@PostMapping  
	public CabSystem saveCabSystem(@RequestBody CabSystem cabSystem)   
	{  
		// set cab state to OFFLINE after registration.
		cabSystem.setCab_State(CabStateMachine.CabStates.OFFLINE);
		return cabManagementService.saveOrUpdate(cabSystem);  
	}
	
	@PostMapping ("/login")
	public CabStates login(@RequestBody int id)
	{
		CabEventLog cabEvent = new CabEventLog();
		cabEvent.setEvent(CabEvent.CAB_LOGIN);
		cabEvent.setCab_id(id);
		cabEventLogService.saveOrUpdate(cabEvent);
		return cabManagementService.setCabStateById(id, CabStates.IDLE);
	}
	
	@PostMapping ("/logout")
	public CabStates logout(@RequestBody int id)
	{
		CabEventLog cabEvent = new CabEventLog();
		cabEvent.setEvent(CabEvent.CAB_LOGOFF);
		cabEvent.setCab_id(id);
		cabEventLogService.saveOrUpdate(cabEvent);
		return cabManagementService.setCabStateById(id, CabStates.OFFLINE);
	}
	
	
	//creating put mapping that updates the cab detail   
	@PutMapping("/{id}")  
	public CabSystem update(@RequestBody CabSystem cabSystem, @PathVariable("id") int id)   
	{  
		return cabManagementService.update(cabSystem, id);  

	}
	
	@GetMapping("/{id}/cab_State")  
	public CabStates updateState(@PathVariable("id") int id)   
	{  
		return cabManagementService.getCabStateById(id);

	}
	@PutMapping("/{id}/cab_State")  
	public CabStates updateState(@RequestBody CabStates state, @PathVariable("id") int id)   
	{  
		return cabManagementService.setCabStateById(id, state);

	}
	
	@PutMapping("/{id}/city_Id")  
	public String updateCity(@RequestBody String city, @PathVariable("id") int id)   
	{  
		return cabManagementService.setCabCityById(id, city);

	}
	
	@PostMapping("/event")
	public String postEvent(@RequestBody CabEventLog cabEvent)   
	{  
		cabEventLogService.saveOrUpdate(cabEvent);
		tripRequestService.processEvent(cabEvent);
		return cabManagementService.processEvent(cabEvent);
	}
	
}
