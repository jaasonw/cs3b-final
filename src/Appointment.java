public class Appointment {
	private Person visitor;
	private String reason;
	private boolean open;
	private Time start;
	private Time end;
	
	public Appointment(Time start, Time end) {
		this.visitor = new Person();
		this.open = true;
		this.start = start;
		this.end = end;
	}
	
	public Person getVisitor() { return visitor; }
	public void setVisitor(Person visitor) { this.visitor = visitor; }
	public String getReason() { return this.reason; }
	public void setReason(String reason) { this.reason = reason; }

	@Override
	public String toString() {
		return start + " - " + end + " " + (this.open) ? "OPEN" : "CLOSED";
	}
}
