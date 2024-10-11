package baitap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Chuoi {
    public static void bai1() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap chuoi: ");
        String textAll = scanner.nextLine();
        int count = 0;
        //String texetetextAllTrim = textAll.replaceAll(" ", "");
        String[] texts = textAll.split("");
        for (String text: texts) {
//            if(text.equals(text.toUpperCase())) {
//                count += 1;
//            }
            //kiem tra ky tu la chu cai va la chu in hoa
            if (text.matches("[A-Z]")) {
                count += 1;
            }
        }
        System.out.println(count);
    }

    public static void bai2() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap chuoi: ");
        String textAll = scanner.nextLine();
        String textAllNoSpace = textAll.replaceAll(" ", "");
        String[] texts = textAllNoSpace.split("");
        int countA = 0;
        for (String text : texts) {
            if(text.matches("a")) {
                countA += 1;
            }
        }
        System.out.println("Tong chu a la: " +countA);

        if(textAllNoSpace.contains("Testing")) {
            System.out.println(true);
        }
        else {
            System.out.println(false);
        }

        if (textAllNoSpace.startsWith("Automation")) {
            System.out.println(true);
        }
        else {
            System.out.println(false);
        }

        if(textAllNoSpace.endsWith("Online")) {
            System.out.println(true);
        }
        else {
            System.out.println(false);
        }

        if(textAllNoSpace.indexOf("Tutorials") == -1) {
            System.out.println("Khong ton tai");
        }
        else {
            System.out.println(textAllNoSpace.indexOf("Tutorials"));
        }

        System.out.println(textAll.replaceAll("(?)Online", "Offline"));

        String[] count = textAll.split(" ");
        System.out.println(count.length);

    }

    public static void bai3() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap chuoi: ");
        String textAll = scanner.nextLine();
//        String[] textNoSpace = textAll.split("");
//        List<String> texts = new ArrayList<>();
//        for(String text : textNoSpace) {
//            texts.add(text);
//        }
//        Collections.reverse(texts);
//        System.out.println(String.join("", texts));

        //StringBuilder : dao nguoc chuoi
        String textDN = new StringBuilder(textAll).reverse().toString();
        System.out.println(textDN);

    }

    public static void bai4() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap chuoi: ");
        String textAll = scanner.nextLine();
        if(textAll.matches("[0-9]{10}")) {
            if(textAll.startsWith("7") || textAll.startsWith("8") || textAll.startsWith("9")) {
                System.out.println("So dien thoai dung dinh dang");
            }
            else {
                System.out.println("So dien thoai khong dung dinh dang");
            }
        } else {
            System.out.println("Khong dung dinh dang");
        }
    }
    public static void main(String[] args) {
        //bai1();
        //bai2();
        //bai3();
        bai4();
    }
}
