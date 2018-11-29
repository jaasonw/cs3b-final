import java.util.Scanner;

public class VirtualAssistant {
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in);
		String questionOrAppointment = "";
		
		System.out.print("Hello! Are you here for appointments or to ask a question? ");
		questionOrAppointment = in.next().toLowerCase();
		
		while(!questionOrAppointment.equals("question") && !questionOrAppointment.equals("appointment"))
		{
			System.out.print("I'm sorry I didn't understand that, are you here to make a appointment or ask a question? ");
			questionOrAppointment = in.next().toLowerCase();
		}
		
		if(questionOrAppointment.equals("appointment"))
		{
			
		}
		
		else if(questionOrAppointment.equals("question"))
		{
			
		}
	}
}
