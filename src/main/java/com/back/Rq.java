package com.back;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Rq {
    private final String actionName;
    private final Map<String, String> paramsMap;

    public Rq(String cmd) {
        String[] cmdBit = cmd.split("\\?", 2);
        actionName = cmdBit[0];
        String queryString = cmdBit.length > 1 ? cmdBit[1].trim() : "";

        // 스트림을 사용하여 쿼리 문자열 파싱
        this.paramsMap = Arrays.stream(queryString.split("&"))
                .map(String::trim) // 각 쿼리 파라미터를 앞뒤 공백 제거
                .filter(s -> !s.isEmpty()) // 빈 문자열 필터링 (예: "&&" 같은 경우)
                .map(queryParam -> queryParam.split("=", 2)) // "=" 기준으로 분리
                .filter(queryParamBits -> queryParamBits.length > 0 && !queryParamBits[0].trim().isEmpty()) // 키가 비어있지 않은 경우만 필터링
                .collect(Collectors.toMap(
                        queryParamBits -> queryParamBits[0].trim(), // 키
                        queryParamBits -> queryParamBits.length > 1 ? queryParamBits[1].trim() : "", // 값 (값이 없으면 빈 문자열)
                        (oldValue, newValue) -> oldValue, // 중복 키 발생 시 기존 값 유지 (또는 원하는 처리 방식 선택)
                        HashMap::new // HashMap으로 수집
                ));
    }

    public String getActionName() {
        return actionName;
    }

    public String getParam(String paramName, String defaultValue) {
        return paramsMap.getOrDefault(paramName, defaultValue);
    }

    public int getParamAsInt(String paramName, int defaultValue) {
        String value = getParam(paramName, "");

        if (value.isEmpty()) {
            return defaultValue;
        }

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
