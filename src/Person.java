/**
 * This class will represent a person with a name
 */

import java.util.ArrayList;

public class Person
{
	private String name;
	private String id;
	private String phoneNum;
	private String email;
	private ArrayList<Appointment> appointments = new ArrayList<Appointment>();
	
	/**
	 * A constructor that will initialize the name
	 */
	public Person()
	{
		name = "";
		id = "";
		phoneNum = "";
		email = "";
	}
	
	public Person(String name,String id,String phoneNum,String email)
	{
		this.name  = name;
		this.id = id;
		this.phoneNum = phoneNum;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Person)) {
            return false;
        }
        Person person = (Person) o;
        return name == person.name && id == person.id && phoneNum == person.phoneNum && email == person.email;
    }
	
	public void addAppointment(Appointment newAppointment) {
		this.appointments.add(newAppointment);
	}
}
