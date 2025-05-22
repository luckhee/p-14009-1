package com.back;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("== 명언 앱 ==");

        Scanner sc = new Scanner(System.in);



        while(true) {
            System.out.print("명령) ");
            String input = sc.nextLine();
            if(input.equals("종료")) break;
            else if (input.equals("등록")) {
                System.out.print("명언 :");
                String input1 = sc.nextLine();
                System.out.println(input1);
                System.out.print("작가 :");
                String input2 = sc.nextLine();
                System.out.println(input2);
            }
        }

    }
}
