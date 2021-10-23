package com.sahaj.assignment.tigerCard.pojos;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum ZoneTravelMasterManager 
{
	
	INSTANCE;
	
	private Map<FromZoneToZone, ZoneTravelMaster> zoneTravelMasterData = new HashMap<FromZoneToZone, ZoneTravelMaster>();
	
	
	
	public void addZoneTravelMaster(FromZoneToZone key, ZoneTravelMaster value)
	{
		if(key == null || value == null)
		{
			throw new IllegalArgumentException("Zone Key and Zone Data Value cannot be null");
		}
		
		zoneTravelMasterData.put(key, value);
	}
	
	
	public ZoneTravelMaster getZoneMasterDataForFromZoneToZone(FromZoneToZone key)
	{
		if(key == null)
		{
			throw new IllegalArgumentException("Zone Key cannot be null");
		}
		
		return zoneTravelMasterData.get(key);
	}
	
	
	public Map<FromZoneToZone, ZoneTravelMaster> getZoneTravelMasterData()
	{
		return Collections.unmodifiableMap(zoneTravelMasterData);
	}
	

}
