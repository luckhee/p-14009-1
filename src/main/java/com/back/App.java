package com.back;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class App {

    Scanner scanner = new Scanner(System.in);
    Infor infor = null;
    int lastId = 0;
    ArrayList<Infor> list = new ArrayList<>();
    Path path = Paths.get("db/wiseSaying");

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
            } else if (cmd.startsWith("삭제?id=")) {
                actionDelete(cmd);
            } else if (cmd.startsWith("수정?id=")) {
                actionModify(cmd);
            }
        }
        scanner.close();
    }

    void actionWrite() {

        System.out.print("명언 : ");
        String content = scanner.nextLine().trim();

        System.out.print("작가 : ");
        String author = scanner.nextLine().trim();

        infor = new Infor(++lastId,author,content);
        list.add(infor);


        infor.save(path);
        infor.saveLastId(path);

        System.out.println(lastId + "번 명언이 등록되었습니다.");



    }

    void modifyWrite(Infor infor) {

        System.out.println("명언(기존) : " + infor.getWiseSaying());
        System.out.print("명언 : ");
        String content = scanner.nextLine().trim();

        System.out.println("작가(기존) : " + infor.getWiseSayingAuthor());
        System.out.print("작가 : ");
        String author = scanner.nextLine().trim();

        setWrite(infor ,content, author);

        //System.out.println(lastId + "번 명언이 등록되었습니다.");



    }

    void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("______________________");

        findForList();


    }

    void findForList() {


        List<Infor> inforList;

        inforList = getAllWiseSayings();

        for (int i = inforList.size() - 1; i >= 0; i--) {
            Infor a = inforList.get(i);
            System.out.println("%d /  %s / %s".formatted(
                    a.getNo(),
                    a.getWiseSayingAuthor(),
                    a.getWiseSaying()
            ));
        }

        //return forListWiseSayings;

    }

    void actionDelete(String cmd) {
        String[] cmdBit = cmd.split("=",2);

        if(cmdBit.length < 2 || cmdBit[1].isEmpty()) {
            System.out.println("id를 입력해주세요");
            return;
        }

        int id = Integer.parseInt(cmdBit[1]);

        int deletedIndex = delete(id);

        if(deletedIndex == -1) {
            System.out.println(id+"번 명언은 존재하지 않습니다.");
        }
    }

    int delete (int num) {
        int deletedIndex = -1;

        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getNo() == num) {
                list.remove(i);
                System.out.println(num+ "번 명언이 삭제되었습니다.");
                deletedIndex = i;
                break;
            }
        }

        return deletedIndex;
    }

    void actionModify(String cmd) {
        String[] cmdBit = cmd.split("=",2);

        if(cmdBit.length < 2 || cmdBit[1].isEmpty()) {
            System.out.println("id를 입력해주세요");
            return;
        }

        int id = Integer.parseInt(cmdBit[1]);

        int deletedIndex = modify(id);

        if(deletedIndex == -1) {
            System.out.println(id+"번 명언은 존재하지 않습니다.");
        }

    }

    int modify(int id) {
       int modifyIndex = -1;

        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getNo() == id) {
                Infor infor = list.get(i);
                modifyIndex=i;
                modifyWrite(infor);
            }
        }

        return modifyIndex;
    }

    Infor setWrite (Infor infor, String content, String author) {


        infor.setWiseSayingAuthor(author);
        infor.setWiseSaying(content);

        return infor;

    }

    private List<Infor> getAllWiseSayings() {
        List<Infor> wiseSayings = new ArrayList<>();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path, "*.json")) {
            for (Path path : stream) {
                if (path.getFileName().toString().equals("data.json")) continue;

                Infor infor = new Infor().readJson(path);
                if (infor != null) {
                    wiseSayings.add(infor);
                }
            }
            wiseSayings.sort(Comparator.comparing(Infor::getNo));
            return wiseSayings;
        } catch (IOException e) {
            System.out.println("명언 목록 불러오기 실패: " + e.getMessage());
            return null;
        }
    }

}

