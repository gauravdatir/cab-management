package com.cabsys.cabManagement;

public class CabStateMachine {
	public enum CabEvent {
		CAB_LOGIN,
		CAB_LOGOFF,
		TRIP_REQUESTED,
		TRIP_COMPLETED,
		CAB_FAULT
	}
	
	public enum CabStates {
		IDLE {
			@Override
			public CabStates nextState(CabEvent event)
			{
				if (event == CabEvent.TRIP_REQUESTED)
					return ON_TRIP;
				else if (event == CabEvent.CAB_FAULT)
					return MAINTANANCE;
				else if (event == CabEvent.CAB_LOGOFF)
					return OFFLINE;
				else
					return IDLE;
			}
			@Override
			public String getString()
			{
				return "IDLE";
			}
		},
		ON_TRIP{
			@Override
			public CabStates nextState(CabEvent event)
			{
				if (event == CabEvent.TRIP_COMPLETED)
					return IDLE;
				else if (event == CabEvent.CAB_FAULT)
					return MAINTANANCE;
				else
					return ON_TRIP;
			}
			@Override
			public String getString()
			{
				return "ON_TRIP";
			}
			
		},
		OFFLINE{
			@Override
			public CabStates nextState(CabEvent event)
			{
				if (event == CabEvent.CAB_LOGIN)
					return IDLE;
				else if (event == CabEvent.CAB_FAULT)
					return MAINTANANCE;
				else
					return OFFLINE;
			}
			@Override
			public String getString()
			{
				return "OFFLINE";
			}
		},
		MAINTANANCE{
			@Override
			public CabStates nextState(CabEvent event)
			{
				if (event == CabEvent.CAB_LOGOFF)
					return OFFLINE;
				else if (event == CabEvent.CAB_LOGIN)
					return IDLE;
				else
					return MAINTANANCE;
			}
			@Override
			public String getString()
			{
				return "MAINTANANCE";
			}
		};

		public CabStates nextState(CabEvent event) {
			// TODO Auto-generated method stub
			return null;
		}

		public String getString() {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
}
