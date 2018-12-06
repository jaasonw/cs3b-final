/**
 * Staff
 */

import java.util.ArrayList;
import java.util.HashMap; 

public class Staff extends Person
{
	private Time startTime;
	private Time endTime;
	private String roomNum;
	private ArrayList<Date> dates = new ArrayList<Date>();
	private HashMap<Date, ArrayList<Appointment>> schedule = new HashMap<>(); 
	
	public Staff(String name, String id, String phoneNum, String email,String roomNum, int[] startTime,String startAmPm, int[] endTime,String endAmPm)
	{
		super(name,id,phoneNum,email);
		this.roomNum = roomNum;

		if (startAmPm.equals("AM"))
			this.startTime = new Time(startTime[0],startTime[1]);
		else
			this.startTime = new Time(startTime[0] + 12,startTime[1]);
		
		if (endAmPm.equals("AM"))
			this.endTime = new Time(endTime[0],endTime[1]);
		else
			this.endTime = new Time(endTime[0] + 12,endTime[1]);
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	
	public ArrayList<Appointment> createAppointments(Date date) {
		Time interval = new Time(this.startTime.getHour(),this.getStartTime().getMinute());
		Time appointmentStart;
		Time appointmentEnd;
		ArrayList<Appointment> appointmentList = new ArrayList<Appointment>();

		while(!interval.equals(this.endTime))
		{
			appointmentStart = new Time(interval.getHour(),interval.getMinute());
			interval.addMinutes(30);
			appointmentEnd = new Time(interval.getHour(),interval.getMinute());
			Appointment newAppointment = new Appointment(appointmentStart,appointmentEnd);
			newAppointment.setDate(date);
			appointmentList.add(newAppointment);
		}
		
		return appointmentList;
	}
	
	public void createSchedule() {
		final int APPOINTMENT_RANGE = 14; //You can change this number depending on how many days in advance you can schedule an appointment
		
		for(int i = 0; i < APPOINTMENT_RANGE;i++)
		{
			Date today = new Date();
			today.addDays(i);
			this.dates.add(today);
			
			this.schedule.put(today, createAppointments(today));
		}
	}
	
	public ArrayList<Date> getDates() {
		return dates;
	}

	public void setDates(ArrayList<Date> dates) {
		this.dates = dates;
	}

	public void showDates() {
		
		for(int i = 0;i < this.dates.size();i++) {
			System.out.println(i+1 + ". " + this.dates.get(i));
		}
	}
	
	public void showTimes(Date specifiedDate) {
		
		for(int i = 0;i < this.schedule.get(specifiedDate).size();i++)
		{
			System.out.println((i+1) + ". " + this.schedule.get(specifiedDate).get(i));
		}
	}
	
	public ArrayList<Appointment> getAppointments(Date specifiedDate){
		
		return this.schedule.get(specifiedDate);
		
	}
}
