package guru.qa.hwtask.model;

import java.util.List;

public class PersonMan {
    public String firstName;
    public String lastName;
    public String gender;
    public String age;

    public Address address;
    public List<PhoneNumbers> phoneNumbers;

    public static class Address {
        public String streetAddress;
        public String city;
        public String state;

    }

    public static class PhoneNumbers {
        public String type;
        public String number;
    }
}
