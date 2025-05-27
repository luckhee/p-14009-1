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

        streamAvgAge(people);

        nostreamAvgAge(people);

        noStreamGetName(people);

        streamGetName(people);

        streamGetId(people);

    }
// 나이 합계
    static int noStreamSum(List<Person> people) {
        int sum = 0;

        for(Person x : people) {
            if(x.getGender() == 'M') {
                sum += x.getAge();
            }
        }
        System.out.println(sum);
        return sum;
    }

    static int streamSum(List<Person> people) {
        int sum = people.stream()
                .filter(e-> e.getGender() == 'M')
                .mapToInt(e -> e.getAge())
                .sum();
        System.out.println(sum);
        return sum;
    }
// 평균 나이
    static double streamAvgAge(List<Person> people) {
        double avg = people.stream()
                .filter(e->e.getGender() =='M')
                .mapToInt(e -> e.getAge())
                .average()
                .orElse(0); // 값이 없을 때를 대비해서 Default 값 설정

        System.out.println(avg);

        return avg;
    }

    static double nostreamAvgAge(List<Person> people) {
        double avg = 0;
        int cnt = 0;
        int sum = 0;
        for(Person x : people) {
            if(x.getGender() == 'M'){
                sum += x.getAge();
                cnt +=1;
            }
        }
        avg = sum/ (double) cnt;
        System.out.println(avg);
        return avg;
    }

    static List<String> noStreamGetName(List<Person> people) {
        List<String> name = new ArrayList<>();

        for(Person x : people) {
            if(x.getGender() == 'M') name.add(x.getName());
        }

        System.out.println(name);

        return name;
    }

    static List<String> streamGetName(List<Person> people) {

        List<String> list = people.stream()
                .filter(e -> e.getGender() == 'M')
                .map(e -> e.getName())
                .toList();

        System.out.println(list);

        return list;
    }

    static String streamGetId(List<Person> people) {
        String str = people.stream()
                .filter(e -> e.getId() == 2)
                .map(e -> e.getName())
                .findFirst()
                .orElse("없음");


        System.out.println(str);
        return str;
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
