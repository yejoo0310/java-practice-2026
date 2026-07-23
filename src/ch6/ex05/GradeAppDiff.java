package src.ch6.ex05;

import java.util.Scanner;
import java.util.StringTokenizer;

class Grade {
    private String[] grade;

    public Grade(String[] grade) {
        this.grade = grade;
    }

    public double calcAvg() {
        double sum = 0;
        int count = grade.length;
        for (int i = 0; i < count; i++) {
            String token = grade[i].toUpperCase();
            switch (token) {
                case "A":
                    sum += 100;
                    break;
                case "B":
                    sum += 90;
                    break;
                case "C":
                    sum += 80;
                    break;
                case "D":
                    sum += 70;
                    break;
                case "F":
                    break;
                default:
                    System.out.println("입력 오류:" + token);
                    return -1;
            }
        }
        return sum / count;
    }
}

public class GradeAppDiff {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("여러 과목의 학점을 빈 칸으로 분리 입력>>");
            String line = scanner.nextLine();
            if (line.equals("그만")) {
                break;
            }

            String[] gradeArray = line.split(" ");
            Grade grade = new Grade(gradeArray);
            double avg = grade.calcAvg();
            if (avg == -1) {
                continue;
            }
            System.out.println("평균은 " + avg);
        }
        scanner.close();
    }
}
