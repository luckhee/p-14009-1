package com.back;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static String getJsonValue(String json, String key) {
        String target = "\"" + key + "\"";
        int index = json.indexOf(target);
        if (index == -1) return "";

        int start = json.indexOf(':', index) + 1;
        while (start < json.length() && (json.charAt(start) == ' ' || json.charAt(start) == '\"')) {
            start++;
        }

        int end = start;
        boolean isString = json.charAt(start - 1) == '\"';
        while (end < json.length() && (isString ? json.charAt(end) != '\"' : Character.isDigit(json.charAt(end)))) {
            end++;
        }

        return json.substring(start, end).trim();
    }

    public static void jsonMerge() {
        Path path = Paths.get("db/wiseSaying");
        List<String> jsonList = new ArrayList<>();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path, "*.json")) {
            for (Path jsonFile : stream) {
                String content = Files.readString(jsonFile);
                // 각 줄마다 한 칸 들여쓰기
                String indented = content.replaceAll("(?m)^", " ");
                jsonList.add(indented.trim());
            }
            String mergedJson = "[\n" + String.join(",\n", jsonList) + "\n]";
            Files.writeString(path.resolve("data.json"), mergedJson);
            System.out.println("data.json 파일의 내용이 갱신되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
