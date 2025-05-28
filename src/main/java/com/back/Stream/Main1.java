package com.back.Stream;

import java.util.Arrays;

public class Main1 {
    public static void main(String[] args) {
//        System.out.println("== 비 스트림 ==");
//        for (int i = 1; i <= 10; i++) {
//            System.out.println(i);
//        }
//        System.out.println();
//
//        System.out.println("== 스트림 V1(no lamda) ==");
//        IntStream.rangeClosed(1, 10)
//                .forEach(
//                        new IntConsumer() {
//                            @Override
//                            public void accept(int value) {
//                                System.out.println(value);
//                            }
//                        }
//                );
//        System.out.println();
//
//        System.out.println("== 스트림 V2(use lamda) ==");
//        IntStream.rangeClosed(1, 10)
//                .forEach(
//                        (int value) -> {
//                            System.out.println(value);
//                        }
//                );
//        System.out.println();
//
//        System.out.println("== 스트림 V3(매개변수 타입 생략) ==");
//        IntStream.rangeClosed(1, 10)
//                .forEach(
//                        (value) -> {
//                            System.out.println(value);
//                        }
//                );
//        System.out.println();
//
//        System.out.println("== 스트림 V4(매개변수 괄호 생략) ==");
//        IntStream.rangeClosed(1, 10)
//                .forEach(
//                        value -> {
//                            System.out.println(value);
//                        }
//                );
//        System.out.println();
//
//        System.out.println("== 스트림 V5(메서드 바디 중괄호 생략) ==");
//        IntStream.rangeClosed(1, 10)
//                .forEach(value -> System.out.println(value));
//        System.out.println();
//
//        System.out.println("== 스트림 V6(최종축약형) ==");
//        IntStream.rangeClosed(1, 10)
//                .forEach(System.out::println);
//

//        IntStream.rangeClosed(1,10)
//                .filter(e -> e % 2 != 0)
//                .forEach(e -> System.out.println(e));


        System.out.println("== No Stream ==");
        noStreamVersion();

        System.out.println("== Stream v1 ==");
        streamVersionV1();

        System.out.println("== Stream v2 ==");
        streamVersionV2();
    }

    private static void noStreamVersion() {
        int[] numbers = new int[]{1, 2, 3};

        String[] messages = new String[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0) continue;

            messages[i] = numbers[i] + "번";
        }

        System.out.println("= 원본 =");
        System.out.println(Arrays.toString(numbers));
        System.out.println("= 결과 =");
        System.out.println(Arrays.toString(messages));
    }

    private static void streamVersionV1() {
        Object[] array = Arrays.stream(new int[]{1, 2, 3})
                .filter(e -> e % 2 != 0)
                .mapToObj(e -> e + "번")
                .toArray();

        System.out.println("= 결과 =");
        System.out.println(Arrays.toString(array));
    }

    private static void streamVersionV2() {
        String[] array = Arrays.stream(new int[]{1, 2, 3})
                .filter(e -> e % 2 != 0)
                .mapToObj(e -> e + "번")
                .toArray(String[]::new);

        System.out.println("= 결과 =");
        System.out.println(Arrays.toString(array));
    }

}


