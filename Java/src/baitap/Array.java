package baitap;

import java.util.Scanner;

public class Array {
    public static void bai1() {
        int[] numbers = {2, 7, 6, 8, 9};
        int max = numbers[0];
        for (int number : numbers) {
            if(number > max) {
                max = number;
            }
        }
        System.out.println("Phan tu lon nhat trong mang: " +max);
    }

    public static void bai1_1() {
        int[] num = new int[5];
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        int max = num[0];
        while (i < num.length) {
            System.out.println("nhap phan tu thu " +(i+1)+ ": ");
            num[i] = scanner.nextInt();
            i++;
        }
        System.out.println("Cac phan tu vua nhap trong mang la: ");
        for(int n : num) {
            System.out.print(n+ " ");
            if(n > max) {
                max = n;
            }
        }
        System.out.println("Phan tu lon nhat: " +max);
    }

    public static void bai2() {
        Scanner scanner = new Scanner(System.in);
        int[] arr = new int[5];
        for (int i = 0; i < arr.length; i++) {
            System.out.println("Nhap phan tu thu " +(i+1)+ ": ");
            arr[i] = scanner.nextInt();
        }
        System.out.println("Cac phan tu vua nhap la: ");
        for (int n : arr) {
            System.out.print(n+ " ");
        }
        int result = arr[0] + arr[4];
        System.out.println("Result: " +result);
    }
    public static void main(String[] args) {
        bai1();
        bai1_1();
        bai2();
    }
}
