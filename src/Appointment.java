
public class Appointment {
	
	Person visitor;
	String visitDescription;
	
	public Appointment()
	{
		visitor = new Person();
	}
	public Appointment(Person visitor,String  description)
	{
		this.visitor = visitor;
		visitDescription = description;
	}
	public Person getVisitor() {
		return visitor;
	}
	public void setVisitor(Person visitor) {
		this.visitor = visitor;
	}
	public String getVisitDescription() {
		return visitDescription;
	}
	public void setVisitDescription(String visitDescription) {
		this.visitDescription = visitDescription;
	}
}
