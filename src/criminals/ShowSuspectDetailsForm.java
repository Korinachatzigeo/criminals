package criminals;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionEvent;

//=============================================================================
//H GUI class για εμφάνιση των στοιχείων του criminal
//=============================================================================
public class ShowSuspectDetailsForm {

	private JFrame frmSuspectDetails;
	private JTextField txtName;
	private JTextField txtCriminalName;
	private JTextField txtCodeName;
	private JTextPane txtListOfPhones;
	private JTextField txtPhoneNumber;
	private Suspect _theCurrentSuspect = new Suspect();

	/**
	 * Launch the application.
	 */
	public static void main(Suspect s) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowSuspectDetailsForm window = new ShowSuspectDetailsForm(s);
					window.frmSuspectDetails.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ShowSuspectDetailsForm(Suspect aSuspect) {
		initialize(aSuspect);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Suspect aSuspect) {

		_theCurrentSuspect = aSuspect;

		frmSuspectDetails = new JFrame();
		frmSuspectDetails.setTitle("Details of Suspect: " + _theCurrentSuspect.getName());
		frmSuspectDetails.setBounds(100, 100, 550, 470);
		frmSuspectDetails.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSuspectDetails.getContentPane().setLayout(null);

		// ===================================
		// First panel: Show criminal details
		// ===================================
		JPanel pnlShowCriminalDetails = new JPanel();
		pnlShowCriminalDetails.setBounds(10, 0, 524, 80);
		frmSuspectDetails.getContentPane().add(pnlShowCriminalDetails);
		pnlShowCriminalDetails.setLayout(null);

		txtName = new JTextField();
		txtName.setEditable(false);
		txtName.setBounds(10, 11, 144, 20);
		pnlShowCriminalDetails.add(txtName);
		txtName.setColumns(10);

		txtCodeName = new JTextField();
		txtCodeName.setEditable(false);
		txtCodeName.setBounds(161, 11, 153, 20);
		pnlShowCriminalDetails.add(txtCodeName);
		txtCodeName.setColumns(10);

		txtListOfPhones = new JTextPane();
		txtListOfPhones.setEditable(false);
		txtListOfPhones.setBounds(324, 11, 190, 60);
		pnlShowCriminalDetails.add(txtListOfPhones);

		// Βάζουμε τις τιμές από τις ιδιότητες του criminal, στα πεδία της φόρμας
		txtName.setText(aSuspect.getName());
		txtCodeName.setText(aSuspect.getCodeName());

		// Εμφανίζουμε τη λίστα με τα τηλέφωνα του criminal
		// Φτιάχνουμε ένα string με τη λίστα από τα τηλέφωνα του criminal
		String _listOfPhones = "";
		for (int i = 0; i < _theCurrentSuspect.listOfPhoneNumbers.size(); i++)
			_listOfPhones += _theCurrentSuspect.listOfPhoneNumbers.get(i) + "\n";

		// Και τo βάζουμε σαν περιεχόμενο σε TextAreaField
		txtListOfPhones.setText(_listOfPhones);

		// =============================================
		// Second panel: Show criminal contacts' sorted
		// =============================================
		JPanel pnlShowCriminalPartners = new JPanel();
		pnlShowCriminalPartners.setBounds(10, 78, 524, 103);
		frmSuspectDetails.getContentPane().add(pnlShowCriminalPartners);
		pnlShowCriminalPartners.setLayout(null);

		JLabel lblContacts = new JLabel();
		lblContacts.setBounds(241, 44, 72, 25);
		pnlShowCriminalPartners.add(lblContacts);
		lblContacts.setText("Partners:");

		JTextPane txtListOfPartners = new JTextPane();
		txtListOfPartners.setEditable(false);
		txtListOfPartners.setText("");
		txtListOfPartners.setBounds(323, 11, 191, 81);
		pnlShowCriminalPartners.add(txtListOfPartners);

		// Ταξινομούμε τις επαφές του criminal αλφαβητικά
		Collections.sort(_theCurrentSuspect.listOfContacts);

		// Φτιάχνουμε ένα string με τη λίστα από τις contacts του criminal
		String _listOfContacts = "";
		for (int i = 0; i < _theCurrentSuspect.listOfContacts.size(); i++)
			_listOfContacts += _theCurrentSuspect.listOfContacts.get(i) + "\n";

		// Και τo βάζουμε σαν περιεχόμενο σε TextAreaField
		txtListOfPartners.setText(_listOfContacts);

		// ===========================================================
		// Third panel: Find criminal's dirty SMSs using a phone-number
		// ===========================================================
		JPanel pnlSearchCriminalDetails = new JPanel();
		pnlSearchCriminalDetails.setBounds(10, 181, 524, 103);
		frmSuspectDetails.getContentPane().add(pnlSearchCriminalDetails);
		pnlSearchCriminalDetails.setLayout(null);

		JLabel lblFindPhones = new JLabel();
		lblFindPhones.setText("Enter a phone number:");
		lblFindPhones.setBounds(10, 11, 144, 25);
		pnlSearchCriminalDetails.add(lblFindPhones);

		txtPhoneNumber = new JTextField();
		txtPhoneNumber.setText((String) null);
		txtPhoneNumber.setColumns(10);
		txtPhoneNumber.setBounds(10, 34, 118, 20);
		pnlSearchCriminalDetails.add(txtPhoneNumber);

		JTextPane txtListSMSWithImproperWords = new JTextPane();
		txtListSMSWithImproperWords.setEditable(false);
		txtListSMSWithImproperWords.setText("");
		txtListSMSWithImproperWords.setBounds(164, 11, 191, 81);
		pnlSearchCriminalDetails.add(txtListSMSWithImproperWords);

		JButton btnFindCriminalsSMS = new JButton();
		btnFindCriminalsSMS.setText("Find SMSs");
		btnFindCriminalsSMS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Αν το πεδίο txtPhoneNumber δεν είναι κενό,
				if (!txtPhoneNumber.getText().equals("")) {
					// ψάχνουμε στη λίστα των τηλεφωνημάτων του criminal
					// για να βρούμε τα SMSs που περιέχουν απαγορευμένες λέξεις
					String _messages = Registry.getSMSWithDirtyWordsSendFromPhone(_theCurrentSuspect.listOfPhoneNumbers,
							txtPhoneNumber.getText());

					// Αν δεν υπάρχουν τέτοια SMSs, εμφανίζουμε μήνυμα
					if (_messages.equals("")) {
						JOptionPane.showMessageDialog(new JFrame(),
								"The suspect didnot communicate with the phone " + txtPhoneNumber.getText(), "Message",
								JOptionPane.INFORMATION_MESSAGE);
					} else
						// αλλιώς, δημιουργούμε με αυτά το περιεχόμενο της textArea
						txtListSMSWithImproperWords.setText(_messages);
				} else
					JOptionPane.showMessageDialog(new JFrame(), "Please enter a phone number", "Message",
							JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnFindCriminalsSMS.setBounds(389, 34, 108, 40);
		pnlSearchCriminalDetails.add(btnFindCriminalsSMS);

		// =============================================
		// Fourth panel: Show criminal contacts' sorted
		// =============================================
		JPanel pnlShowSuggestedPartners = new JPanel();
		pnlShowSuggestedPartners.setBounds(10, 284, 524, 103);
		frmSuspectDetails.getContentPane().add(pnlShowSuggestedPartners);
		pnlShowSuggestedPartners.setLayout(null);

		JLabel lblName = new JLabel();
		lblName.setText("Give a name:");
		lblName.setBounds(10, 11, 144, 25);
		pnlShowSuggestedPartners.add(lblName);

		txtCriminalName = new JTextField();
		txtCriminalName.setBounds(10, 38, 119, 20);
		txtCriminalName.setText("");
		txtCriminalName.setColumns(10);
		pnlShowSuggestedPartners.add(txtCriminalName);

		JLabel lblSuggestedContacts = new JLabel();
		lblSuggestedContacts.setText("Suggested Partners:");
		lblSuggestedContacts.setBounds(181, 47, 132, 25);
		pnlShowSuggestedPartners.add(lblSuggestedContacts);

		JTextPane txtListOfSuggestedPartners = new JTextPane();
		txtListOfSuggestedPartners.setEditable(false);
		txtListOfSuggestedPartners.setText("");
		txtListOfSuggestedPartners.setBounds(323, 11, 191, 81);
		pnlShowSuggestedPartners.add(txtListOfSuggestedPartners);

		// Φτιάχνουμε το κουμπί εδώ, έχοντας ήδη δημιουργήσει το πεδίο
		// txtListOfSuggestedPartners
		// στο οποίο θα μπει το αποτέλεσμα του κουμπιού
		JButton btnFindSugestedContacts = new JButton();
		btnFindSugestedContacts.setText("Find Suggested");
		btnFindSugestedContacts.setBounds(10, 69, 125, 23);
		btnFindSugestedContacts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Αν το πεδίο txtCriminalName δεν είναι κενό,
				if (!txtCriminalName.getText().equals("")) {
					//Το try χρειάζεται για την περίπτωση που επιστρέψει ένα αντικείμενο τύπου suspect κενό
					try {
						// Με το όνομα, βρίσκουμε τον Suspect
						Suspect aSuspect = Registry.findASuspectByName(txtCriminalName.getText());

						// Κατόπιν συγκρίνουμε τις contacts του suspect που βρήκαμε με τον υπό εξέταση
						// και βρίσκουμε τη διαφορά τους.
						ArrayList<String> _listOfSuggestions = _theCurrentSuspect.suggestedSuspects(aSuspect);
						
						// Στη συνέχεια, 
						// Παίρνουμε τη λίστα με τις επαφές που βρήκαμε και φτιάχνουμε ένα string για να
						// το βάλουμε σαν περιεχόμενο στην textArea
						String _contacts = "";
						for (String s : _listOfSuggestions)
							_contacts += s + "\n";

						txtListOfSuggestedPartners.setText(_contacts);
						// }
					} catch (Exception e1) {
						// Αν δεν υπάρχουν προτεινόμενοι, εμφανίζουμε μήνυμα
						JOptionPane.showMessageDialog(new JFrame(), "No suggestions found.", "Message",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else
					// Αν είναι κενό, εμφανίζουμε προειδοποιητικό μήνυμα
					JOptionPane.showMessageDialog(new JFrame(), "Please enter a name!", "Message",
							JOptionPane.INFORMATION_MESSAGE);
			}
		});
		txtPhoneNumber.setBounds(10, 34, 118, 20);
		pnlShowSuggestedPartners.add(btnFindSugestedContacts);

		// =============================================
		// Fifth panel: Close form
		// =============================================
		JPanel pnlClose = new JPanel();
		pnlClose.setBounds(10, 391, 524, 29);
		frmSuspectDetails.getContentPane().add(pnlClose);
		pnlClose.setLayout(null);

		JButton btnClose = new JButton();
		btnClose.setBounds(174, 0, 176, 23);
		pnlClose.add(btnClose);
		btnClose.setText("Back to Search Screen");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmSuspectDetails.setVisible(false);
			}
		});
	}

}
