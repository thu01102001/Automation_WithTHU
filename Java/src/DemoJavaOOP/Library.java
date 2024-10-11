package DemoJavaOOP;

import java.util.ArrayList;
import java.util.Scanner;

//tao lop class Library voi mot bo suu tap sach va cac phuong thuc de them va xóa sach
public class Library {
        String bookName;
        String bookAuthor;
        String bookType;
        int yearManufacture;

        //danh sach chua cac cuon sach trong thu vien
        public static ArrayList<Library> bookList = new ArrayList<>();
        public Library(String bookName, String bookAuthor, String bookType, int yearManufacture) {
                this.bookName = bookName;
                this.bookAuthor = bookAuthor;
                this.bookType = bookType;
                this.yearManufacture = yearManufacture;
        }

        public String getBookName() {
                return bookName;
        }

        public void setBookName(String bookName) {
                this.bookName = bookName;
        }

        public String getBookAuthor() {
                return bookAuthor;
        }

        public void setBookAuthor(String bookAuthor) {
                this.bookAuthor = bookAuthor;
        }

        public String getBookType() {
                return bookType;
        }

        public void setBookType(String bookType) {
                this.bookType = bookType;
        }

        public int getYearManufacture() {
                return yearManufacture;
        }

        public void setYearManufacture(int yearManufacture) {
                this.yearManufacture = yearManufacture;
        }

        public static void addBook() {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Nhap ten sach");
                String bookName = scanner.nextLine();
                System.out.println("Nhap ten tac gia cua sach: ");
                String bookAuthor = scanner.nextLine();
                System.out.println("Nhap the loai sach: ");
                String bookType = scanner.nextLine();
                System.out.println("Nhap nam sang tac: ");
                int year = scanner.nextInt();
                scanner.nextLine();

                //tao doi tuong sach moi va them vao danh sach
                Library newBook = new Library(bookName, bookAuthor, bookType, year);
                bookList.add(newBook);
                System.out.println("Add thanh cong");
        }

        public static void deleteBook() {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Nhap cuon sach can xoa: ");
                String bookNameDel = scanner.nextLine();
                boolean timThay = false;
                for(Library sach : bookList) {
                        if(sach.getBookName().equals(bookNameDel)) {
                                bookList.remove(sach);
                                System.out.println("Da xoa");
                                timThay = true;
                                break;
                        }
                }
                if(!timThay) {
                        System.out.println("Khong tim thay");
                }

        }

        public static void showBook() {
                if(bookList.isEmpty()) {
                        System.out.println("Thu vien ko co sach nao");
                }
                else {
                        System.out.println("Danh sach: ");
                        for (Library sach : bookList) {
                                System.out.println("Ten sach: " +sach.getBookName());
                                System.out.println("Ten tac gia sach: " +sach.getBookAuthor());
                                System.out.println("The loai sach: " +sach.getBookType());
                                System.out.println("Nam suat ban sach: " +sach.getYearManufacture());
                                System.out.println("");
                        }
                }

        }

        public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);

                int choose;
                do {
                        System.out.println("1. Them sach");
                        System.out.println("2. Xoa sach");
                        System.out.println("3. In sach");
                        System.out.println("4. Thoat");
                        System.out.println("Nhap lua chon cua ban");
                        choose = scanner.nextInt();
                        scanner.nextLine();

                        switch (choose) {
                                case 1:
                                        addBook();
                                        break;
                                case 2:
                                        deleteBook();
                                        break;
                                case 3:
                                        showBook();
                                        break;
                                case 4:
                                        System.out.println("Thoat");
                                        break;
                                default:
                                        System.out.println("Lua chon ko hop le");
                                        break;
                        }
                }
                while (choose != 4);
        }
}
