public class Appointment {
	private String reason;
	private boolean open;
	private Time start;
	private Time end;
	private Date date;

	public Appointment(Time start, Time end) {
		this.open = true;
		this.start = start;
		this.end = end;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getStartTime() {
		return this.start;
	}
	
	public void setStartTime(Time start) {
		this.start = start;
	}

	public Time getEndTime() {
		return this.end;
	}

	public void setEndTime(Time end) {
		this.end = end;
	}
	public String getReason() { return this.reason; }
	
	public void setReason(String reason) { this.reason = reason; }

	public void closeAppointment() {this.open = false;}
	
	public boolean getOpen() {return this.open;}
	
	@Override
	public String toString() {
		return this.start + " - " + this.end + " " + ((this.open) ? "OPEN" : "CLOSED");
	}

	public String toSaveableString() {
		return this.start + "\n" + this.end + "\n" + ((this.open) ? "1" : "0");
	}
}
