package src.ch7.ex01;

import java.util.*;

public class VectorEx {
    private final Scanner scanner;
    private final Vector<Integer> numbers;

    public VectorEx(Scanner scanner) {
        this.scanner = scanner;
        this.numbers = new Vector<Integer>();
    }

    private void readNumbers() {
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
                    throw new NumberFormatException();
                }
                numbers.add(number);
            } catch (NumberFormatException e) {
                System.out.println(input + "은(는) 정수가 아니므로 무시합니다.");
            }
        }
    }

    private void printMinimum() {
        if (numbers.isEmpty()) {
            System.out.println("저장된 정수가 없습니다.");
            return;
        }

        int minValue = Collections.min(numbers);
        System.out.println("제일 작은 수는 " + minValue);
    }

    public void run() {
        readNumbers();
        printMinimum();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            VectorEx ex = new VectorEx(scanner);
            ex.run();
        } finally {
            scanner.close();
        }
    }
}
