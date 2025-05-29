package com.back;

import com.back.Controller.SystemController;
import com.back.Controller.WiseSayingController;

import java.util.Scanner;

public class App {

    private Scanner scanner = new Scanner(System.in);


    void run() {
        System.out.println("== 명언 앱 ==");

        SystemController systemController = new SystemController();
        WiseSayingController wiseSayingController = new WiseSayingController(scanner);
        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine().trim();

            Rq rq = new Rq(cmd);
            switch(rq.getActionName()) {
                case "종료" ->{
                    systemController.actionExit();
                    return;}
                case "등록"->
                    wiseSayingController.actionWrite();
                case "목록"->
                    wiseSayingController.actionList();
                case "삭제" ->
                    wiseSayingController.actionDelete(rq);
                case "수정" ->
                    wiseSayingController.actionModify(rq);
                case "빌드" ->
                    wiseSayingController.actionBuild();
            }
        }
    }



}

