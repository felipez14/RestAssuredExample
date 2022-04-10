package files;

import com.github.javafaker.Faker;

public class Payload {

    private static final Faker faker = new Faker();

    public static String payloadExample(){

        return "{\"name\":\"John\", \"age\":30, \"car\":null}";
    }
}