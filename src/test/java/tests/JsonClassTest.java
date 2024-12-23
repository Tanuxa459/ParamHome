package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Person;
import model.Phone;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Json тесты")
public class JsonClassTest {

    private final ClassLoader cl = JsonClassTest.class.getClassLoader();

    @DisplayName("Проверяем файл jsontest.json")
    @Test
    void jsonTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("jsontest.json");
             InputStreamReader isr = new InputStreamReader(is)) {

            Person person = new ObjectMapper().readValue(isr, Person.class);

            assertThat(person.getName()).isEqualTo("John");
            System.out.println("Name: " + person.getName());
            assertThat(person.getLastName()).isEqualTo("Smith");
            System.out.println("Last name: " + person.getLastName());
            assertThat(person.getAge()).isEqualTo(30);
            System.out.println("Age: " + person.getAge());
            for (Phone phoneNumber : person.getPhoneNumbers()) {
                System.out.println("Phone (" + phoneNumber.getType() + "): " + phoneNumber.getNumber());
            }


        }
    }
}