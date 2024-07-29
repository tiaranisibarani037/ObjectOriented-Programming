//buatlah rpogram untuk memeriksa bilangan genap atau ganjil

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter an integer: ");
        int bilangan = input.nextInt();
        if (bilangan % 2 != 0) {
            System.out.println("Weird");
        } else {
            if (bilangan >= 2 && bilangan <= 5) {
                System.out.println("Not Weird");
            } else if (bilangan >= 6 && bilangan <= 20) {
                System.out.println("Weird");
            } else {
                System.out.println("Not Weird");
            }
        }
    }
}