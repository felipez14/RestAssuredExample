package jsonObjects;

public class PayloadExampleObject {
    private String name;
    private float age;
    private String car = null;


    // Getter Methods

    public String getName() {
        return name;
    }

    public float getAge() {
        return age;
    }

    public String getCar() {
        return car;
    }

    // Setter Methods

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(float age) {
        this.age = age;
    }

    public void setCar(String car) {
        this.car = car;
    }
}
