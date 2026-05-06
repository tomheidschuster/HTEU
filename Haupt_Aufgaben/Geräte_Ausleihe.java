package geräteAusleihe;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.lang.ArrayIndexOutOfBoundsException;

public class GeräteAusleihe {
	public static void main(String[] args) {

		// Beinhaltet den zustand aller Tablets.
		String[] freiheit = { "frei", "frei", "frei", "frei", "frei", "frei", "frei", "frei", "frei", "frei" };
		while (true) {
			if (hauptmenü(freiheit) == 1) {
				return;
			}
		}
	}

	static int hauptmenü(String freiheit[]) {

		// Befehl um die Print Ausgabe in mehreren Zeilen schreiben zu können.
		String newLine = System.lineSeparator();

		Scanner input = new Scanner(System.in);

		// Enthält die Eingabe des Nutzers.
		int aktion = 0;
		for (int i = 0; i < freiheit.length; i++) {
			freiheit[i].toLowerCase();
		}
		//arrayVergleich(freiheit);

		System.out.println("Was möchten sie machen?" + newLine + "(1) Gerätestatus anzeigen" + newLine
				+ "(2) Gerät ausleihen" + newLine + "(3) Gerät zurückgeben" + newLine + "(4) Suchen" + newLine
				+ "(5) Zurücksetzen" + newLine + "(6) Gerät Eintauschen" + newLine + "(7) Program beenden");

		try {
			aktion = input.nextInt();
		} catch (InputMismatchException e) {
			System.err.println("Eingabe muss aus Zahlen bestehen.");
			return (0);
		}
		switch (aktion) {
		// Zeigt den Status aller Tablets an.
		case 1:

			status(freiheit);
			if (weiter() == 1) {
				return (1);
			}
			break;
		// Lässt den Nutzer ein Gerät ausleihen.
		case 2:
			ausleihen(freiheit);
			if (weiter() == 1) {
				return (1);
			}
			break;
		// Lässt den Nutzer ein Gerät zurückgeben.
		case 3:
			zurückgeben(freiheit);
			if (weiter() == 1) {
				return (1);
			}
			break;
		// Lässt den Nutzer nach einem Gerät oder einer Person suchen.
		case 4:
			suchen(freiheit);
			if (weiter() == 1) {
				return (1);
			}
			break;
		// Lässt den Nutzer die Liste zurücksetzen
		case 5:
			reset(freiheit);
			if (weiter() == 1) {
				return (1);
			}
			break;
		case 6:
			gerätWechseln(freiheit);
			if (weiter() == 1) {
				return 1;
			}
			break;
		// Beendet das Programm.
		case 7:
			return (1);
		// Fehlermeldung für falsche Eingabe.
		default:
			System.err.println("Sie können " + aktion + " nicht eingeben, versuchen sie 1-7.");
			break;
		}
		return (0);
	}

	static void status(String freiheit[]) {

		// Zeigt alle Gerätenummern und Ihren Status an.
		int auswahl = 0;
		Scanner input = new Scanner(System.in);
		String newLine = System.lineSeparator();
		while (true) {
			try {
				System.out.println("Was möchten sie sehen?" + newLine + "(1) Alle Geräte" + newLine + "(2) Freie Geräte"
						+ newLine + "(3) Gebrauchte Geräte" + newLine + "(4) Zurück");
				auswahl = input.nextInt();
				break;
			} catch (InputMismatchException e) {
				System.err.println("Sie dürfen nur 1-4 eingeben");
				status(freiheit);
				return;
			}
		}
			if (auswahl == 1) {
				alleAnzeigen(freiheit);
				return;
			}else if (auswahl == 2) {
				freiAnzeigen(freiheit);
				return;
			}else if (auswahl == 3) {
				belegtAnzeigen(freiheit);
				return;
			} else if (auswahl == 4) {
				return;
			} else {
				System.err.println("Sie können nut 1-4 eingeben");
			}
		
		}

	static void ausleihen(String freiheit[]) {

		long gerätenummer = 0;

		String benutzer = "";
		Scanner nutzer = new Scanner(System.in);
		while (true) {
			try {
//Fragt den Nutzernamen ab.
				System.out.println("Was ist der Name des Benutzers? (Max. 10 Zeichen)");
				benutzer = nutzer.nextLine();
				benutzer = benutzer.strip();
				benutzer = benutzer.toLowerCase();
				if (benutzer.length() > 10) {
					System.err.println("Die eingabe darf nicht länger als 10 zeichen sein.");
					continue;
				}

				
				// Überprüft ob die Eingabe eine Zahl beinhaltet.
				boolean nummer = false;
				char[] nummern = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
				char[] myArray = benutzer.toCharArray();
				for (int b = 0; b < myArray.length; b++) {
					for (int a = 0; a < nummern.length; a++) {
						if (myArray[b] == nummern[a]) {
							nummer = true;
						}
					}
				}
				if (nummer == true) {
					System.err.println("Dein text darfst keine Zahlen beinhalten.");
					continue;
				}

				long a = Integer.parseInt(benutzer);

				System.err.println("Ihr Name darf keine Zahlen beinhalten.");
				continue;
			} catch (java.lang.NumberFormatException e) {
			}
			// Überprüft ob der Nutzer "frei" oder leer eingibt.
			if (benutzer == "frei") {
				System.err.println("Sie können geräte nicht mit dem namen frei buchen.");
				continue;
			} else if (benutzer.isBlank()) {
				System.err.println("Sie können geräte nicht ohne namen buchen.");
				continue;
			}
			break;
		}
		// Fragt die Gerätenummer ab.
		System.out.println("Was ist die Gerätenummer?");
		try {
			gerätenummer = nutzer.nextInt();
		} catch (InputMismatchException e) {
			System.err.println("Eingabe muss aus Zahlen bestehen");
			return;
		}
		// Überprüft ob das Tablet verliehen ist und weist es andernfalls dem Nutzer zu
		try {
			
			arrayVergleich(freiheit, benutzer, (int) gerätenummer);

			if (freiheit[(int) (gerätenummer - 1)] == "frei") {
				freiheit[(int) (gerätenummer - 1)] = benutzer;

			} else {
				System.err.println("Das gerät ist schon ausgeliehen.");
				return;
			}
			System.out.println("Das Gerät " + gerätenummer + " wurde von " + benutzer + " ausgeliehen.");
			return;
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			System.err.println("Eingabe muss zwischen 1 und 10 sein.");
			return;
		}
	}

	static void zurückgeben(String freiheit[]) {

		// Überprüft ob Tablets verliehen sind
		for (int q = 0; q < freiheit.length; q++) {
			if (!freiheit[q].equals("frei")) {

				// Fragt nach der Gerätenummer.
				System.out.println("Was ist die Gerätenummer oder der Nutzer?");
				Scanner nummer = new Scanner(System.in);
				String gerät = "";
				try {
					gerät = nummer.nextLine();
					int a = Integer.parseInt(gerät);

					if (freiheit[(int) (a - 1)] == "frei") {
						System.err.println("Das gerät ist nicht ausgeliehen.");
					} else {
						String benutzer = freiheit[(int) (a - 1)];
						freiheit[(int) (a - 1)] = "frei";

						System.out.println("Das gerät " + a + " wurde von " + benutzer + " zurückgegeben.");
					}

				} catch (NumberFormatException e) {
					for (int i = 0; i < freiheit.length; i++) {
						if (gerät.equalsIgnoreCase(freiheit[(int) i])) {
							System.out.println(gerät + " hat gerät " + (i + 1) + " zurückgegeben");
							freiheit[i] = "frei";
							return;
						}
					}
				}
			}
			System.err.println("Es ist kein Gerät verliehen.");
			return;
		}
	}

	static void suchen(String freiheit[]) {
		// Nutzer Eingabe.
		Scanner eingabe = new Scanner(System.in);
		System.out.println("Nach was suchen sie?");
		String in = "";
		// Testet auf die Länge.
		while (true) {
			in = eingabe.nextLine();
			in = in.strip();
			in = in.toLowerCase();
			if (in.length() > 10) {
				System.err.println("Die eingabe darf nicht länger als 10 zeichen sein.");
			} else {
				break;
			}
		}
		// Testet ob die Eingabe "frei" oder leer ist.
		if (in.equalsIgnoreCase("frei")) {
			System.err.println("Sie können nicht nach frei suchen.");
			return;
		} else if (in.isBlank()) {
			System.err.print("Sie müssen etwas eingeben.");
		}

		try {

//Überprüft ob die Eingabe eine Zahl oder ein String ist.
			long a = Integer.parseInt(in);
			// Sucht nach einer Gerätenummer.
			if (freiheit[(int) (a - 1)].equalsIgnoreCase("frei")) {
				System.out.println("Das gerät ist nicht verliehen.");
			}

			else if (a < 10) {
				System.out.println("Gerät " + a + " wird von " + freiheit[(int) (a - 1)] + " benutzt.");
			}
//Sucht nach einem Nutzer.
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			System.err.println("Sie müssen eine Zahl von 1 bis 10 eingeben.");
		} catch (java.lang.NumberFormatException r) {
			for (long i = 0; i < freiheit.length; i++) {
				if (in.equalsIgnoreCase(freiheit[(int) i])) {
					System.out.println(in + " hat gerät " + (i + 1) + " ausgeliehen");
					return;
				}
			}
			System.err.println("Der Nutzer " + in + " wurde nicht gefunden.");
			return;

		}
	}

	static void reset(String freiheit[]) {
		Scanner eingabe = new Scanner(System.in);

		jaNeinAbfrage("Sind Sie Sicher?");

		for (int i = 0; i < freiheit.length; i++) {
			freiheit[i] = "frei";
		}
		System.out.println("Geräte wurde Zurückgesetzt");
	}

	static void freiAnzeigen(String freiheit[]) {
	System.out.println("Ni");
		for (int i = 0; i < freiheit.length; i++) {			
			if (freiheit[i].equalsIgnoreCase("frei")) {
				if (i + 1 < 10) {
					System.out.print("0");
				}
				System.out.println(i + 1 + " " + freiheit[(int) i]);
			}
		}
		return;
	}

	static void belegtAnzeigen(String freiheit[]) {
		System.out.println("Ni");
		for (int i = 0; i < freiheit.length; i++) {
			if (!freiheit[i].equalsIgnoreCase("frei")) {
				if (i + 1 < 10) {
					System.out.print("0");
				}
				System.out.println(i + 1 + " " + freiheit[(int) i]);
			}
		}
		return;
	}
	static void alleAnzeigen(String freiheit[]) {
		String grün = "\u001B[32m";
		String rot = "\u001B[31m";
		String weiß = "\u001B[37m";
		for (int i = 0; i < 10; i++) {
			if (freiheit[i].equalsIgnoreCase("frei")) {
				System.out.print(grün);
			} else {
				System.out.print(rot);
			}
			if (i + 1 < 10) {
				System.out.print("0");
			}
			System.out.println(i + 1 + " " + freiheit[i] + weiß);

		}
		return;

	}

	static void gerätWechseln(String freiheit[]) {
		Scanner eingabe = new Scanner(System.in);
		int in = 0;
		int neu = 0;
		while (true) {
			try {
				System.out.println("Welches Gerät möchten sie zurückgeben?");
				in = eingabe.nextInt();
				if (in < freiheit.length && in > 0) {

				} else {
					System.err.println("Sie dürfen nur Zahlen von 1 bis 10 eingeben");
				}

				System.out.println("Welches Gerät möchten sie eintauschen?");
				neu = eingabe.nextInt();
				if (neu < freiheit.length && neu > 0) {
					break;
				} else {
					System.err.println("Sie dürfen nur Zahlen von 1 bis 10 eingeben");
				}
			} catch (InputMismatchException e) {
				System.err.println("Sie dürfen nur Zahlen eingeben.");
			}
		}
		if (!freiheit[in - 1].equalsIgnoreCase("frei") && freiheit[neu - 1].equalsIgnoreCase("frei")) {
			String platzhalter = freiheit[in - 1];
			freiheit[in - 1] = "frei";
			freiheit[neu - 1] = platzhalter;
		} else {
			System.out.println("Ihr gerät ist nicht verliehen, oder Das neue Ist bereits verliehen.");
		}
	}

//Überprüft (mithilfe der jaNeimTest Funktion) ob der Nutzer weitermachen möchte. 0 = weiter und 1 = Stop
	static int weiter() {
		return (jaNeinAbfrage("Weiter?"));

	}

	static int jaNeinAbfrage(String frage) {
		String weiterTest = "";
		String newLine = System.lineSeparator();
		System.out.flush();
		System.out.println(frage + newLine + "J/N");
		System.out.flush();
		while (!weiterTest.equalsIgnoreCase("J") || !weiterTest.equalsIgnoreCase("N")) {
			switch (jaNeinTest(weiterTest, newLine)) {
			case 2:
				break;
			case 1:
				return (1);
			case 0:
				return (0);
			}
		}
		return (0);
	}

//Fragt den Nutzer (J/N), überprüft die Eingabe und gibt die an "weiter" weiter.
	static int jaNeinTest(String weiterTest, String newLine) {
		Scanner input = new Scanner(System.in);
		while (true) {
			weiterTest = input.nextLine();

			if (weiterTest.equalsIgnoreCase("J")) {
				return (0);
			} else if (weiterTest.equalsIgnoreCase("N")) {
				return (1);
			} else {
				System.err.println("Falsche eingabe!");
				System.out.println("Weiter? " + newLine + "J/N");
			}
		}
	}

	static void arrayVergleich(String[] freiheit, String in, int numIn) {
		boolean test = false;
		int e = 0;
		int z = 0;
		for (int i = 0; i < freiheit.length; i++) {

			if (freiheit[i].equalsIgnoreCase("frei")) {
				continue;
			}
				if (freiheit[i].equals(in)) {
					System.out.println("Gerät " + (i + 1) + " wird bereits von "
							+ freiheit[i] + " ausgeliehen!");
					test = true;
					e = i;
					z = numIn -1;

				}
			}
		if (test == true) {
			arrayKorrektur(freiheit, e, z);
		}
	}

	static boolean arrayTest(String[] freiheit, int position, int positionZwei) {

		for (int i = 0; i < freiheit.length; i++) {
			if (freiheit[i].equalsIgnoreCase("frei") && i != position) {
				continue;
			}

			for (int p = i + 1; p < freiheit.length; p++) {
				if (freiheit[i].equals(freiheit[p]) && p != positionZwei) {
					return true;
				}
			}
		}
		return false;
	}

	static void arrayKorrektur(String[] freiheit, int eins, int zwei) {
		while (true) {
			Scanner input = new Scanner(System.in);
			int eingabe = 0;
			String neu = "";
			while (true) {
				try {
					System.out.println("Welche position möchten sie änern? " + (eins + 1) + " oder " + (zwei + 1));
					eingabe = input.nextInt();
					break;
				} catch (InputMismatchException e) {
					System.err.println("Sie dürfen nur Zahlen angeben");
				}
			}
			if (eingabe == eins + 1) {
				while (true) {
					try {
						System.out.println("Zu was möchten sie " + (eins + 1) + " ändern?");
						System.out.flush();
						neu = input.nextLine();
					} catch (InputMismatchException e) {
						System.err.println("Sie dürfen nur Zahlen angeben");
					}

					if (neu.equalsIgnoreCase("frei")) {
						System.err.println("Sie können sich nicht frei nennen.");
						continue;
					} else if (arrayTest(freiheit, eins, zwei)) {
						System.err.println("Den Namen gibt es bereits");
						continue;
					} else if (freiheit[eins].equalsIgnoreCase(neu)) {
						System.err.println("Sie müssen einen neuen Namen eingeben.");
						continue;
					}
					if (neu.isBlank()) {
						System.err.println("Sie müssen einen Namen eingeben.");
						continue;
					}
					freiheit[eins] = neu;
					System.out.println("Die Position wurde umbenant");
					return;
				}
			} else if (eingabe == zwei + 1) {
				while (true) {
					try {
						System.out.println("Zu was möchten sie " + (zwei + 1) + " ändern?");
						System.out.flush();
						neu = input.nextLine();
					} catch (InputMismatchException e) {
						System.err.println("Sie dürfen nur Zahlen angeben");
					}

					if (neu.equalsIgnoreCase("frei")) {
						System.err.println("Sie können sich nicht frei nennen.");
						continue;
					} else if (arrayTest(freiheit, eins, zwei)) {
						System.err.println("Den Namen gibt es bereits");
						continue;
					} else if (freiheit[zwei].equalsIgnoreCase(neu)) {
						System.err.println("Sie müssen einen neuen Namen eingeben.");
						continue;
					}
					if (neu.isBlank()) {
						System.err.println("Sie müssen einen Namen eingeben.");
						continue;
					}
					freiheit[zwei] = neu;
					System.out.println("Die Position wurde umbenant");
					return;
				}
			} else {
				System.out.println("Falsche eingabe geben sie " + (eins + 1) + " oder " + (zwei + 1) + " ein.");
				continue;
			}
		}
	}
}