package src.ch5.openchallenge;

import java.util.Scanner;

class Game {
    private Player[] players;
    private final Scanner scanner;

    public Game() {
        players = new Player[2];
        scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("***** 묵찌빠 게임을 시작합니다. *****");
        createPlayers();

        Player owner = players[0];
        play(owner);
    }

    private void play(Player owner) {
        System.out.println("처음 주도권은 " + owner.getName() + "에게 있습니다.");
        Player opponent = players[1];

        while (true) {
            owner.next();
            opponent.next();
            printBets(owner, opponent);

            if (isSameBet(owner, opponent)) {
                System.out.println(owner.getName() + "이(가) 이겼습니다.");
                System.out.println("게임을 종료합니다.");
                return;
            }

            Player winner = findWinner(owner, opponent);
            if (winner != owner) {
                opponent = owner;
                owner = winner;
                System.out.println("오너가 " + owner.getName() + "로 변경되었습니다.");
            }
            System.out.println();
        }
    }

    private Player findWinner(Player owner, Player opponent) {
        String firstBet = owner.getBet();
        String secondBet = opponent.getBet();

        boolean firstWins = firstBet.equals("묵") && secondBet.equals("찌")
                || firstBet.equals("찌") && secondBet.equals("빠")
                || firstBet.equals("빠") && secondBet.equals("묵");

        if (firstWins) {
            return owner;
        }
        return opponent;
    }

    private boolean isSameBet(Player owner, Player opponent) {
        return owner.getBet().equals(opponent.getBet());
    }

    private void printBets(Player owner, Player opponent) {
        System.out.print(owner.getName() + " : " + owner.getBet() + ", ");
        System.out.print(opponent.getName() + " : " + opponent.getBet() + "\n");
    }

    private void createPlayers() {
        while (true) {
            System.out.print("선수이름 입력>>");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                continue;
            }
            players[0] = new Human(name, scanner);
            break;
        }
        while (true) {
            System.out.print("컴퓨터이름 입력>>");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                continue;
            }
            players[1] = new Computer(name);
            break;
        }
        System.out.println("2명의 선수를 생성 완료하였습니다." + "\n");
    }

    public void close() {
        scanner.close();
    }
}

public class MGPApp {
    public static void main(String[] args) {
        Game game = new Game();
        try {
            game.run();
        } finally {
            game.close();
        }
    }
}
