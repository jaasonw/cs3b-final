import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Eric
 *
 * we are going to use v for visitors at the end of their id and s for staff 
 * 
 * Placeee the staff into the appointment and check thee info and wokr on dateee and time
 */

public class VirtualAssistant {
	public static void main(String args[]) throws FileNotFoundException
	{
		Scanner in = new Scanner(System.in);
		String questionOrAppointment = getAppointmentOrQuestion(in);
		
		/*Saving info
		ArrayList<Appointment> allAppointments = new ArrayList<Appointment>();
		String fileName = getAppointmentFiles();
		
		File inFile = new File(fileName);
		Scanner console = new Scanner(inFile);
		
		allAppointments = getAppointmentFile(console);
		*/
		
		//================= Staff Info ========================
		ArrayList<Staff> allStaffs = new ArrayList<Staff>();
		//String staffFileName = getStaffFiles();
		File staffFile = new File("staffList");
		Scanner console = new Scanner(staffFile);
		allStaffs = getStaffs(console);
		
		for(int i = 0; i < allStaffs.size(); i++)
		{
			System.out.println(allStaffs.get(i).getName());
			System.out.println(allStaffs.get(i).getId());
			System.out.println(allStaffs.get(i).getPhoneNum());
			System.out.println(allStaffs.get(i).getEmail());
			System.out.println(allStaffs.get(i).getRoomNum());
			System.out.println(allStaffs.get(i).getStartTime());
		}
		
		checkQuestionOrAppointment(questionOrAppointment,in);
		
		
		in.close();
	}
	/**
	 * This function will get the file containing all the appointments the user wants to read in
	 * @return file - returns the name of the file that was able to be read in.
	 */
	public static String getStaffFiles()
	{
		//========================= Variables =====================================
		boolean done = false;
		Scanner in = new Scanner(System.in);
		String file = "";
		//========================= Calculations =====================================
		while(!done)
		{
			try
			{
				System.out.print("Pleaase enter a The file containing the appointments to be read: ");
				file = in.nextLine();
				File inFile = new File(file);
				Scanner console = new Scanner(inFile);
				console.close();
				done = true;
			}
			catch (FileNotFoundException exception)
			{
				System.out.println("File not found.");
			}
		}
		//========================= Return =====================================
		return file;
	}
	public static ArrayList<Staff> getStaffs(Scanner console)
	{
		ArrayList<Staff> allStaffs = new ArrayList<Staff>();
		int[] startTime = new int[2];
		int[] endTime = new int[2];
		
		
		while(console.hasNextLine())
		{	
			String name = console.nextLine();
			String id = console.nextLine();
			String phoneNum = console.nextLine();
			String email = console.nextLine();
			String roomNum = console.nextLine();
			startTime[0] = console.nextInt();
			startTime[1]= console.nextInt();
			endTime[0] = console.nextInt();
			endTime[1] = console.nextInt();
			
			Staff newStaff = new Staff(name, id, phoneNum, email, roomNum, startTime, endTime);
			
			allStaffs.add(newStaff);
		}
		
		return allStaffs;
	}
	/**
	 * This function will get the file containing all the appointments the user wants to read in
	 * @return file - returns the name of the file that was able to be read in.
	 */
	public static String getAppointmentFiles()
	{
		//========================= Variables =====================================
		boolean done = false;
		Scanner in = new Scanner(System.in);
		String file = "";
		//========================= Calculations =====================================
		while(!done)
		{
			try
			{
				System.out.print("Pleaase enter a The file containing the appointments to be read: ");
				file = in.nextLine();
				File inFile = new File(file);
				Scanner console = new Scanner(inFile);
				console.close();
				done = true;
			}
			catch (FileNotFoundException exception)
			{
				System.out.println("File not found.");
			}
		}
		//========================= Return =====================================
		return file;
	}
	/**
	 * This function will get the date of the appointment from the line read in
	 * @param line - the line in the file 
	 * @return dates - 3 integers representing the month, day and year
	 */
	public static int[] getDate(String line)
	{
		//======================== Variables ======================================
		String month = "";
		String day = "";
		String year = "";
		String[] findDate = line.split("/");
		int[] dates  = new int[3];
		
		//======================== Calculations ======================================
		//gets the month
		for(int i = 0; i < findDate[0].length(); i++)
		{
			if(Character.isDigit(line.charAt(i)))
			{
				month += line.charAt(i);
			}
		}
		//contaians the day and the year
		day = findDate[1];
		year = findDate[2];
		
		dates[0] = Integer.parseInt(month);
		dates[1] = Integer.parseInt(day);
		dates[2] = Integer.parseInt(year);
		
		//======================== Return ======================================
		return dates;
		
	}
	public static String getAppointmentOrQuestion(Scanner in)
	{
		String questionOrAppointment = "";
		
		System.out.print("Hello! Are you here for appointments or to ask a question? ");
		questionOrAppointment = in.next().toLowerCase();
		
		while(!questionOrAppointment.equals("question") && !questionOrAppointment.equals("appointment"))
		{
			System.out.print("I'm sorry I didn't understand that, are you here to make a appointment or ask a question? ");
			questionOrAppointment = in.next().toLowerCase();
		}
		
		return questionOrAppointment;
	}
	public static void checkQuestionOrAppointment(String qOrA,Scanner in)
	{
		if(qOrA.equals("appointment"))
		{
			checkOrMakeAppointment(in);
		}
		else if(qOrA.equals("question"))
		{
			askQuestions();
		}
	}
	/**
	 * For right now the user has to type new Appointment to make  a new appointment if we want voice recognition  then change what the setOrchecekAppointment is looking for
	 * @param in
	 */
	public static void checkOrMakeAppointment(Scanner in)
	{
		String setOrCheckAppointments = "";
		
		System.out.print("Would you like to check in or make a new appointment (type: check in or new appointment)? ");
		in.nextLine();
		setOrCheckAppointments = in.nextLine().toLowerCase();
		
		while(!setOrCheckAppointments.equals("check in") && !setOrCheckAppointments.equals("new appointment"))
		{
			System.out.print("I'm sorry I didn't understand that, do you want to check in  or make a new appointment (type: check in or new appointment)? ");
			setOrCheckAppointments = in.nextLine().toLowerCase();
		}
		
		//If the user wants to make a new appointment
		if(setOrCheckAppointments.equals("new appointment"))
		{
			createNewAppointment(in);
		}
		//If the user wants to check in
		else if(setOrCheckAppointments.equals("check in"))
		{
			
		}
			
	}
	public static void askQuestions()
	{
		System.out.println("asking question");
	}
	//================================== Creating new appointments ============================================
	public static void createNewAppointment(Scanner in)
	{
		//Visitor
		Person visitor = getVisitorInfo(in);
		
		//Show available appointment times
		//displayAppointmentTimes();
		
		//Show staff
		
		//appointment information
		String appointmentDescription = getDescription(in);
		
		//Appointment
		Appointment newAppointment = new Appointment(visitor,appointmentDescription);
	}
	
	//=================================== Collecting visitor info ===============================================
	public static Person getVisitorInfo(Scanner in)
	{
		String changeInfo = "";
		//Visitor's name, id, phone,  email, brief description
		//for now the  user can only set  their name once but there will  be an interface to change names
		String visitorName = getName(in);
		String visitorId = getID(in);
		String visitorPhone = getPhone(in);
		String visitorEmail = getEmail(in);
		
		//Checking to make sure user input all their personal info correctly
		
		//Add the visitor's email later
		while(!changeInfo.equals("next"))
		{
			changeInfo = checkInfo(visitorName,visitorId,visitorPhone,visitorEmail,in);
			
			if(changeInfo.equals("name"))
			{
				visitorName = getName(in);
			}
			else if(changeInfo.equals("id"))
			{
				visitorId = getID(in);
			}
			else if(changeInfo.equals("phone number"))
			{
				visitorPhone = getPhone(in);
			}
			else if(changeInfo.equals("email"))
			{
				visitorEmail = getEmail(in);
			}
		}
		
		Person visitor = new Person(visitorName,visitorId,visitorPhone,visitorEmail);
		
		return visitor;
	}
	//============================================================ Inputs/input validations for visitor section =======================================================
	
	//========================= NAME  ==========================
	public static String getName(Scanner in)
	{
		String name = "";
		boolean noDigits = false;
		
		System.out.print("What is your name?: ");
		name = in.nextLine();
				
		while(noDigits == false)
		{
			if(checkName(name) == true)
			{
				noDigits = true;
			}
			else
			{
				noDigits = false;
				System.out.print("Name contained invalid characters or is too long please try again: ");
				name = in.nextLine();
			}
		}
		noDigits = false;
	
		return name;
	}
	/**
	 * This function will validate whether or not a number is in the word
	 * @param word - the word to check
	 * @return - true if there is no digits, false otherwise
	 */
	public static boolean checkName(String word)
	{
		//===================== Variables =======================================
		int inputErrorCounter = 0;
		
		//===================== calculations ====================================
		for(int i = 0;i<word.length();i++)
		{
			if((!Character.isLetter(word.charAt(i)) && !Character.isWhitespace((word.charAt(i))) && word.charAt(i) != '.' ))
			{
				inputErrorCounter += 1;
			}
		}
		if(word.length() < 2  || word.length() > 50)
		{
			inputErrorCounter += 1;
		}
		//===================== return ==========================================
		if(inputErrorCounter > 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	//============================== ID ====================================
	public static String getID(Scanner in)
	{
		String id = "";
		boolean onlyDigits = false;
		
		System.out.print("Please enter your 8 digit student id: ");
		id = in.nextLine();
		
		while(onlyDigits == false)
		{
			if(checkID(id) == true)
			{
				onlyDigits = true;
			}
			else
			{
				onlyDigits = false;
				System.out.print("The ID should contain 8 numbers: ");
				id = in.nextLine();
			}
		}
		onlyDigits = false;
	
		return id + "v";
	}
	
	/**
	 * This function will validate whether or not a number is in the word
	 * @param word - the word to check
	 * @return - true if there is no digits, false otherwise
	 */
	public static boolean checkID(String word)
	{
		//===================== Variables =======================================
		int inputErrorCounter = 0;
		
		//===================== calculations ====================================
		for(int i = 0;i<word.length();i++)
		{
			if(!Character.isDigit(word.charAt(i)))
			{
				inputErrorCounter += 1;
			}
		}
		if(word.length() != 8)
		{
			inputErrorCounter += 1;
		}
		//===================== return ==========================================
		if(inputErrorCounter > 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	//========================= PHONE  ==========================
	public static String getPhone(Scanner in)
	{
		String phoneNum = "";
		boolean goodInput = false;
		
		System.out.print("Please enter your phone number(xxx-xxx-xxxx): ");
		phoneNum = in.nextLine();
		
		while(goodInput == false)
		{
			if(checkPhoneNum(phoneNum) == true)
			{
				goodInput = true;
			}
			else
			{
				goodInput = false;
				System.out.print("The phone number should be in this form (xxx-xxx-xxxx): ");
				phoneNum = in.nextLine();
			}
		}
		
		
		return phoneNum;
	}
	
	/**
	 * This function will validate whether or not a number is in the word
	 * @param word - the word to check
	 * @return - true if there is no digits, false otherwise
	 */
	public static boolean checkPhoneNum(String word)
	{
		//===================== Variables =======================================
		int inputErrorCounter = 0;
		
		//===================== calculations ====================================
		if(word.length() != 12)
		{
			inputErrorCounter += 1;
		}
		else
		{
			//Checks area code
			for(int i = 0;i<3;i++)
			{
				if(!Character.isDigit(word.charAt(i)))
				{
					inputErrorCounter += 1;
				}
			}
			//Check for hyphens
			if(word.charAt(3) != '-' || word.charAt(7) != '-')
			{
				inputErrorCounter += 1;
			}
			//Check the middle 3  numbers
			for(int i = 4;i<7;i++)
			{
				if(!Character.isDigit(word.charAt(i)))
				{
					inputErrorCounter += 1;
				}
			}
			//Check the last 4
			for(int i = 8;i<word.length();i++)
			{
				if(!Character.isDigit(word.charAt(i)))
				{
					inputErrorCounter += 1;
				}
			}
		}
		//===================== return ==========================================
		if(inputErrorCounter > 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	//**** Find a way to validate the emails later
	//========================= EMAIL  ==========================
	public static String getEmail(Scanner in)
	{
		String email = "";
		boolean goodInput = false;
		
		System.out.print("Please enter your email address: ");
		email = in.nextLine();
		
		while(goodInput == false)
		{
			if(checkEmail(email) == true)
			{
				goodInput = true;
			}
			else
			{
				goodInput = false;
				System.out.print("The email address should be in the form of example@emailCompany.com: ");
				email = in.nextLine();
			}
		}
		
		
		return email;
	}
	/**
	 * This function will validate whether or not a number is in the word
	 * @param word - the word to check
	 * @return - true if there is no digits, false otherwise
	 */
	public static boolean checkEmail(String word)
	{
		// check if theres an @
		if (word.indexOf('@') != -1)
		{
			String[] emailSplit = word.split("@");
			// check if the 2nd part has a .
			if (emailSplit[1].indexOf('.') != -1)
			{
				// check if the 2nd part doesnt start with a .
				if (emailSplit[1].charAt(0) != '.')
					return true;
			}
		}
		return false;
	}
	//========================== DESCRIPTION ==================================
	public static String getDescription(Scanner in)
	{
		String description = "";
		boolean goodInput = false;
		
		System.out.print("Please enter a brief description for your appointment up to 250 words: ");
		description = in.nextLine();
		
		if(description.length() >= 250)
		{
			description =  description.substring(0, 251);
		}
		
		return description;
	}
	//========================== Checking user's info =========================
	public static String checkInfo(String name,String id,String phoneNum,String email,Scanner in)
	{
		String change = "";
		
		System.out.println("\nIs your information here correct?");
		displayInfo(name,id,phoneNum,email);
		System.out.println("\nIf any of the information here is incorrect please enter the information you want to change ex.name");
		System.out.print("If everything is fine type \"next\": ");
		
		change = in.nextLine().toLowerCase();
		
		
		while(!change.equals("name") && !change.equals("id") && !change.equals("phone number") && !change.equals("email") && !change.equals("next"))
		{
			System.out.print("That is not part of the information! Please try again: ");
			change = in.nextLine().toLowerCase();
		}
		
		return change;
	}
	//========================== DISPLAY INFO =================================
	public static void displayInfo(String name,String id,String phoneNum,String email)
	{
		System.out.println("Name: " + name);
		//Doesn't display the v, the v will keep track of who's visitor and who's staff behind the scene
		System.out.println("ID: " + id.substring(0, id.length()-1));
		System.out.println("Phone number: " + phoneNum);
		System.out.println("Email: " + email);
	}
}
