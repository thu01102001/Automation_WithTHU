package baitap;

import java.util.Scanner;

public class soNguyenTo {
    public static boolean isPrime(int number) {
        if(number <= 1) {
            return false;
        }
        else {
            boolean isPrime = true;
            for (int i = 1; i <= number; i++) {
                if (number % i == 0) {
                    System.out.println("i = " + i);
                    if(i != 1 && i != number) {
                        isPrime = false;
                    }
                }
            }
            return isPrime;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap so nguyen to: ");
        int number = scanner.nextInt();
        if (isPrime(number)) {
            System.out.println(number+ " la so nguyen to");
        }
        else {
            System.out.println(number+ " khong phai la so nguyen to");
        }
    }
}
