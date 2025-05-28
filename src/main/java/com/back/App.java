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

    private Scanner scanner = new Scanner(System.in);
    private Infor infor = null;
    private int lastId = 0;
    private ArrayList<Infor> list = new ArrayList<>();
    private Path path = Paths.get("db/wiseSaying");

    void run() {
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine().trim();

            Rq rq = new Rq(cmd);
            switch(rq.getActionName()) {
                case "종료":
                    System.out.println("명언 앱을 종료합니다.");
                    return;
                case "등록":
                    actionWrite();
                    break;
                case "목록":
                    actionList();
                    break;
                case "삭제":
                    actionDelete(rq);
                    break;
                case "수정":
                    actionModify(rq);
                    break;
                case "빌드":
                    actionBuild();
                default:
                    System.out.println("알 수 없는 명령입니다.");
            }
        }
    }

    private void actionWrite() {

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

    private void modifyWrite(Infor infor) {

        System.out.println("명언(기존) : " + infor.getWiseSaying());
        System.out.print("명언 : ");
        String content = scanner.nextLine().trim();

        System.out.println("작가(기존) : " + infor.getWiseSayingAuthor());
        System.out.print("작가 : ");
        String author = scanner.nextLine().trim();

        setWrite(infor ,content, author);

        //System.out.println(lastId + "번 명언이 등록되었습니다.");



    }

    private void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("______________________");

        findForList();


    }

    private void findForList() {


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

    private void actionDelete(Rq rq) {
        int id = rq.getParamAsInt("id", -1);
        if(id ==-1) {
            System.out.println("id를 입력해주세요");
            return;
        }
        int deletedIndex = delete(id);

        if(deletedIndex == -1) {
            System.out.println(id+"번 명언은 존재하지 않습니다.");
        }
    }

    private int delete (int num) {
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

    private void actionModify(Rq rq) {
        int id = rq.getParamAsInt("id", -1);

        modify(id);

        if(id == -1) {
            System.out.println(id+"번 명언은 존재하지 않습니다.");
        }

    }

    private int modify(int id) {
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

    private Infor setWrite (Infor infor, String content, String author) {


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

    private void actionBuild() {
        JsonUtils.jsonMerge();
    }

}

