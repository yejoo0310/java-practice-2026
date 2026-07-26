package src.ch5.openchallenge;
import java.util.Scanner;

abstract public class Player {
    // todo: enum으로 바꿔보기
    protected String bet[] = {"묵", "찌", "빠"};
    protected String name;
    protected String lastBet = null;

    protected Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getBet() {
        return lastBet;
    }

    abstract public String next();
}

class Human extends Player{
    private Scanner scanner;
    public Human(String name, Scanner scanner) {
        super(name);
        this.scanner = scanner;
    }

    @Override
    public String next() {
        while (true) {
            System.out.print(name + ">>");
            String input = scanner.nextLine().trim();

            if (isValidBet(input)) {
                lastBet = input;
                return lastBet;
            }
            System.out.println("묵, 찌, 빠 중에서 입력해야 합니다.");
        }
    }

    private boolean isValidBet(String input) {
        return input.equals(bet[0]) 
                || input.equals(bet[1])
                || input.equals(bet[2]);
    }
}
 
class Computer extends Player{
    public Computer(String name) {
        super(name);
    }

    @Override
    public String next() {
        System.out.println(name + ">> 결정하였습니다.");
        int choice = (int)(Math.random()*bet.length);
        lastBet = bet[choice];
        return lastBet;
    }
}