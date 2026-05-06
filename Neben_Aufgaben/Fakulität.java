package fakulität;
import java.util.InputMismatchException;
import java.util.Scanner;

public class fakulität {
	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		int e = 0;
		try {
			System.out.print("Welche Fakulität willst du testen?");
			e = input.nextInt();
		} catch (InputMismatchException d) {
			System.out.println("Eingaben können nur Zahlen beinhalten!");
		}

		for (int i = e - 1; i > 0; i--) {
			if (i < 1) {
				e = e * i;
			}
			if (e <= 0) {
				e = 1;
			}
			System.out.println(e);
			input.close();
		}
	}
}
