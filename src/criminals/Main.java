/**
 * @author k
 *
 */
 package criminals;

public class Main {
		
	public static void main(String[] args) {
		
		Registry registry = new Registry();
		
		System.out.print("Add some contacts\n================================\n");
		Suspect s1 = new Suspect("John Dow", "Sleepy Dog", "Barcelona" );
		s1.addNumber("00496955444444");
		s1.addNumber("00496955333333");
		s1.addNumber("00496955333333");		
		Registry.criminals.add(s1);
		
		Suspect s2 = new Suspect("Tolstoi Russ", "Angry Tom", "Roma" );
		s2.addNumber("00356955444444");
		s2.addNumber("00356955333333");		
		Registry.criminals.add(s2);
				
		Suspect s3 = new Suspect("Matis Dyke", "Moby Dick", "Parigi" );
		s3.addNumber("00566955444444");
		s3.addNumber("00566955333333");
		s3.addNumber("0056955453333");		
		Registry.criminals.add(s3);

		Suspect s4 = new Suspect("John Papas", "The Gipsy", "Athens" );
		s4.addNumber("00306955444444");
		s4.addNumber("00306955333333");
		s4.addNumber("00306955333333");		
		Registry.criminals.add(s4);
		
		s1.addToContacts("Matis Dyke");		
		s2.addToContacts("John Dow");
		s2.addToContacts("Matis Dyke");		
		s3.addToContacts("John Dow");
		s3.addToContacts("Tolstoi Russ");		
		s4.addToContacts("Matis Dyke");
		s4.addToContacts("Tolstoi Russ");
		s4.addToContacts("John Dow");
		
		System.out.print("\n\n");				
		registry.printSuspectsInfo();
		
		System.out.print("\n\n");
		Suspect s = new Suspect();
		s.printSuspectInfo ( registry.getSuspectWithMostPartners( ) );
		
		s1.printCommonPartners( s2 );
		s2.printCommonPartners( s1 );
		s3.printCommonPartners( s1 );

		System.out.println( "\n\nCommon contacts between " + s3.getName() + " and "+ s1.getName() + ": "+s3.getCommonPartners( s1 ));

		//Create some communications
		registry.addCommunication("00306955444444", "00496955333333", new CommunicationType("PhoneCall", 23));
		registry.addCommunication("00306955444444", "00496955333333", new CommunicationType("SMS", "Tonight Gun Attack in the harbor"));
		registry.addCommunication("00306955444444", "00496955333333", new CommunicationType("PhoneCall", 45));
		registry.addCommunication("00306955444444", "00496955333333", new CommunicationType("SMS", "The boss took my Gun Ihave no Gun for the Attack"));
		registry.addCommunication("00306955444444", "00496955333333", new CommunicationType("PhoneCall", 13));
		registry.addCommunication("00306955444444", "00496955333333", new CommunicationType("SMS", "Lunch in 5 at Gun-Attack"));
		
		registry.getLongestPhoneCallBetween("00306955444444", "00496955333333");
		registry.getMessagesBetween("00306955444444", "00496955333333");
		
		//Οι δυο αυτές γραμμές κώδικα υλοποιούν το τρίτο μέρος της άσκησης
		new SearchSuspectForm();
		SearchSuspectForm.main( null );

	}
}
