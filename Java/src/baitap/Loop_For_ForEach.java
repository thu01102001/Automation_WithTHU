package baitap;

import java.util.Scanner;

public class Loop_For_ForEach {
    public static void bai1(int n) {
        for(int i = 1; i <= n; i++) {
            System.out.print(i+ " ");
        }
    }

    public static void bai2(int a, int b) {
        for(int i = a; i <= b; i++) {
            System.out.print(i+ " ");
        }
    }

    public static void bai3() {
        for (int i = 1; i <= 10; i++) {
            if(i % 2 == 0) {
                System.out.print(i+ " ");
            }
        }
    }

    public static void bai4(int n1, int n2) {
        int tong = 0;
        for (int i = n1; i <= n2; i++) {
            tong += i;
        }
        System.out.println(tong);
    }

    public static void bai5(int number) {
        int tong = 0;
        for (int i = 0; i <= number; i++) {
            if(i % 2 != 0) {
                tong += i;
            }
        }
        System.out.println(tong);
    }

    public static void bai6(int number1, int number2) {
        for (int i = number1; i <= number2; i++) {
            if(i % 3 == 0) {
                System.out.print(i+ " ");
            }
        }
    }

    public static void bai7(int so) {
        int result = 1;
        for (int i = 1; i <= so; i++) {
            result *= i;  // Nhân dần dần với các giá trị từ 1 đến so
        }
        System.out.println(result);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap n = ");
        int n = scanner.nextInt();
        bai1(n);
        System.out.println("--------------------");

        System.out.println("Nhap a = ");
        int a = scanner.nextInt();
        System.out.println("Nhap b = ");
        int b = scanner.nextInt();
        bai2(a, b);
        System.out.println("--------------------");

        bai3();
        System.out.println("--------------------");

        System.out.println("Nhap n1 = ");
        int n1 = scanner.nextInt();
        System.out.println("Nhap n2 = ");
        int n2 = scanner.nextInt();
        bai4(n1, n2);
        System.out.println("--------------------");

        System.out.println("Nhap n = ");
        int number = scanner.nextInt();
        bai5(number);
        System.out.println("--------------------");

        System.out.println("Nhap number1 = ");
        int number1 = scanner.nextInt();
        System.out.println("Nhap number2 = ");
        int number2 = scanner.nextInt();
        bai6(number1, number2);
        System.out.println("--------------------");

        System.out.println("Nhap so = ");
        int so = scanner.nextInt();
        bai7(so);

    }
}
