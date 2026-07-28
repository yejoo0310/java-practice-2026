package src.ch7.ex01;

import java.util.*;

public class VectorEx {
    private final Scanner scanner;
    private final Vector<Integer> v;

    public VectorEx() {
        scanner = new Scanner(System.in);
        v = new Vector<Integer>();
    }

    private void getUserInput() {
        System.out.print("정수 입력(-1이면 종료)>>");
        String line = scanner.nextLine().trim();

        String[] inputs = line.split("\\s+");
        for (String input : inputs) {
            try {
                int number = Integer.parseInt(input);
                if (number == -1) {
                    return;
                }
                if (number < 0) {
                    continue;
                }
                v.add(number);
            } catch (NumberFormatException ignored) {
            }
        }
    }

    public void run() {
        getUserInput();

        int minValue = Collections.min(v);
        System.out.println("제일 작은 수는 " + minValue);
    }

    public void close() {
        scanner.close();
    }

    public static void main(String[] args) {
        VectorEx ex = new VectorEx();

        try {
            ex.run();
        } finally {
            ex.close();
        }
    }
}
