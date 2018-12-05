/**
 * Time
 */
public class Time {

    private int hour;
    private int minute;

    public Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public int getHour() {
        return this.hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return this.minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }
    
    public void addMinutes(int minutes)
    {
    	this.minute += minutes;
    	if(this.minute >= 60)
    	{
    		this.minute -= 60;
    		this.hour += 1;
    	}
    }
    
    @Override
    public String toString() {
        String hour = "";
        String minutes = "";
        String ampm = "";
        if (this.hour > 12) {
            hour += this.hour - 12;
            ampm = "pm";
        }
        else if (this.hour == 12) {
            hour += this.hour;
            ampm = "pm";
        }
        else if (this.hour == 0) {
            hour += "12";
            ampm = "am";
        }
        else {
            hour += this.hour;
            ampm = "am";
        }
        minutes += this.minute + ((this.minute < 10) ? "0" : "");
    	return String.format("%s:%s %s", hour, minutes, ampm);
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Time)) {
            return false;
        }
        Time time = (Time) o;
        return hour == time.hour && minute == time.minute;
    }
}

