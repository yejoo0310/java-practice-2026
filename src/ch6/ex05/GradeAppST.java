package src.ch6.ex05;

import java.util.Scanner;
import java.util.StringTokenizer;

public class GradeAppST {
    private final Scanner scanner;

    public GradeAppST() {
        scanner = new Scanner(System.in);
    }

    private StringTokenizer inputGrade() {
        while (true) {
            System.out.print("여러 과목의 학점을 빈 칸으로 분리 입력>>");
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                System.out.println("학점을 입력해주세요.");
                continue;
            }

            if (line.equals("그만")) {
                return null;
            }

            return new StringTokenizer(line);
        }
    }

    private int getScore(String grade) {
        switch (grade.toUpperCase()) {
            case "A":
                return 100;
            case "B":
                return 90;
            case "C":
                return 80;
            case "D":
                return 70;
            case "F":
                return 0;
            default:
                throw new IllegalArgumentException("입력 오류: " + grade);
        }
    }

    private double calculateAverage(StringTokenizer st) {
        int sum = 0;
        int count = st.countTokens();

        while (st.hasMoreTokens()) {
            String grade = st.nextToken();
            sum += getScore(grade);
        }
        return (double) sum / count;
    }

    public void run() {
        while (true) {
            StringTokenizer st = inputGrade();
            if (st == null) {
                break;
            }

            try {
                double average = calculateAverage(st);
                System.out.println("평균은 " + average);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void close() {
        scanner.close();
    }

    public static void main(String[] args) {
        GradeAppST app = new GradeAppST();

        try {
            app.run();
        } finally {
            app.close();
        }
    }
}
