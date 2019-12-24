package criminals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

//=============================================================================
//Η κλάση που χειρίζεται Suspects
//=============================================================================
public class Registry {

	// Η λίστα με τους criminals
	public static ArrayList<Suspect> criminals = new ArrayList<Suspect>();
	// Η λίστα με τις επικοινωνίες ανάμεσα στους criminals
	public static ArrayList<Communication> communications = new ArrayList<Communication>();

	// Αρχικοποίηση με λέξεις-κλειδιά μέσα για αναζήτηση μέσα στα sms-texts
	public static ArrayList<String> stems = new ArrayList<String>(Arrays.asList("Bomb", "Attack", "Explosives", "Gun"));

	public Registry() {
		// TODO Auto-generated constructor stub
	}

	//==========================================================================
	public void printSuspectsInfo() {
		System.out.print("A complete list of the criminals\n================================\n");
		for (Suspect s : criminals) {
			s.printSuspectInfo(s);
		}
	}

	//==========================================================================
	public void addSuspect(Suspect aSuspect) {
		// αν ο criminal δεν υπάρχει, πρόσθεσέ τον
		if (!criminals.contains(aSuspect))
			criminals.add(aSuspect);
	}

	//==========================================================================
	public Suspect getSuspectWithMostPartners() {

		System.out.print("The suspect with the most contacts\n==================================\n");

		int maxContacts = 0;
		Suspect s = null;

		// Εξετάζουμε τη λίστα με τους criminals
		for (Suspect c : criminals) {
			// Αν ο υπό εξέταση criminal έχει περισσότερες contacts από τον τρέχοντα
			// criminal με τη μεγαλύτερη λίστα από contacts
			if (c.listOfContacts.size() >= maxContacts) {
				// τον βάζουμε στη θέση του criminals με τη μεγαλύτερη λίστα με contacts
				maxContacts = c.listOfContacts.size();
				s = c;
			}
		}
		
		return s;

	}

	//==========================================================================
	public void addCommunication(String _phone1, String _phone2, CommunicationType communicationType) {

		// Παίρνουμε την τρέχουσα ώρα/ημέρα
		LocalDateTime now = LocalDateTime.now();

		// Ανάλογα το είδος της επικοινωνίας (τηλεφώνημα/sms)
		// δημιουργούμε το κατάλληλο αντικείμενο και το αποθηκεύουμε στη λίστα με τις
		// Επικοινωνίες
		if (communicationType.getType().equals("PhoneCall")) {
			PhoneCall p = new PhoneCall(_phone1, _phone2, now.getDayOfMonth(), now.getMonthValue(), now.getYear(),
					communicationType);
			communications.add(p);
			p.printInfo();
		} else {
			SMS p = new SMS(_phone1, _phone2, now.getDayOfMonth(), now.getMonthValue(), now.getYear(),
					communicationType);
			communications.add(p);
			p.printInfo();
		}

		// Στη συνέχεια, μέσω της επικοινωνίας, ελέγχουμε αν οι δυο criminals είναι
		// συνδεδεμένοι.
		connectCriminalsThroughPhoneNumbers(_phone1, _phone2);
	}

	//==========================================================================
	// Αν η επικοινωνία αφορά τους τηλεφωνικούς αριθμούς Num1 και Num2,
	// τότε για τους υπόπτους που διαθέτουν τους αντίστοιχους αριθμούς ενημερώνεται η
	// λίστα των συνεργατών τους.
	// This function is called internally. No need to be public
    //=============================================================================
	private void connectCriminalsThroughPhoneNumbers(String _phone1, String _phone2) {
		System.out.println(
				"\nSearching between the criminals to create new contacts........\n==============================================================\n");

		// Για το _phone1
		System.out.println("Working on the first number: " + _phone1);

		// Επειδή ο έλεγχος για το αν δυο criminals συνδέονται μπορεί να γίνει είτε γατί
		// ο ένας κάλεσε πρώτα τον άλλον ή αντίθετα...
		// με την methodπου ακολουθεί εεκέγχεται αν το _phone1 υπάρχει στη λίστα των επαφών ενός
		// άλλου criminal
		searchForASpecificPhoneNumber(_phone1);

		// όμοια για για το _phone2
		System.out.println("\nWorking on the second number: " + _phone2);
		searchForASpecificPhoneNumber(_phone2);
	}

	//==========================================================================
	// This function is called internally. No need to be public
    //=============================================================================
	private void searchForASpecificPhoneNumber(String _phone) {

		// Παίρνουμε ένα-ένα τα στοιχεία της λίστας με τους criminals.
		// Δεν θέλουμε να ψάξουμε σε όλους τους criminals.
		// Αντίθετα, θέλουμε να σταματήσουμε μόλις βρούμε τον criminal που έχει
		// τον εν λόγω αριθμό.
		// Για αυτό, χρησιμοποιούμε την boolean numberNotFound
		boolean numberNotFound = true;
		Iterator<Suspect> aCriminal = criminals.iterator();
		while (aCriminal.hasNext() && numberNotFound) {

			// Αποθηκεύουμε τον υπό εξέταση criminal σε μια μεταβλητή, 
			// Ο λόγος είναι ότι η .hasNext() πάντα φέρνει το επόμενο item του ArrayList
			// Εμείς σταθεροποιούμε το τρέχον και ελέγχουμε τις ιδιότητές του
			// Έτσι, δημιουργούμε έναν κενό Suspect
			Suspect _outerSuspect = new Suspect();
			_outerSuspect = aCriminal.next();

			// Για κάθε ένα από τους criminals,
			System.out.println("Searching the Criminal: " + _outerSuspect.getName() + " having contacts: "
					+ _outerSuspect.getListOfContacts() + " and phones: " + _outerSuspect.getPhoneNumbers());

			// ελέγχουμε αν στη λίστα με τα τηλέφωνά τους υπάρχει ο δοθέν τηλεφωνικός
			// αριθμός
			// Αν δεν υπάρχει -δλδ το τηλέφωνο δεν είναι δικό του-
			boolean criminalNotFound = true;
			if (!_outerSuspect.getPhoneNumbers().contains(_phone)) {

				// ψάχνουμε ξανά στη λίστα με τους criminals,
				// για να βρούμε τον criminal που έχει τον τηλεφωνικό αριθμό
				Iterator<Suspect> aSuspect = criminals.iterator();
				while (aSuspect.hasNext() && criminalNotFound) {
					// Αποθηκεύουμε τον υπό εξέταση criminal σε μια μεταβλητή 
					Suspect _innerSuspect = new Suspect();
					_innerSuspect = aSuspect.next();

					// Ψάχνουμε μέσα στη λίστα των contacts του υπό εξέταση criminal.
					// Αν είναι ήδη συνδεδεμένοι αγνούμε την κοινή contact
					if (_innerSuspect.getPhoneNumbers().contains(_phone)
							&& !_innerSuspect.getName().equals(_outerSuspect.getName())) {

						System.out.println("Found the criminal with the phone number. \nWe compare "
								+ _outerSuspect.getName() + " to the criminal " + _innerSuspect.getName()
								+ " having contacts: " + _innerSuspect.getListOfContacts() + " and phones: "
								+ _innerSuspect.getPhoneNumbers());
						// και τον βάζουμε στις contacts του
						_outerSuspect.addToContacts(_innerSuspect.getName());
						System.out.println("Contact " + _innerSuspect.getName() + " added to the contacts of "
								+ _outerSuspect.getName());

						// Μόλις βρούμε τον criminal που έχει τον τηλεφωνικό αριθμό, σταματάμε να
						// ψάχνουμε μέσα στους criminals,
						// γιατί κανείς δεν μοιράζεται το ίδιο τηλεφωνικό νούμερο με κάποιον άλλον
						criminalNotFound = false;
						numberNotFound = false;
					}
				}
			}
		}
	}

	//==========================================================================
	// getLongestPhoneCallBetween(String number1, String number2):
	// δέχεται ως παράμετρο δύο αριθμούς
	// και επιστρέφει την μεταξύ τους τηλεφωνική κλήση με τη μεγαλύτερη διάρκεια
    //=============================================================================
	public void getLongestPhoneCallBetween(String number1, String number2) {
		// Αυτή η συνάρτηση ψάχνει ανάμεσα σε στη λίστα με τις τηλεφωνικές κλήσεις
		int maxDuration = 0;
		for (Communication c : communications) {
			// Αν πρόκειται για τηλεφωνική κλήση...
			if (c.getCommunicationType().getType().equals("PhoneCall")) {

				// Πρέπει και οι δυο αριθμοί της κλήσης να ταιριάζουν με τους δοθέντες.
				// Μπορεί όμως να είναι μια κλήση ανάμεσα στους (number1,number2)
				// ή μία κλήση ανάμεσα στους (number2,number1)
				if ((c.getFirstphone().equals(number1) && c.getSecondPhone().equals(number2))
						|| (c.getSecondPhone().equals(number1) && c.getFirstphone().equals(number2)))

					if (c.getCommunicationType().getSeconds() > maxDuration)
						maxDuration = c.getCommunicationType().getSeconds();
			}
		}

		System.out.println(
				"\n\nThe phone call with the longest duration:\n===========================================");

		if (maxDuration > 0)
			System.out.println(
					"lasts " + maxDuration + " and took place between phone: " + number1 + " and phone: " + number2);
		else
			System.out.println("There was not call between these two phones");
	}

	//==========================================================================
	// getMessagesBetween(String number1, String number2):
	// δέχεται ως παράμετρο 2 αριθμούς κι επιστρέφει όλα τα μηνύματα μεταξύ τους
	// που περιέχουν οποιαδήποτε από τις λέξεις “Bomb”, “Attack”, “Explosives”,
	// “Gun”,
	// Για το σύστημά μας αυτές οι λέξεις είναι αποθηευμένες σε ένα dictionary
	public void getMessagesBetween(String number1, String number2) {
		// Αυτή η συνάρτηση ψάχνει ανάμεσα στη λίστα με τις τηλεφωνικές κλήσεις
		String m = "";
		for (Communication c : communications) {
			// Αν πρόκειται για SMS...
			if (c.getCommunicationType().getType().equals("SMS")) {

				// Πρέπει και οι δυο αριθμοί του sms να ταιριάζουν με τους δοθέντες.
				// Μπορεί λοιπόν να είναι ένα sms ανάμεσα στους (number1,number2)
				// ή ένα sms ανάμεσα στους (number2,number1)
				if ((c.getFirstphone().equals(number1) && c.getSecondPhone().equals(number2))
						|| (c.getSecondPhone().equals(number1) && c.getFirstphone().equals(number2))) {

					// Διασπάμε το περιεχόμενο του SMS σε λέξεις για να το συγκρίνουμε με τις
					// επίμαχες λέξεις.
					String[] words = c.getCommunicationType().getMessage().trim().split(" ");
					ArrayList<String> wordList = new ArrayList<String>(Arrays.asList(words));

					// Κάνουμε loop σε όλες τις λέξεις του sms για να τις συγκρίνουμε 
					//με τις λέξεις του dictionary των απαγορευμένων λέξεων
					for (String s : wordList) {
						if (stems.contains(s))
							m += "The text message '" + c.getCommunicationType().getMessage() + "' between " + number1
									+ " and " + number2 + " contains the improper words '" + s + "' \n";
					}
				}
			}
		}

		System.out.println("\n\nSMSs having inapropriate words:\n===============================");

		if (!m.isEmpty())
			System.out.println(m);
		else
			System.out.println(
					"There was not inapropriate text messages call between these two phones, or the two numbers are not connected");
	}

	//==========================================================================
	public static Suspect findASuspectByName(String aSuspect) {
		Suspect _tmpSuspect = new Suspect();
		_tmpSuspect = null;
		boolean suspectNotFound = true;
		Iterator<Suspect> aCriminal = criminals.iterator();

		while (aCriminal.hasNext() && suspectNotFound) {
			Suspect cSuspect = aCriminal.next();
			if (cSuspect.getName().equals(aSuspect)) {
				suspectNotFound = false;
				_tmpSuspect = cSuspect;
			}
		}
		return _tmpSuspect;
	}

	// The φfollowing function ia a duplicate of the above.
	// These come, to fulfill the GUI part of the exercise --Show info in a GUI form
	// Έτσι η method αυτή είναι επανάληψη της getMessagesBetween. Φτιαγμένη για το GUI.
	public static String getSMSWithDirtyWordsSendFromPhone(ArrayList<String> _listOfPhones, String number2) {
		// Αυτή η συνάρτηση ψάχνει ανάμεσα στη λίστα με τις τηλεφωνικές κλήσεις
		// που έγιναν από τηλέφωνα του υπόπτου
		// και του κατόχου του δεύτερου αριθμού
		// για να εντοπίσει αν υπάρχουν SMSs με απαγορευμένες λέξεις

		String _SMSTextsContainingDirtyWords = "";

		// Loop ανάμεσα στους τηλεφωνικούς αριθμούς του Suspect που βρίσκονται στη
		// _listOfPhones
		for (String _phoneNumber : _listOfPhones) {

			// Για καθένα από αυτούς
			// ψάχνουμε μέσα στο αρχείο με τις επικοινωνίες
			for (Communication c : communications) {
				// Αν πρόκειται για SMS...
				if (c.getCommunicationType().getType().equals("SMS")) {

					// Πρέπει να ταιριάζουν με τους δοθέντες και οι δυο αριθμοί του sms.
					// όμως, ένα sms μπορεί να είναι ανάμεσα στους (_phoneNumber, number2)
					// ή ανάμεσα στους (number2, _phoneNumber)
					if ((c.getFirstphone().equals(_phoneNumber) && c.getSecondPhone().equals(number2))
							|| (c.getSecondPhone().equals(_phoneNumber) && c.getFirstphone().equals(number2))) {

						// Διασπάμε το περιεχόμενο του SMS σε λέξεις για να το συγκρίνουμε με τις
						// επίμαχες λέξεις.
						String[] words = c.getCommunicationType().getMessage().trim().split(" ");
						ArrayList<String> wordList = new ArrayList<String>(Arrays.asList(words));

						// Κάνουμε loop σε όλες τις λέξεις του sms για να τις συγκρίνουμε με τα stems
						for (String s : wordList) {
							if (stems.contains(s))
								_SMSTextsContainingDirtyWords += c.getCommunicationType().getMessage() + "\n";
						}
					}
					//Ακόμα κι αν βρούμε ένα sms με λέξεις, δε σταματάμε την αναζήτηση.
					//Συνεχίζουμε την αναζήτηση σε όλο το σύνολο των Communications
					//για να βρούμε όλες τις επικοινωνίες ανάμεσα σε αυτά τα τηλέφωνα
				}
			}
		}
		return _SMSTextsContainingDirtyWords;
	}
}
