import java.util.Calendar;
import java.util.TimeZone;

public class Date {
	private int day;
	private int month;
	private int year;
	
	public Date()
	{
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		
		day = calendar.get(Calendar.DATE);
		month = calendar.get(Calendar.MONTH) + 1;
		year = calendar.get(Calendar.YEAR);
	}
	
	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public void addDays(int daysToAdd) {
		int[] daysInMonth = {31,28,31,30,31,30,31,31,30,31,30,31};
		
		for(int i = 0; i < daysToAdd;i++)
		{
			this.day += 1;
			
			//Check if it is on december 31
			if(this.month == 12)
			{
				if(this.day >= daysInMonth[this.month - 1])
				{
					this.day = 1;
					this.month = 1;
				}
			}
			else
			{
				if(this.day >= daysInMonth[this.month - 1])
				{
					this.day = 1;
					this.month += 1;
				}
			}
		}
	}
	
	public String toString() {
		return this.month + "/" + this.day + "/" + this.year;
	}
}
