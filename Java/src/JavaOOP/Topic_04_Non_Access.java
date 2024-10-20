package JavaOOP;

public class Topic_04_Non_Access {
    //static: Variable / Method
    //dung cho tat ca cac instance / object
    //Pham vi cho toan bo system su dung
    //co the override duoc (gan lai gia tri moi)
    static String browserName = "Chrome";


    //non static
    String serverName = "Testing";

    //final
    final String colorCar = "Red"; //khong gan gia tri la bi bao loi - KHONG DUOC gan lai gia tri
    final static String REGISTER_BUTTON = "";

    public void clickToElement(String elementName) {
        System.out.println(elementName);

    }

    public static void sendKeyToElement(String elementName) {
        System.out.println(elementName);
    }

    //final method
    public final void setCarName() {
        
    }
    public static void main(String[] args) {
        System.out.println(browserName);

        browserName = "FireFox";
        System.out.println(browserName);

        Topic_04_Non_Access topic04NonAccess = new Topic_04_Non_Access();
        System.out.println(topic04NonAccess.serverName);

        topic04NonAccess.clickToElement("Button");

        //1 ham static co the goi den 1 ham static khac trong cung 1 class
        sendKeyToElement("Link");

        System.out.println(topic04NonAccess.colorCar);
        System.out.println(REGISTER_BUTTON);

    }
}
