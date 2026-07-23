package src.ch4.ex09;

import java.util.*;

class Player {
    private final String name;
    private int guess;
    private int score;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setGuess(int guess) {
        this.guess = guess;
    }

    public int getGuess() {
        return guess;
    }

    public void addScore() {
        this.score++;
    }

    public int getScore() {
        return score;
    }
}

public class GuessGame {
    private Player[] players;
    private final Scanner scanner;

    public GuessGame() {
        scanner = new Scanner(System.in);
    }

    private void createPlayers() {
        System.out.println("*** 예측 게임을 시작합니다. ***");
        int count;

        while (true) {
            System.out.print("게임에 참여할 선수 수(2명 이상)>>");

            if (!scanner.hasNextInt()) {
                System.out.println("정수를 입력해야 합니다.");
                scanner.next();
                continue;
            }

            count = scanner.nextInt();
            if (count < 2) {
                System.out.println("선수는 2명 이상이어야 합니다.");
                continue;
            }

            break;
        }

        players = new Player[count];

        for (int i = 0; i < players.length; i++) {
            System.out.print("선수 이름>>");
            String name = scanner.next();
            players[i] = new Player(name);
        }
    }

    private void getNumberFromUser(Player p) {
        while (true){
            System.out.print(p.getName() + ">>");
            if (!scanner.hasNextInt()){
                System.out.println("정수를 입력해야 합니다.");
                scanner.next();
                continue;
            }

            int guess = scanner.nextInt();
            if (guess < 0 || guess > 100){
                System.out.println("1부터 100 사이의 숫자를 입력하세요.");
                continue;
            }
            
            p.setGuess(guess);
            break;
        }
    }

    // todo: 동점 규칙 추가해보자
    private Player calcWinner(int answer) {
        Player closetPlayer = players[0];
        int closestDiff = Math.abs(answer - closetPlayer.getGuess());

        for (int i = 1; i < players.length; i++) {
            int diff = Math.abs(answer - players[i].getGuess());

            if (diff < closestDiff) {
                closetPlayer = players[i];
                closestDiff = diff;
            }
        }
        return closetPlayer;
    }

    private void playRound(){
        int answer = (int) (Math.random() * 100 + 1);
        System.out.println("1~100 사이의 숫자가 결정되었습니다. 선수들은 맞추어 보세요.");
    
        for (Player player : players) {
            getNumberFromUser(player);
        }
    
        Player winner = calcWinner(answer);
        winner.addScore();
    
        System.out.println("정답은 " + answer + ". " + winner.getName() + "이(가) 이겼습니다. 승점 1점 확보!");
    }

    private boolean askToContinue(){
        System.out.print("계속하려면 yes 입력>>");
        String line = scanner.nextLine();

        return line.equalsIgnoreCase("yes");
    }
    
    private void showResult() {
        Player finalWinner = players[0];
        for (Player player : players) {
            System.out.print(player.getName() + ":" + player.getScore() + " ");
            if (player.getScore() > finalWinner.getScore()) {
                finalWinner = player;
            }
        }
        System.out.println("\n" + finalWinner.getName() + "이(가) 최종 승리하였습니다.");
    }

    public void run() {
        createPlayers();

        do {
            playRound();
        } while (askToContinue());

        showResult();
    }

    public void close() {
        scanner.close();
    }

    public static void main(String[] args) {
        GuessGame game = new GuessGame();
        game.run();
        game.close();
    }
}
