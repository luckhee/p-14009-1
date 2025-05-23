package com.back;

import java.util.ArrayList;
import java.util.Scanner;

public class App {

    Scanner scanner = new Scanner(System.in);
    infor infor = null;
    int cnt = 1;
    ArrayList<infor> list = new ArrayList<>();

    void run() {
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine().trim();

            if (cmd.equals("종료")) {
                break;
            } else if (cmd.equals("등록")) {
                actionList();
            } else if (cmd.equals("목록")) {
                actionWrite();
            }
        }

        scanner.close();
    }

    void actionList() {
        infor = new infor();
        System.out.print("명언 : ");
        String wiseSayingContent = scanner.nextLine().trim();
        infor.wiseSaying = wiseSayingContent;

        System.out.print("작가 : ");
        String wiseSayingAuthor = scanner.nextLine().trim();
        infor.wiseSayingAuthor = wiseSayingAuthor;

        infor.no = cnt;
        System.out.println(cnt + "번 명언이 등록되었습니다.");
        cnt += 1;

        list.add(infor);
    }

    void actionWrite() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("______________________");

        for (int i = list.size() - 1; i >= 0; i--) {
            infor a = list.get(i);
            System.out.println("%d /  %s / %s".formatted(
                    a.no,
                    a.wiseSayingAuthor,
                    a.wiseSaying
            ));
        }
    }
}

