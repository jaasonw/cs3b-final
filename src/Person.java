/**
 * This class will represent a person with a name
 */
public class Person implements Comparable
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
	}
	/**
	 * This method will set the name of the person
	 * @param n - name of the person
	 */
	public void setName(String n)
	{
		name = n;
	}
	/**
	 * This method will get the name of the person
	 * @return - the name of the person
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * This method will overload the compareTo method in order to get the orders of the names
	 * return -1 if first name is before second, 0 if they both start with the same letter, and 1 if first name comes before other
	 */
	public int compareTo(Object otherObject) {
		
		Person other = (Person)(otherObject);
		
		if(name.charAt(0) < other.getName().charAt(0)) {return -1;}
		else if(name.charAt(0) == other.getName().charAt(0))  {return 0;}
		else {return 1;}
		
	}

}
