package criminals;

//Αυτή η class δημιουργεί πολυμορφισμό ανάμεσα στα δυο είδη επικοινωνίας.
//περιέχει τις βασικές μεθόδους των δυο τύπων επικοινωνίας
public class Communication {
	String firstphone;
	String secondPhone;
	private int day;
	private int month;
	private int year;

	private CommunicationType communicationType;

	public Communication() {

	}

	public Communication(String firstphone, String secondPhone, int day, int month, int year,
			CommunicationType communicationType) {
		super();
		this.firstphone = firstphone;
		this.secondPhone = secondPhone;
		this.day = day;
		this.month = month;
		this.year = year;
		this.communicationType = communicationType;
	}

	public String getFirstphone() {
		return firstphone;
	}

	public void setFirstphone(String firstphone) {
		this.firstphone = firstphone;
	}

	public String getSecondPhone() {
		return secondPhone;
	}

	public void setSecondPhone(String secondPhone) {
		this.secondPhone = secondPhone;
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

	public CommunicationType getCommunicationType() {
		return communicationType;
	}

	public void setCommunicationType(CommunicationType communicationType) {
		this.communicationType = communicationType;
	}

	public void printInfo() {
		System.out.println("Phone call between: " + getFirstphone() + " ---- " + getSecondPhone() + " on " + getDay()
				+ "/" + getMonth() + "/" + getYear() + " seconds:" + getCommunicationType().getSeconds());
	}

}
