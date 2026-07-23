package src.ch6.ex05;

import java.util.*;

class Grade {
    private StringTokenizer st;

    public Grade(StringTokenizer st) {
        this.st = st;
    }

    public double calcAvg() {
        double sum = 0;
        int count = st.countTokens();
        while (st.hasMoreTokens()) {
            String token = st.nextToken().toUpperCase();
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

public class GradeApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("여러 과목의 학점을 빈 칸으로 분리 입력>>");
            String line = scanner.nextLine();
            if (line.equals("그만")) {
                break;
            }

            StringTokenizer st = new StringTokenizer(line, " ");
            Grade grade = new Grade(st);
            double avg = grade.calcAvg();
            if (avg == -1) {
                continue;
            }
            System.out.println("평균은 " + avg);
        }
        scanner.close();
    }
}
