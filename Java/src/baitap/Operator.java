package baitap;

import java.util.Scanner;

public class Operator {
    public static void age(int ageCurrent) {
        int ageAfter = ageCurrent + 5;
        System.out.println("5 nam sau tuoi cua ban se la: " +ageAfter);
    }

    public static void hoanDoi(int a, int b) {
        System.out.println("a = " +b);
        System.out.println("b = " +a);
    }

    public static void dieuKien(int s1, int s2) {
        if(s1 > s2) {
            System.out.println(true);
        }
        else {
            System.out.println(false);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int ageCurrent;
        System.out.println("Nhap so tuoi cua ban: ");
        ageCurrent = scanner.nextInt();
        age(ageCurrent);

        System.out.println("-------------");
        System.out.println("Nhap a = ");
        int a = scanner.nextInt();
        System.out.println("Nhap b = ");
        int b = scanner.nextInt();
        hoanDoi(a, b);

        System.out.println("-------------");
        System.out.println("Nhap so thu 1: ");
        int s1 = scanner.nextInt();
        System.out.println("Nhap so thu 2: ");
        int s2 = scanner.nextInt();
        dieuKien(s1, s2);


    }
}
