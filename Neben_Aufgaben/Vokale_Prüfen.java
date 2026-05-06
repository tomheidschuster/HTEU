import java.util.Scanner;

public class VokalePrüfen {
	public static void main(String[] args) {

		boolean vokal = false;
		char[] vokalArray = { 'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U' };
		char[] kleinbuchstabenArray = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
				'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', };
		char[] großbuchstabenArray = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
				'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		
		String myStr = eingabe();
		char[] myArray = myStr.toCharArray();
		for (int b = 0; b < myArray.length; b++) {
			for (int a = 0; a < vokalArray.length; a++) {
				if (myArray[b] == vokalArray[a]) {
					vokal = true;
				}
			}
		}
		if (vokal == true) {
			for (int i = 0; i < myArray.length; i++) {
				for (int p = 0; p <= (großbuchstabenArray.length - 1); p++) {
					if (myArray[i] == kleinbuchstabenArray[p] || myArray[i] == großbuchstabenArray[p]) {
						System.out.print(großbuchstabenArray[p]);
					}
				}
			}
		} else {
			System.err.println("Dein Wort enthält keine Vokale");
		}
	}

	static String eingabe() {
		Scanner eingabe = new Scanner(System.in);
		String myStr = "Hallo";
		while (true) {
			try {
				System.out.println("Was möchtest du auf Vokale Prüfen?");
				myStr = eingabe.nextLine();
				int test = Integer.parseInt(myStr);

				System.err.println("Sie dürfen keine Zahlen eingeben.");
				continue;

			} catch (NumberFormatException d) {
				if (myStr.isBlank()) {
					System.err.println("Sie müssen etwas eingeben!");
					continue;
				}
				eingabe.close();
				return myStr;
			}
		}
	}
}