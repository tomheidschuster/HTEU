package kassenSystem;
import java.util.InputMismatchException;
import java.util.Scanner;

public class KleinesKassensystem {
	public static void main(String[] args) {
		Scanner eingabe = new Scanner(System.in);
		String newLine = System.lineSeparator();

		double preis = 0;
		double anzahl = 0;
		double gesamtPreis = 0;
		double rabattPreis = 0;

		String Ja = "Ja";
		String Nein = "Nein";
		try {
		System.out.println("Wie teuer sind die Artikel?");
		preis = eingabe.nextInt();

		System.out.println("Wie viele Artikel gibt es?");
		anzahl = eingabe.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Sie Dürfen nur Zahlen eingeben!");
		}
		gesamtPreis = preis * anzahl;

		boolean kundenkarte = true;
		System.out.print("Haben sie eine Kundenkarte?");
		eingabe.nextLine();
		String Str = eingabe.nextLine();
		if (Str.equals(Ja)) {
			kundenkarte = true;
		} else if (Str.equals(Nein)) {
			kundenkarte = false;
		} else {
			System.out.println("Falsche Eingabe!" );
		}
		eingabe.close();

		if (kundenkarte == true) {
			rabattPreis = gesamtPreis - (gesamtPreis * 0.10);
		}

		System.out.println("Gesamter Preis: " + gesamtPreis);
		if (kundenkarte == true) {
			System.out.println(newLine + "Rabattwert: " + gesamtPreis * 0.10 + newLine + "Rabatt Preis: " + rabattPreis);
		}

		if (kundenkarte == true && rabattPreis >= 50) {
			System.out.println("Bei beträgen über 50€ bitte nur mit Karte betahlen");
		} else if (kundenkarte == false && gesamtPreis >= 50) {
			System.out.println("Bei beträgen über 50€ bitte nur mit Karte betahlen");
		}
	}
}