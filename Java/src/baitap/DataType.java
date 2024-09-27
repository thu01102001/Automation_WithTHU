package baitap;

public class DataType {
    public static void tongHieuTichThuong(int a, int b) {
        int P1 = a + b;
        int P2 = a - b;
        int P3 = a * b;
        int P4 = a / b;
        System.out.println("Tong P1 = " +P1);
        System.out.println("Hieu P2 = " +P2);
        System.out.println("Tich P3 = " +P3);
        System.out.println("Thuong P4 = " +P4);
    }

    public static void dienTichHCN(float d, float r) {
        float s = d * r;
        System.out.println("Dien tich hinh chu nhat S = " +s);
    }

    public static void inText(String text) {
        System.out.println(text);
    }

    public static void main(String[] args) {
        tongHieuTichThuong(6, 2);
        System.out.println("-------------------------");
        dienTichHCN(7.5F, 3.8F);
        System.out.println("-------------------------");
        inText("Automation testing");
    }
}
