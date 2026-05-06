package toDoListe;
import java.util.*;
import java.io.*;

public class ToDoListe {
	static final String GRÜN = "\u001B[32m";
	static final String ROT = "\u001B[31m";
	static final String WEIß = "\u001B[37m";
	static final String GELB = "\033[33m";
	static ArrayList<String> toDo = new ArrayList<String>();
	static ArrayList<String> wichtigkeit = new ArrayList<String>();
	static ArrayList<String> ablaufdatum = new ArrayList<String>();
	static ArrayList<String> erledigt = new ArrayList<String>();
	static ArrayList<String> nutzer = new ArrayList<String>();
	static ArrayList<String> passwoerter = new ArrayList<String>();
	static String newLine = System.lineSeparator();


	static File user = new File("UsersToDoListe.txt");
	static File datei = new File("ToDoListe.txt");

	public static void main(String args[]) {
		nutzer.add("admin");
		passwoerter.add("1234");
		int in = 0;
		getUsers(user);
		datei = addUserPrefix(datei);
		setupFile(datei);

		while (true) {

			System.out.println("Was möchten sie machen?");
			System.out.println("(0) Benutzer Hinzufügen");
			System.out.println("(1) Programm beenden");
			System.out.println("(2) Aufgabe Hinzufügen");
			if (toDo.size() > 0) {
				System.out.println("(3) Aufgaben Anzeigen");
			}
			if (erledigt.size() > 0) {
				System.out.println("(4) Erledigte Aufgaben Anzeigen");
			}
			if (toDo.size() > 0) {
				System.out.println("(5) Aufgabe entfernen");
			}

			try {
				in = getUserInt("");
			} catch (InputMismatchException e) {
				System.err.println("Sie können nur Zahlen eingaben!");
			}
			switch (in) {
			case 0:
				addUsers(user);
				break;
			case 1:
				saveUsers(user);
				saveList(datei);
				return;
			case 2:
				String[] e = newTask();
				toDo.add(e[0]);
				ablaufdatum.add(e[1]);
				wichtigkeit.add(e[2]);
				break;
			case 3:
				try {
					in = getUserInt("Möchten sie Alle(1) , Sehr Wichtige(2), Wichtige(3) oder Nicht Wichtige(4) Aufgaben sehen?");
				} catch (InputMismatchException r) {
					System.out.println("Bitte geben sie eine Zahl ein!");
				}
				switch (in) {
				case 1:
					showList();
					break;
				case 2:
					showSehrWichtig();
					break;
				case 3:
					showWichtig();
				case 4:
					showNichtWichtig();
					break;
				default:
					System.out.println("Bitte geben sie eine Zahl von 1 bis 4 ein");
				}
				break;

			case 4:
				if (erledigt.size() > 0) {
					showFinished();
				} else {
					System.err.println("Bitte verwenden Sie eine der Optionen");
				}
				break;
			case 5:
				if (toDo.size() > 0) {
					removeTask();
				} else {
					System.err.println("Bitte verwenden Sie eine der Optionen");
				}
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

	public static void showList() {
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

	public static void showSehrWichtig() {
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

	public static void showWichtig() {
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

	public static void showNichtWichtig() {
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

	public static void showFinished() {
		if (erledigt.size() == 0) {
			System.out.println("Sie haben keine fertigen Aufgaben!");
			return;
		}
		for (int i = 0; i < erledigt.size(); i++) {
			if (i < 10) {
				System.out.println("0" + (i + 1) + " " + erledigt.get(i));
			} else {
				System.out.println((i + 1) + " " + erledigt.get(i));
			}
		}
	}

	public static void removeTask() {
		int in = 0;
		while (true) {
			try {
				in = getUserInt("Welche Aufgabe möchen sie Löschen?") - 1;
				erledigt.add(toDo.get(in));
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
				break;
			} else {
				System.err.println("Bitte geben sie etwas ein");
			}
		}
		while (weiter) {
			level = getUserString("Wie wichtig ist die aufgabe?" + newLine + "1 = Sehr wichtig" + newLine + "2 = Wichtig" + newLine +"3 = Nicht wichtig");
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

	public static void setupFile(File datei) {
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

	public static void getUsers(File datei) {
		String[] cutData = {""};
		if (datei.exists()) {
			System.out.println(GRÜN + "User Wurden Gefunden" + WEIß);
			System.out.println(datei.getAbsolutePath());
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

	public static File addUserPrefix(File file) {
		String username = "";
		while (true) {
			username = getUserString("Benutzer: ");
			if (nutzer.contains(username)) {
				login(nutzer.indexOf(username));
				System.out.println("Sie sind Angemeldet");
				File datei = new File(username + file);
				return datei;

			} else {
				System.err.println("Bitte geben sie einen Gültigen nutzer ein!");
			}
		}
	}

	public static void addUsers(File datei) {
		String newUser = "";
		newUser = getUserString("Wie soll ihr nutzername sein?");
		nutzer.add(newUser);
		newUser = getUserString("Wie soll ihr passwort sein?");
		passwoerter.add(newUser);
		
	}

	public static void saveList(File name) {
		try {

			FileWriter writer = new FileWriter(name);
			writer.write("");
			for (int i = 0; i < toDo.size(); i++) {
				System.out.println(toDo.get(i) + "#" + wichtigkeit.get(i) + "#" + ablaufdatum.get(i));
				writer.append(toDo.get(i) + "#" + wichtigkeit.get(i) + "#" + ablaufdatum.get(i) + "\n");
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("Datei nicht beschrieben");
			e.printStackTrace();
		}
	}

	public static void saveUsers(File user2) {
		try {
			FileWriter writer = new FileWriter(user2);
			writer.write("");
			for (int i = 1; i < toDo.size(); i++) {
				System.out.println(nutzer.get(i));
				writer.append(nutzer.get(i) + "#" +  passwoerter.get(i) + "\n");
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("Datei nicht beschrieben");
			e.printStackTrace();
		}
	}
	public static boolean login(int pos) {
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
		Scanner in = new Scanner (System.in);
		System.out.println(frage);
		return in.nextLine();
	}
	
	public static int getUserInt(String frage) {
		Scanner in = new Scanner (System.in);
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
}