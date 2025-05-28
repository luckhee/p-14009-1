package com.back;


public class Main {
    public static void main(String[] args) {
        App app = new App();
        app.run();

//        testRq1();
    }

    private static void testRq1() {
        Rq rq = new Rq("목록?searchKeywordType=content&searchKeyword=자바");
        String searchKeywordType = rq.getParam("searchKeywordType", "");
        String searchKeyword = rq.getParam("searchKeyword", "");

        System.out.println("actionName : " + rq.getActionName());
        System.out.println("param searchKeywordType : " + searchKeywordType);
        System.out.println("param searchKeyword : " + searchKeyword);
    }
}
