package com.sahaj.assignment.tigerCard.pojos;

public class ZoneTravelMaster {
	
	private FromZoneToZone fromZoneToZone;
	
	private double peakHourFare;
	private double offPeakHourFare;
	
	
	private double dailyCapFare;
	private double weeklyCapFare;
	


	public double getPeakHourFare() {
		return peakHourFare;
	}


	public double getOffPeakHourFare() {
		return offPeakHourFare;
	}


	public double getDailyCapFare() {
		return dailyCapFare;
	}


	public double getWeeklyCapFare() {
		return weeklyCapFare;
	}


	private ZoneTravelMaster(ZoneTravelMasterBuilder builder)
	{
		builder.fromZoneToZone = this.fromZoneToZone;
		builder.peakHourFare = this.peakHourFare;
		builder.offPeakHourFare = this.offPeakHourFare;
		builder.dailyCapFare = this.dailyCapFare;
		builder.weeklyCapFare = this.weeklyCapFare;
	}
	
	
	public static class ZoneTravelMasterBuilder {
		
		
		private FromZoneToZone fromZoneToZone;
		
		private double peakHourFare;
		private double offPeakHourFare;
		
		
		private double dailyCapFare;
		private double weeklyCapFare;
		
		
		
		public ZoneTravelMasterBuilder fromZone(FromZoneToZone fromZoneToZone)
		{
			this.fromZoneToZone = fromZoneToZone;
			return this;
		}

		
		public ZoneTravelMasterBuilder peakHourFare(double peakHourFare)
		{
			this.peakHourFare = peakHourFare;
			return this;
		}
		
		public ZoneTravelMasterBuilder offPeakHourFare(double offPeakHourFare)
		{
			this.offPeakHourFare = offPeakHourFare;
			return this;
		}
		
		
		public ZoneTravelMasterBuilder dailyCapFare(double dailyCapFare)
		{
			this.dailyCapFare = dailyCapFare;
			return this;
		}
		
		
		public ZoneTravelMasterBuilder weeklyCapFare(double weeklyCapFare)
		{
			this.weeklyCapFare = weeklyCapFare;
			return this;
		}
		
		
		public ZoneTravelMaster build() {
            ZoneTravelMaster zoneTravelMaster =  new ZoneTravelMaster(this);
            //validateUserObject(user);
            return zoneTravelMaster;
        }
		
	
	}
	
	

}


 
