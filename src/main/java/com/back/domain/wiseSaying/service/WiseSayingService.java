package com.back.domain.wiseSaying.service;

import com.back.Infor;
import com.back.JsonUtils;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class WiseSayingService {
    private final List<Infor> list = new ArrayList<>();
    private int lastId = 0;
    private final Path path = Paths.get("db/wiseSaying");

    public Infor write(String author, String content) {
        Infor infor = new Infor(++lastId, author, content);
        list.add(infor);
        infor.save(path);
        infor.saveLastId(path);
        return infor;
    }

    public List<Infor> getAll() {
        List<Infor> wiseSayings = new ArrayList<>();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path, "*.json")) {
            for (Path path : stream) {
                if (path.getFileName().toString().equals("data.json")) continue;
                Infor infor = new Infor().readJson(path);
                if (infor != null) wiseSayings.add(infor);
            }
            wiseSayings.sort(Comparator.comparing(Infor::getNo));
        } catch (IOException e) {
            System.out.println("명언 목록 불러오기 실패: " + e.getMessage());
        }

        return wiseSayings;
    }

    public int delete(int id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getNo() == id) {
                list.remove(i);
                return id;
            }
        }
        return -1;
    }

    public Infor findById(int id) {
        for (Infor infor : list) {
            if (infor.getNo() == id) return infor;
        }
        return null;
    }

    public Infor modify(Infor infor, String author, String content) {
        infor.setWiseSayingAuthor(author);
        infor.setWiseSaying(content);
        return infor;
    }

    public void build() {
        JsonUtils.jsonMerge();
    }
}
