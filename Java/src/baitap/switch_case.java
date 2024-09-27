package baitap;

import java.util.Scanner;

public class switch_case {
    public static void bai1(int number) {
        switch (number) {
            case 1:
                System.out.println("One");
                break;
            case 2:
                System.out.println("Two");
                break;
            case 3:
                System.out.println("Three");
                break;
            case 4:
                System.out.println("Four");
                break;
            case 5:
                System.out.println("Five");
                break;
            case 6:
                System.out.println("Six");
                break;
            case 7:
                System.out.println("Seven");
                break;
            case 8:
                System.out.println("Eight");
                break;
            case 9:
                System.out.println("Nine");
                break;
            case 10:
                System.out.println("Ten");
                break;
            default:
                System.out.println("Khong hop le");
                break;
        }
    }

    public static void bai2(int a, int b) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap phep toan: ");
        String phepToan = scanner.nextLine();
        switch (phepToan) {
            case "+":
                System.out.println(a+b);
                break;
            case "-":
                System.out.println(a-b);
                break;
            case "*":
                System.out.println(a*b);
                break;
            case "/":
                System.out.println(a/b);
                break;
            default:
                System.out.println("Khong hop le");
                break;

        }
    }

    public static void thang(int month) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                System.out.println("Thang co 31 ngay");
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                System.out.println("Thang co 30 ngay");
                break;
            case 2:
                System.out.println("Thang co 28 ngay");
                break;
            default:
                System.out.println("Thang ko hop le");
                break;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap so: ");
        int number = scanner.nextInt();
        bai1(number);

        System.out.println("----------------");
        System.out.println("Nhap a = ");
        int a = scanner.nextInt();
        System.out.println("Nhap b = ");
        int b = scanner.nextInt();
        scanner.nextLine();
        bai2(a, b);

        System.out.println("----------------");
        System.out.println("Nhap thang = ");
        int month = scanner.nextInt();
        thang(month);
    }
}
