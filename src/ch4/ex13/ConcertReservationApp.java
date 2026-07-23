package src.ch4.ex13;

import java.util.*;

// todo: 0 누르면 해당 선택 취소하는 기능 추가
class InputValidator {
    public static int getIntInRange(String input, int minValue, int maxValue) {
        int value = 0;

        try {
            value = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("정수를 입력해야 합니다.");
            return -1;
        }

        if (value < minValue || value > maxValue) {
            System.out.println(minValue + "부터 " + maxValue + " 사이의 정수를 입력해야 합니다.");
            return -1;
        }

        return value;
    }

    public static boolean isBlank(String input) {
        if (input == null || input.trim().isEmpty()) {
            System.out.println("이름을 입력해야 합니다.");
            return true;
        }
        return false;
    }
}

class Seat {
    private String name;

    public Seat() {
        name = "";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isEmpty() {
        return name.isEmpty();
    }
}

class Concert {
    private Seat[] sSeat;
    private Seat[] aSeat;
    private Seat[] bSeat;

    public Concert() {
        sSeat = new Seat[10];
        aSeat = new Seat[10];
        bSeat = new Seat[10];

        for (int i = 0; i < sSeat.length; i++) {
            sSeat[i] = new Seat();
            aSeat[i] = new Seat();
            bSeat[i] = new Seat();
        }
    }

    private void showSeats(String type, Seat[] seats) {
        System.out.print(type + ">>");
        for (int i = 0; i < seats.length; i++) {
            if (seats[i].isEmpty()) {
                System.out.print(" ---");
                continue;
            }
            System.out.print(" " + seats[i].getName());
        }
        System.out.println();
    }

    public void reserveSeat(Scanner scanner) {
        int cmd = -1;
        do {
            System.out.print("좌석구분 S(1), A(2), B(3)>>");
            String input = scanner.nextLine();
            cmd = InputValidator.getIntInRange(input, 1, 3);
        } while (cmd == -1);

        String type = "";
        Seat[] selectedSeats = null;
        switch (cmd) {
            case 1:
                type = "S";
                selectedSeats = sSeat;
                break;
            case 2:
                type = "A";
                selectedSeats = aSeat;
                break;
            case 3:
                type = "B";
                selectedSeats = bSeat;
                break;
            default:
                break;
        }
        showSeats(type, selectedSeats);

        while (true) {
            System.out.print("이름>>");
            String name = scanner.nextLine();
            if (InputValidator.isBlank(name)) {
                continue;
            }
            System.out.print("좌석 번호>>");
            String input = scanner.nextLine();
            int index = InputValidator.getIntInRange(input, 1, 10);
            if (index == -1) {
                continue;
            }

            if (!selectedSeats[index - 1].isEmpty()) {
                System.out.println("이미 예약되어 있는 좌석입니다. 다른 좌석을 선택해주세요.");
                continue;
            }
            selectedSeats[index - 1].setName(name);
            break;
        }
    }

    public void showAll() {
        showSeats("S", sSeat);
        showSeats("A", aSeat);
        showSeats("B", bSeat);
        System.out.println("<<<조회를 완료하였습니다.>>>");
    }

    public void cancelSeat(Scanner scanner) {
        int cmd = -1;

        do {
            System.out.print("좌석구분 S(1), A(2), B(3)>>");
            String input = scanner.nextLine();
            cmd = InputValidator.getIntInRange(input, 1, 3);
        } while (cmd == -1);

        String type = "";
        Seat[] selectedSeats = null;
        switch (cmd) {
            case 1:
                type = "S";
                selectedSeats = sSeat;
                break;
            case 2:
                type = "A";
                selectedSeats = aSeat;
                break;
            case 3:
                type = "B";
                selectedSeats = bSeat;
                break;
            default:
                break;
        }
        showSeats(type, selectedSeats);

        String name = "";
        while (true) {
            System.out.print("이름>>");
            name = scanner.nextLine().trim();
            if (InputValidator.isBlank(name)) {
                continue;
            }
            break;
        }

        for (Seat seat : selectedSeats) {
            if (seat.isEmpty()) {
                continue;
            }
            if (seat.getName().equals(name)) {
                seat.setName("");
                break;
            }
        }
    }
}

public class ConcertReservationApp {
    static Scanner scanner;
    static Concert concert;

    public ConcertReservationApp() {
        System.out.println("명품콘서트홀 예약 시스템입니다.");
        scanner = new Scanner(System.in);
        concert = new Concert();
    }

    public void run() {
        while (true) {
            System.out.print("예약:1, 조회:2, 취소:3, 끝내기:4>>");

            String input = scanner.nextLine();
            int cmd = InputValidator.getIntInRange(input, 1, 4);

            switch (cmd) {
                case -1:
                    break;
                case 1:
                    concert.reserveSeat(scanner);
                    break;
                case 2:
                    concert.showAll();
                    break;
                case 3:
                    concert.cancelSeat(scanner);
                    break;
                case 4:
                    System.out.println("프로그램이 종료됩니다.");
                    return;
                default:
                    System.out.println("프로그램이 종료됩니다");
                    return;
            }
        }
    }

    public void close() {
        scanner.close();
    }

    public static void main(String[] args) {
        ConcertReservationApp app = new ConcertReservationApp();
        try {
            app.run();
        } finally {
            app.close();
        }
    }
}
