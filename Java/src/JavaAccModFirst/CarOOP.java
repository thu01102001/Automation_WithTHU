package JavaAccModFirst;

public class CarOOP {
    private String carCompany;
    private String carType;
    private String fuelType;
    private float mileAge;
    private double carPrice;

    public CarOOP(String carCompany, String carType, String fuelType, float mileAge, double carPrice) {
        this.carCompany = carCompany;
        this.carType = carType;
        this.fuelType = fuelType;
        this.mileAge = mileAge;
        this.carPrice = carPrice;
    }

    //method
    //chia se: public
    //dung trong class: private
    //dung trong package: package
    //dung cho ke thua: protected


    protected String getCarCompany() {
        return carCompany;
    }

    protected void setCarCompany(String carCompany) {
        this.carCompany = carCompany;
    }

    protected String getCarType() {
        return carType;
    }

    protected void setCarType(String carType) {
        this.carType = carType;
    }

    protected String getFuelType() {
        return fuelType;
    }

    protected void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    protected float getMileAge() {
        return mileAge;
    }

    protected void setMileAge(float mileAge) {
        this.mileAge = mileAge;
    }

    protected double getCarPrice() {
        return carPrice;
    }

    protected void setCarPrice(double carPrice) {
        this.carPrice = carPrice;
    }

    public void showCarInfo() {
        System.out.println("Car company: " +getCarCompany());
        System.out.println("Car type: " +getCarType());
        System.out.println("Car fuel type: " +getFuelType());
        System.out.println("Car mile age: " +getMileAge());
        System.out.println("Car price: " +getCarPrice());
    }

    public static void main(String[] args) {
        //Honda
        CarOOP honda = new CarOOP("Honda", "City", "Petrol", 500f, 5000d);
        honda.showCarInfo();
        System.out.println("========================");
        //toyota
        CarOOP toyota = new CarOOP("Toyota", "City1", "Petrol1", 400f, 4000d);
        toyota.showCarInfo();
    }
}
