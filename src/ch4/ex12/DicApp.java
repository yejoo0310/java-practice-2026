package src.ch4.ex12;

import java.util.*;

class Dictionary {
    // todo: 나중에 Map으로 바꾸면 좋을듯
    private static final String[] KOR = { "사랑", "아기", "돈", "미래", "희망" };
    private static final String[] ENG = { "love", "baby", "money", "future", "hope" };

    public static String kor2Eng(String word) {
        for (int i = 0; i < KOR.length; i++) {
            if (word.equals(KOR[i])) {
                return ENG[i];
            }
        }
        return null;
    }
}

public class DicApp {
    private final Scanner scanner;

    public DicApp() {
        scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("한영 단어 검색 프로그램입니다.");

        while (true) {
            System.out.print("한글 단어?");
            String input = scanner.nextLine().trim();
            if (input.equals("그만")) {
                break;
            }
            String engWord = Dictionary.kor2Eng(input);
            if (engWord == null) {
                System.out.println(input + "은(는) 저의 사전에 없습니다.");
                continue;
            }
            System.out.println(input + "은(는) " + engWord);
        }
    }

    public void close() {
        scanner.close();
    }

    public static void main(String[] args) {
        DicApp app = new DicApp();
        try {
            app.run();
        } finally {
            app.close();
        }
    }
}
