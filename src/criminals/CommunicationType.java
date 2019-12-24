package criminals;

//=============================================================================
//� class ���� ���������� �� ��� ���� ������������
//�������� ��� constructors, ��� ��� ���������� -��� �������� ��������- ��� ���� �� sms -��� �������� text
//=============================================================================
public class CommunicationType {
	private String communicationType;
	private String message;
	private int seconds;

	public CommunicationType(String communicationType, int seconds) {
		this.communicationType = communicationType;
		this.seconds = seconds;
	}

	public CommunicationType(String communicationType, String message) {
		this.communicationType = communicationType;
		this.message = message;
	}

	public String getType() {
		return communicationType;
	};

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public void setCommunicationType(String communicationType, int seconds) {
		this.communicationType = communicationType;
		this.seconds = seconds;
	}

	public String getCommunicationType() {
		return communicationType;
	}

	public void setCommunicationType(String communicationType) {
		this.communicationType = communicationType;
	}

}