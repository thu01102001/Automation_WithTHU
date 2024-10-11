package Freetuts;

import java.util.Scanner;

public class Uppercase_Lowercase {
    public static void UpperCaseTextFirst(String text) {
        String textFirst = text.substring(0, 1);
        String textRemaining = text.substring(1, text.length());
        textFirst = textFirst.toUpperCase();
        text = textFirst + textRemaining;
        System.out.println(text);
    }
    public static void UpperCaseAllTextFirstOnString(String text) {
        String[] textSplits = text.split("\\s+");
        String result = "";  // Biến để lưu chuỗi cuối cùng

        // Lặp qua từng từ
        for (String textSplit : textSplits) {
            String textFirst = textSplit.substring(0, 1);  // Lấy ký tự đầu tiên
            String textRemaining = textSplit.substring(1);  // Phần còn lại của từ
            String textFirstUppercase = textFirst.toUpperCase();  // Viết hoa ký tự đầu

            // Nối từ đã viết hoa vào kết quả, thêm khoảng trắng giữa các từ
            result += textFirstUppercase + textRemaining + " ";
        }

        // Loại bỏ khoảng trắng thừa ở cuối chuỗi và in ra kết quả
        result = result.trim();  // Loại bỏ khoảng trắng thừa cuối cùng
        System.out.println(result);  // Kết quả sẽ là "Le Thu"

    }

    public static void UppercaseLowercase(String text) {
        System.out.println(text.toUpperCase());
        System.out.println(text.toLowerCase());
    }

    public static void CharString() {
        char kyTu = 'a';
        //chuyen char sang string
        String str = Character.toString(kyTu);
        System.out.println(str);
        String text = "Thu";
        for(int i = 0; i < text.length(); i++) {
            // text.charAT => lay ra ky tu o vi tri i
            char kytu = text.charAt(i);
            System.out.println("Ky tu " +kytu+ " o vi tri " +i);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        System.out.println("Nhap text: ");
//        String text = scanner.nextLine();
//        UpperCaseTextFirst(text);
//        System.out.println("Nhap chuoi: ");
//        String chuoi = scanner.nextLine();
//        UpperCaseAllTextFirstOnString(chuoi);
//        System.out.println("Nhap chuoi: ");
//        String chuoi = scanner.nextLine();
//        UppercaseLowercase(chuoi);
        CharString();
    }
}
