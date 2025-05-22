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
        }

    }
}
