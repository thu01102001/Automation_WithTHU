package baitap;

import java.util.Scanner;

public class if_else {
    public static void chanLe(int n) {
        String result = (n % 2 == 0) ? n+ " la so chan" : n+ " la so le";
        System.out.println(result);
    }

    public static void lonNho(int a, int b) {
        String result = (a >= b) ? "a lon hon hoac bang b" : "a nho hon b";
        System.out.println(result);
    }

    public static void name(String name1, String name2) {
        String result = (name1 == name2) ? "2 nguoi cung ten" : "2 nguoi khac ten";
        System.out.println(result);
    }

    public static void sosanh3So(int s1, int s2, int s3) {
        int max = Math.max(s1, Math.max(s2, s3));
        System.out.println("So lon nhat la: " +max);
    }

    public static void search10_100(int number) {
        String result = (number >=10 && number <= 100) ? number+ " nam trong khoang 10 - 100" : number+ " khong nam trong khoang 10 - 100";
        System.out.println(result);
    }

    public static void diemSV(float diem) {
        String result = (diem >= 0 && diem <5) ? "Diem D" : (diem >=5 && diem < 7.5F) ? "Diem C" :
                (diem >= 7.5F && diem < 8.5F) ? "Diem B" : (diem >= 8.5F && diem <= 10) ? "Diem A" : "Khong phu hop";
        System.out.println(result);
    }

    public static void thang(int month) {
        String result = (month == 1 && month == 3 && month == 5 && month == 7 && month ==8 && month == 10 && month ==12) ? "Thang co 31 ngay" :
                (month == 4 && month == 6 && month == 9 && month == 11) ? "Thang co 30 ngay" :
                        (month == 2) ? "Thang co 28 ngay" : "Thang khong phu hop";
        System.out.println(result);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap n: ");
        int n = scanner.nextInt();
        chanLe(n);

        System.out.println("-----------------");
        System.out.println("Nhap a = ");
        int a = scanner.nextInt();
        System.out.println("Nhap b = ");
        int b = scanner.nextInt();
        lonNho(a, b);

        scanner.nextLine();

        System.out.println("-----------------");
        System.out.println("Nhap ten nguoi thu 1: ");
        String name1 = scanner.nextLine();

        System.out.println("Nhap ten nguoi thu 2: ");
        String name2 = scanner.nextLine();
        name(name1, name2);


        System.out.println("-----------------");
        System.out.println("Nhap s1 = ");
        int s1 = scanner.nextInt();
        System.out.println("Nhap s2 = ");
        int s2 = scanner.nextInt();
        System.out.println("Nhap s3 = ");
        int s3 = scanner.nextInt();
        sosanh3So(s1, s2, s3);

        System.out.println("-----------------");
        System.out.println("Nhap number: ");
        int number = scanner.nextInt();
        search10_100(number);

        System.out.println("-----------------");
        System.out.println("Nhap diem: ");
        float diem = scanner.nextFloat();
        diemSV(diem);

        System.out.println("-----------------");
        System.out.println("Nhap thang: ");
        int month = scanner.nextInt();
        thang(month);

    }
}
