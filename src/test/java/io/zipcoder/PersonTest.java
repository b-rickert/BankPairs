package io.zipcoder;

import org.junit.Assert;
import org.junit.Test;

public class PersonTest {

    @Test
    public void testConstructor() {
        // Given
        String firstName = "John";
        String lastName = "Doe";
        String email = "john.doe@example.com";
        String phoneNumber = "555-1234";

        // When
        Person person = new Person(firstName, lastName, email, phoneNumber);

        // Then
        Assert.assertNotNull(person);
    }

    @Test
    public void testGetFirstName() {
        // Given
        String expectedFirstName = "Jane";
        Person person = new Person(expectedFirstName, "Smith", "jane@example.com", "555-5678");

        // When
        String actualFirstName = person.getFirstName();

        // Then
        Assert.assertEquals(expectedFirstName, actualFirstName);
    }

    @Test
    public void testGetLastName() {
        // Given
        String expectedLastName = "Smith";
        Person person = new Person("Jane", expectedLastName, "jane@example.com", "555-5678");

        // When
        String actualLastName = person.getLastName();

        // Then
        Assert.assertEquals(expectedLastName, actualLastName);
    }

    @Test
    public void testGetEmail() {
        // Given
        String expectedEmail = "jane@example.com";
        Person person = new Person("Jane", "Smith", expectedEmail, "555-5678");

        // When
        String actualEmail = person.getEmail();

        // Then
        Assert.assertEquals(expectedEmail, actualEmail);
    }

    @Test
    public void testGetPhoneNumber() {
        // Given
        String expectedPhoneNumber = "555-5678";
        Person person = new Person("Jane", "Smith", "jane@example.com", expectedPhoneNumber);

        // When
        String actualPhoneNumber = person.getPhoneNumber();

        // Then
        Assert.assertEquals(expectedPhoneNumber, actualPhoneNumber);
    }

    @Test
    public void testSetFirstName() {
        // Given
        Person person = new Person("Jane", "Smith", "jane@example.com", "555-5678");
        String newFirstName = "Janet";

        // When
        person.setFirstName(newFirstName);

        // Then
        Assert.assertEquals(newFirstName, person.getFirstName());
    }

    @Test
    public void testSetLastName() {
        // Given
        Person person = new Person("Jane", "Smith", "jane@example.com", "555-5678");
        String newLastName = "Johnson";

        // When
        person.setLastName(newLastName);

        // Then
        Assert.assertEquals(newLastName, person.getLastName());
    }

    @Test
    public void testSetEmail() {
        // Given
        Person person = new Person("Jane", "Smith", "jane@example.com", "555-5678");
        String newEmail = "janet.johnson@example.com";

        // When
        person.setEmail(newEmail);

        // Then
        Assert.assertEquals(newEmail, person.getEmail());
    }

    @Test
    public void testSetPhoneNumber() {
        // Given
        Person person = new Person("Jane", "Smith", "jane@example.com", "555-5678");
        String newPhoneNumber = "555-9999";

        // When
        person.setPhoneNumber(newPhoneNumber);

        // Then
        Assert.assertEquals(newPhoneNumber, person.getPhoneNumber());
    }
}
