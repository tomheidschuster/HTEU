package ratespiel;
import java.util.Scanner;

public class Ratespiel {
	public static void main(String[] args) {

		Scanner eingabe = new Scanner(System.in);
		String grün = "\u001B[32m";
		String rot = "\u001B[31m";
		String weiß = "\u001B[37m";

		int versuche = 5;
		int randomNum = 0;
		int spielerNum = 1;
		randomNum = (int) (Math.random() * 24);
		randomNum = randomNum + 1;
		System.out.println("Errate die Zahl zwischen 0 und 25.");

		while (spielerNum != randomNum && versuche > 0) {
			System.out.println("Du hast noch " + versuche + " Versuche" + weiß);
			spielerNum = eingabe.nextInt();
			
			if (versuche > 0) {
				if (spielerNum == randomNum) {
					System.out.println(grün + "Du hast die Zahl " + spielerNum + " erraten!");
				} else if (spielerNum < randomNum) {
					System.out.println(rot + "Die Zahl ist größer als " + spielerNum);
					versuche--;
				} else {
					System.out.println(rot + "Die Zahl ist kleiner als " + spielerNum);
					versuche--;
				}
			}
		}
		if (spielerNum != randomNum) {
			System.out.println(rot + "Du hast die Zahl nicht erraten, dir Zahl war " + randomNum + "!");

			eingabe.close();

		}
	}
}