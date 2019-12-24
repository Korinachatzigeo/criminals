 package criminals;

import java.util.ArrayList;
import java.util.Collections;

//=============================================================================
//Αυτή η κλάση περιγράφει τον Suspect
// Υλοποιεί όλες τις ενέργειες που μπορεί να γίνουν για τον suspect
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
	// printCommonPartners δέχεται ως παράμετρο έναν ύποπτο και εκτυπώνει μια λίστα
	// με τους κοινούς συνεργάτες των δύο υπόπτων
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
	// εξελιγμένη λειτουργία υπολογισμού και επιστροφής μιας λίστας με προτεινόμενους 
	// πιθανούς συνεργάτες (Suggested Suspects). 
	// Aν ένας ύποπτος Α  
	// έχει συνεργάτες τους Β και Δ, και ο Β έχει συνεργάτες τους Α, Δ και Ε, 
	// τότε για τον χρήστη Α, το σύστημα θα πρέπει να επιστρέψει ως προτεινόμενο πιθανό συνεργάτη τον Ε. 
    //=============================================================================
	public ArrayList<String> suggestedSuspects(Suspect aSuspect ) {
		//ελέγχουμε τις contacts των δυο suspects Α και Β
		//βρίσκουμε τους συνεργάτες του Β που δεν είναι συνεργάτες του Α
		//και τους προτείνουμε σαν συνεργάτες για τον Α
		
		ArrayList<String> _suggestedSuspects = new ArrayList<String>();
				
		for  ( String s : aSuspect.getListOfContacts() ) {
			//Αν η contact του suspect δεν ανήκει στη λίστα με τις contacts και δεν είναι και ο ίδιος
			//τότε βάλτον στη λίστα των προτεινόμενων
			if ( !this.getListOfContacts().contains( s ) && !this.getName().equals( s )) {
				_suggestedSuspects.add( s );
			}
		}

		// Ταξινομούμε τις επαφές του criminal αλφαβητικά
		Collections.sort(_suggestedSuspects); 

		return _suggestedSuspects;

	}
	
    //=============================================================================
	// printCommonPartners δέχεται ως παράμετρο έναν ύποπτο και επιστρέφει μια λίστα
	// με τους κοινούς πιθανούς συνεργάτες των δύο υπόπτων
    //=============================================================================
	public ArrayList<String> getCommonPartners(Suspect aSuspect) {
		ArrayList<String> listOfGangsters = new ArrayList<String>();

		//Για όλες τις επαφές του aSuspect (g)
		for (String g : aSuspect.listOfContacts) {
			//Για όλες τις επαφές του τρέχοντος suspect (contact)
			for (String contact : this.listOfContacts)
				//ελέγχουμε αν είναι κοινοί
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
	// Προσθέτει ένα όνομα στη λίστα των contacts του criminal
    //=============================================================================
	public void addToContacts(String aCodeName) {
		if (!listOfContacts.contains(aCodeName)) {
			listOfContacts.add(aCodeName);
			System.out.println("Partner added in contact list succesfully.");
		} else
			System.out.println("Partner already in list.");
	}

    //=============================================================================
	// Βρίσκει τον suspect από τό όνομά του
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
	// Τυπώνει τη λίστα με τα τηλέφωνα του suspect

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
	// Τυπώνει τη λίστα με τις contacts του suspect
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
