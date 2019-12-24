package criminals;

//=============================================================================
//Η class αυτή περιγράφει το sms
//=============================================================================
public class SMS extends Communication {
	private String message;

	public SMS(String firstphone, String secondPhone, int day, int month, int year, CommunicationType communicationType ) {
		super(firstphone, secondPhone, day, month, year, communicationType) ;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void printInfo() {
		System.out.println("The " + getCommunicationType().getType() + " has the following info: " + getFirstphone() + " ---- " + getSecondPhone()
				+ " Date: " + getDay() + "/" + getMonth() + "/" + getYear() + " Duration: " + getCommunicationType().getMessage());
	}

}
