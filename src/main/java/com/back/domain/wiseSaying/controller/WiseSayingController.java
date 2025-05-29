package com.back.domain.wiseSaying.controller;

import com.back.Infor;
import com.back.JsonUtils;
import com.back.Rq;
import com.back.domain.wiseSaying.service.WiseSayingService;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class WiseSayingController {
    private Infor infor ;
    private int lastId = 0;
    private ArrayList<Infor> list = new ArrayList<>();
    private Path path = Paths.get("db/wiseSaying");
    private final Scanner scanner;
    private final WiseSayingService wiseSayingService = new WiseSayingService();

    public WiseSayingController(Scanner scanner) {
        this.scanner = scanner;

    }


    public void actionWrite() {

        System.out.print("명언 : ");
        String content = scanner.nextLine().trim();

        System.out.print("작가 : ");
        String author = scanner.nextLine().trim();



        wiseSayingService.inforSave(content,author);
        wiseSayingService.save(path);
        wiseSayingService.saveLastId(path);

        System.out.println(++lastId + "번 명언이 등록되었습니다.");



    }



    public void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("______________________");

        wiseSayingService.findForList();


    }



    public void actionDelete(Rq rq) {
        int id = rq.getParamAsInt("id", -1);
        if(id ==-1) {
            System.out.println("id를 입력해주세요");
            return;
        }
        int deletedIndex = wiseSayingService.delete(id);

        if(deletedIndex == -1) {
            System.out.println(id+"번 명언은 존재하지 않습니다.");
        }
    }



    public void actionModify(Rq rq) {
        int id = rq.getParamAsInt("id", -1);

        wiseSayingService.modifyWrite(id);

        if(id == -1) {
            System.out.println(id+"번 명언은 존재하지 않습니다.");
        }

    }


    public Infor setWrite (Infor infor, String content, String author) {
        infor.setWiseSayingAuthor(author);
        infor.setWiseSaying(content);
        return infor;
    }



    public void actionBuild() {
        JsonUtils.jsonMerge();
    }


}
