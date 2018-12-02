/**
 * This class will represent a person with a name
 */
public class Person
{
	private String name;
	private String id;
	private String phoneNum;
	private String email;
	
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
	
	public Person(String name,String id,String phoneNum)
	{
		this.name  = name;
		this.id = id;
		this.phoneNum = phoneNum;
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
	
	

}
