package baitap;

import java.util.Scanner;

public class Break_Continue {
    public static void bai1() {
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Nhap ten trinh duyet: ");
            String browser = scanner.nextLine().trim();
            if(browser.equalsIgnoreCase("IE")) {
                continue;
            }
            if(browser.equalsIgnoreCase("Exit")) {
                System.out.println("Thoat");
                break;
            }
            System.out.println("Trinh duyet la: " +browser);
        }

        System.out.println("////////////////////");

        while (true) {
            System.out.println("Nhap thang: ");
            int month = scanner.nextInt();
            switch (month) {
                case 1:
                    System.out.println("JAN");
                    break;
                case 2:
                    System.out.println("FEB");
                    break;
                case 3:
                    System.out.println("MAR");
                    break;
                case 4:
                    System.out.println("APR");
                    break;
                case 5:
                    System.out.println("MAY");
                    break;
                case 6:
                    System.out.println("JUN");
                    break;
                case 7:
                    System.out.println("JUL");
                    break;
                case 8:
                    System.out.println("AUG");
                    break;
                case 9:
                    System.out.println("SEP");
                    break;
                case 10:
                    System.out.println("OCT");
                    break;
                case 11:
                    System.out.println("NOV");
                    break;
                case 12:
                    System.out.println("DEC");
                    break;
                default:
                    System.out.println("Thang ko hop le");
                    break;
            }
            if(month <= 0 || month > 13) {
                break;
            }
        }
    }

}
