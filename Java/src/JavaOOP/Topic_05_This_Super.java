package JavaOOP;

public class Topic_05_This_Super {
    //tham chieu bien cua lop hien tai
    private int firstNumber;
    private int secondNumber;
    private int thirdNumber;

    //constructor nay goi qua constructor kia
    public Topic_05_This_Super() {
        this(10,15,20); //luon luon phai dung dau

    }
    public Topic_05_This_Super(int a, int b, int thirdNumber) {
        firstNumber = a;
        secondNumber = b;
        this.thirdNumber = thirdNumber;

    }
    public void SumNumber() {
        System.out.println(firstNumber + secondNumber + thirdNumber);
    }

    //goi den method

    public static void main(String[] args) {
        Topic_05_This_Super topic05ThisSuper = new Topic_05_This_Super(1,2,3);
        topic05ThisSuper.SumNumber();
    }

}
