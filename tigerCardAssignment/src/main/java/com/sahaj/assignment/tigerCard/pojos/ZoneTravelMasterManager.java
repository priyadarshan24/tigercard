package com.sahaj.assignment.tigerCard.pojos;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ZoneTravelMasterManager {
	
	
	private Map<FromZoneToZone, ZoneTravelMaster> zoneTravelMasterData = new HashMap<FromZoneToZone, ZoneTravelMaster>();
	
	
	
	public void addZoneTravelMaster(FromZoneToZone key, ZoneTravelMaster value)
	{
		if(key == null || value == null)
		{
			throw new IllegalArgumentException("ZOne Key and Zone Data Value cannot be null");
		}
		
		zoneTravelMasterData.put(key, value);
	}
	
	
	public void getZoneMasterDataForFromZoneToZone(FromZoneToZone key)
	{
		if(key == null)
		{
			throw new IllegalArgumentException("Zone Key cannot be null");
		}
		
		zoneTravelMasterData.get(key);
	}
	
	
	public Map<FromZoneToZone, ZoneTravelMaster> getZoneTravelMasterData()
	{
		return Collections.unmodifiableMap(zoneTravelMasterData);
	}
	

}
