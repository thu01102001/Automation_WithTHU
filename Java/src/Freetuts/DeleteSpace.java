package Freetuts;

import java.util.Scanner;

public class DeleteSpace {
    public static void DeleteDefault(String text) {
        String textAfter = text.replaceAll(" ", "");
        System.out.println("Text sau khi loai bo khoang trang: " +textAfter);
    }
    public static void DeleteTypingFromKeyboard(String text) {
        String textAfter = text.replaceAll(" ", "");
        System.out.println("Text sau khi loai bo khang trang: " +textAfter);
    }

    public static void main(String[] args) {
        DeleteDefault("Nguyen  Thi    Le Thu           ");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap text: ");
        String text = scanner.nextLine();
        DeleteTypingFromKeyboard(text);

    }
}
