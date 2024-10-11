package JavaOOP;

public class Topic_02_Variable_Property_Method {
    //access modifier
    //data type
    //variable name
    //value

    //static variable
    public static String StudentAddress = "Ho chi minh";
    // => Dung va gan lai duoc

    private static String country = "Viet Nam"; // ko phai final co the gan du lieu lai duoc,
    // dung trong ham (truy cap truc tiep ko can thong qua doi tuong, ra ngoai dung class deu gan lai duoc
    // => Dung trong pham vi class (cho tat ca instance / object)


    //final variable
    final String studentPhone = "0364697180";
    // ko gan lai gia tri duoc (override) -> Muon du lieu co dinh , ko dc phep thay doi trong qua trinh chay

    //static final variable
    static final float PI_Number = 3.1423544f; //hang so (constant),
    // ko dc ghi de (ko gan lai duoc)
    //co the truy cap rong rai cho tat ca cac instance / thread

    private String studentName = "AutomationFC"; // bien toan cuc
    int number = 123; //access modifier: default

    //ham (method)
    public void display() {
        String student = "Automation"; //bien cuc bo
    }

    //local variable
    //==> Bat buoc khoi tao moi duoc dung
    // ko su dung access modifier


    //variable : gan du lieu truc tiep ko qua cac ham setter
    //property: gan du lieu ca ham setter, lay du lieu qua ham getter => Dac diem cua doi tuong
    


}
