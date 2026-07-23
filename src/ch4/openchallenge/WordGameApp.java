package src.ch4.openchallenge;
// 4장 Open Challenge 문제
import java.util.*;

class Player {
    private String name;
    private String word;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }
}

public class WordGameApp {
    private Player[] players;
    private final Scanner scanner;

    public WordGameApp() {
        scanner = new Scanner(System.in);
    }

    public void createPlayers() {
        System.out.println("끝말잇기 게임을 시작합니다...");
        System.out.print("게임에 참가하는 인원은 몇명입니까?>>");

        int count = scanner.nextInt();
        players = new Player[count];

        for (int i = 0; i < count; i++) {
            System.out.print("참가자의 이름을 입력하세요>>");
            String name = scanner.next();
            players[i] = new Player(name);
        }
    }

    public void getWordFromUser(Player player) {
        System.out.print(player.getName() + ">>");
        player.setWord(scanner.next());

    }

    public boolean checkSuccess(String word, String wordFromPlayer) {
        char lastChar = word.charAt(word.length() - 1);
        char firstChar = wordFromPlayer.charAt(0);

        return lastChar == firstChar;
    }

    public void run() {
        createPlayers();

        System.out.println("시작하는 단어는 '아버지'입니다.");
        String previousWord = "아버지";

        while (true) {
            for (Player player : players) {
                getWordFromUser(player);

                if (!checkSuccess(previousWord, player.getWord())) {
                    System.out.println(player.getName() + "이(가) 졌습니다.");
                    return;
                }

                previousWord = player.getWord();
            }
        }
    }

    public void close() {
        scanner.close();
    }

    public static void main(String[] args) {
        WordGameApp app = new WordGameApp();
        app.run();
        app.close();
    }
}
