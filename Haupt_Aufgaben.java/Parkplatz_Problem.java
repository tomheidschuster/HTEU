import java.util.*;

public class App {

	public static void main(String[] args) {

		// Eingabe der vorhandenen Reifen.
		int reifen = 0;

		// Eingabe der vorhandenen Fahrzeuge.
		int fahrzeuge = 0;

		// Anzahl der Fehlermeldungen (außgenommen reifen oder Fahrzeuge !=Int).
		int fehler = 0;

		// Ausgabeanzahl der Motorräder.
		int anzahlMotoräder = 0;

		// Ausgabeanzahl der Autos.
		int anzahlAutos = 0;

		// Befehl um die Print Ausgabe in mehreren Zeilen schreiben zu können.
		String newLine = System.lineSeparator();
		// Scanner definieren
		Scanner eingabe = new Scanner(System.in);

//Nutzer eingaben und die Kontrolle ob es möglich ist.

		// Prüfen ob Fahrzeuge und Reifen aus Zahlen bestehen.
		try {
			System.out.print("Wie viele Farzeuge?");
			fahrzeuge = eingabe.nextInt();

			System.out.print("Wie viele Räder?");
			reifen = eingabe.nextInt();
		} catch (InputMismatchException e) {

		}
//Prüfen ob Reifen Positiv sind.
		if (fahrzeuge < 0 || reifen < 0) {
			System.err.print("Zahl muss Positiv sein");
			fehler = fehler + 1;
		}
		eingabe.close();
//Prüfen ob die Anzahl der Reifen gerade ist
		if (reifen % 2 == 1) {
			System.err.println("Die Räder müssen gerade sein");
			fehler = fehler + 1;
		}
//Prüfen ob die Anzahl an reifen zu groß / klein ist
		if (reifen < fahrzeuge * 2) {
			System.err.println("Es gibt zu wenig Räder");
			fehler = fehler + 1;
		} else if (reifen > fahrzeuge * 4) {
			System.err.println("Es gibt zu viele Räder");
			fehler = fehler + 1;
		}
		if (fehler == 0) {
//Berechnung
			int[] typen = berechneFahrzeugtypen(fahrzeuge, reifen);

//Ausgabe
			System.out.println("Autos :" + typen[0] + newLine + "Motoräder :" + typen[1]);
		} else {
			System.err.println("Es gab " + fehler + " Fehler.");
		}
	}

	/**
	 * Berechnet aus der Anzahl an Fahrzeugen und Reifen die Anzahl der Autos und
	 * Motoräder.
	 * 
	 * @param f Gibt die Anzahl an Fahrzeugen an. Der Wert muss größer als Null sein.
	 * @param r Gibt die Anzahl an Fahrzeugen an. Der Wert muss größer als Null sein.
	 * @return Gibt ein Array mit 2 Werten zurück. Der erste entspricht der Anzahl der Autos, der zweite die Anzahl der Motoräder.
	 */
	public static int[] berechneFahrzeugtypen(int f, int r) {
		int[] typen = new int[2];
		typen[0] = (r - f * 2) / 2;
		typen[1] = f - typen[0];
		return typen;
	}
}