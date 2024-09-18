package baitap;

import java.util.Scanner;

public class bmi {
    public static void calculator(double weight, double height) {
        double bMI = weight / (height * height);
        System.out.println(bMI);
        if (bMI < 18.5) {
            System.out.println("Gay");
        } else if (bMI >= 18.5 && bMI < 24.9) {
            System.out.println("Binh thuong");
        } else if (bMI >= 25 && bMI < 29.9) {
            System.out.println("Thua can");
        } else {
            System.out.println("Beo phi");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nhap can nang: ");
        double weight = scanner.nextDouble();

        System.out.println("Nhap chieu cao: ");
        double height = scanner.nextDouble();

        calculator(weight, height);
    }

}