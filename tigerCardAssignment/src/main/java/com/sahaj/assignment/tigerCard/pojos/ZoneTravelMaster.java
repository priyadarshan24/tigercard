package com.sahaj.assignment.tigerCard.pojos;

public class ZoneTravelMaster {
	
	private String fromZone;
	private String toZone;
	
	private double peakHourFare;
	private double offPeakHourFare;
	
	
	private double dailyCapFare;
	private double weeklyCapFare;
	
	
	
	private ZoneTravelMaster(ZoneTravelMasterBuilder builder)
	{
		builder.fromZone = this.fromZone;
		builder.toZone = this.toZone;
		builder.peakHourFare = this.peakHourFare;
		builder.offPeakHourFare = this.offPeakHourFare;
		builder.dailyCapFare = this.dailyCapFare;
		builder.weeklyCapFare = this.weeklyCapFare;
	}
	
	
	public static class ZoneTravelMasterBuilder {
		
		
		private String fromZone;
		private String toZone;
		
		private double peakHourFare;
		private double offPeakHourFare;
		
		
		private double dailyCapFare;
		private double weeklyCapFare;
		
		
		
		public ZoneTravelMasterBuilder fromZone(String fromZone)
		{
			this.fromZone = fromZone;
			return this;
		}
		
		public ZoneTravelMasterBuilder toZone(String toZone)
		{
			this.toZone = fromZone;
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


 
