package src.ch4.ex10;
import java.util.*;

class DayDiary {
    private final int day;
    private String content;

    public DayDiary(int day) {
        this.day = day;
        this.content = "";
    }

    public int getDay() {
        return day;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}

public class MonthDiary {
    private static final int DAYS_IN_MONTH = 30;
    static final int MAX_CONTENT_LENGTH = 4;

    private final Scanner scanner;
    private final int year;
    private final int month;
    private final DayDiary[] diaries;

    public MonthDiary(int year, int month) {
        this.scanner = new Scanner(System.in);
        this.year = year;
        this.month = month;

        diaries = new DayDiary[DAYS_IN_MONTH];
        for (int i = 0; i < diaries.length; i++) {
            diaries[i] = new DayDiary(i + 1);
        }
    }

    private void write() {
        while (true) {
            System.out.print("날짜(1~" + DAYS_IN_MONTH + ")와 텍스트(빈칸없이 " + MAX_CONTENT_LENGTH + "글자 이하)>>");
            String line = scanner.nextLine();
            StringTokenizer st = new StringTokenizer(line);

            if (st.countTokens() != 2) {
                System.out.println("날짜와 텍스트를 빈칸 기준으로 작성해야 합니다.");
                continue;
            }

            String dayToken = st.nextToken();
            String content = st.nextToken();

            int day;
            try {
                day = Integer.parseInt(dayToken);
            } catch (NumberFormatException e) {
                System.out.println("날짜는 정수로 입력해야 합니다.");
                continue;
            }

            if (day < 1 || day > DAYS_IN_MONTH) {
                System.out.println("날짜는 1부터 " + DAYS_IN_MONTH + " 사이여야 합니다.");
                continue;
            }

            if (content.length() > MAX_CONTENT_LENGTH) {
                System.out.println("텍스트는 " + MAX_CONTENT_LENGTH + "글자 이하여야 합니다.");
                continue;
            }

            diaries[day - 1].setContent(content);
            break;
        }
    }

    private void show() {
        for (DayDiary diary : diaries) {
            if (diary.getContent().isEmpty()) {
                System.out.print("...   ");
            } else {
                System.out.print(diary.getContent() + "   ");
            }

            if (diary.getDay() % 7 == 0) {
                System.out.println();
            }
        }

        if (diaries.length % 7 != 0) {
            System.out.println();
        }
    }

    private void runMenu() {
        while (true) {
            System.out.print("기록:1, 보기:2, 종료:3>>");
            String input = scanner.nextLine();

            int cmd;
            try {
                cmd = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("정수를 입력해야 합니다.");
                continue;
            }

            switch (cmd) {
                case 1:
                    write();
                    break;
                case 2:
                    show();
                    break;
                case 3:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("1, 2, 3 중에 선택해야 합니다.");
                    break;
            }
        }
    }

    public void run() {
        System.out.println("***** " + year + "년 " + month + "월 다이어리 *****");
        runMenu();
    }

    public void close() {
        scanner.close();
    }

    public static void main(String[] args) {
        MonthDiary monthDiary = new MonthDiary(2026, 7);
        try {
            monthDiary.run();
        } finally {
            monthDiary.close();
        }
    }
}
