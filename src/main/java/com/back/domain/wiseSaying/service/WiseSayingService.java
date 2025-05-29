package com.back.domain.wiseSaying.service;

import com.back.Infor;
import com.back.JsonUtils;
import com.back.domain.wiseSaying.repository.WiseSayingRepository;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class WiseSayingService {
    private Infor infor;
    private  WiseSayingRepository repository;
    private List<Infor> list = new ArrayList<>();
    private int lastId = 0;
    private Scanner scanner = new Scanner(System.in);
    private final Path path = Paths.get("db/wiseSaying");



    public WiseSayingService() {

    }

    public boolean saveLastId(Path path) {
        Path filePath = path.resolve("lastId");
        String no = infor.saveLastId();

        try {
            Files.writeString(filePath, no);
            return true;
        } catch (IOException e) {
            System.out.println("저장 실패" + e.getMessage());
            return false;
        }

    }

    public void inforSave( String content, String author) {
        infor= new Infor(++lastId, author, content);
        list.add(infor);
        repository = new WiseSayingRepository(lastId,content,author);
    }

    public boolean save(Path path) {
        Path filePath = path.resolve(infor.getNo()+ ".json");
        String json = infor.toJsonString(0);

        try {
            Files.writeString(filePath, json);
            return true;
        } catch (IOException e) {
            System.out.println("저장 실패" + e.getMessage());
            return false;
        }
    }

    public List<Infor> getAll() {
        return repository.findAll(); // 저장소에서 전체 불러오기
    }



    public Infor modifyInfor(Infor infor, String author, String content) {
        infor.setWiseSayingAuthor(author);
        infor.setWiseSaying(content);
        return infor;
    }


    public int modifyWrite(int id) {
        int modifyIndex = -1;

        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getNo() == id) {
                Infor changeInfor = list.get(i);
                modifyIndex=i;
                Write(changeInfor);
            }
        }

        return modifyIndex;
    }

    public void build() {
        JsonUtils.jsonMerge();
    }


    public int delete (int num) {
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


    public void findForList() {


        List<Infor> inforList = getAllWiseSayings();



        for (int i = inforList.size() - 1; i >= 0; i--) {
            Infor a = inforList.get(i);
            System.out.println("%d /  %s / %s".formatted(
                    a.getNo(),
                    a.getWiseSayingAuthor(),
                    a.getWiseSaying()
            ));
        }
    }

    public void Write(Infor infor) {

        System.out.println("명언(기존) : " + infor.getWiseSaying());
        System.out.print("명언 : ");
        String content = scanner.nextLine().trim();

        System.out.println("작가(기존) : " + infor.getWiseSayingAuthor());
        System.out.print("작가 : ");
        String author = scanner.nextLine().trim();

        modifyInfor(infor ,content, author);


        //System.out.println(lastId + "번 명언이 등록되었습니다.");
    }


    public List<Infor> getAllWiseSayings() {
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
