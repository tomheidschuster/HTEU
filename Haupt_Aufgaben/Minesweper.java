import java.util.InputMismatchException;
import java.util.Scanner;

public class Minesweper {
	static int DIM = 4;
	static int BOMBENPROZENT = 25;
	static int vermutungen = 0;
	static int FREIEFELDER;
	static int DISPLAY[][];
	static boolean AUSGEWÄHLT[][];
	static boolean[][] bombenVermutungen;
	static int[][] startFeld;

	public static void main(String[] args) {
		String grün = "\u001B[32m"; // 2 Nachbarn
		String rot = "\u001B[31m"; // 5 - 8 Nachbarn
		String gelb = "\u001B[33m"; // 3 Nachbarn
		String blau = "\u001B[34m"; // 1 Nachbar
		String lila = "\u001B[35m"; // 4 Nachbarn
		String zurück = "\u001B[0m"; // zurücksetzen

		Scanner auswahl = new Scanner(System.in);
		int größe = DIM;
		while (true) {
			try {
				System.out.print("Dimensionen: ");
				größe = auswahl.nextInt();
				if (größe <= 1 || größe >= 100) {
					System.err.println("Bitte geben sie eine Zahl von 2 bis 99 ein");
					continue;
				}
				DIM = größe;

				break;
			} catch (InputMismatchException e) {
				System.err.println("Bite geben sie eine Zahl ein.");
				auswahl.next();
			}
		}

		while (true) {
			try {
				System.out.print("Prozent an Bomben: ");
				größe = auswahl.nextInt();
				if (größe >= 100) {
					System.err.println("Sie können nicht 100% oder mehr eingeben.");
					continue;
				}
				BOMBENPROZENT = größe;

				break;
			} catch (InputMismatchException e) {
				System.err.println("Bite geben sie eine Zahl ein.");
				auswahl.next();
			}
		}

		FREIEFELDER = DIM * DIM;
		DISPLAY = new int[DIM + 1][DIM + 1];
		AUSGEWÄHLT = new boolean[DIM + 1][DIM + 1];
		bombenVermutungen = new boolean[DIM + 1][DIM + 1];
		startFeld = new int[DIM + 1][DIM + 1];
		int[][] felder = new int[DIM + 1][DIM + 1];
		bombenleger(felder);
		System.out.println("Geben sie die Felder in einem Zahl/Zahl Format ein!");
		System.out.println("Um eine Bombe einzutragen geben sie  " + rot + "\"b\" " + zurück + " vor dem Feld ein.");
		while (true) {
			for (int b = 0; b < felder.length; b++) {
				for (int a = 0; a < felder.length; a++) {
					if (a == 0 || b == 0) {

						if (a == 0) {
							if (DIM >= 10 && b < 10) {
								System.out.print(" ");
							}
							System.out.print(" " + b + " ");

						} else {
							if (DIM >= 10 && a < 10) {
								System.out.print(" ");
							}
							System.out.print(" " + a + " ");
						}
					} else {
						if (DIM >= 10) {
							System.out.print(" ");
						}
						if (AUSGEWÄHLT[b][a]) {
							if (DISPLAY[b][a] == 1) {
								System.out.print(blau + "[" + DISPLAY[b][a] + "]" + zurück);
							} else if (DISPLAY[b][a] == 2) {
								System.out.print(grün + "[" + DISPLAY[b][a] + "]" + zurück);

							} else if (DISPLAY[b][a] == 3) {
								System.out.print(gelb + "[" + DISPLAY[b][a] + "]" + zurück);

							} else if (DISPLAY[b][a] == 4) {
								System.out.print(lila + "[" + DISPLAY[b][a] + "]" + zurück);

							} else if (DISPLAY[b][a] >= 5 && DISPLAY[b][a] <= 8) {
								System.out.print(rot + "[" + DISPLAY[b][a] + "]" + zurück);

							} else {
								System.out.print("[" + DISPLAY[b][a] + "]");

							}
						} else if (bombenVermutungen[b][a]) {
							System.out.print("[*]");
						} else {
							System.out.print("[?]");
						}
					}
				}
				System.out.println();
			}
			if (winTest()) {
				System.out.println(grün + "Sie haben gewonnen!" + zurück);
				return;
			}

			if (nutzerAuswahl(felder)) {
				return;
			} else {
				continue;
			}
		}
	}

	static void bombenleger(int[][] felder) {
		String rot = "\u001B[31m"; // 5 - 8 Nachbarn
		String zurück = "\u001B[0m"; // zurücksetzen

		// 25% sollen Bomben sein.
		int mengeBomben = (int) (DIM * DIM * BOMBENPROZENT) / 100;
		if (mengeBomben < 1) {
			mengeBomben = 1;
		}

		System.out.println("Menge an Bomben: " + rot + mengeBomben + zurück);
		int randomNum1 = 0;
		int randomNum2 = 0;
		int randomCount = 0;

		while (randomCount < mengeBomben) {
			randomNum1 = (int) (Math.random() * DIM) + 1;
			randomNum2 = (int) (Math.random() * DIM) + 1;
			if (felder[randomNum1][randomNum2] == 0) {
				felder[randomNum1][randomNum2] = 1;
				startFeld[randomNum1][randomNum2] = 1;
				randomCount++;
			}
		}
	}

	static boolean nutzerAuswahl(int[][] felder) {
		String feld = "";
		Scanner auswahl = new Scanner(System.in);
		int pos1 = 0;
		int pos2 = 0;

		String grün = "\u001B[32m";
		String rot = "\u001B[31m";
		String weiß = "\u001B[37m";

		boolean bombenVermutung = false;
		while (true) {
			try {
				System.out.print(grün + "Feld: " + weiß);
				feld = auswahl.nextLine();
				feld = feld.strip();
				if (feld.contains("b")) {
					feld = feld.substring(1);
					bombenVermutung = true;
					vermutungen++;
					System.out.println("Feld wird als Bombe vermutet");
				}
				if (feld.contains("/")) {

					String[] myArray = feld.split("/");

					pos1 = Integer.parseInt(myArray[0]);
					pos2 = Integer.parseInt(myArray[1]);
					if (bombenVermutung) {
						bombenVermutungen[pos1][pos2] = true;
						break;
					}

					if (pos1 > DIM || pos2 > DIM || pos1 <= DIM - DIM || pos2 <= DIM - DIM) {
						System.err.println("Ihre Eingabe darf Maximal " + DIM + " oder mindestens 1 sein.");
						continue;
					}
					if (bombenVermutungen[pos1][pos2]) {

						if (jaNeinAbfrage("Sie haben gesagt dieses feld hat eine Bombe sind sie sicher?") == 1) {
							continue;
						} else {
							bombenVermutungen[pos1][pos2] = false;
						}
					}

					if (!AUSGEWÄHLT[pos1][pos2]) {
						break;
					} else {
						System.err.println("Dieses Feld haben sie schon ausgewählt");
						continue;
					}
				} else if (feld.equals("l")) {
					for (int b = 0; b < startFeld.length; b++) {
						for (int a = 0; a < startFeld.length; a++) {

							if (a == 0) {
								if (DIM >= 10 && b < 10) {
									System.out.print(" ");
								}
								System.out.print(" " + b + " ");
							} else if (b == 0) {
								if (DIM >= 10 && a < 10) {
									System.out.print(" ");
								}
								System.out.print(" " + a + " ");
							} else {
								if (DIM >= 10) {
									System.out.print(" ");
								}
								System.out.print("[" + startFeld[b][a] + "]");

							}

						}
						System.out.println();

					}
				}

				System.err.println("Sie Müssen Zahl/Zahl Eingeben");
			} catch (NumberFormatException e) {
				System.err.println("Sie Müssen Zahl/Zahl Eingeben");
			}

		}
		if (bombenVermutung == true)

		{
			bombenVermutungen[pos1][pos2] = true;
			return false;
		}
		if (felder[pos1][pos2] == 0) {
			System.out.println("Sie haben ein freies Feld Getroffen!");
			if (bombenVermutungen[pos1][pos2]) {
				bombenVermutungen[pos1][pos2] = false;
			}
			AUSGEWÄHLT[pos1][pos2] = true;
			anliegende(felder, pos1, pos2);
			FREIEFELDER--;
			if (FREIEFELDER == ((DIM * DIM) / 4)) {
				System.out.println(grün + "Sie haben gewonnen!" + weiß);
				return true;
			}
			return false;
		} else {
			System.out.println(rot + "Sie haben eine Bombe Getroffen!");
			System.out.println("Bomben:");
			for (int b = 0; b < startFeld.length; b++) {
				for (int a = 0; a < startFeld.length; a++) {

					if (a == 0) {
						if (DIM >= 10 && b < 10) {
							System.out.print(" ");
						}
						System.out.print(" " + b + " ");
					} else if (b == 0) {
						if (DIM >= 10 && a < 10) {
							System.out.print(" ");
						}
						System.out.print(" " + a + " ");
					} else {
						if (DIM >= 10) {
							System.out.print(" ");
						}
						if (startFeld[b][a] == 1) {
							System.out.print("[*]");
						} else {
							System.out.print("[ ]");						}
					}

				}
				System.out.println();

			}
			System.out.println(weiß);
			return true;
		}
	}

	static void anliegende(int[][] felder, int pos1, int pos2) {

		int counter = 0;
		try {
			if (startFeld[pos1 - 1][pos2] == 1) {
				counter++;
			}
		} catch (IndexOutOfBoundsException e) {
		}
		try {
			if (startFeld[pos1 + 1][pos2] == 1) {
				counter++;
			}
		} catch (IndexOutOfBoundsException e) {
		}
		try {
			if (startFeld[pos1][pos2 - 1] == 1) {
				counter++;
			}

		} catch (IndexOutOfBoundsException e) {
		}
		try {
			if (startFeld[pos1][pos2 + 1] == 1) {
				counter++;
			}
		} catch (IndexOutOfBoundsException e) {
		}
		try {
			if (startFeld[pos1 - 1][pos2 - 1] == 1) {
				counter++;
			}
		} catch (IndexOutOfBoundsException e) {
		}
		try {
			if (startFeld[pos1 - 1][pos2 + 1] == 1) {
				counter++;
			}
		} catch (IndexOutOfBoundsException e) {
		}
		try {
			if (startFeld[pos1 + 1][pos2 - 1] == 1) {
				counter++;
			}
		} catch (IndexOutOfBoundsException e) {
		}
		try {
			if (startFeld[pos1 + 1][pos2 + 1] == 1) {
				counter++;
			}
		} catch (IndexOutOfBoundsException e) {
		}
		DISPLAY[pos1][pos2] = counter;

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

	static int countDigits(int number) {
		int count = 0;

		if (number < 10) {
			count++;
		} else {
			count += countDigits(number / 10) + 1;
		}

		return count;
	}

	public static boolean winTest() {
		// Startfeld for-schleife
		int count = 0;
		for (int i = 0; i < startFeld.length; i++) {
			for (int o = 0; o < startFeld.length; o++) {
				// bombenvermutungen for schleifen
				if (startFeld[i][o] == 1 && bombenVermutungen[i][o] == true) {
					count++;
				}
			}
		}
		if (count == (int) (DIM * DIM * BOMBENPROZENT) / 100) {
			return true;
		}
		return false;
	}

}