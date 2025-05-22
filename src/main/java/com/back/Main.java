package com.back;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("== 명언 앱 ==");

        Scanner sc = new Scanner(System.in);

        int cnt = 0;

        while(true) {
            System.out.print("명령) ");
            String input = sc.nextLine();
            if(input.equals("종료")) break;
            else if (input.equals("등록")) {
                System.out.print("명언 :");
                String input1 = sc.nextLine().trim();

                System.out.print("작가 :");
                String input2 = sc.nextLine().trim();
                cnt +=1;
                System.out.println(cnt+"번 명령이 등록되었습니다.");

            }
        }

    }
}
