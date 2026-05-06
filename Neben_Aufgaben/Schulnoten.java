package schulnoten;
import java.util.*;

public class Schulnoten {
	public static void main(String[] args) {

		// Variable zum Merken der eingegebenen Note
		int Note = 0;

		// Scanner definieren
		Scanner eingabe = new Scanner(System.in);

		
		// Eingabe und Kontrolle ob Eingabe ein Integer ist
		while (true) {

			try {
				System.out.println("Welche Note hat der Schüler?");
				Note = 0;
				Note = eingabe.nextInt();
				if (Note < 1) {
					System.err.println("Die Note ist zu klein, sie muss mindestens 1 sein");
				} else if (Note > 6) {
					System.err.println("Die Note ist zu groß, sie darf maximal 6 sein");
				} else {
					break;
				}
			} catch (InputMismatchException e) {
				System.err.println("Eingabe muss aus Zahlen bestehen");
				continue;

			}
		}
		eingabe.close();

		// Ausgabe

		if (Note == 1) {
			System.out.println("Sehr Gut");
		} else if (Note == 2) {
			System.out.println("Gut");
		} else if (Note == 3) {
			System.out.println("Befriedigend");
		} else if (Note == 4) {
			System.out.println("Ausreichen");
		} else if (Note == 5) {
			System.out.println("Mangelhaft");
		} else if (Note == 6) {
			System.out.println("Ungenügend");
		}
	}
}
