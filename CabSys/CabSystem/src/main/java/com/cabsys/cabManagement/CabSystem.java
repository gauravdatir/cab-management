package com.cabsys.cabManagement;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cabsys.cabManagement.CabStateMachine.CabStates;

@Entity

@Table


public class CabSystem {

	@Id
	@Column
	int cab_Id;
	
	@Column
	CabStates cab_State;
	
	@Column
	String city_Id;
	
	@Column
	String cabLocation;
	
	@Column
	int iddle_Time;
	
	@Column
	int trip_count;
	
	@Column
	Timestamp iddle_start;

	@Column
	String driverName;
	
	public int getCab_Id() {
		return cab_Id;
	}



	public void setCab_Id(int cab_Id) {
		this.cab_Id = cab_Id;
	}





	public String getCity_Id() {
		return city_Id;
	}



	public void setCity_Id(String city_Id) {
		this.city_Id = city_Id;
	}



	public String getCabLocation() {
		return cabLocation;
	}



	public void setCabLocation(String cabLocation) {
		this.cabLocation = cabLocation;
	}




	
	public CabStates getCab_State() {
		return cab_State;
	}



	public void setCab_State(CabStates cab_State) {
		this.cab_State = cab_State;
	}




	
	public Timestamp getIddle_start() {
		return iddle_start;
	}



	public void setIddle_start(Timestamp iddle_start) {
		this.iddle_start = iddle_start;
	}



	public int getTrip_count() {
		return trip_count;
	}



	public void setTrip_count(int trip_count) {
		this.trip_count = trip_count;
	}



	public int getIddle_Time() {
		return iddle_Time;
	}



	public void setIddle_Time(int iddle_Time) {
		this.iddle_Time = iddle_Time;
	}



	public String getDriverName() {
		return driverName;
	}



	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	
	
	public CabSystem() 
	{
		
	}
	

}
