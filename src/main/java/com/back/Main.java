package com.back;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("== 명언 앱 ==");
        ArrayList<String> list = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        Scanner sc = new Scanner(System.in);

        int cnt = 0;

        while (true) {
            System.out.print("명령) ");
            String input = sc.nextLine();
            if (input.equals("종료")) break;
            else if (input.equals("등록")) {
                System.out.print("명언 :");
                String input1 = sc.nextLine().trim();
                stack.add(input1);
                System.out.print("작가 :");
                String input2 = sc.nextLine().trim();
                stack.add(input2);
                cnt += 1;
                stack.add(String.valueOf(cnt));
                System.out.println(cnt + "번 명령이 등록되었습니다.");
                System.out.println(stack);
            } else if(input.equals("목록")) {
                int x =10;
                System.out.println("번호 / 작가 / 명언");
                System.out.println("______________________");
                while(!stack.empty()) {
                    System.out.print(stack.pop());// 번호
                    System.out.print(" / ");
                    System.out.print(stack.pop()); // 작가
                    System.out.print(" / ");
                    System.out.print(stack.pop()); // 명언
                    System.out.println();
                }



            }
        }

    }
}
