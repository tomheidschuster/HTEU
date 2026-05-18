package toDoListe;

import java.util.*;
import java.io.*;

public class ToDoListe {
	static final String GRÜN = "\u001B[32m";
	static final String ROT = "\u001B[31m";
	static final String WEIß = "\u001B[37m";
	static final String GELB = "\033[33m";

	static String newLine = System.lineSeparator();

	static File user = new File("UsersToDoListe.txt");
	static File datei = new File("ToDoListe.txt");

	public static void main(String args[]) {

		ArrayList<String> toDo = new ArrayList<String>();
		ArrayList<String> wichtigkeit = new ArrayList<String>();
		ArrayList<String> ablaufdatum = new ArrayList<String>();
		ArrayList<Boolean> erledigt = new ArrayList<Boolean>();
		ArrayList<String> nutzer = new ArrayList<String>();
		ArrayList<String> passwoerter = new ArrayList<String>();

		nutzer.add("admin");
		passwoerter.add("1234");
		int in = 0;
		getUsers(user, nutzer, passwoerter);
		if (nutzer.size() < 2) {
			System.out.println("Es wurde kein Nutzer gefunden!");
			createNewUser(nutzer, passwoerter);
		}
		datei = addUserPrefix(datei, nutzer, passwoerter);
		setupFile(datei, toDo, wichtigkeit, ablaufdatum);
		System.out.println("Nutzer:");

		while (true) {

			System.out.println("Was möchten sie machen?");
			System.out.println("(0) Benutzer Hinzufügen");
			System.out.println("(1) Programm beenden");
			System.out.println("(2) Aufgabe Hinzufügen");
			if (toDo.size() > 0) {
				System.out.println("(3) Aufgaben Anzeigen");
				System.out.println("(4) Sortiert Anzeigen");
				System.out.println("(5) Suchen");
				System.out.println("(6) Aufgabe entfernen");
			}

			try {
				in = getUserInt("");
			} catch (InputMismatchException e) {
				System.err.println("Sie können nur Zahlen eingaben!");
			}
			switch (in) {
			case 0:
				createNewUser(nutzer, passwoerter);
				break;
			case 1:

				saveUsers(user, nutzer, passwoerter);
				saveList(datei, toDo, wichtigkeit, ablaufdatum);
				return;
			case 2:
				String[] e = newTask();
				toDo.add(e[0]);
				erledigt.add(false);
				ablaufdatum.add(e[1]);
				wichtigkeit.add(e[2]);
				break;
			case 3:
				try {
					in = getUserInt(
							"Möchten sie Alle(1) , Sehr Wichtige(2), Wichtige(3), Nicht Wichtige(4), Offene(5) oder Erledigte(6) Aufgaben sehen?");
				} catch (InputMismatchException r) {
					System.out.println("Bitte geben sie eine Zahl ein!");
				}
				switch (in) {
				case 1:
					showList(toDo, wichtigkeit, ablaufdatum);
					break;
				case 2:
					showSehrWichtig(toDo, wichtigkeit, ablaufdatum);
					break;
				case 3:
					showWichtig(toDo, wichtigkeit, ablaufdatum);
				case 4:
					showNichtWichtig(toDo, wichtigkeit, ablaufdatum);
					break;
				case 5:
					showOpen(toDo, erledigt);
				case 6:
					showFinished(toDo, erledigt);
				default:
					System.out.println("Bitte geben sie eine Zahl von 1 bis 4 ein");
				}
				break;

			case 4:
				while (true) {

					try {
						System.out.println("Wie Möchent sie die Aufgaben Sortieren?");
						System.out.println("(1) Nach Priorität");
						System.out.println("(2) Nach Ablaufdatum");
						System.out.println("(3) Alphabetisch");
						in = getUserInt("(4) Nach Staus");
					} catch (InputMismatchException z) {
						System.err.println("Bitte geben sie nur Zahlen ein!");
					}
					if (in <= 4 && in > 0) {
						break;
					}
				}
				switch (in) {
				case 1:
					sortByPriority(toDo, wichtigkeit, ablaufdatum);
					break;
				case 2:
					sortByDates(toDo, ablaufdatum);
					break;
				case 3:
					sortByName(toDo);
					break;
				case 4:
					sortByStatus(toDo, erledigt);
					break;
				}
				break;
			case 5:
				search(toDo);
				break;

			case 6:
				removeTask(toDo, erledigt, ablaufdatum, wichtigkeit);
				break;
			default:
				System.err.println("Bitte verwenden Sie eine der Optionen");
			}
		}
	}

	public static String[] newTask() {
		String in = "";
		while (true) {
			in = getUserString("Was möchten sie hinzufügen?");
			if (!in.isEmpty()) {
				String[] e = getTimeAndLevel();
				String[] TaskAndTimeAndLevel = { in, e[0], e[1] };
				return TaskAndTimeAndLevel;
			}
		}
	}

	public static void showList(ArrayList<String> toDo, ArrayList<String> wichtigkeit, ArrayList<String> ablaufdatum) {
		if (toDo.size() == 0) {
			System.out.println("Sie haben noch keine Aufgaben!");
			return;
		}
		for (int i = 0; i < toDo.size(); i++) {
			if (i < 10) {
				if (wichtigkeit.get(i).equals("Sehr Wichtig")) {
					System.out.print(ROT);
				} else if (wichtigkeit.get(i).equals("Wichtig")) {
					System.out.print(GELB);
				} else if (wichtigkeit.get(i).equals("Nicht Wichtig")) {
					System.out.print(GRÜN);
				}

				System.out.println("0" + (i + 1) + " " + toDo.get(i) + ", " + wichtigkeit.get(i) + ", Ablaufdatum: "
						+ ablaufdatum.get(i));
			} else {
				System.out.println((i + 1) + " " + toDo.get(i) + ", " + wichtigkeit.get(i) + ", Ablaufdatum:  "
						+ ablaufdatum.get(i));
			}
		}
		System.out.print(WEIß);
	}

	public static void showSehrWichtig(ArrayList<String> toDo, ArrayList<String> wichtigkeit,
			ArrayList<String> ablaufdatum) {
		if (!wichtigkeit.contains("Sehr Wichtig")) {
			System.out.println("Sie haben keine Sehr Wichtigen Aufgaben!");
			return;
		}
		for (int i = 0; i < toDo.size(); i++) {
			if (wichtigkeit.get(i).equals("Sehr Wichtig")) {
				System.out.print(ROT);
				if (i < 10) {

					System.out.println("0" + (i + 1) + " " + toDo.get(i) + ", " + wichtigkeit.get(i) + ", Ablaufdatum: "
							+ ablaufdatum.get(i));
				} else {
					System.out.println((i + 1) + " " + toDo.get(i) + ", " + wichtigkeit.get(i) + ", Ablaufdatum:  "
							+ ablaufdatum.get(i));
				}
			}
		}
		System.out.print(WEIß);
	}

	public static void showWichtig(ArrayList<String> toDo, ArrayList<String> wichtigkeit,
			ArrayList<String> ablaufdatum) {
		if (!wichtigkeit.contains("Wichtig")) {
			System.out.println("Sie haben keine Wichtigen Aufgaben!");
			return;
		}
		for (int i = 0; i < toDo.size(); i++) {
			if (wichtigkeit.get(i).equals("Wichtig")) {
				System.out.print(GELB);
				if (i < 10) {

					System.out.println("0" + (i + 1) + " " + toDo.get(i) + ", " + wichtigkeit.get(i) + ", Ablaufdatum: "
							+ ablaufdatum.get(i));
				} else {
					System.out.println((i + 1) + " " + toDo.get(i) + ", " + wichtigkeit.get(i) + ", Ablaufdatum:  "
							+ ablaufdatum.get(i));
				}
			}
		}
		System.out.print(WEIß);
	}

	public static void showNichtWichtig(ArrayList<String> toDo, ArrayList<String> wichtigkeit,
			ArrayList<String> ablaufdatum) {
		if (!wichtigkeit.contains("Nicht Wichtig")) {
			System.out.println("Sie haben keine Nicht Wichtigen Aufgaben!");
			return;
		}
		for (int i = 0; i < toDo.size(); i++) {
			if (wichtigkeit.get(i).equals("Nicht Wichtig")) {
				System.out.print(GRÜN);
				if (i < 10) {

					System.out.println("0" + (i + 1) + " " + toDo.get(i) + ", " + wichtigkeit.get(i) + ", Ablaufdatum: "
							+ ablaufdatum.get(i));
				} else {
					System.out.println((i + 1) + " " + toDo.get(i) + ", " + wichtigkeit.get(i) + ", Ablaufdatum:  "
							+ ablaufdatum.get(i));
				}
			}
		}
		System.out.print(WEIß);
	}

	public static void showFinished(ArrayList<String> toDo, ArrayList<Boolean> erledigt) {
		if (erledigt.size() == 0) {
			System.out.println("Sie haben keine fertigen Aufgaben!");
			return;
		}
		for (int i = 0; i < erledigt.size(); i++) {
			if (i < 10) {
				if (erledigt.get(i)) {
					System.out.println("0" + (i + 1) + " " + toDo.get(i));
				}
			} else {
				if (erledigt.get(i)) {
					System.out.println((i + 1) + " " + erledigt.get(i));
				}
			}
		}
	}

	public static void showOpen(ArrayList<String> toDo, ArrayList<Boolean> erledigt) {
		if (erledigt.size() == 0) {
			System.out.println("Sie haben keine fertigen Aufgaben!");
			return;
		}
		for (int i = 0; i < erledigt.size(); i++) {
			if (i < 10) {
				if (!erledigt.get(i)) {
					System.out.println("0" + (i + 1) + " " + toDo.get(i));
				}
			} else {
				if (!erledigt.get(i)) {
					System.out.println((i + 1) + " " + erledigt.get(i));
				}
			}
		}
	}

	public static void sortByPriority(ArrayList<String> toDo,ArrayList<String> wichtigkeit, ArrayList<String> ablaufdatum ) {
			showSehrWichtig(toDo, wichtigkeit, ablaufdatum);
			showWichtig(toDo, wichtigkeit, ablaufdatum);
			showNichtWichtig(toDo, wichtigkeit, ablaufdatum);
		}

	public static void removeTask(ArrayList<String> toDo, ArrayList<Boolean> erledigt, ArrayList<String> ablaufdatum,
			ArrayList<String> wichtigkeit) {
		int in = 0;
		while (true) {
			try {
				in = getUserInt("Welche Aufgabe möchen sie Löschen?") - 1;
				erledigt.remove(in);
				erledigt.add(in, true);
				toDo.remove(in);
				ablaufdatum.remove(in);
				wichtigkeit.remove(in);
				return;
			} catch (InputMismatchException e) {
				System.err.println("Bitte geben Sie eine Zahl ein!");
			} catch (IndexOutOfBoundsException r) {
				System.out.println("Bitte geben sie eine Gültige zahl ein!");
			}

		}

	}

	public static String[] getTimeAndLevel() {
		String time = "";
		String level = "";
		boolean weiter = true;
		while (true) {
			time = getUserString("Bis wann muss die Aufgabe erledigt sein?");
			if (!time.isBlank()) {
				if (checkDate(time)) {
					break;
				} else {
					continue;
				}
			} else {
				System.err.println("Bitte geben sie etwas ein");
			}
		}
		while (weiter) {
			level = getUserString("Wie wichtig ist die aufgabe?" + newLine + "1 = Sehr wichtig" + newLine
					+ "2 = Wichtig" + newLine + "3 = Nicht wichtig");
			switch (level) {
			case "1":
				level = "Sehr Wichtig";
				weiter = false;
				break;
			case "2":
				level = "Wichtig";
				weiter = false;
				break;
			case "3":
				level = "Nicht Wichtig";
				weiter = false;
				break;
			default:
				System.err.println("Geben sie eine Zahl von 1 bis 3 an!");
			}
		}
		String[] ret = { time, level };
		return ret;
	}

	public static void setupFile(File datei, ArrayList<String> toDo, ArrayList<String> wichtigkeit,
			ArrayList<String> ablaufdatum) {
		if (datei.exists()) {
			System.out.println(GRÜN + "Die To-Do Liste ist bereit" + WEIß);
			System.out.println(datei.getAbsolutePath());
			try (Scanner scan = new Scanner(datei)) {
				while (scan.hasNextLine()) {
					String data = scan.nextLine();
					// data verarbeiten
					String[] cutData = data.split("#");
					toDo.add(cutData[0]);
					wichtigkeit.add(cutData[1]);
					ablaufdatum.add(cutData[2]);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			try {
				datei.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void getUsers(File datei, ArrayList<String> nutzer, ArrayList<String> passwoerter) {
		String[] cutData = { "" };
		if (datei.exists()) {
			System.out.println(GRÜN + "User Wurden Gefunden" + WEIß);
			try (Scanner scan = new Scanner(datei)) {
				while (scan.hasNextLine()) {
					String data = scan.nextLine();
					// data verarbeiten
					cutData = data.split("#");
					nutzer.add(cutData[0]);
					passwoerter.add(cutData[1]);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			try {
				datei.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static File addUserPrefix(File file, ArrayList<String> nutzer , ArrayList<String> passwoerter) {
		String username = "";
		while (true) {
			username = getUserString("Benutzer (\"no\" to create a new one): ");
			if (username.equals("no")) {
				createNewUser(nutzer, passwoerter);
			}
			if (nutzer.contains(username)) {
				login(nutzer.indexOf(username), passwoerter);
				System.out.println("Sie sind Angemeldet");
				File datei = new File(username + file);
				return datei;

			} else {
				System.err.println("Bitte geben sie einen Gültigen nutzer ein!");
			}
		}
	}

	public static void saveList(File name, ArrayList<String> toDo, ArrayList<String> wichtigkeit, ArrayList<String> ablaufdatum) {
		try {

			FileWriter writer = new FileWriter(name);
			writer.write("");
			try {
				for (int i = 0; i < toDo.size(); i++) {
					writer.append(toDo.get(i) + "#" + wichtigkeit.get(i) + "#" + ablaufdatum.get(i) + "\n");
				}
			} catch (IndexOutOfBoundsException e) {

			}
			writer.close();
		} catch (IOException e) {
			System.out.println("Datei nicht beschrieben");
			e.printStackTrace();
		}
	}

	public static void saveUsers(File user2, ArrayList<String> nutzer, ArrayList<String> passwoerter) {
		System.out.println(GRÜN + "Nutzer werden gespeichert." + WEIß);
		try {
			FileWriter writer = new FileWriter(user2);
			writer.write("");
			try {
				for (int i = 1; i < nutzer.size(); i++) {

					writer.append(nutzer.get(i) + "#" + passwoerter.get(i) + "\n");
				}
			} catch (IndexOutOfBoundsException e) {
				System.err.println("Fehler Beim Nutzer Speichern!");
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("Datei nicht beschrieben");
			e.printStackTrace();
		}
	}

	public static boolean login(int pos, ArrayList<String> passwoerter) {
		String pas = "";
		int count = 3;
		while (true) {
			if (count <= 0) {
				System.out.print("Sie haben das passwort zu oft falsch eingegeben!");
				return false;
			}
			pas = getUserString("Passwort: ");
			if (pas.equals(passwoerter.get(pos))) {
				return true;
			} else {
				count--;
				System.err.println("Ihr Passwort war Falsch! Sie haben noch " + count + " versuche");
			}
		}
	}

	public static String getUserString(String frage) {
		Scanner in = new Scanner(System.in);
		System.out.println(frage);
		return in.nextLine();
	}

	public static int getUserInt(String frage) {
		Scanner in = new Scanner(System.in);
		int eingabe = 0;
		while (true) {
			try {
				System.out.println(frage);
				eingabe = in.nextInt();
			} catch (InputMismatchException f) {
				System.err.println("Bitte geben sie eine Zahl ein!");
			}
			return eingabe;
		}
	}

	public static boolean checkDate(String datum) {
		// Daum sollte in TT.MM.JJJJ Format sein.

		String[] cut = datum.split("[.]");
		if (cut.length != 3) {
			System.err.println("Bitte geben sie Ihr Datum in einem TT.MM.JJJJ Format ein!");

			return false;
		}
		int tag = Integer.parseInt(cut[0]);
		int monat = Integer.parseInt(cut[1]);
		int jahr = Integer.parseInt(cut[2]);

		if (tag < 10) {
			tag = Integer.parseInt("0" + tag);
		}
		if (monat < 10) {
			monat = Integer.parseInt("0" + monat);
		}
		if (jahr < 2000) {
			System.err.println("Bitte geben sie ein jahr nach 2000 ein");
		}
		switch (monat) {
		case 1:
			if (tag > 31) {
				System.err.println("Der Januar hat nur 31 Tage!");
				return false;
			}
			break;
		case 2:
			if (jahr % 4 == 0) {
				if (tag > 29) {
					System.err.println("Der Februar hat in Schaltjahren nur 29 Tage!");
					return false;
				}
			} else if (tag > 28) {
				System.err.println("Der Februar hat nur 28 Tage!");
				return false;
			}
			break;
		case 3:
			if (tag > 31) {
				System.err.println("Der März hat nur 31 Tage!");
				return false;
			}
			break;
		case 4:
			if (tag > 30) {
				System.err.println("Der April hat nur 30 Tage!");
				return false;
			}
			break;
		case 5:
			if (tag > 31) {
				System.err.println("Der Mai hat nur 31 Tage!");
				return false;
			}
			break;
		case 6:
			if (tag > 30) {
				System.err.println("Der Juni hat nur 30 Tage!");
				return false;
			}
			break;
		case 7:
			if (tag > 31) {
				System.err.println("Der Juli hat nur 31 Tage!");
				return false;
			}
			break;
		case 8:
			if (tag > 31) {
				System.err.println("Der August hat nur 31 Tage!");
				return false;
			}
			break;
		case 9:
			if (tag > 30) {
				System.err.println("Der September hat nur 30 Tage!");
				return false;
			}
			break;
		case 10:
			if (tag > 31) {
				System.err.println("Der Oktober hat nur 31 Tage!");
				return false;
			}
			break;
		case 11:
			if (tag > 30) {
				System.err.println("Der November hat nur 30 Tage!");
				return false;
			}
			break;
		case 12:
			if (tag > 31) {
				System.err.println("Der Dezember hat nur 31 Tage!");
				return false;
			}
			break;
		default:
			System.err.println("Es gibt nur 12 Monate!");
			return false;

		}
		return true;

	}

	public static void createNewUser(ArrayList<String> nutzer, ArrayList<String> passwoerter) {
		nutzer.add(getUserString("Bitte geben sie einen Neuen Nutzer ein:"));
		passwoerter.add(getUserString("Neues Passwort:"));
	}

	public static void editTask(ArrayList<String> toDo, ArrayList<String> wichtigkeit, ArrayList<String> ablaufdatum, ArrayList<Boolean> erledigt) {
		int num = 0;
		int e = 0;
		String neu = "";
		String wich = "wichtig";
		while (true) {
			try {
				num = getUserInt("Welche Aufgabe möchten sie bearbeiten?");
				if (num < toDo.size() && num > 0) {
					break;
				}
			} catch (InputMismatchException t) {
				System.err.println("Bitte geben sie nur Gültige zahlen ein!");
			}
		}
		while (true) {
			try {
				System.out.println("Was möchten sie ändern?");
				System.out.println("(1) Titel");
				System.out.println("(2) Priorität");
				System.out.println("(3) Fälligkeitsdatum");
				e = getUserInt("(4) Status");
			} catch (InputMismatchException r) {
				System.err.println("Bitte geben sie nur Gültige zahlen ein!");
			}
			if (e <= 4 && e > 0) {
				break;
			}
		}
		switch (e) {
		case 1:
			toDo.remove(num);
			toDo.add(num, getUserString("Zu was möchten sie den Titel ändern?"));
			break;
		case 2:
			wichtigkeit.remove(num);
			System.out.println("Wie wichtig ist die Aufgabe?");
			System.out.println("(1) Sehr Wichtig");
			System.out.println("(2) Wichtig");
			e = getUserInt("(3) Nicht Wichtig");
			switch (e) {
			case 1:
				wichtigkeit.add(num, "Sehr Wichtig");
				break;
			case 2:
				wichtigkeit.add(num, "Wichtig");
				break;
			case 3:
				wichtigkeit.add(num, "Sehr Wichtig");
				break;
			}
			break;
		case 3:
			ablaufdatum.remove(num);
			if (checkDate(getUserString("Zu welchem datum soll es geändet werden?"))) {
				ablaufdatum.add(num, neu);
			}
		case 4:
			if (erledigt.get(num)) {
				erledigt.remove(num);
				erledigt.add(num, false);
			} else {
				erledigt.remove(num);
				erledigt.add(num, true);
			}
		}
	}

	public static void search(ArrayList<String> toDo) {
		String in = "";
		in = getUserString("Wonach möchten sie suchen?");
		for (int i = 0; i < toDo.size(); i++) {
			if (toDo.get(i).contains(in)) {
				System.out.println(toDo.get(i));
			}
		}
	}

	public static void sortByDates(ArrayList<String> toDo, ArrayList<String> ablaufdatum) {
		ArrayList<Integer> daten = formatDates(ablaufdatum);
		boolean[] printed = new boolean[daten.size()];
		int toPrint = 0;
		int biggest = 0;
		boolean allPrinted = false;
		while (!allPrinted) {
			for (int i = 0; i < daten.size(); i++) {
				if (daten.get(i) > biggest && !printed[i]) {
					biggest = daten.get(i);
					toPrint = i;
				}
			}
			System.out.println(toDo.get(toPrint));
			daten.remove(toPrint);
			printed[toPrint] = true;
			biggest = 0;
			allPrinted = true;
			for (boolean i : printed) {
				if (!i) {
					allPrinted = false;
					break;
				}
			}
		}
	}

	public static void sortByName(ArrayList<String> toDo) {
		ArrayList<String> liste = toDo;
		liste.sort(String::compareTo);
		for (String i : liste) {
			System.out.println(i);
		}

	}

	public static void sortByStatus(ArrayList<String> toDo, ArrayList<Boolean> erledigt) {

		for (int w = 0; w < toDo.size(); w++) {
			if (!erledigt.get(w)) {
				System.out.println(toDo.get(w));
			}
		}
		for (int w = 0; w < toDo.size(); w++) {
			if (erledigt.get(w)) {
				System.out.println(toDo.get(w));
			}
		}
	}

	public static ArrayList<Integer> formatDates(ArrayList<String> ablaufdatum) {
		ArrayList<Integer> daten = new ArrayList<Integer>();
		for (int i = 0; i < ablaufdatum.size(); i++) {
			String[] cutDate = ablaufdatum.get(i).split("\\.");
			String fixedDate = cutDate[2] + cutDate[1] + cutDate[0];
			daten.add(Integer.parseInt(fixedDate));
		}
		return daten;
	}
}