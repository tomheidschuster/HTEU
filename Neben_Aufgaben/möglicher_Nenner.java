package möglicheNenner;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MöglicherNennerEinerZahl {
	public static void main(String[] args) {

		int n = 0;
		boolean vorherigerTeiler = false;

		Scanner eingabe = new Scanner(System.in);
		
			// Eingabe und Kontrolle ob Eingabe ein Integer ist
			try {
				System.out.println("Welche zahl möchtest du auf teilbarkeit durch 2, 3, 5 oder 7 testen?");
				n = eingabe.nextInt();
			} catch (InputMismatchException e) {
				System.err.println("Eingabe muss aus Zahlen bestehen");
				eingabe.close();

			}

			System.out.print("Die zahl ist ");
			if (n % 2 == 0 || n % 3 == 0 || n % 5 == 0 || n % 7 == 0) {
				if (n % 2 == 0) {
					System.out.print(" durch 2 ");
					vorherigerTeiler = true;
				}

				if (n % 3 == 0) {
					if (vorherigerTeiler == true) {
						System.out.print("und ");
					}
					System.out.print(" durch 3 ");
					vorherigerTeiler = true;
				}

				if (n % 5 == 0) {
					if (vorherigerTeiler == true) {
						System.out.print("und ");
					}
					System.out.print(" durch 5 ");
					vorherigerTeiler = true;
				}

				if (n % 7 == 0) {
					if (vorherigerTeiler == true) {
						System.out.print("und ");
					}
					System.out.print(" durch 7 ");
					vorherigerTeiler = true;
				}
				System.out.print("telibar.");
			} else {
				System.out.print("nicht durch 2, 3, 5 oder 7 teilbar.");
			}
		}
	}
