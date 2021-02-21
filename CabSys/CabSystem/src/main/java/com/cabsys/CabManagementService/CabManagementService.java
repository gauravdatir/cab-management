package com.cabsys.CabManagementService;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;  
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.cabsys.cabManagement.CabEventLog;
import com.cabsys.cabManagement.CabStateMachine;
import com.cabsys.cabManagement.CabStateMachine.CabEvent;
import com.cabsys.cabManagement.CabStateMachine.CabStates;
import com.cabsys.cabManagement.CabSystem;
import com.cabsys.cabManagement.TripRequest;
import com.cabsys.CabManagementRepository.CabManagementRepository;

@Service 
public class CabManagementService {

	@Autowired 
	CabManagementRepository cabManagementRepo;

	//getting all cab_State record by using the method findaAll() of CrudRepository
	public List<CabSystem> getAllCabSystems()   
	{  
		List<CabSystem> cabSysList = new ArrayList<CabSystem>();  
		//List<CabSystem> cab_State = null;
		cabManagementRepo.findAll().forEach(cabSys1 -> cabSysList.add(cabSys1));  
		return cabSysList;  
	}


	public List<CabSystem> getAllBookedCabs()   
	{  
		List<CabSystem> bookedCabs = new ArrayList<CabSystem>();  
		//List<CabSystem> cab_State = null;
		List<CabSystem> allCabs = (List<CabSystem>) cabManagementRepo.findAll();
		for (CabSystem cabSystem : allCabs) {
			if (cabSystem.getCab_State() == CabStateMachine.CabStates.ON_TRIP)
			{
				bookedCabs.add(cabSystem);
			}
		}
		return bookedCabs;  
	}

	//getting a specific record by using the method findById() of CrudRepository  
	public CabSystem geCabSysById(int id)   
	{  
		return cabManagementRepo.findById(id).get();  
	}  

	public CabStates getCabStateById(int id)
	{
		return cabManagementRepo.findById(id).get().getCab_State();
	}

	public CabStates setCabStateById(int id, CabStates state)
	{
		Optional<CabSystem> curCab = cabManagementRepo.findById(id);
		curCab.get().setCab_State(state);

		if (state == CabStates.IDLE)
		{
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			curCab.get().setIddle_start(timestamp);
		}
		if (state ==CabStates.OFFLINE)
		{
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			curCab.get().setIddle_start(timestamp);
			curCab.get().setIddle_Time(0);
		}

		cabManagementRepo.save(curCab.get());
		return cabManagementRepo.findById(id).get().getCab_State();

	}


	public String setCabCityById(int id, String city)
	{
		Optional<CabSystem> curCab = cabManagementRepo.findById(id);
		curCab.get().setCity_Id(city);
		cabManagementRepo.save(curCab.get());
		return city;
	}

	//saving a specific record by using the method save() of CrudRepository  
	public CabSystem saveOrUpdate(CabSystem cabSys)   
	{  
		int id = (int) cabManagementRepo.count();
		cabSys.setCab_Id(++id);
		return cabManagementRepo.save(cabSys);  
	}  


	//updating a record  
	public CabSystem update(CabSystem cabSys, int cabId)   
	{  
		Optional<CabSystem> curCab = cabManagementRepo.findById(cabId);
		if (curCab.get().getCab_Id() == cabSys.getCab_Id())
		{
			cabManagementRepo.save(cabSys);
			return curCab.get();
		}
		return null;

	}

	public  List<CabSystem> getCabListByCity(String city)
	{
		List<CabSystem> cityCabs = new ArrayList<CabSystem>();  
		List<CabSystem> allCabs = (List<CabSystem>) cabManagementRepo.findAll();
		for (CabSystem cabSystem : allCabs) {
			if (cabSystem.getCity_Id().compareTo(city) ==0)
			{
				cityCabs.add(cabSystem);
			}
		}	
		return cityCabs;
	}

	public  List<CabSystem> getCabListByStateCity(String city, CabStates state)
	{
		List<CabSystem> cityAvailableCabs = new ArrayList<CabSystem>();  
		List<CabSystem> allCabs = (List<CabSystem>) cabManagementRepo.findAll();
		for (CabSystem cabSystem : allCabs) {
			if (city.compareToIgnoreCase(cabSystem.getCity_Id()) == 0)
			{
				if (cabSystem.getCab_State() == state)
					cityAvailableCabs.add(cabSystem);
			}
		}	
		return cityAvailableCabs;
	}



	public CabSystem bookCab(TripRequest request)
	{
		CabSystem cabS = null;
		List<CabSystem> cityAvailableCabs = getCabListByStateCity(request.getCity_Id(), CabStates.IDLE);
		int bookedCabId = -1;
		long iddleTime = 1000000000;
		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
		for (CabSystem cabSystem : cityAvailableCabs) {
			if (cabSystem.getCabLocation().compareTo(request.getCabLocation()) == 0)
			{
				long iddleTimeTemp = currentTimestamp.getTime() - cabSystem.getIddle_start().getTime();
				if (iddleTimeTemp < iddleTime)
				{
					bookedCabId = cabSystem.getCab_Id();
				}
			}
		}
		if (bookedCabId != -1)
		{
			cabS = cabManagementRepo.findById(bookedCabId).get();
			CabStates newState = cabS.getCab_State().nextState(CabEvent.TRIP_REQUESTED);
			cabS.setCab_State(newState);
			cabS.setTrip_count(cabS.getTrip_count() + 1);
			cabS.setCity_Id(request.getDestination_city());
			cabManagementRepo.save(cabS);
		}

		return cabS;
	}

	public String processEvent(CabEventLog event)
	{
		CabSystem curCab = cabManagementRepo.findById(event.getCab_id()).get();

		CabStates curState = curCab.getCab_State();
		CabStates nextState = curState.nextState(event.getEvent());
		curCab.setCab_State(nextState);
		
		cabManagementRepo.save(curCab);
		return curCab.getCab_State().toString();
	}

}
