package criminals;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//=============================================================================
//Η κλάση με GUI που αναζητεί τον criminal
//=============================================================================
public class SearchSuspectForm {

	private JFrame frmFindSuspect;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchSuspectForm window = new SearchSuspectForm();
					window.frmFindSuspect.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SearchSuspectForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFindSuspect = new JFrame();
		frmFindSuspect.setTitle("Find suspect");
		frmFindSuspect.setBounds(100, 100, 450, 150);
		frmFindSuspect.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFindSuspect.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(30, 30, 263, 34);
		frmFindSuspect.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Find");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!textField.getText().equals("")) {
					
					Suspect s = (Registry.findASuspectByName( textField.getText() ));
					
					if ( s == null ) {
						JOptionPane.showMessageDialog(new JFrame(), "The suspect " + textField.getText() + " was not found", "Message",
						        JOptionPane.INFORMATION_MESSAGE);
					} else {
						new ShowSuspectDetailsForm(s);
						ShowSuspectDetailsForm.main( s );
					}
				} else 
					JOptionPane.showMessageDialog(new JFrame(), "Please enter a name", "Message", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnNewButton.setBounds(303, 24, 108, 40);
		frmFindSuspect.getContentPane().add(btnNewButton);
	}
	
}
