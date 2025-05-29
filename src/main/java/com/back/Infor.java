package com.back;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Infor {
    private int no;
    private String wiseSayingAuthor;
    private String wiseSaying;

    public Infor(int no, String wiseSayingAuthor, String wiseSaying) {
        this.no = no;
        this.wiseSayingAuthor = wiseSayingAuthor;
        this.wiseSaying = wiseSaying;
    }

    public int getNo() {
        return no;
    }

    public String getWiseSayingAuthor() {
        return wiseSayingAuthor;
    }

    public String getWiseSaying() {
        return wiseSaying;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setWiseSayingAuthor(String wiseSayingAuthor) {
        this.wiseSayingAuthor = wiseSayingAuthor;
    }

    public void setWiseSaying(String wiseSaying) {
        this.wiseSaying = wiseSaying;
    }

    public Infor() {

    }

    public boolean save(Path path) {
        Path filePath = path.resolve(no+ ".json");
        String json = toJsonString(0);

        try {
            Files.writeString(filePath, json);
            return true;
        } catch (IOException e) {
            System.out.println("저장 실패" + e.getMessage());
            return false;
        }
    }

    public boolean saveLastId(Path path) {
        Path filePath = path.resolve("lastId");
        String no = saveLastId();

        try {
            Files.writeString(filePath, no);
            return true;
        } catch (IOException e) {
            System.out.println("저장 실패" + e.getMessage());
            return false;
        }

    }

    public String toJsonString(int indentLevel) {
        String indent = " ".repeat(indentLevel);
        String innerIndent = " ".repeat(indentLevel+1);

        return indent + "{\n" +
                innerIndent + "\"no\": " + no + ",\n" +
                innerIndent + "\"wiseSaying\": \"" + wiseSaying + "\",\n" +
                innerIndent + "\"wiseSayingAuthor\": \"" + wiseSayingAuthor + "\"\n" +
                indent + "}";
    }

    public String saveLastId() {
        return String.valueOf(no);
    }

    public Infor readJson(Path path) {
        try {
            String json = Files.readString(path);
            //System.out.println(json);
            int id = Integer.parseInt(JsonUtils.getJsonValue(json, "no"));
            String content = JsonUtils.getJsonValue(json,"wiseSaying");
            String author = JsonUtils.getJsonValue(json,"wiseSayingAuthor");
            return new Infor(id,content,author);
        } catch (IOException e) {
            System.out.println("불러오기 실패 " + e);
            return null;
        }

    }



}
