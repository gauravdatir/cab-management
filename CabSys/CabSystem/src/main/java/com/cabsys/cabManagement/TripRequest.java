package com.cabsys.cabManagement;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table

public class TripRequest {

	public String getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}



	@Id
	@Column
	int req_id;
	
	@Column
	String city_Id;
	
	@Column
	String cabLocation;
	
	@Column
	Timestamp request_time;
	
	@Column
	String destination_city;
	
	@Column
	String requestStatus;
	
	
	public String getDestination_city() {
		return destination_city;
	}
	public void setDestination_city(String destination_city) {
		this.destination_city = destination_city;
	}
	public int getReq_id() {
		return req_id;
	}
	public void setReq_id(int req_id) {
		this.req_id = req_id;
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
	public Timestamp getRequest_time() {
		return request_time;
	}
	public void setRequest_time(Timestamp request_time) {
		this.request_time = request_time;
	}
	
	
	
	public TripRequest() 
	{
		
	}
	
}
