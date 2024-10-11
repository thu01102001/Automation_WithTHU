package DemoJavaOOP;

import java.util.Scanner;

public class ManagerStudent {
    private int studentID;
    private String studentName;
    private float KnowledgePoint;
    private float PracticePoint;

    //Constructor

    public ManagerStudent(int studentID, String studentName, float knowledgePoint, float practicePoint) {
        this.studentID = studentID;
        this.studentName = studentName;
        KnowledgePoint = knowledgePoint;
        PracticePoint = practicePoint;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public double getKnowledgePoint() {
        return KnowledgePoint;
    }

    public void setKnowledgePoint(float knowledgePoint) {
        KnowledgePoint = knowledgePoint;
    }

    public double getPracticePoint() {
        return PracticePoint;
    }

    public void setPracticePoint(float practicePoint) {
        PracticePoint = practicePoint;
    }

    public float avgPoint() {
        return (this.KnowledgePoint + this.PracticePoint*2) /3;
    }

    public void addStudent() {

    }

    public void showStudent() {

    }

    public void arrangeAvgStudent() {

    }

    public void XepLoaiStudent() {

    }
    public void XuatListStudent() {

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("CHUONG TRINH QUAN LY SINH VIEN");
            System.out.println("1. Nhap du lieu");
            System.out.println("2. In danh sach sinh vien");
            System.out.println("3. Sap xep sinh vien theo DTB");
            System.out.println("4. Xep loai sinh vien");
            System.out.println("5. Xuat DS sinh vien");
            System.out.println("0. Thoat");
            System.out.println("**************************************");
            System.out.println("Nhap lua chon cua ban");
            int choose = scanner.nextInt();
            scanner.nextLine();

            switch (choose) {
                case 1:
                    
            }
        }
    }
}
