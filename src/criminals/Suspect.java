 package criminals;

import java.util.ArrayList;
import java.util.Collections;

//=============================================================================
//���� � ����� ���������� ��� Suspect
// �������� ���� ��� ��������� ��� ������ �� ������ ��� ��� suspect
//=============================================================================
public class Suspect {
	String name;
	String codeName;
	String town;
	ArrayList<String> listOfPhoneNumbers = new ArrayList<String>();
	ArrayList<String> listOfContacts = new ArrayList<String>();

	public Suspect() {
	}

	public Suspect(String name, String codeName, String town) {
		super();
		this.name = name;
		this.codeName = codeName;
		this.town = town;
	}

	public void addNumber(String number) {
		if (!listOfPhoneNumbers.contains(number)) {
			listOfPhoneNumbers.add(number);
			System.out.println("Number added in phone list succesfully.");
		} else
			System.out.println("Number already in list.");

	}

	public boolean isConnectedTo(Suspect aSuspect) {
		if (listOfContacts.contains(aSuspect))
			return true;
		else
			return false;
	}

	public void setPhoneNumbers(ArrayList<String> phones) {
		for (String phone : phones)
			addNumber(phone);
	}
	
    //=============================================================================
	// printCommonPartners ������� �� ��������� ���� ������ ��� ��������� ��� �����
	// �� ���� ������� ���������� ��� ��� �������
    //=============================================================================
	public void printCommonPartners(Suspect aSuspect) {
		if (aSuspect.getListOfContacts().size() > 0) {

			System.out.print(this.name + " and " + aSuspect.getName() + " have in common the contacts: ");

			int i = 0;
			if (this.listOfContacts.size() > 0) {
				for (String contact : this.listOfContacts) {
					i++;
					for (String c : aSuspect.listOfContacts) {
						if (c.equals(contact)) {
							System.out.print(c);
							if (this.listOfContacts.size() > i)
								System.out.print(", ");
						}
					}
				}
			} else
				System.out.print(this.name + " and " + aSuspect.getName() + " have NO contacts in common:");

			System.out.print("\n\n");

		} else
			System.out.print(this.name + " and " + aSuspect.getName() + " have NO contacts in common:");
	}

	//=============================================================================
	// ���������� ���������� ����������� ��� ���������� ���� ������ �� �������������� 
	// �������� ���������� (Suggested Suspects). 
	// A� ���� ������� �  
	// ���� ���������� ���� � ��� �, ��� � � ���� ���������� ���� �, � ��� �, 
	// ���� ��� ��� ������ �, �� ������� �� ������ �� ���������� �� ������������ ������ ��������� ��� �. 
    //=============================================================================
	public ArrayList<String> suggestedSuspects(Suspect aSuspect ) {
		//��������� ��� contacts ��� ��� suspects � ��� �
		//��������� ���� ���������� ��� � ��� ��� ����� ���������� ��� �
		//��� ���� ����������� ��� ���������� ��� ��� �
		
		ArrayList<String> _suggestedSuspects = new ArrayList<String>();
				
		for  ( String s : aSuspect.getListOfContacts() ) {
			//�� � contact ��� suspect ��� ������ ��� ����� �� ��� contacts ��� ��� ����� ��� � �����
			//���� ������ ��� ����� ��� �������������
			if ( !this.getListOfContacts().contains( s ) && !this.getName().equals( s )) {
				_suggestedSuspects.add( s );
			}
		}

		// ����������� ��� ������ ��� criminal ����������
		Collections.sort(_suggestedSuspects); 

		return _suggestedSuspects;

	}
	
    //=============================================================================
	// printCommonPartners ������� �� ��������� ���� ������ ��� ���������� ��� �����
	// �� ���� ������� �������� ���������� ��� ��� �������
    //=============================================================================
	public ArrayList<String> getCommonPartners(Suspect aSuspect) {
		ArrayList<String> listOfGangsters = new ArrayList<String>();

		//��� ���� ��� ������ ��� aSuspect (g)
		for (String g : aSuspect.listOfContacts) {
			//��� ���� ��� ������ ��� ��������� suspect (contact)
			for (String contact : this.listOfContacts)
				//��������� �� ����� ������
				if (g.equals(contact))
					listOfGangsters.add(contact);
		}
		return listOfGangsters;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public ArrayList<String> getPhoneNumbers() {
		return listOfPhoneNumbers;
	}

	public ArrayList<String> getListOfContacts() {
		return listOfContacts;
	}

    //=============================================================================
	// ��������� ��� ����� ��� ����� ��� contacts ��� criminal
    //=============================================================================
	public void addToContacts(String aCodeName) {
		if (!listOfContacts.contains(aCodeName)) {
			listOfContacts.add(aCodeName);
			System.out.println("Partner added in contact list succesfully.");
		} else
			System.out.println("Partner already in list.");
	}

    //=============================================================================
	// ������� ��� suspect ��� �� ����� ���
    //=============================================================================
	public Suspect getSuspectFromName( String _name ) {
		Suspect aSuspect = new Suspect();
		
		return aSuspect;
	}

	public void printSuspectInfo(Suspect aSuspect) {
		System.out.print("Name:" + aSuspect.getName() + ", Code-name: " + aSuspect.getCodeName() + ", Town: "
				+ aSuspect.getTown());

		printListOfPhones(aSuspect);
		printListOfContacts(aSuspect);
		System.out.println("\n");

	}

    //=============================================================================
	// ������� �� ����� �� �� �������� ��� suspect

    //=============================================================================
	public void printListOfPhones(Suspect aSuspect) {
		int i = 0;
		if (aSuspect.getPhoneNumbers().size() > 0) {
			System.out.print("\nThe list with her/his phones: ");
			for (String phone : aSuspect.getPhoneNumbers()) {
				System.out.print(phone);
				i++;
				if (aSuspect.getPhoneNumbers().size() > i)
					System.out.print(", ");
			}
		}
	}

    //=============================================================================
	// ������� �� ����� �� ��� contacts ��� suspect
    //=============================================================================
	public void printListOfContacts(Suspect aSuspect) {
		int i = 0;
		if (aSuspect.getListOfContacts().size() > 0) {
			System.out.print("\nThe list of her/his contacts: ");
			for (String contact : aSuspect.getListOfContacts()) {
				System.out.print(contact);
				i++;
				if (aSuspect.getListOfContacts().size() > i)
					System.out.print(", ");
			}
		}
	}
}
