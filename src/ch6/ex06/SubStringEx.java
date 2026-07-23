package src.ch6.ex06;

import java.util.*;

public class SubStringEx {
    public static void main(String[] args) {
        System.out.println("문자열을 입력하세요. 빈 칸이 있어도 되고 영어 한글 모두 됩니다.");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        for (int i = 0; i < line.length(); i++) {
            char first = line.charAt(0);
            String remain = line.substring(1);
            line = remain + first;
            System.out.println(line);
        }
        scanner.close();
    }
}
