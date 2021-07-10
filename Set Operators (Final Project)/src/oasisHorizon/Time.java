package oasisHorizon;



public class Time {
	
	private int second;
	private int minute;
	private int hour;
	
	public Time(int second, int minute, int hour){
		this.setTime(second, minute, hour);
	}
	
	public Time() {
		this.second = 0;
		this.minute = 0;
		this.hour = 0;
	}
	
	public void setSecond(int second) {
		if(second <= 60 && second >= 0) {
			this.second = second;
		}
		else {
			throw new IllegalArgumentException("Invalid Seconds!");
		}
	}
	
	public void setMinute(int minute) {
		if(minute <= 60 && minute >= 0) {
			this.minute = minute;
		}
		else {
			throw new IllegalArgumentException("Invalid Minutes!");
		}
	}
	
	public void setHour(int hour) {
		if(hour <= 24 && hour >= 0) {
			this.hour = hour;
		}
		else {
			throw new IllegalArgumentException("Invalid Hours!");
		}
	}
	
	public void setTime(int second, int minute, int hour) {
		this.setSecond(second);
		this.setMinute(minute);
		this.setHour(hour);
	}
	
	public int getSecond() {
		return this.second;
	}
	
	public int getMinute() {
		return this.minute;
	}
	
	public int getHour() {
		return this.hour;
	}
	
	public String getTime() {
		if(hour < 10) {
			if(minute < 10) {
				if(second < 10) {
					return "0" + hour + ":0" + minute + ":0" + second;
				}
				else {
					return "0" + hour + ":0" + minute + ":" + second;
				}
			}
			else {
				if(second < 10) {
					return "0" + hour + ":" + minute + ":0" + second;
				}
				else {
					return "0" + hour + ":" + minute + ":" + second;
				}
			}
		}
		else {
			if(minute < 10) {
				if(second < 10) {
					return "" + hour + ":0" + minute + ":0" + second;
				}
				else {
					return "" + hour + ":0" + minute + ":" + second;
				}
			}
			else {
				if(second < 10) {
					return "" + hour + ":" + minute + ":0" + second;
				}
				else {
					return "" + hour + ":" + minute + ":" + second;
				}
			}
		}
		
	}

	
	public String toString() {
		return String.format("%02d:%02d:%02d", hour, minute, second);
	}
	
	public Time nextSecond() {
		second++;
		if(second == 60) {
			second = 0;
			minute++;
			if(minute == 60) {
				minute = 0;
				hour++;
				if(hour == 24) {
					hour = 0;
				}
			}
		}
		return this;
	}
}