package com.back.domain.wiseSaying.controller;

import com.back.Rq;
import com.back.Infor;
import com.back.domain.wiseSaying.service.WiseSayingService;

import java.util.List;
import java.util.Scanner;

public class WiseSayingController {
    private final WiseSayingService service;
    private final Scanner scanner;

    public WiseSayingController(Scanner scanner) {
        this.scanner = scanner;
        this.service = new WiseSayingService();
    }

    public void actionWrite() {
        System.out.print("명언 : ");
        String content = scanner.nextLine().trim();
        System.out.print("작가 : ");
        String author = scanner.nextLine().trim();

        Infor infor = service.write(author, content);
        System.out.println(infor.getNo() + "번 명언이 등록되었습니다.");
    }

    public void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("______________________");

        List<Infor> list = service.getAll();
        for (int i = list.size() - 1; i >= 0; i--) {
            Infor a = list.get(i);
            System.out.println("%d / %s / %s".formatted(
                    a.getNo(),
                    a.getWiseSayingAuthor(),
                    a.getWiseSaying()
            ));
        }
    }

    public void actionDelete(Rq rq) {
        int id = rq.getParamAsInt("id", -1);
        if (id == -1) {
            System.out.println("id를 입력해주세요");
            return;
        }

        int result = service.delete(id);
        if (result == -1) {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
        } else {
            System.out.println(id + "번 명언이 삭제되었습니다.");
        }
    }

    public void actionModify(Rq rq) {
        int id = rq.getParamAsInt("id", -1);
        if (id == -1) {
            System.out.println("id를 입력해주세요");
            return;
        }

        Infor infor = service.findById(id);
        if (infor == null) {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
            return;
        }

        System.out.println("명언(기존) : " + infor.getWiseSaying());
        System.out.print("명언 : ");
        String content = scanner.nextLine().trim();

        System.out.println("작가(기존) : " + infor.getWiseSayingAuthor());
        System.out.print("작가 : ");
        String author = scanner.nextLine().trim();

        service.modify(infor, author, content);
        System.out.println(id + "번 명언이 수정되었습니다.");
    }

    public void actionBuild() {
        service.build();
    }
}
