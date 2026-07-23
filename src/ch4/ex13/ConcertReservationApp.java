package src.ch4.ex13;

import java.util.*;

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
        int cmd = 0;

        while (true) {
            System.out.print("좌석구분 S(1), A(2), B(3)>>");
            String input = scanner.nextLine();
            try {
                cmd = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("정수를 입력해야 합니다.");
                continue;
            }
            if (cmd < 1 || cmd > 4) {
                System.out.println("1부터 3 사이의 정수를 입력해야 합니다.");
                continue;
            }
            break;
        }

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
            System.out.print("좌석 번호>>");
            String input = scanner.nextLine();
            int index = 0;
            try {
                index = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("정수를 입력해야 합니다.");
                continue;
            }
            if (index < 1 || index > 11) {
                System.out.println("좌석은 1부터 10까지 입니다.");
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
        int cmd = 0;

        while (true) {
            System.out.print("좌석구분 S(1), A(2), B(3)>>");
            String input = scanner.nextLine();
            try {
                cmd = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("정수를 입력해야 합니다.");
                continue;
            }
            if (cmd < 1 || cmd > 4) {
                System.out.println("1부터 3 사이의 정수를 입력해야 합니다.");
                continue;
            }
            break;
        }

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

        System.out.print("이름>>");
        String name = scanner.nextLine().trim();
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

    private int getUserCmd(String input) {
        int cmd = 0;

        try {
            cmd = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("정수를 입력해야 합니다.");
            return 0;
        }

        if (cmd < 1 || cmd > 5) {
            System.out.println("1부터 4 사이의 정수를 입력해야 합니다.");
            return 0;
        }

        return cmd;
    }

    public void run() {
        while (true) {
            System.out.print("예약:1, 조회:2, 취소:3, 끝내기:4>>");

            String input = scanner.nextLine();
            int cmd = getUserCmd(input);

            switch (cmd) {
                // cmd == 0 일 때 다시 돌게 하는 로직 이렇게 하는거 괜찮나?
                case 0:
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
