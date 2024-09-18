package baitap;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class daoNguoc {
    public  static void reverseString(String chuoi) {
        String[] mangKyTu = chuoi.split(""); //tach chuoi thanh mang ky tu
        Collections.reverse(Arrays.asList(mangKyTu)); //chuyen mang thanh danh sach
        String chuoiDaoNguoc = String.join("", mangKyTu);
        System.out.println(chuoiDaoNguoc);

    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap chuoi");
        String chuoi = scanner.nextLine();
        reverseString(chuoi);
    }
}
