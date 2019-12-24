package criminals;


//=============================================================================
//Η class αυτή περιγράφει το τηλεφώνημα
//=============================================================================
public class PhoneCall extends Communication {
	int seconds;

	public PhoneCall() {
	}

	public PhoneCall(String firstphone, String secondPhone, int day, int month, int year,
			CommunicationType communicationType) {
		super(firstphone, secondPhone, day, month, year, communicationType);
	}

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public void printInfo() {
		System.out.println("The " + getCommunicationType().getType() + " has the following info: " + getFirstphone() + " ---- " + getSecondPhone()
				+ " Date: " + getDay() + "/" + getMonth() + "/" + getYear() + " Duration: " + getCommunicationType().getSeconds());
	}

}
