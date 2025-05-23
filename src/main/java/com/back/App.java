package com.back;

import java.util.ArrayList;
import java.util.Scanner;

public class App {

    Scanner scanner = new Scanner(System.in);
    infor infor = null;
    int lastId = 0;
    ArrayList<infor> list = new ArrayList<>();

    void run() {
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine().trim();

            if (cmd.equals("종료")) {
                break;
            } else if (cmd.equals("등록")) {
                actionWrite();
            } else if (cmd.equals("목록")) {
                actionList();
            }
        }

        scanner.close();
    }

    void actionWrite() {
        infor = new infor();
        System.out.print("명언 : ");
        String content = scanner.nextLine().trim();


        System.out.print("작가 : ");
        String author = scanner.nextLine().trim();

        infor infor = write(content, author);

        System.out.println(lastId + "번 명언이 등록되었습니다.");



    }

    void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("______________________");

        ArrayList<infor> forListWiseSaying = findForList();

        for(infor infor : forListWiseSaying) {
            System.out.println("%d /  %s / %s".formatted(
                    infor.no,
                    infor.wiseSayingAuthor,
                    infor.wiseSaying
            ));
        }
    }

    infor write (String content, String author) {
        infor infor = new infor();
        infor.wiseSayingAuthor = author;
        infor.no = ++lastId;
        infor.wiseSaying = content;
        list.add(infor);
        return infor;
    }

    ArrayList<infor> findForList() {
        ArrayList<infor> forListWiseSayings = new ArrayList<>();

        for (int i = list.size() - 1; i >= 0; i--) {
            infor a = list.get(i);
            System.out.println("%d /  %s / %s".formatted(
                    a.no,
                    a.wiseSayingAuthor,
                    a.wiseSaying
            ));
        }

        return forListWiseSayings;

    }

    void delete (int num) {
        for (int i = list.size() - 1; i >= 0; i--) {
            infor a = list.get(i);
            if (a.no == num) {
                list.remove(a.no);
                System.out.println(a.no+"번 명언이 삭제 되었습니다.");
            }
        }

    }
}

