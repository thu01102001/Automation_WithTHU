package baitap;

import java.util.Scanner;

public class While_doWhile {
    public static void bai1(int n) {
        int i = n;
        while (i <= 100) {
            if(i % 2 == 0) {
                System.out.print(i+ " ");
            }
            i++;
        }
    }
    public static void bai2(int a, int b) {
        int i = a;
        while (i <= b) {
            if(i % 3 == 0 && i % 5 == 0) {
                System.out.print(i+ " ");
            }
            i++;
        }
    }

    public static void bai3(int number) {
        int i = 0;
        int result = 0;
        while (i <= number) {
            if(i % 2 != 0) {
                result += i;
            }
            i++;
        }
        System.out.println(result);
    }

    public static void bai4(int s1, int s2) {
        int i = s1;
        while (i <= s2) {
            if(i % 3 == 0) {
                System.out.print(i+ " ");
            }
            i++;
        }
    }

    public static void bai5(int so) {
        int i = 1;
        int result = 1;
        while (i <= so) {
            result *= i;
            i++;
        }
        System.out.println(result);
    }

    public static void bai6() {
        int i = 1;
        while (i <= 10) {
            if(i % 2 == 0) {
                System.out.print(i+ " ");
            }
            i++;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap n = ");
        int n = scanner.nextInt();
        bai1(n);
        System.out.println("-------------");
        System.out.println("Nhap a = ");
        int a = scanner.nextInt();
        System.out.println("Nhap b = ");
        int b = scanner.nextInt();
        bai2(a, b);
        System.out.println("-------------");
        System.out.println("Nhap number = ");
        int number = scanner.nextInt();
        bai3(number);
        System.out.println("-------------");
        System.out.println("Nhap s1 = ");
        int s1 = scanner.nextInt();
        System.out.println("Nhap s2 = ");
        int s2 = scanner.nextInt();
        bai4(s1, s2);
        System.out.println("-------------");
        System.out.println("Nhap so = ");
        int so = scanner.nextInt();
        bai5(so);

        System.out.println("-------------");
        System.out.println("Day so 1 - 10 chia het cho 2: ");
        bai6();

    }
}
