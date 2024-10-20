package JavaOOP;

public class Testing {
    public static void main(String[] args) {
        //ngoai class: truy cap truc tiep tu ten class (ko can phai tao instance / object)
        //khong nen lam dung
        System.out.println(Topic_04_Non_Access.browserName);

        //dung de khoi tao cac class
        Topic_04_Non_Access.sendKeyToElement("Link");

        //final  : khong ghi de duoc
        System.out.println(Topic_04_Non_Access.REGISTER_BUTTON);
    }
}
