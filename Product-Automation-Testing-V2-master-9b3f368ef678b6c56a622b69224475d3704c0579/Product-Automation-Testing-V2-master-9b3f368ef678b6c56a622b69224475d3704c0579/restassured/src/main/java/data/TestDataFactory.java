package data;

import com.github.javafaker.Faker;

public class TestDataFactory {
    private static final Faker faker = new Faker();

    public static String getRandomName() {
        return faker.name().fullName();
    }

    public static String getRandomEmail() {
        return faker.internet().emailAddress();
    }
    
    public static String getRandomAddress() {
        return faker.address().streetAddress();
    }

}
