package division;
import java.util.Scanner;

public class DivisionsMethode {

	static double division(double zahl1, double zahl2 ) { //double = ausgabetyp , division = name
		if (zahl2 != 0) {
			double ergebnis = zahl1 / zahl2;
			return (ergebnis);
		} else {
			System.err.println("Sie können zahlen nicht mit 0 divisieren, es wird 0 ausgegeben");
			return (0);
		}
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Was ist die erste Zahl?");
		double zahl1 = input.nextDouble();
		System.out.println("Was ist die zweite Zahl?");
		double zahl2 = input.nextDouble();
		input.close();

		System.out.println("Das ergebnis ist " + division(zahl1, zahl2) + ".");
	}
}