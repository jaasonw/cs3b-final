/**
 * check in
 * Questions
 * back track from time to dates to staff
 * UI
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
	private ArrayList<Staff> allStaffs;
	private String staffListFile;
	private Scanner input;
	private ArrayList<Person> allVisitors;
	
	public VirtualAssistant() {
		this.input = new Scanner(System.in);
		this.staffListFile = "staffList.txt";
		this.allStaffs = new ArrayList<Staff>();
		this.allVisitors = new ArrayList<Person>();
		
		try {
			this.getStaffs(staffListFile);
		}
		catch (FileNotFoundException e) {
			System.out.println("Error: staff list file not found");
		}
		this.generateSchedule();
	}
	
	public void addVisitor(Person newVisitor)
	{
		this.allVisitors.add(newVisitor);
	}
	
	public ArrayList<Person> getVisitorList()
	{
		return this.allVisitors;
	}
	
	public static void main(String args[]) throws FileNotFoundException
	{	
		VirtualAssistant bot = new VirtualAssistant();
		boolean done = false;
		
		while(!done)
		{
			String questionOrAppointment = bot.getAppointmentOrQuestion();
			bot.checkQuestionOrAppointment(questionOrAppointment);
		}
	}
	public void getStaffs(String fileName) throws FileNotFoundException
	{
		Scanner staffFile = new Scanner(new File(fileName));
		int[] startTime = new int[2];
		int[] endTime = new int[2];	
		
		while(staffFile.hasNextLine())
		{	
			try
			{
				String name = staffFile.nextLine();
				String id = staffFile.nextLine();
				String phoneNum = staffFile.nextLine();
				String email = staffFile.nextLine();
				String roomNum = staffFile.nextLine();
				startTime[0] = Integer.parseInt(staffFile.nextLine());
				startTime[1]= Integer.parseInt(staffFile.nextLine());
				String startAmPm = staffFile.nextLine().toUpperCase();
				endTime[0] = Integer.parseInt(staffFile.nextLine());
				endTime[1] = Integer.parseInt(staffFile.nextLine());
				String endAmPm = staffFile.nextLine().toUpperCase();
				
				Staff newStaff = new Staff(name, id, phoneNum, email, roomNum, startTime,startAmPm, endTime,endAmPm);
				
				this.allStaffs.add(newStaff);
			}
			catch(Exception e)
			{
				System.out.println("The starting time is greater than the ending time, Please fix the staff's appointment time then reboot");
				System.exit(0);
			}
		}
		staffFile.close();
	}
	public void generateSchedule()
	{
		for(Staff staff : this.allStaffs)
		{
			staff.createSchedule();
		}
	}
	/**
	 * This function will get the file containing all the appointments the user wants to read in
	 * @return file - returns the name of the file that was able to be read in.
	 */
	// public void getAppointmentFiles()
	// {
	// 	//========================= Variables =====================================
	// 	boolean done = false;
	// 	String fileName = "";
	// 	Scanner file;
	// 	//========================= Calculations =====================================
	// 	while(!done)
	// 	{
	// 		try
	// 		{
	// 			System.out.print("Pleaase enter a The file containing the appointments to be read: ");
	// 			fileName = this.input.nextLine();
	// 			File inFile = new File(file);
	// 			file = new Scanner(inFile);

	// 			done = true;
	// 		}
	// 		catch (FileNotFoundException exception)
	// 		{
	// 			System.out.println("File not found.");
	// 		}
	// 	}
	// 	while (file.hasNextLine()) {
	// 		Person person = new Person();
	// 		Queue<String> appointmentData = new Queue<String>();
	// 		person.setId(file.nextLine());
	// 		person.setName(file.nextLine());
	// 		person.setEmail(file.nextLine());
	// 		// skip open bracket
	// 		file.nextLine();
	// 		for (String next = file.nextLine(); !next.equals("]"); next = file.nextLine()) {
	// 			appointmentData.add(file.nextLine());
	// 		}
	// 		while (!appointmentData.isEmpty()) {
	// 			Appointment newAppointment = new Appointment();
	// 			Date date = new Date();
	// 			date.setMonth(Integer.parseInt(appointmentData.poll()));
	// 			date.setDay(Integer.parseInt(appointmentData.poll()));
	// 			date.setYear(Integer.parseInt(appointmentData.poll()));
	// 			newAppointment.setDate(date);
				
	// 			Time startTime = new Time();
	// 			startTime.setHour(Integer.parseInt(appointmentData.poll()));
	// 			startTime.setMinute(Integer.parseInt(appointmentData.poll()));
	// 			newAppointment.setStartTime(startTime);

	// 			Time endTime = new Time();
	// 			endTime.setHour(Integer.parseInt(appointmentData.poll()));
	// 			endTime.setMinute(Integer.parseInt(appointmentData.poll()));
	// 			newAppointment.setEndTime(endTime);

	// 			if (appointmentData.poll() == "1")
	// 				newAppointment.setOpen(true);
	// 			else
	// 				newAppointment.setOpen(false);
	// 			newAppointment.setReason(appointmentData.poll());
	// 		}
	// 	}
	// 	file.close();
	// }
	/**
	 * This function will get the date of the appointment from the line read in
	 * @param line - the line in the file 
	 * @return dates - 3 integers representing the month, day and year
	 */
	public int[] getDate(String line)
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
	public String getAppointmentOrQuestion()
	{
		String questionOrAppointment = "";
		
		System.out.print("Hello! Are you here for appointments or to ask a question? ");
		questionOrAppointment = this.input.next().toLowerCase();
		
		while(!questionOrAppointment.equals("question") && !questionOrAppointment.equals("appointment"))
		{
			System.out.print("I'm sorry I didn't understand that, are you here to make a appointment or ask a question? ");
			questionOrAppointment = this.input.next().toLowerCase();
		}
		
		return questionOrAppointment;
	}
	public void checkQuestionOrAppointment(String qOrA)
	{
		if(qOrA.equals("appointment"))
		{
			this.checkOrMakeAppointment(allStaffs);
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
	public void checkOrMakeAppointment(ArrayList<Staff> allStaffs)
	{
		String setOrCheckAppointments = "";
		
		System.out.print("Would you like to check in or make a new appointment (type: check in or new appointment)? ");
		this.input.nextLine();
		setOrCheckAppointments = this.input.nextLine().toLowerCase();
		
		while(!setOrCheckAppointments.equals("check in") && !setOrCheckAppointments.equals("new appointment"))
		{
			System.out.print("I'm sorry I didn't understand that, do you want to check in  or make a new appointment (type: check in or new appointment)? ");
			setOrCheckAppointments = this.input.nextLine().toLowerCase();
		}
		
		//If the user wants to make a new appointment
		if(setOrCheckAppointments.equals("new appointment"))
		{
			createNewAppointment();
			this.save("test.txt");
		}
		//If the user wants to check in
		else if(setOrCheckAppointments.equals("check in"))
		{
			Person visitor = getVisitorInfo();
			Person currentVisitor;
			
			System.out.println("Please enter your info to check in: ");
			
			for(int i = 0;i < this.getVisitorList().size();i++)
			{
				if(this.getVisitorList().get(i).equals(visitor))
				{
					currentVisitor = this.getVisitorList().get(i);
				}
			}
			
			System.out.println("Is this information correct?");
			
		}
			
	}
	public void askQuestions()
	{
		System.out.println("asking question");
	}
	
	//================================== Creating new appointments ============================================
	public void createNewAppointment()
	{
		int staffChosen = 0;
		Staff currentStaff;
		
		int dateChosen = 0;
		Date currentDate;
		
		int appointmentChosen = 0;
		Appointment currentAppointment;
		
		//Show staff
		displayStaff();
		
		//Choose staff you want to see
		staffChosen = chooseStaff(this.allStaffs.size());		
		//The current staff that was picked
		currentStaff = this.allStaffs.get(staffChosen);
		
		//Display staff schedule
		System.out.println("Available dates: ");
		currentStaff.showDates();
		
		dateChosen = chooseDate(currentStaff.getDates().size()); 
		
		currentDate = currentStaff.getDates().get(dateChosen);
		
		//Display the available appointment times
		System.out.println("Available times: ");
		currentStaff.showTimes(currentDate);
		
		appointmentChosen = chooseAppointment(currentStaff.getAppointments(currentDate).size());
		
		currentAppointment = currentStaff.getAppointments(currentDate).get(appointmentChosen);
	
		//If the current appointment is already chosen
		while(!currentAppointment.getOpen()) {
			System.out.println("Sorry that time is already taken! Please try again: ");
			
			currentStaff.showTimes(currentDate);
			
			appointmentChosen = chooseAppointment(currentStaff.getAppointments(currentDate).size());
			
			currentAppointment = currentStaff.getAppointments(currentDate).get(appointmentChosen);
		}
		
		//Visitor
		Person visitor = getVisitorInfo();
		
		//appointment information
		String appointmentDescription = getDescription();
		
		//Set the appointment info into the appointment class
		currentAppointment.setReason(appointmentDescription);
		currentAppointment.closeAppointment();
		
		//set the appointment inside the person and set the person inside the virtual assistant
		visitor.addAppointment(currentAppointment);
		this.addVisitor(visitor);
		
		
		//Show that the appointment closed
		currentStaff.showTimes(currentDate);
		
	}
	public void displayStaff()
	{
		System.out.println("Here is a list of our staffs: ");
		for(int i = 0; i < this.allStaffs.size(); i++)
		{
			System.out.println(i+1 + ".");
			System.out.println("Name: " + this.allStaffs.get(i).getName());
			System.out.println("ID: " + this.allStaffs.get(i).getId());
			System.out.println("Phone number: " + this.allStaffs.get(i).getPhoneNum());
			System.out.println("Email: " + this.allStaffs.get(i).getEmail());
			System.out.println("Room number: " + this.allStaffs.get(i).getRoomNum());
			System.out.println("Start Time: " + this.allStaffs.get(i).getStartTime());
			System.out.println("End Time: " + this.allStaffs.get(i).getEndTime());
		}
	}
	
	public int chooseStaff(int maxNum)
	{
		int chooseStaff = 1;
		
		System.out.print("Which Staff member would you like to see?: ");
		chooseStaff = this.input.nextInt();
		
		while(chooseStaff < 1 || chooseStaff >= maxNum + 1)
		{
			System.out.print("Sorry, but that is not an option, please try again: ");
			chooseStaff = this.input.nextInt();
		}
		
		this.input.nextLine();
		
		return chooseStaff - 1;
	}
	
	public int chooseDate(int maxNum)
	{
		int chooseDate = 1;
		
		System.out.print("Which Date would you like to see?: ");
		chooseDate = this.input.nextInt();
		
		while(chooseDate < 1 || chooseDate >= maxNum + 1)
		{
			System.out.print("Sorry, but that is not an option, please try again: ");
			chooseDate = this.input.nextInt();
		}
		
		this.input.nextLine();
		
		return chooseDate - 1;
	}
	
	public int chooseAppointment(int maxNum)
	{
		int chooseAppointment = 1;
		
		System.out.print("Which Date would you like to see?: ");
		chooseAppointment = this.input.nextInt();
		
		while(chooseAppointment < 1 || chooseAppointment >= maxNum + 1)
		{
			System.out.print("Sorry, but that is not an option, please try again: ");
			chooseAppointment = this.input.nextInt();
		}
		
		this.input.nextLine();
		
		return chooseAppointment - 1;
	}
	
	//=================================== Collecting visitor info ===============================================
	public Person getVisitorInfo()
	{
		String changeInfo = "";
		//Visitor's name, id, phone,  email, brief description
		//for now the  user can only set  their name once but there will  be an interface to change names
		String visitorName = getName();
		String visitorId = getID();
		String visitorPhone = getPhone();
		String visitorEmail = getEmail();
		
		//Checking to make sure user input all their personal info correctly
		
		//Add the visitor's email later
		while(!changeInfo.equals("next"))
		{
			changeInfo = checkInfo(visitorName,visitorId,visitorPhone,visitorEmail);
			
			if(changeInfo.equals("name"))
			{
				visitorName = getName();
			}
			else if(changeInfo.equals("id"))
			{
				visitorId = getID();
			}
			else if(changeInfo.equals("phone number"))
			{
				visitorPhone = getPhone();
			}
			else if(changeInfo.equals("email"))
			{
				visitorEmail = getEmail();
			}
		}
		
		Person visitor = new Person(visitorName,visitorId,visitorPhone,visitorEmail);
		
		return visitor;
	}
	//============================================================ Inputs/input validations for visitor section =======================================================
	
	//========================= NAME  ==========================
	public String getName()
	{
		String name = "";
		boolean noDigits = false;
		
		System.out.print("What is your name?: ");
		name = this.input.nextLine();
				
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
				name = this.input.nextLine();
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
	public boolean checkName(String word)
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
	public String getID()
	{
		String id = "";
		boolean onlyDigits = false;
		
		System.out.print("Please enter your 8 digit student id: ");
		id = this.input.nextLine();
		
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
				id = this.input.nextLine();
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
	public boolean checkID(String word)
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
	public String getPhone()
	{
		String phoneNum = "";
		boolean goodInput = false;
		
		System.out.print("Please enter your phone number(xxx-xxx-xxxx): ");
		phoneNum = this.input.nextLine();
		
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
				phoneNum = this.input.nextLine();
			}
		}
		
		
		return phoneNum;
	}
	
	/**
	 * This function will validate whether or not a number is in the word
	 * @param word - the word to check
	 * @return - true if there is no digits, false otherwise
	 */
	public boolean checkPhoneNum(String word)
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
	public String getEmail()
	{
		String email = "";
		boolean goodInput = false;
		
		System.out.print("Please enter your email address: ");
		email = this.input.nextLine();
		
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
				email = this.input.nextLine();
			}
		}
		
		
		return email;
	}
	/**
	 * This function will validate whether or not a number is in the word
	 * @param word - the word to check
	 * @return - true if there is no digits, false otherwise
	 */
	public boolean checkEmail(String word)
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
	public String getDescription()
	{
		String description = "";
		boolean goodInput = false;
		
		System.out.print("Please enter a brief description for your appointment up to 250 words: ");
		description = this.input.nextLine();
		
		if(description.length() >= 250)
		{
			description =  description.substring(0, 251);
		}
		
		return description;
	}
	//========================== Checking user's info =========================
	public String checkInfo(String name,String id,String phoneNum,String email)
	{
		String change = "";
		
		System.out.println("\nIs your information here correct?");
		displayInfo(name,id,phoneNum,email);
		System.out.println("\nIf any of the information here is incorrect please enter the information you want to change ex.name");
		System.out.print("If everything is fine type \"next\": ");
		
		change = this.input.nextLine().toLowerCase();
		
		
		while(!change.equals("name") && !change.equals("id") && !change.equals("phone number") && !change.equals("email") && !change.equals("next"))
		{
			System.out.print("That is not part of the information! Please try again: ");
			change = this.input.nextLine().toLowerCase();
		}
		
		return change;
	}
	//========================== DISPLAY INFO =================================
	public void displayInfo(String name,String id,String phoneNum,String email)
	{
		System.out.println("Name: " + name);
		//Doesn't display the v, the v will keep track of who's visitor and who's staff behind the scene
		System.out.println("ID: " + id.substring(0, id.length()-1));
		System.out.println("Phone number: " + phoneNum);
		System.out.println("Email: " + email);
	}
	public void save(String fileName) {
		PrintWriter writer;
		try {
			writer = new PrintWriter(fileName, "UTF-8");
		}
		catch(Exception e) {
			System.out.println("file not found");
			return;
		}
		for (Person p : this.allVisitors) {
			writer.println(p.getId());
			writer.println(p.getName());
			writer.println(p.getEmail());
			writer.println(p.getPhoneNum());
			writer.println("[");
			for (Appointment a : p.getAppointments()) {
				writer.println(a.getDate().getMonth());
				writer.println(a.getDate().getDay());
				writer.println(a.getDate().getYear());
				writer.println(a.getStartTime().getHour());
				writer.println(a.getStartTime().getMinute());
				writer.println(a.getEndTime().getHour());
				writer.println(a.getEndTime().getMinute());
				writer.println((a.getOpen()) ? "1" : "0");
				writer.println(a.getReason());
			}
			writer.println("]");
			
		}
		writer.close();
	}
}
