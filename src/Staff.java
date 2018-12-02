/**
 * Staff
 */
public class Staff extends Person
{
	private Time startTime;
	private Time endTime;
	private String roomNum;
	
	public Staff(String name, String id, String phoneNum, String email,String roomNum, int[] startTime, int[] endTime)
	{
		super(name,id,phoneNum,email);
		this.roomNum = roomNum;
		this.startTime = new Time(startTime[0],startTime[1]);
		this.endTime = new Time(endTime[0],endTime[1]);
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
	
}
