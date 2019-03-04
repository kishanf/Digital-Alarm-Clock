package uk.co.kishan.Alarm;

import java.util.Calendar;
import java.util.List;

import javax.swing.SwingWorker;  
/*
SwingWorker is designed for situations where you need to have a long running task run in a background thread 
and provide updates to the UI either when done, or while processing. Subclasses of SwingWorker 
must implement the doInBackground() method to perform the background computation.
*/

public class ClockLogic extends SwingWorker<Void, Void>{ // no value 
	
	AlarmClock clock;
	private int time_hours;
	private int time_minutes;
	private int time_seconds;
	private int alarm_hours;
	private int alarm_minutes;
	private int alarm_seconds;
	private boolean alarm;
	
	public ClockLogic(AlarmClock gui){
		
		clock = gui;
	}
	@Override // clocklogic class 
	protected Void doInBackground() throws Exception { // protected so its accessible for alarm clock & uses doInbBackgroudn to run swingworker
		
		while(isCancelled() == false)
		{
			if(time_hours == alarm_hours && time_minutes == alarm_minutes && time_seconds == alarm_seconds && alarm == true){ // Checks if alarm is true to current time 
				
				clock.invokeAlarmAlert(); // Invoke to allows for lets me run another method at Runtime 
				System.out.println("WAKE UP!");	
			}			
		this.publish(); // sends data chunks straight away to be processed in the "process" method below 
		Thread.sleep(1000);
		}

		return null;
	}
	protected Void doInBackground1() throws Exception { // protected so its accessible for alarm clock & uses doInbBackgroudn to run swingworker
		
		while(isCancelled() == false)
		{
			if(time_hours == alarm_hours && time_minutes == alarm_minutes && time_seconds == alarm_seconds && alarm == true){ // Checks if alarm is true to current time 
				
				clock.invokeAlarmAlert1(); // Invoke to allows for lets me run another method at Runtime 
				System.out.println("WAKE UP!");	
			}			
		this.publish(); // sends data chunks straight away to be processed in the "process" method below 
		Thread.sleep(1000);
		}

		return null;
	}
	
	protected void process(List<Void> chunks) {
		
		//// Gets current time from the computer 
		Calendar currentTime = Calendar.getInstance();
        time_hours = currentTime.get(Calendar.HOUR_OF_DAY);
        time_minutes = currentTime.get(Calendar.MINUTE);
        time_seconds = currentTime.get(Calendar.SECOND);
         
        clock.setTime(time_hours, time_minutes, time_seconds);
	}
	
	public void setAlarm(int hours, int minutes, int second){
		alarm_hours = hours;
		alarm_minutes = minutes;
		alarm_seconds = second;
	}
	
	public void setBoolean(boolean Alarm){
		alarm = Alarm;
	}
	
}
