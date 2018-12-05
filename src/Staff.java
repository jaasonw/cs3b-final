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
	private ArrayList<Appointment> appointments = new ArrayList<Appointment>();
	//private HashMap schedule<Date, ArrayList<Appointment>> map = new HashMap<>(); 
	
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

	public ArrayList<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(ArrayList<Appointment> appointments) {
		this.appointments = appointments;
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
	
	public void createAppointments() {
		Time interval = new Time(this.startTime.getHour(),this.getStartTime().getMinute());
		Time appointmentStart;
		Time appointmentEnd;

		while(!interval.equals(this.endTime))
		{
			appointmentStart = new Time(interval.getHour(),interval.getMinute());
			interval.addMinutes(30);
			appointmentEnd = new Time(interval.getHour(),interval.getMinute());
			Appointment newAppointment = new Appointment(appointmentStart,appointmentEnd);

			this.appointments.add(newAppointment);
		}
	}
	
	public void showAppointments()
	{
		for(int i = 0;i < this.appointments.size();i++)
		{
			System.out.print(i + ": ");
			System.out.println(this.appointments.get(i));

		}
	}
	
	
}
