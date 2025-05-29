package com.back.domain.wiseSaying.repository;

import com.back.Infor;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class WiseSayingRepository {


        private final Path path = Paths.get("db/wiseSaying");
        private final int lastId;
        private final String content;
        private final String author;



    public WiseSayingRepository(int lastId, String content, String author) {
            this.content = content;
            this.author = author;
            this.lastId = lastId;
        }


    public List<Infor> findAll() {
        List<Infor> wiseSayings = new ArrayList<>();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path, "*.json")) {
            for (Path file : stream) {
                if (file.getFileName().toString().equals("data.json")) continue;

                Infor infor = new Infor().readJson(file);
                if (infor != null) {
                    wiseSayings.add(infor);
                }
            }
        } catch (IOException e) {
            System.out.println("명언 목록 불러오기 실패: " + e.getMessage());
        }

        wiseSayings.sort(Comparator.comparing(Infor::getNo));
        return wiseSayings;
    }





}


