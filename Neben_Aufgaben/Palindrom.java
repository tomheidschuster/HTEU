package palindrom;
import java.util.Scanner;

public class palindrom {
	public static void main(String[] args) {
		String myStr = "Hallo";
		boolean palindrom = true;
		Scanner eingabe = new Scanner(System.in);
		String grün = "\u001B[32m";
		String rot = "\u001B[31m";
		String weiß = "\u001B[37m";

		while (true) {
			try {

				System.out.println("Was möchtest du auf ein Palindrom Prüfen?");
				myStr = eingabe.nextLine();
				if (myStr.isBlank()) {
					System.err.println("Sie müssen etwas eingeben.");
					continue;
				}
				long a = Integer.parseInt(myStr);
				System.err.println("Sie dürfen keine Zahlen eingeben");
				continue;

			} catch (java.lang.NumberFormatException r) {
				myStr = myStr.toLowerCase();
				char[] myArray = myStr.toCharArray();
				for (int b = 0, a = myArray.length - 1; b < myArray.length; b++, a--) {
					if (myArray[b] != myArray[a]) {
						palindrom = false;
					}
				}
				if (palindrom == false) {
					System.out.println(rot + "Ihr Wort ist kein Palindrom" + weiß);
					if (weiter() == 0) {
						continue;
					} else {
						break;
					}
				} else {
					System.out.println(grün + "Ihr Wort ist ein Palindrom" + weiß);
					if (weiter() == 0) {
						continue;
					} else {
						break;
					}
				}
			}
		}
	}

	// Überprüft (mithilfe der jaNeimTest Funktion) ob der Nutzer weitermachen
	// möchte. 0 = weiter und 1 = Stop
	static int weiter() {
		String weiterTest = "";
		String newLine = System.lineSeparator();
		System.out.flush();
		System.out.println("Weiter? " + newLine + "J/N");
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

	// Fragt den Nutzer (J/N) und überprüft die Eingabe.
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
}