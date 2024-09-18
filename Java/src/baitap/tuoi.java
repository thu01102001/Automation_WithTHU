package baitap;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class tuoi {
    public  static void calculateAge(int year) {
        int yearNow = LocalDate.now().getYear();
        if (year > yearNow) {
            System.out.println("Nam sinh khong hop le");
        }
        else {
            int age = yearNow - year;
            System.out.println("Tuoi cua ban la: " +age);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap nam sinh cua ban: ");
        int year = scanner.nextInt();
        calculateAge(year);
    }
}
