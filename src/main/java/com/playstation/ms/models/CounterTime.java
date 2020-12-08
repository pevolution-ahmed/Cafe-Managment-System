package com.playstation.ms.models;

public class CounterTime {
	private String hours;
	private String minutes;
	private String seconds;
	public String getHours() {
		return hours;
	}
	public void setHours(String hours) {
		this.hours = hours;
	}
	public String getMinutes() {
		return minutes;
	}
	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}
	public String getSeconds() {
		return seconds;
	}
	public void setSeconds(String seconds) {
		this.seconds = seconds;
	}
	@Override
	public String toString() {
		return this.hours +":"+this.minutes+":"+this.seconds;
	}
}
