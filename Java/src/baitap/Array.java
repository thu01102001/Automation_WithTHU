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

    public static void bai3() {
        Scanner scanner = new Scanner(System.in);
        int [] arr = new int[6];
        for(int i=0; i<arr.length; i++) {
            System.out.println("Nhap phan tu thu " +(i+1)+ ": ");
            arr[i] = scanner.nextInt();
        }
        System.out.println("Cac phan tu vua nhap la: ");
        for(int n : arr) {
            System.out.print(n+ " ");
        }
        System.out.println("Cac phan tu chan: ");
        for (int n : arr) {
            if(n % 2 == 0) {
                System.out.print(n+ " ");
            }
        }
    }

    public static void bai4() {
        Scanner scanner = new Scanner(System.in);
        int[] arr = new int[10];
        for(int i = 0; i < arr.length; i++) {
            System.out.println("Nhap phan tu thu " +(i+1)+ ": ");
            arr[i] = scanner.nextInt();
        }
        System.out.print("Cac phan tu vua nhap: ");
        for(int n : arr) {
            System.out.print(n+ " ");
        }
        System.out.println("Tong cac phan tu > 0: ");
        int result = 0;
        for(int n : arr) {
            if(n > 0) {
                result += n;
            }
        }
        System.out.println(result);
    }

    public static void bai5() {
        Scanner scanner = new Scanner(System.in);
        int[] arr = new int[8];
        for(int i = 0; i < arr.length; i++) {
            System.out.println("Nhap phan tu thu " +(i+1)+ ": ");
            arr[i] = scanner.nextInt();
        }
        System.out.print("Cac phan tu vua nhap: ");
        for(int n : arr) {
            System.out.print(n+ " ");
        }
        System.out.println();
        System.out.print("Phan tu >=0 va <= 10: ");
        for (int n : arr) {
            if(n>=0 && n<=10) {
                System.out.print(n+ " ");
            }
        }
    }

    public static void bai6() {
        int[] arr = {3, 5, 7, 30, 10, 5, 8, 23, 0, -5};
        int sum = 0;
        float avg = 0;
        for(int n: arr) {
            sum += n;
        }
        avg = (float) sum/(arr.length);
        System.out.println("Sum = " +sum);
        System.out.println("Avg = " +avg);
    }

    public static class Student {
        int id;
        String name;
        int age;
        float score;

        public Student(int id, String name, int age, float score) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.score = score;
        }
        public void display() {
            System.out.println("--------------");
            System.out.println("ID: " +id);
            System.out.println("Name: " +name);
            System.out.println("Age: " +age);
            System.out.println("Score: " +score);
        }
    }

//    public static void main(Chuoi[] args) {
//        //bai1();
//        //bai1_1();
//        //bai2();
//        //bai3();
//        //bai4();
//        //bai5();
//        //bai6();
//        Scanner scanner = new Scanner(System.in);
//        Student[] students = new Student[2];
//
//        for (int i = 0; i < students.length; i++) {
//            System.out.println("Nhap id sinh vien thu " +(i+1)+ ": ");
//            int id = scanner.nextInt();
//            scanner.nextLine();
//            System.out.println("Nhap name sinh vien thu " +(i+1)+ ": ");
//            String name = scanner.nextLine();
//            System.out.println("Nhap age sinh vien thu " +(i+1)+ ": ");
//            int age = scanner.nextInt();
//            System.out.println("Nhap score sinh vien thu " +(i+1)+ ": ");
//            float score = scanner.nextFloat();
//            students[i] = new Student(id, name, age, score);
//        }
//        for (Student n: students) {
//            n.display();
//        }
//    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Student[] students = new Student[2];

        for (int i = 0; i < students.length; i++) {
            System.out.println("Nhap id sinh vien thu " +(i+1)+ ": ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Nhap name sinh vien thu " +(i+1)+ ": ");
            String name = scanner.nextLine();
            System.out.println("Nhap age sinh vien thu " +(i+1)+ ": ");
            int age = scanner.nextInt();
            System.out.println("Nhap score sinh vien thu " +(i+1)+ ": ");
            float score = scanner.nextFloat();
            students[i] = new Student(id, name, age, score);
        }
        for (Student n: students) {
            n.display();
        }
    }
}
