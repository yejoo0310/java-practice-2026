package src.ch7.ex01;

import java.util.*;

public class VectorEx {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Vector<Integer> v = new Vector<Integer>();
        System.out.print("정수 입력(-1이면 종료)>>");
        while (true) {
            int input = scanner.nextInt();
            if (input <= 0) {
                break;
            }
            v.add(input);
        }
        int minIndex = 0;
        for (int i = 1; i < v.size(); i++) {
            if (v.get(minIndex) > v.get(i)) {
                minIndex = i;
            }
        }
        System.out.println("제일 작은 수는 " + v.get(minIndex));
        scanner.close();
    }
}
