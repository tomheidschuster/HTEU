package login;
import java.util.*;

public class Login {

	enum Zustand {
		ABGEMELDET, PASSWORTVERSUCH1, PASSWORTVERSUCH2, PASSWORTVERSUCH3, ANGEMELDET, GESPPERT
	}

	static final String benutzer = "Tom";
	static final String passwort = "passwort";

	public static void main(String[] args) {
		Zustand test = Zustand.ABGEMELDET;
		boolean weiter = true;

		while (weiter) {
			switch (test) {
			case ABGEMELDET:
				switch (abgemeldet()) {
				case "0":
					test = Zustand.ABGEMELDET;
					return;

				case "1":
					test = Zustand.PASSWORTVERSUCH1;
					break;
				}
				break;
			case PASSWORTVERSUCH1:
				if (passwortversuch()) {
					test = Zustand.ANGEMELDET;
				} else {
					test = Zustand.PASSWORTVERSUCH2;
				}
				break;

			case PASSWORTVERSUCH2:
				System.err.println("Sie haben ein falsches Passwort eingegeben! Sie haben noch 2 versuche");
				if (passwortversuch()) {
					test = Zustand.ANGEMELDET;
				} else {
					test = Zustand.PASSWORTVERSUCH3;
				}
				break;
			case PASSWORTVERSUCH3:
				System.err.println("Sie haben ein falsches Passwort eingegeben! Sie haben noch 1 versuch");
				if (passwortversuch()) {
					test = Zustand.ANGEMELDET;
				} else {
					test = Zustand.GESPPERT;
				}
				break;
			case ANGEMELDET:
				if (angemeldet()) {
					test = Zustand.ABGEMELDET;
				} else {
					test = Zustand.ABGEMELDET;
					weiter = false;
				}
				break;
			case GESPPERT:
				System.err.println("Sie haben das Passwort 3 mal falsch eingegeben!");
				weiter = false;

			}
		}

	}

	public static String getUserInput(String frage) {
		Scanner input = new Scanner(System.in);
		System.out.print(frage);
		String eingabe = input.nextLine();
		return eingabe;
	}

	public static String abgemeldet() {
		String eingabe = getUserInput("Bitte geben sie ihren Benutzernamen (oder 1 um zu beenden) ein: ");
		if (eingabe.equals("1")) {
			return "0";
		}
		if (eingabe.equals(benutzer)) {
			return "1";
		} else {
			System.err.println("Ihr Benutzer erxistiert nicht!");
			return "2";
		}
	}

	public static boolean passwortversuch() {
		String eingabe = getUserInput("Bitte geben sie ihr Passwort ein: ");
		if (eingabe.equals(passwort)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean angemeldet() {
		System.out.println("Sie sind Angemeldet!");
		System.out.println("1: Abmelden");
		String eingabe = getUserInput("2: Programm beenden");
		if (eingabe.equalsIgnoreCase("1")) {
			return true;
		} else if (eingabe.equalsIgnoreCase("2")) {
			return false;
		}
		return true;
	}
}