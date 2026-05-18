package uebung;

import java.util.ArrayList;

public class Uebung {

	static ArrayList<String> printer = new ArrayList<String>();

	public static void main(String args[]) {
		printer.add("c");
		printer.add("b");
		printer.add("a");
		printer.add("d");
		printer.add("e");

		sortByName();
	}

	public static void sortByName() {
		ArrayList<String> namen = printer;
		int champ = 100;
		int klein = 0;
		int groß = 0;
		int c = namen.size();
		String toPrint = "";
		int returnArray[] = new int[2];
		boolean printing = true;
		while (printing) {
			for (String i : namen) {
				klein = compareToKleinbuchstaben(i);
				groß = compareToGroßbuchstaben(i);
				if (klein == -1) {
					if (groß < champ) {
						champ = groß;
						toPrint = i;
					}
				} else {
					if (klein < champ) {
						champ = klein;
						toPrint = i;
					}
				}
			}
		}
		System.out.print(toPrint);
		namen.remove(champ);
		champ=100;

		c--;
		if (c == 0) {
			printing = false;
		}

	}

	public static int compareToKleinbuchstaben(String name) {
		int c = 0;
		char[] nameArray = name.toCharArray();
		char[] kleinbuchstabenArray = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
				'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', ' ' };

		for (char i : kleinbuchstabenArray) {
			c++;
			if (nameArray[0] == i) {
				return c;
			}
		}
		return -1;
	}

	public static int compareToGroßbuchstaben(String name) {
		int c = 0;
		char[] nameArray = name.toCharArray();

		char[] großbuchstabenArray = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
				'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' ' };
		for (char i : großbuchstabenArray) {
			c++;
			if (nameArray[0] == i) {

				return c;
			}

		}
		return -1;
	}

	public static int[] sortN(int e, int champ) {
		boolean toPrint = false;
		if (e < champ) {
			champ = e;
			toPrint = true;
		}
		if (toPrint) {
			return new int[] { champ, 1 };
		} else {
			return new int[] { champ, 0 };

		}
	}

}
