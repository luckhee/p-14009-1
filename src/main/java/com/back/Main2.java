package com.back;

import java.util.ArrayList;
import java.util.List;

class Main2 {

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person(1, "Alice", 20, 'F'));
        people.add(new Person(2, "Bob", 25, 'M'));
        people.add(new Person(3, "David", 35, 'M'));

        streamSum(people);

        noStreamSum(people);

    }

    static int noStreamSum(List<Person> people) {
        int sum = 0;

        for(Person x : people) {
            if(x.getGender() == 'M') {
                sum += x.getAge();
            }
        }
        return sum;
    }

    static int streamSum(List<Person> people) {
        int sum = people.stream()
                .filter(e-> e.getGender() == 'M')
                .mapToInt(e -> e.getAge())
                .sum();

        return sum;
    }
}

class Person {
    private int id;
    private String name;
    private int age;
    private char gender;

    public Person(int id, String name, int age, char gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public char getGender() {
        return gender;
    }
}
