package com.cabsys.cabManagement;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cabsys.cabManagement.CabStateMachine.CabEvent;

@Entity
@Table
public class CabEventLog {

	public CabEventLog() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@Column
	int event_id;
	
	public int getEvent_id() {
		return event_id;
	}

	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}

	public CabEvent getEvent() {
		return event;
	}

	public void setEvent(CabEvent event) {
		this.event = event;
	}

	public int getCab_id() {
		return cab_id;
	}

	public void setCab_id(int cab_id) {
		this.cab_id = cab_id;
	}

	public int getRequest_id() {
		return request_id;
	}

	public void setRequest_id(int request_id) {
		this.request_id = request_id;
	}

	@Column
	CabEvent event;
	
	@Column
	int cab_id;
	
	@Column
	int request_id;
	
}
